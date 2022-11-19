# Magento2测试

文档：https://devdocs.magento.com/guides/v2.4/install-gde/composer.html

## 1.安装

### 1.1.搭建LNMP环境

LNMP即Linux+Nginx+Mysql+Php。

- **安装Nginx**

```shell
apt-get install nginx
nginx -v
# 启动
service nginx start
```

![image-20221003121813910](Magento2测试.assets/image-20221003121813910.png)

- **安装mysql**

```shell
apt-get install mysql-server     	# 安装Mysql
netstat -tap | grep mysql			# 检测安装成功与否
systemctl start mysql.service				# 启动
```

![image-20221003131026297](Magento2测试.assets/image-20221003131026297.png)

​	mysql首次启动的时候，会生成一个临时密码，存放在/var/log/mysqld.log，我们先把它找出来，使用它登录mysql，并修改成我们自己想要的且符合策略（至少包含一个大写字母，一个小写字母，一位数字和一个特殊字符，并且密码总长度至少为8个字符）的密码。

```shell
mysql -uroot -p
# 成功登录到mysql后，就可以修改密码了
ALTER  USER  'root'@'localhost'  IDENTIFIED  BY  'NewPassword4!';
```

密码修改完成后，输入exit，退出mysql，安装php。



- **安装PHP**

```shell
apt-get install php				# 安装
php -v							# 查看版本
php -m							# 查看启用的模块
```

![image-20221003131737514](Magento2测试.assets/image-20221003131737514.png)

​	一般来说，我们会创建一个单独的用户和用户组，用于执行web服务。比如说：www www。以下内容仅供参考，不执行也不影响LNMP环境的搭建。

```shell
# 下述如果没有文件或目录则创建
# 添加用户和用户组
useradd www
# groupadd www
#useradd -g www www

# 修改用户和分组
vim /etc/php-fpm.d/www.conf
user = www
group = www

# 修改项目目录所有者
chown -R www.www /var/www/html/magento/

# 不能忘了修改/var/lib/nginx/所有者为www.www。否则前端传递大数据的时候，会因为无权限使用缓冲目录，导致截断。
chown -R /var/lib/nginx/ www.www
```



### 1.2.安装Elasticsearch

M2.4开始，产品的搜索强制使用Elasticsearch，所以需要先安装Elasticsearch。

```shell
wget -qO - https://artifacts.elastic.co/GPG-KEY-elasticsearch | sudo gpg --dearmor -o /usr/share/keyrings/elasticsearch-keyring.gpg				# 下载并安装公共密钥

sudo apt-get install apt-transport-https				  # 安装APT repository

echo "deb [signed-by=/usr/share/keyrings/elasticsearch-keyring.gpg] https://artifacts.elastic.co/packages/8.x/apt stable main" | sudo tee /etc/apt/sources.list.d/elastic-8.x.list					# 将储存库定义保存到/etc/apt/sources.list.d/elastic-8.x.list

sudo apt-get update && sudo apt-get install elasticsearch	# 下载并安装es

sudo systemctl start elasticsearch.service					# 启动es
```

检测：

```shell
apt-get install curl
curl -XGET 'http://localhost:9200'
curl http://localhost:9200/_cluster/health?pretty
```

如为检测到可查看jdk，如果没有，需要安装：

```shell
java -version
apt-get install openjdk-8-jre-headless
# 如果 java 或 javac可以运行，启动es仍然报jdk错误，可以改变jdk的权限
whereis java
chmod 777 /usr/bin/java
systemctl restart elasticsearch.service				# 重启
curl -XGET 'http://localhost:9200/_watcher/stats?pretty'
```



### 1.3.使用Composer安装Magento

Composer是PHP中用于依赖管理的工具，它允许您声明项目所依赖的库，并将为您管理（安装/更新）他们。

Composer不想yum或apt那样的包管理器。是的，它处理”包“或库，但它在每个项目的基础上管理他们，将他们安装到vendor项目内的目录中，默认情况下它不会全局安装任何东西，因此它是一个依赖管理器。但是，为了方便起见，它确实通过"global"命令支持”全局“项目。Composer收到node的npm和ruby的bundler的强烈启发，认为：

1. 您有一个依赖多个库的项目；
2. 其中一些库依赖于其他库。

Composer的作用为：

- 是您能够声明所依赖的库
- 找出可以并且需要安装哪些软件包的哪些版本，并安装他们（这意味着它将他们下载到您的项目中）
- 您可以在一个命令中更新所有的依赖项

Composer需要至少PHP 7.2.5才能运行。

​	在Windows安装程序要求已经安装了PHP-将为您下载Composer并设置您的Path环境变量，以便您可以单纯的从任何目录调用，下载Composer的网站为：https://getcomposer.org/download/

​	在命令行安装Composer，请在终端运行以下脚本：

```shell
sudo curl -sS https://getcomposer.org/installer | php
sudo mv composer.phar /usr/local/bin/composer			# 添加到PATH中
composer -v
```

![image-20221003194002180](Magento2测试.assets/image-20221003194002180.png)



**值得注意的安装选项**

- **--install-dir：**您可以通过使用选项`--install-dir`并提供目标目录将Composer安装到特定目录，如：

```shell
php composer-setup.php --install-dir=bin
```



-  **--filename：**您可以使用该命令指定文件名，如：

```shell
php composer-setup.php --filename=composer
```



- **--version：**您可以通过使用该选项并提供目标版本将 Composer 安装到特定版本，如：

```shell
php composer-setup.php --version=1.0.0-alpha8
```



-  **--preview, --snapshot, --1 (EOL), --2, --2.2 (LTS)：**选择特定版本通道而不是默认的最新稳定版，如：

```shell
php composer-setup.php --2.2
```



-  **--help：**安装程序为特定环境提供了更多选项，使用 `--help`选项查看所有选项。



- **获取Magento Composer存储库的身份验证密钥**

存储库repo.magento.com是Magento 2和第三方Composer包的存储位置，需要身份验证。使用您的Commerce Maketplace账户生成**一对32个字符的身份验证密钥**以访问存储库。



要创建身份验证密钥，您需要以下步骤：

1. 登录到Commerce Maketplace，如果没有账户，需要注册；
2. 单击页面右上角的账户名称，然后选择我的个人资料；
3. 单击市场选项卡中的访问密钥；
4. 单击创建新的访问密钥，输入密钥的特定名称（如：接收密钥的开发人员名称），然后单击OK；
5. 新的公钥和私钥现在与您的账户相关联，您可以单击以进行复制。使用Magento项目是保存此信息或保持页面打开。使用**公钥作为您的用户名**，使用**私钥作为您的密码**。

<img src="Magento.assets/2022-09-27_215458.png" style="zoom:50%;" />

![](Magento2测试.assets/2022-09-27_215812.png)

![](Magento2测试.assets/2022-09-27_220821.png)

![](Magento2测试.assets/2022-09-27_220913.png)

![](Magento2测试.assets/2022-09-27_221353.png)

![](Magento2测试.assets/2022-09-27_221528-1664288289591.png)

- **管理您的密码**

您还可以禁用或删除身份验证密码。例如：您可以在有人离开您的组织后处于安全原因警用或删除密钥。

- 禁用：单击Disable。如果您想暂停使用您的密钥，可以这样做；
- 启用：单击Enable；
- 删除：单击Delete。

- **管理SSH访问令牌**

要使用SSH下载Magento版本，您必须生成下载访问令牌，要生成令牌要完成以下步骤：

1. 登录到您的magento.com账户；
2. 单击页面顶部的我的账户；
3. 单击账户设置-->下载访问令牌；
4. 单击生成新的令牌，以替换和禁用现有令牌。

您必须使用您的MAGIED和您的令牌来下载版本，您的MAGIED显示在您的账户页面的左上角。如：

```shell
curl -k https://MAGEID:TOKEN@www.magentocommerce.com/products/downloads/info/help
```

![](Magento2测试.assets/2022-09-27_223154.png)

- **使用composer获取代码**

  ```shell
  cd /var/www/html/magento 
  chown -R www.www /var/www/html/magento
  su www
  # --ignore-platform-reqs参数为忽略错误，强制安装
  composer create-project --repository-url=https://repo.magento.com/ magento/project-community-edition  --ignore-platform-reqs
  # 如需指定版本
  # composer create-project --repository-url=https://repo.magento.com/ magento/project-community-edition=2.4.0 
  
  # composer show magento/product-community-edition 2.4.\* --all | grep -m 1 versions
  
  # 如果中途中断了，不要再用上面的命令，改用下面的命令继续获取代码
  composer update
  
  # 上面的下载过程很漫长，请耐心等待
  ```

![image-20221003203552943](Magento2测试.assets/image-20221003203552943.png)



- **安装Magento**

Magento2.4开始只能通过命令行安装了。参考以下命令（可根据自己情况调整参数）：

```shell
bin/magento setup:install \
--base-url=http://test.magento.com \
--db-host=localhost \
--db-name=magento \
--db-user=root \
--db-password=root \
--admin-firstname=admin \
--admin-lastname=admin \
--admin-email=admin@admin.com \
--admin-user=admin \
--admin-password=admin123 \
--language=en_US \
--currency=USD \
--timezone=America/Chicago \
--use-rewrites=1
```

安装完毕后，会出现以下内容：

```shell
[SUCCESS]: Magento installation complete.
[SUCCESS]: Magento Admin URI: /admin_vvxyuo
Nothing to import.
```

从中获取后台入口，我这里的入口是admin_vvxyuo。

- **nginx配置**

```shell
su root
cd /etc/nginx/conf.d
vim test.magento.com.conf
```

```shell
upstream fastcgi_backend {
    server 127.0.0.1:9000;
}

server {
    listen 80;
    server_name test.magento.com;

    access_log  /var/log/nginx/magento.access.log main;
    error_log  /var/log/nginx/magento.error.log error;

    set $MAGE_ROOT /var/www/html/magento;
    include /var/www/html/magento/nginx.conf;
}
```

```shell
cp  /var/www/html/magento/nginx.conf.sample   /var/www/html/magento/nginx.conf
nginx -t
# 重启nginx
nginx -s reload
# 启动php-fpm
/usr/sbin/php-fpm
```

访问http://test.magento.com/

![image-20221005195915391](Magento2测试.assets/image-20221005195915391.png)

访问http://test.magento.com/admin_vvxyuo

![image-20221005195944112](Magento2测试.assets/image-20221005195944112.png)

M2.4后台增加了二次验证功能，登录后，要出现以下提示：

![image-20221005200002645](Magento2测试.assets/image-20221005200002645.png)

如果不需要这个功能，可以禁用Magento_TwoFactorAuth模块。

```shell
cd /var/www/html/magento/
bin/magento module:disable Magento_TwoFactorAuth
```

再次尝试登录，成功。

![image-20221005200040383](Magento2测试.assets/image-20221005200040383.png)
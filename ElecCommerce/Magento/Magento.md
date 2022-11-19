# Magento

https://github.com/magento/magento2



## 1.安装

Magento使用Composer来管理组件及依赖，使用Composer获取Magento元包有以下特点：

- 重用第三方库而不是将它们与源代码捆绑在一起
- 通过使用基于组件的架构和强大的依赖管理来减少扩展冲突和兼容性的问题
- 遵守PHP Framework Interoperability Group(FIG)标准
- 将Magento开源与其他组件重新打包
- 在生产环境中使用Magento软件



### 1.1.先决条件

在安装Magento之前，我们需要做以下操作，以确保能够有一个安装Magento环境。在Linux中，root以具有特权的用户身份输入以下命令：

- Ubuntu:

```shell
apt-get update
apt-get upgrade
```

- 中央操作系统：

```shell
yum -y update
yum -y upgrade
```

- 检查系统版本，Magento支持Apache2.4版本：

```shell
httpd -v              # 中央操作系统
apache2 -v			  # Ubuntu

# 若没有下载apache2,可输入以下命令下载
apt install apache2-bin

# 如果apache版本未达2.4，请输入以下命令进行升级（以Ubuntu为例）
apt-get -y update
apt-add-repository ppa:ondrej/apache2
apt-get -y update
```

![image-20220924210301981](Magento.assets/image-20220924210301981.png)

- PHP下载和版本查看：

```shell
apt-get install php				# 下载php，版本要求尽量是7.0及以上
php -v 							# 查看php版本
```

<img src="Magento.assets/image-20220924210524847.png" alt="image-20220924210524847" style="zoom: 67%;" />

- MySQL安装：

```shell
sudo netstat -tap | grep mysql				# 检测是否安装mysql
sudo apt-get install mysql-server mysql-client			# 若没有安装输入以下命令
sudo netstat -tap | grep mysql				# 测试是否安装成功
service mysql start							# 启动mysql
mysql -u <database root user or database owner name> -p   # 设置登录密码和端口，默认3306端口
# 如以下设置
mysql -u root -proot
```

![image-20220924211945119](Magento.assets/image-20220924211945119.png)

![image-20220924212232408](Magento.assets/image-20220924212232408.png)



- 下载Elasticsearch或OpenSearch

```shell
wget -qO - https://artifacts.elastic.co/GPG-KEY-elasticsearch | sudo gpg --dearmor -o /usr/share/keyrings/elasticsearch-keyring.gpg				# 下载并安装公共密钥
sudo apt-get install apt-transport-https				  # 安装APT repository
echo "deb [signed-by=/usr/share/keyrings/elasticsearch-keyring.gpg] https://artifacts.elastic.co/packages/8.x/apt stable main" | sudo tee /etc/apt/sources.list.d/elastic-8.x.list					# 将储存库定义保存到/etc/apt/sources.list.d/elastic-8.x.list

sudo apt-get update && sudo apt-get install elasticsearch	# 下载并安装es

sudo systemctl start elasticsearch.service					# 启动es
```



```shell
curl -XGET '<elasticsearch-hostname>:<elasticsearch-port>'			# 测试
# 如
curl --cacert /etc/elasticsearch/certs/http_ca.crt -u elastic https://localhost:9200
```



### 1.2.安装Composer

​	Composer是PHP中用于依赖管理的工具，它允许您声明项目所依赖的库，并将为您管理（安装/更新）他们。

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
php -r "copy('https://getcomposer.org/installer', 'composer-setup.php');"
php -r "if (hash_file('sha384', 'composer-setup.php') === '55ce33d7678c5a611085589f1f3ddf8b3c52d662cd01d4ba75c0ee0459970c2200a51f492d557530c71c15d8dba01eae') { echo 'Installer verified'; } else { echo 'Installer corrupt'; unlink('composer-setup.php'); } echo PHP_EOL;"
php composer-setup.php         # 运行
php -r "unlink('composer-setup.php');"    		# 删除
```

​	上面的安装脚本将简单的检查一些php.ini设置，如果设置不正确会警告您，然后Composer.phar在当前目录中下载最新的，上面4行将按顺序：

- 下载安装程序到当前目录
- 验证安装程序SHA-384
- 运行安装程序
- 删除安装程序

最有可能的是，您希望将composer.phar放入PATH上的目录中，因此您可以简单的composer从任何目录（全局安装）调用，例如：

```shell
sudo mv composer.phar /usr/local/bin/composer
```

![image-20220927215245912](Magento.assets/image-20220927215245912.png)

- **值得注意的安装选项**

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

### 1.3.获取Magento Composer存储库的身份验证密钥

​	存储库repo.magento.com是Magento 2和第三方Composer包的存储位置，需要身份验证。使用您的Commerce Maketplace账户生成**一对32个字符的身份验证密钥**以访问存储库。



要创建身份验证密钥，您需要以下步骤：

1. 登录到Commerce Maketplace，如果没有账户，需要注册；
2. 单击页面右上角的账户名称，然后选择我的个人资料；
3. 单击市场选项卡中的访问密钥；
4. 单击创建新的访问密钥，输入密钥的特定名称（如：接收密钥的开发人员名称），然后单击OK；
5. 新的公钥和私钥现在与您的账户相关联，您可以单击以进行复制。使用Magento项目是保存此信息或保持页面打开。使用**公钥作为您的用户名**，使用**私钥作为您的密码**。

<img src="Magento.assets/2022-09-27_215458.png" style="zoom:50%;" />

![](Magento.assets/2022-09-27_215812.png)

![](Magento.assets/2022-09-27_220821.png)

![](Magento.assets/2022-09-27_220913.png)

![](Magento.assets/2022-09-27_221353.png)

![](Magento.assets/2022-09-27_221528-1664288289591.png)

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

![](Magento.assets/2022-09-27_223154.png)



### 1.4.以文件系统所有者身份登录

- 以具有写入Magento文件系统权限的用户身份登录或切换到Magento服务器，如果试用bash shell，可以试用以下语法切换到文件系统所有者：

```shell
su <file system owner> -s /bin/bash -c <command>
```

​	如果文件系统所有者不允许登录，请执行以下操作：

```shell
sudo -u <file system owner>  <command>
```

- 如果文件系统所有者不允许登录，可以执行以下操作：要从任何目录运行Magento命令，请将<Magento_root/>bin添加到系统PATH:

```shell
export PATH=$PATH:/var/www/html/magento2/bin
```



### 1.5.获取metapackage

获取metapackage的步骤为：

1. 以文件系统所有者的身份登录或切换到Magento服务器；
2. 更改为web服务器docroot目录或已配置为虚拟机的docroot的目录；
3. 试用Magento Open Source或Adobe Commerce metapackage创建新的Composer项目。

- **Magento Open Source:**

```shell
composer create-project --repository-url=https://repo.magento.com/ magento/project-community-edition <install-directory-name>
```

- **Adobe Commerce:**

```shell
composer create-project --repository-url=https://repo.magento.com/ magento/project-enterprise-edition <install-directory-name>
```

可能出现以下问题：

<img src="Magento.assets/image-20221001203353532.png" alt="image-20221001203353532" style="zoom:50%;" />



### 1.6.设置文件权限

​	在安装Magento软件之前，必须为web服务器组设置读写权限（必须），以便命令行可以将文件写入Magento文件系统：

```shell
cd /var/www/html/<magento install directory>         # 我创建了文件magento2ee 
# cd /var/www/html/magento2ee

find var generated vendor pub/static pub/media app/etc -type f -exec chmod g+w {} +
find var generated vendor pub/static pub/media app/etc -type d -exec chmod g+ws {} +
chown -R :www-data . # Ubuntu
chmod u+x bin/magento
```



### 1.7.安装Magento

你必须试用命令行安装Magento。此示例假定Magento安装目录名为magento2ee，数据库主机位于同一台计算机（localhost）上，并且数据库名称、数据库用户和数据库密码都是Magento:

```shell
bin/magento setup:install \
--base-url=http://localhost/magento2ee \
--db-host=localhost \
--db-name=magento \
--db-user=magento \
--db-password=magento \
--admin-firstname=admin \
--admin-lastname=admin \
--admin-email=admin@admin.com \
--admin-user=admin \
--admin-password=admin123 \
--language=en_US \
--currency=USD \
--timezone=America/Chicago \
--use-rewrites=1 \
--search-engine=elasticsearch7 \
--elasticsearch-host=es-host.example.com \
--elasticsearch-port=9200 \
--elasticsearch-index-prefix=magento2 \
--elasticsearch-timeout=15
```

安装时出现了以下问题：

![image-20221001210530022](Magento.assets/image-20221001210530022.png)

### 1.8.命令摘要

要显示完整的命令列表，请输入：

```shell
bin/magento list
```

获取帮助信息，请输入：

```shell
bin/magento help <command>
```

举例：

```shell
bin/magento help setup:install
bin/magento help cache:enable
```

下表总结了可用的命令，命令仅以摘要形式显示：

| 命令<command>                        | 秒数                                                         | 先决条件      |
| ------------------------------------ | ------------------------------------------------------------ | ------------- |
| magento setup:install                | 安装Magento软件                                              | None          |
| magento setup:uninstall              | 移除Magento软件                                              | 已下载Magento |
| magento setup:upgrade                | 更新Magento                                                  | 部署配置      |
| magento maintenance:{enable/disable} | 启用或禁用维护模式（在维护模式下，只有豁免IP地址才能访问admin或storefront） | 已下载Magento |
| magento setup:config:set             | 创建或更新部署配置                                           | None          |
| magento module:{enable/disable}      | 启用或禁用模块                                               | None          |
| magento setup:store-config:set       | 设置与店面相关的选项，如URL、语言、时区等                    | 部署配置      |
| magento setup:db-schema:upgrade      | 更新Magento数据库模式                                        | 部署配置      |
| magento setup:db-data:upgrade        | 更新Magento数据库数据                                        | 部署配置      |
| magento setup:db:status              | 检查数据库是否是代码的最新版本                               | 部署配置      |
| magento admin:user:create            | 创建Magento管理员                                            |               |
| magento list                         | 列出所有可用命令                                             | None          |
| magento help                         | 帮助命令                                                     | None          |

下列命令参数是所有命令的通用参数，这些命令可以在安装Magento软件之前或之后运行：



| Long Version     | Short Version | 含义                                                         |
| ---------------- | ------------- | ------------------------------------------------------------ |
| --help           | -h            | 获取任何命令的帮助。例如，./magento help setup:install或./magent to help setup：config:set。 |
| --quiet          | -q            | 安静模式；无输出                                             |
| --no-interaction | -n            | 没有交互式问题                                               |
| --verbose=1,2,3  | -v, -vv, -vvv | 详细程度。例如，--verbose=3或-vvv显示调试详细性，这是最详细的输出。默认值为--verbose=1或-v |
| --version        | -v            | 显示此应用程序版本                                           |
| --ansi           | n/a           | 强制ANSI输出                                                 |
| --no-ansi        | n/a           | 禁用ANSI输出                                                 |


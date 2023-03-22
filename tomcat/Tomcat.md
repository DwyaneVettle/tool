

# Tomcat

## 1·Tomcat是什么？

​		Tomcat是Apache [软件基金会]（Apache Software Foundation）的Jakarta 项目中的一个核心项目，由[Apache](https://baike.baidu.com/item/Apache/6265)、Sun 和其他一些公司及个人共同开发而成。由于有了Sun 的参与和支持，最新的Servlet 和JSP 规范总是能在Tomcat 中得到体现，Tomcat 5支持最新的Servlet 2.4 和JSP 2.0 规范。因为Tomcat 技术先进、性能稳定，而且免费，因而深受Java 爱好者的喜爱并得到了部分软件开发商的认可，成为目前比较流行的Web 应用**服务器**。

## 2·什么是服务器？

服务器：分为服务器硬件 和 服务器软件。在硬件服务器（计算机）上安装了服务器软件，才可以对外提供服务。

比如：让其他的计算机来访问当前服务器，为其他的计算机提供服务。

(1) 服务器硬件：是指在互联网上具有独立IP地址的计算机，比如我们自己用的计算机也可以作为服务器使用。

(2) 服务器软件：就是一个计算机程序，比如MySQL服务器软件，tomcat服务器软件。服务器软件分为很多类型，比如：ftp服务器，数据库服务器，web服务器软件，邮件服务器等。

## 3·什么是Web服务器?

(1) web服务器是指驻留在互联网上的某种类型的计算机程序。当浏览器访问服务器，请求服务器上的文件时，服务器将会处理该请求，并将请求的文件响应给浏览器，并会附带一些信息告诉浏览器如何查看该文件（即文件的类型）

(2) web服务器是可以向 "发出请求的浏览器提供文档" 的程序，比如在访问百度时，其实就是在访问百度的服务器。

![](https://gitee.com/zou_tangrui/note-pic/raw/master/img/202302171726380.png)

网络通信三要素
	1. IP：电子设备(计算机)在网络中的唯一标识。
	2. 端口：应用程序在计算机中的唯一标识。 0~65536
	3. 传输协议：规定了数据传输的规则
		1. 基础协议：
			1. tcp:安全协议，三次握手。 速度稍慢
			2. udp：不安全协议。 速度快

## 4.B/S和C/S架构

### 4.1 C/S架构

即 ***\*Client / Server\****

C/S工作流程图：

![](https://gitee.com/zou_tangrui/note-pic/raw/master/img/202302171726381.png)

在C/S结构的情况下，不同的服务需要安装不同的客户端软件，
比如QQ、迅雷、Foxmail这种情况下安装的软件会越来越多，同时也有许多弊端，
比如A出差，需要在B电脑上查收邮件，但是B电脑并未安装Foxmail等类似的客户端软件，
这样不得不先去下载Foxmail，非常不方便。

app的三种：
-  web app（网页应用） (B/S)
-  hybrid app（混合应用 -- 应用的壳子套着页面） (C/S)
-  native app（原生应用） (C/S)

### 4.2 B/S架构

B/S（即 ***\*Broswer / Server\****）解决了C/S所带来的不便， 将所有的服务都可以通过浏览器来完成（因为基本所有浏览器都安装了浏览器），

但B/S也有一些不利，比如操作稳定性、流畅度等方面相对较弱。

### 4.3 C/S 与 B/S 区别： 

Client/Server是建立在局域网的基础上的.Browser/Server是建立在广域网的基础上的. 
1．硬件环境不同
C/S 一般建立在专用的网络上, 小范围里的网络环境, 局域网之间再通过专门服务器提供连接和数据交换服务. 
B/S 建立在广域网之上的, 不必是专门的网络硬件环境,例如电话上网, 租用设备. 信息管理. 有比C/S更强的适应范围, 一般只要有操作系统和浏览器就行

2．对安全要求不同 
C/S 一般面向相对固定的用户群, 对信息安全的控制能力很强. 一般高度机密的信息系统采用C/S 结构适宜. 可以通过B/S发布部分可公开信息. 
B/S 建立在广域网之上, 对安全的控制能力相对弱, 面向是不可知的用户群.

3．对程序架构不同 
C/S 程序可以更加注重流程, 可以对权限多层次校验, 对系统运行速度可以较少考虑. 
B/S 对安全以及访问速度的多重的考虑, 建立在需要更加优化的基础之上. 比C/S有更高的要求 B/S结构的程序架构是发展的趋势, 从MS的.Net系列的BizTalk 2000 Exchange 2000等, 全面支持网络的构件搭建的系统. SUN 和IBM推的JavaBean 构件技术等,使 B/S更加成熟.

4．软件重用不同 
C/S 程序可以不可避免的整体性考虑, 构件的重用性不如在B/S要求下的构件的重用性好. 
B/S 对的多重结构,要求构件相对独立的功能. 能够相对较好的重用.

5．系统维护不同 
系统维护在是软件生存周期中,开销大, -------重要 
C/S 程序由于整体性, 必须整体考察, 处理出现的问题以及系统升级. 升级难. 可能是再做一个全新的系统 
B/S 构件组成,方面构件个别的更换,实现系统的无缝升级. 系统维护开销减到最小.用户从网上自己下载安装就可以实现升级.

6．处理问题不同 
C/S 程序可以处理用户面固定, 并且在相同区域, 安全要求高需求, 与操作系统相关. 应该都是相同的系统 
B/S 建立在广域网上, 面向不同的用户群, 分散地域, 这是C/S无法作到的. 与操作系统平台关系最小.

7．用户接口不同 
C/S 多是建立的Window平台上,表现方法有限,对程序员普遍要求较高 
B/S 建立在浏览器上, 有更加丰富和生动的表现方式与用户交流. 并且大部分难度减低,减低开发成本.

8．信息流不同 
C/S 程序一般是典型的中央集权的机械式处理, 交互性相对低 
B/S 信息流向可变化, B-B B-C B-G等信息、流向的变化, 更象交易中心

## 5·tomcat下载、安装、启动、配置

#### 1.下载tomcat服务器：

下载地址：http://tomcat.apache.org/

tomcat有很多版本，有解压版 和 安装版，还分windows （还分为32位和64位版）和
linux版，根据自己的需求，选择对应的版本下载。

tomcat服务器运行需要jdk的支持，版本对应为：

```
tomcat5 需要jdk4以上支持
tomcat6 需要jdk5以上支持
tomcat7 需要jdk6以上支持
tomcat8 需要jdk7以上支持
tomcat9,10 需要jdk8及以上支持
```

#### 2.下载索引：

![](https://gitee.com/zou_tangrui/note-pic/raw/master/img/202302171726382.png)

![](https://gitee.com/zou_tangrui/note-pic/raw/master/img/202302171726383.png)

### 3.安装：

直接解压安装。

解压后还需要配置JAVA_HOME环境变量，该变量指向jdk的根目录，指定tomcat启动时使用哪一个位置的jdk。

#### 4.**启动、关闭tomcat服务器：**

**通过 [tomcat根目录]\bin\startup.bat 可以启动tomcat服务器；**

**通过 [tomcat根目录]\bin\shutdown.bat 可以关闭tomcat服务器；**

**注意**：启动可能会有乱码的情况

![](https://gitee.com/zou_tangrui/note-pic/raw/master/img/202302171726384.png)

解决办法：打开tomcat所在目录下的conf目录修改logging.properties，将47行的UTF-8修改成GBK

![](https://gitee.com/zou_tangrui/note-pic/raw/master/img/202302171726385.png)



#### 5.**访问测试服务器：**

在tomcat服务器启动后，服务器会默认监听8080端口，可以通过如下地址访问tomcat服务器的主页：

```
127.0.0.1:8080 或者 localhost:8080
```

出现以下页面表示启动成功

![](https://gitee.com/zou_tangrui/note-pic/raw/master/img/202302171726386.png)

#### 6.关于tomcat修改默认端口和端口号被占用问题：

参考：https://blog.csdn.net/qianzhitu/article/details/102876079

#### 7.tomcat目录结构

tomcat目录结构介绍

![](https://gitee.com/zou_tangrui/note-pic/raw/master/img/202302171726387.png)

tomcat服务器安装根目录下有很多子目录，这些目录的作用是：

```
bin：用于存放tomcat服务器中批处理文件的目录(xx.bat/xx.sh)
conf：用于存放tomcat服务器中的配置文件的目录(其中server.xml文件是tomcat服务器中非常重要的一个文件。)
lib：用于存放tomcat服务器运行时所依赖的jar包。
logs：用于存放tomcat服务器运行时产生的日志文件(启动tomcat服务器时会打印很多日志信息,这些日志信息还会以文件形式保存到logs目录下)
temp：用于存放tomcat服务器产生的临时文件，tomcat会自己清理，可以忽略该目录

webapps：是localhost【虚拟主机】默认管理的目录，将开发好的【web应用】程序放在webapps目录下，就可以通过浏览器访问localhost主机中的Web资源文件了。
可以简单的理解为：webapps目录就是web资源（html、css、js、图片、jsp等）的存放目录，将web资源文件放在该目录下，就可以通过浏览器来访问。

work：用于存放tomcat服务器产生的工作文件（JSP翻译后的Servlet文件会放在work目录下；session对象序列化后产生的文件也会放在work目录下；）
```

#### 8.tomcat整合到IDEA

**第一步：点击run,选择Edit Configurations…（中文版的是点击运行，选择编辑结构）**

![](https://gitee.com/zou_tangrui/note-pic/raw/master/img/202302171726388.png)

**第二步：找到Default,之后打开Default，找到Tomcat Server,选择Local（如果本地电脑做为服务器就选择Local）***

![](https://gitee.com/zou_tangrui/note-pic/raw/master/img/202302171726389.png)

**第三步：点击Configure…选择Tomcat的安装目录完成部署**

![](https://gitee.com/zou_tangrui/note-pic/raw/master/img/202302171726390.png)

![](https://gitee.com/zou_tangrui/note-pic/raw/master/img/202302171726391.png)

# Git--分布式版本控制

## 1.什么是Git？

​		Git（读音为/gɪt/）是一个开源的分布式版本控制系统，可以有效、高速地处理从很小到非常大的项目版本管理。 [1] 也是[Linus Torvalds](https://baike.baidu.com/item/Linus Torvalds/9336769)为了帮助管理Linux内核开发而开发的一个开放源码的版本控制软件。

### git对开发者的作用

- 可以把单机上的代码和版本信息推送到第三方管理平台或者其他服务器上；
- 开发者可以在第三方平台或者服务器上拉取代码和版本信息；
- 多个开发者可以协作开发，避免代码冲突等不必要的因素；
- 可以在本地根据自己的不同开发目的创建分支，修改代码。

![](https://gitee.com/zou_tangrui/note-pic/raw/master/img/202302171726392.jpg)

## 2.Git的下载：

官网下载：https://git-scm.com/

![](https://gitee.com/zou_tangrui/note-pic/raw/master/img/202302171726393.png)

### Git安装：

自定义安装目录后，一直下一步。

![](https://gitee.com/zou_tangrui/note-pic/raw/master/img/202302171726394.png)

安装结束后，当鼠标右键出现如下图所示即表示安装成功：

![](https://gitee.com/zou_tangrui/note-pic/raw/master/img/202302171726395.png)

## 3.Git的基本工作流程

**1.git的工作区域**：

![](https://gitee.com/zou_tangrui/note-pic/raw/master/img/202302171726396.png)

**2.向仓库中添加文件的流程：**

![](https://gitee.com/zou_tangrui/note-pic/raw/master/img/202302171726397.png)

## 4.idea配置Git

##### 1.配置的位置：idea-->File-->Settings-->Version Control-->Git

##### 2.将安装的git配置到idea，选择git安装的根目录下的cmd中的git.exe

##### 3.然后点击Test检测是否配置成功

![](https://gitee.com/zou_tangrui/note-pic/raw/master/img/202302171726398.png)

## 5.idea安装Gitee插件

如果你的项目需要托管的第三方管理平台，则需要安装gitee插件

安装步骤：在idea-->File-->Settings-->Plugins中搜索Gitee，然后install

![](https://gitee.com/zou_tangrui/note-pic/raw/master/img/202302171726400.png)

**注意：一般情况下：安装插件需要重启idea后，插件才能起作用。**

## 6.Git初始化和仓库创建操作

**1.git安装好后需要进行一些基本信息的设置：**

鼠标右键Git Bash Here

- 设置用户名：

  ```
  git  config -- global  user.name  '你在gitee或github上注册的用户名';
  ```

- 设置用户邮箱：

  ```
  git  config -- global  user.email  '注册时候的邮箱';	
  ```

- 配置成功后可以输入以下命令检测是否配置成功，当出现你配置的用户名及邮箱就表明已经配置成功：

  ```
  git config --list
  ```

**2.初始化一个新的Git仓库：**

2.1可以在任一磁盘中鼠标右键创建文件夹，也可以通过git命令的形式来创建

![](https://gitee.com/zou_tangrui/note-pic/raw/master/img/202302171726401.png)

2.2在文件夹内初始化git(创建git仓库)

方法一：直接输入以下命令，再输入git init

```
cd D://test
```

方法二：在刚刚创建的仓库文件夹下鼠标右键Git Bash Here,然后输入git init

```
git init
```

当仓库中出现**.git**文件夹即表示初始化成功。

**3.向仓库中添加文件：**

方法一：直接粘贴复制进去,可以是Java文件，也可以是html;

方法二：通过git命令touch '文件名'到在仓库中，然后通过命令 git add '文件名'将文件暂存做最后的提交操作

可以通过命令git status查看状态

![](https://gitee.com/zou_tangrui/note-pic/raw/master/img/202302171726402.png)

**4.提交文件到仓库**

通过以下命令进行提交：

```
git commit -m 'index.html'
```

**5.修改仓库中的文件**

方法一：通过编辑器打开文件进行修改；

方法二;使用以下命令进行修改，然后再提交；

```
vi '需要修改的文件'     ----修改
git commit -m 'index.html'   ----再提交
```

**6.删除仓库的文件**

方法一：直接删除

方法二：使用如下命令进行删除：

```
git rm '文件名'
```

## 7.使用Git远程管理仓库

 **使用远程仓库的目的**：**备份、实现代码共享集中化管理**

**Git远程仓库实际上就是保持在服务器上的git仓库文件**

![](https://gitee.com/zou_tangrui/note-pic/raw/master/img/202302171726403.png)

![](https://gitee.com/zou_tangrui/note-pic/raw/master/img/202302171726404.png)

![](https://gitee.com/zou_tangrui/note-pic/raw/master/img/202302171726405.png)

# 8.克隆操作

目的：将仓库中的代码克隆到本地

命令：

```
git clone 仓库地址
```

![](https://gitee.com/zou_tangrui/note-pic/raw/master/img/202302171726406.png)

![](https://gitee.com/zou_tangrui/note-pic/raw/master/img/202302171726407.png)

克隆成功后本地仓库就增加了在远程仓库中获取过来的代码文件

![](https://gitee.com/zou_tangrui/note-pic/raw/master/img/202302171726408.png)

**你也可以在idea中clone代码** 

在idea功能栏git/VCS中点击clone,然后将复制的仓库地址粘贴到URL中

![](https://gitee.com/zou_tangrui/note-pic/raw/master/img/202302171726409.png)

![](https://gitee.com/zou_tangrui/note-pic/raw/master/img/202302171726410.png)

**你也可以创建远程仓库将本地代码推送到远程仓库**

![](https://gitee.com/zou_tangrui/note-pic/raw/master/img/202302171726411.png)

**1 提交本地代码到仓库：选中要推送的项目鼠标右键git---commit directory---输入提交信息---commit**

![](https://gitee.com/zou_tangrui/note-pic/raw/master/img/202302171726412.png)

![](https://gitee.com/zou_tangrui/note-pic/raw/master/img/202302171726413.png)

2 **提交后需要进行push操作才能推送到远程仓库**

![](https://gitee.com/zou_tangrui/note-pic/raw/master/img/202302171726414.png)

3 **勾选中要推送的项目再点击push**

![](https://gitee.com/zou_tangrui/note-pic/raw/master/img/202302171726415.png)



4  **push后可能需要输入码云或github等远程仓库的注册名和密码**

![](https://gitee.com/zou_tangrui/note-pic/raw/master/img/202302171726416.png)

5 **当idea右下角出现push successful即表示已经将代码推送到远程仓库。**

## 扩展一：idea右键项目没有git的解决方法

file-settings--version control

![](https://gitee.com/zou_tangrui/note-pic/raw/master/img/202302171726417.png)

## 扩展二：切换分支

在实际的工作中，我们可能将开发代码分为master,dev,prod等分支，以便在不同环境下进行不同的操作，那么我们将代码拉下来该怎么切换分支呢？

在idea中pull代码后，在右下角的master处进行切换：点击master然后check out：

![](https://gitee.com/zou_tangrui/note-pic/raw/master/img/202302171726418.png)

## 扩展三：多人协同开发

以码云为例，多人协同开发需要进入到仓库中点击管理，选择仓库成员管理，添加开发者，将开发连接分享给其他开发者即可

![](https://gitee.com/zou_tangrui/note-pic/raw/master/img/202302171726419.png)

初次进入仓库需要配置公钥才能进行代码的拉取，配置公钥也是在管理中配置

配置步骤按下图所示：

![](https://gitee.com/zou_tangrui/note-pic/raw/master/img/202302171726420.png)

## 扩展四：SVN安装使用

参考：http://www.cnblogs.com/armyfai/p/3985660.html







# Maven

## 1.什么是Maven?

如今我们构建一个项目需要用到很多第三方的类库，如写一个使用Spring的Web项目就需要引入大量的jar包。一个项目Jar包的数量之多往往让我们瞠目结舌，并且Jar包之间的关系错综复杂，一个Jar包往往又会引用其他Jar包，缺少任何一个Jar包都会导致项目编译失败。

以往开发项目时，程序员往往需要花较多的精力在引用Jar包搭建项目环境上，而这一项工作尤为艰难，少一个Jar包、多一个Jar包往往会报一些让人摸不着头脑的异常。

而Maven就是一款帮助程序员构建项目的工具，我们只需要告诉Maven需要哪些Jar 包，它会帮助我们下载所有的Jar，极大提升开发效率。



**下载，安装和整合：**

## 官网下载：https://maven.apache.org/download.cgi

<img src="https://gitee.com/zou_tangrui/note-pic/raw/master/img/202302171726421.png" style="zoom: 50%;" />

<img src="https://gitee.com/zou_tangrui/note-pic/raw/master/img/202302171726422.png" style="zoom: 50%;" />

## 2.Maven规定得目录结构

若要使用Maven，那么项目的目录结构必须符合Maven的规范，其目录结构如下：

![](https://gitee.com/zou_tangrui/note-pic/raw/master/img/202302171726423.jpg)



## 3.Maven的基本命令

​	1.-v:查询Maven版本

​		本命令用于检查maven是否安装成功。

​		Maven安装完成之后，在命令行输入mvn -v，若出现maven信息，则说明安装成功。

​	2.compile：编译

​		将java源文件编译成class文件

​	3.test:测试项目

​		执行test目录下的测试用例

​	4.package:打包

​		将项目打成jar包

​	5.clean:删除target文件夹

​	6.install:安装

将当前项目放到Maven的本地仓库中。供其他项目使用



## 4.什么是Maven仓库

Maven仓库用来存放Maven管理的所有Jar包。分为：本地仓库 和 中央仓库。

- **本地仓库**：Maven本地的Jar包仓库。
- **中央仓库**： Maven官方提供的远程仓库。

当项目编译时，Maven首先从本地仓库中寻找项目所需的Jar包，若本地仓库没有，再到Maven的中央仓库下载所需Jar包。



## 5.什么是坐标

在Maven中，坐标是Jar包的唯一标识，Maven通过坐标在仓库中找到项目所需的Jar包。

如下代码中，groupId和artifactId构成了一个Jar包的坐标。

```

<dependency>
   <groupId>cn.missbe.web.search</groupId>
   <artifactId>resource-search</artifactId>
   <packaging>jar</packaging>
   <version>1.0-SNAPSHOT</version>
</dependency>
```

- **groupId**:所需Jar包的项目名
- **artifactId**:所需Jar包的模块名
- **version**:所需Jar包的版本号



## 6.传递依赖和排除依赖

- 传递依赖：如果我们的项目引用了一个Jar包，而该Jar包又引用了其他Jar包，那么在默认情况下项目编译时，Maven会把直接引用和简洁引用的Jar包都下载到本地。
- 排除依赖：如果我们只想下载直接引用的Jar包，那么需要在pom.xml中做如下配置：(将需要排除的Jar包的坐标写在中)

```
<exclusions>
   <exclusion>
      <groupId>cn.missbe.web.search</groupId>
      <artifactId>resource-search</artifactId>
      <packaging>pom</packaging>
      <version>1.0-SNAPSHOT</version>
   </exclusion>
</exclusions>
```

**依赖范围scope：**

在项目发布过程中，帮助决定哪些构件被包括进来。欲知详情请参考依赖机制。

- compile ：默认范围，用于编译      
- provided：类似于编译，但支持你期待jdk或者容器提供，类似于classpath      
- runtime: 在执行时需要使用      
- test:    用于test任务时使用      
- system: 需要外在提供相应的元素。通过systemPath来取得      
- systemPath: 仅用于范围为system。提供相应的路径      
- optional:   当项目自身被依赖时，标注依赖是否传递。用于连续依赖时使用

## 7.依赖冲突

若项目中多个Jar同时引用了相同的Jar时，会产生依赖冲突，但Maven采用了两种避免冲突的策略，因此在Maven中是不存在依赖冲突的。

### 7.1 短路优先

```
本项目——>A.jar——>B.jar——>X.jar
本项目——>C.jar——>X.jar
```

若本项目引用了A.jar，A.jar又引用了B.jar，B.jar又引用了X.jar，并且C.jar也引用了X.jar。

在此时，Maven只会引用引用路径最短的Jar。

### 7.2 声明优先

若引用路径长度相同时，在pom.xml中谁先被声明，就使用谁。



## 8.聚合

1. 什么是聚合？

   将多个项目同时运行就称为聚合。

2. 如何实现聚合？

   只需在pom中作如下配置即可实现聚合：

   

```
<modules>
    <module>web-connection-pool</module>
    <module>web-java-crawler</module>
</modules>
```



## 9.继承

1. 什么是继承？

   在聚合多个项目时，如果这些被聚合的项目中需要引入相同的Jar，那么可以将这些Jar写入父pom中，各个子项目继承该pom即可。

2. 如何实现继承？

- 父pom配置：将需要继承的Jar包的坐标放入标签即可。

```
<dependencyManagement>
    <dependencies>
          <dependency>
            <groupId>cn.missbe.web.search</groupId>
            <artifactId>resource-search</artifactId>
            <packaging>pom</packaging>
            <version>1.0-SNAPSHOT</version>
          </dependency> 
    </dependencies>
</dependencyManagement>
```



- 子pom配置：

```
<parent>
      <groupId>父pom所在项目的groupId</groupId>
      <artifactId>父pom所在项目的artifactId</artifactId>
      <version>父pom所在项目的版本号</version>
</parent>
 <parent>
      <artifactId>resource-search</artifactId>
      <groupId>cn.missbe.web.search</groupId>
      <version>1.0-SNAPSHOT</version>
</parent>
```


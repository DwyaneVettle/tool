# Jmeter的使用

## 1.Jmeter简介

以下内容来自Jmeter中文网http://www.jmeter.com.cn/jieshao，很好的解释了Jmeter的作用：

Apache JMeter是Apache组织开发的基于Java的压力测试工具。用于对软件做压力测试，它最初被设计用于Web应用测试，但后来扩展到其他测试领域。 它可以用于测试静态和动态资源，例如静态文件、Java [小服务程序](https://baike.baidu.com/item/小服务程序)、CGI 脚本、Java 对象、数据库、FTP 服务器， 等等。JMeter 可以用于对服务器、网络或对象模拟巨大的负载，来自不同压力类别下测试它们的强度和分析整体性能。另外，JMeter能够对应用程序做功能/[回归测试](https://baike.baidu.com/item/回归测试)，通过创建带有断言的脚本来验证你的程序返回了你期望的结果。为了最大限度的灵活性，JMeter允许[使用正则表达式](https://baike.baidu.com/item/使用正则表达式)创建断言。

Apache jmeter 可以用于对静态的和动态的资源（文件，Servlet，Perl脚本，java 对象，数据库和查询，[FTP服务器](https://baike.baidu.com/item/FTP服务器)等等）的性能进行测试。它可以用于对服务器、网络或对象模拟繁重的负载来测试它们的强度或分析不同压力类型下的整体性能。你可以使用它做性能的图形分析或在大并发[负载测试](https://baike.baidu.com/item/负载测试)你的服务器/脚本/对象。

**JMeter的作用**

1.能够对HTTP和FTP服务器进行压力和[性能测试](https://baike.baidu.com/item/性能测试)， 也可以对任何数据库进行同样的测试（通过JDBC）。

2.完全的可移植性和100% 纯java。

3.完全 Swing 和轻量组件支持（[预编译](https://baike.baidu.com/item/预编译)的JAR使用 javax.swing.*)包。

4.完全多线程 框架允许通过多个线程并发取样和 通过单独的[线程组](https://baike.baidu.com/item/线程组)对不同的功能同时取样。

5.精心的GUI设计允许快速操作和更精确的计时。

6.缓存和离线分析/回放测试结果。

**JMeter的高可扩展性**

1.可链接的取样器允许无限制的测试能力。

2.各种负载统计表和可链接的[计时器](https://baike.baidu.com/item/计时器)可供选择。

3.数据分析和可视化[插件](https://baike.baidu.com/item/插件)提供了很好的可扩展性以及个性化。

4.具有提供动态输入到测试的功能（包括Javascript）。

5.支持脚本编程的取样器（在1.9.2及以上版本支持BeanShell）。

在设计阶段，JMeter能够充当HTTP PROXY（代理）来记录IE/NETSCAPE的[HTTP请求](https://baike.baidu.com/item/HTTP请求)，也可以记录apache等WebServer的log文件来重现HTTP流量。当这些HTTP客户端请求被记录以后，测试运行时可以方便的设置重复次数和并发度（[线程数](https://baike.baidu.com/item/线程数)）来产生巨大的流量。JMeter还提供可视化组件以及[报表工具](https://baike.baidu.com/item/报表工具)把量服务器在不同压力下的性能展现出来。

相比其他HTTP测试工具,JMeter最主要的特点在于扩展性强。JMeter能够自动扫描其lib/ext子目录下.jar文件中的[插件](https://baike.baidu.com/item/插件)，并且将其装载到内存，让用户通过不同的菜单调用。

<img src="https://gitee.com/zou_tangrui/note-pic/raw/master/img/202302171721137.png" alt="image-20220602145529651" style="zoom:67%;" />



## 2.Jmeter的安装

进入官网https://jmeter.apache.org/，找到下载入口，下载对应的版本：

![](https://gitee.com/zou_tangrui/note-pic/raw/master/img/202303010824688.png)

<img src="https://gitee.com/zou_tangrui/note-pic/raw/master/img/202303010824684.png" style="zoom:67%;" />



下载安装好后点击jmeter安装目录下的bin/jmeter.bat就可以运行了，运行界面如下：

<img src="https://gitee.com/zou_tangrui/note-pic/raw/master/img/202302171721138.png" alt="image-20220602150407250" style="zoom: 50%;" />



按如下方式可以设置中文：

<img src="https://gitee.com/zou_tangrui/note-pic/raw/master/img/202302171721139.png" alt="image-20220602150531093" style="zoom:50%;" />

如上方式设置重启后还是变成英文，如果想要设置永久有效，可以找到安装目录下的/bin/jmeter.propertise文件做如下设置：

```propertise
language=zh-CN
```

<img src="https://gitee.com/zou_tangrui/note-pic/raw/master/img/202302171721140.png" alt="image-20220602150946701" style="zoom:50%;" />



## 3.创建Jmeter测试计划

​	测试计划定义如何测试，并定义一个布局。例如，web应用程序以及客户端服务器应用程序，它可以被看作容器运行测试，一个完整的测试包括一个或多个元素，如线程组、逻辑控制器、样品产生控制器、监听器、定时器、断言和配置元素。测试计划必须至少有一个线程组。



### 3.1.添加和删除元素

​	通过鼠标右键点击测试计划Test Plan节点，并从"add"列表中选择一个新的元素就可以添加一个测试计划。另外，元素可以从文件中加载，并通过选择"merge"或"open"选项添加。例如：添加一个线程组原件测试计划：

<img src="https://gitee.com/zou_tangrui/note-pic/raw/master/img/202302171721141.png" alt="image-20220602151954820" style="zoom:50%;" />

要删除一个元素，确保元素被选中，右键单击该元素，然后选择"remove"删除选项：

<img src="https://gitee.com/zou_tangrui/note-pic/raw/master/img/202302171721142.png" alt="image-20220602152131474" style="zoom:50%;" />



### 3.2.加载和保存元素

从文件加载一个元素，右键单击您要添加的加载元件对现有树元素，并选择“merge”合并选项。选择文件保存元素。 JMeter会合并的元素，放到树上。

<img src="https://gitee.com/zou_tangrui/note-pic/raw/master/img/202302171721143.png" alt="image-20220602152404112" style="zoom:50%;" />

为了保存树元素，元素上点击右键并选择选择另存为…选项。 JMeter会保存选定的元素，再加上它下面的所有子元素。默认情况下，不保存JMeter 的元素，需要明确地保存它，如前面提到的。



### 3.3.保存测试计划

可以保存整个测试计划到本地，方便随时使用。点击菜单栏"File"文件选项，选择保存"Save"或"Save Test Plan as"：

<img src="https://gitee.com/zou_tangrui/note-pic/raw/master/img/202302171721144.png" alt="image-20220602152655058" style="zoom:50%;" />



### 3.4.运行/停止测试计划

从Run菜单项中选择运行-启动“Start ”（控制+ R）。当运行JMeter是，它显示了一个绿色的小盒子，右手端的部分，只是在菜单栏下。

<img src="https://gitee.com/zou_tangrui/note-pic/raw/master/img/202302171721145.png" alt="image-20220602152809266" style="zoom:50%;" />

停止测试方法有两种：

- 使用stop（Control + '.'）。这立即停止线程如果可能的话。
- 使用shutdown（Control + ','）。这就要求线程停止在任何当前工作的结束。



## 4.Jmeter的主要元素

### 4.1.主要元件

通过上面的创建，可能你还不了解什么是测试计划，什么是线程组，那么我们就来了解下Jmeter的元素组件吧：

- **测试计划：**	是使用Jmeter测试的起点，为其他测试元件提供一个容器，影响其作用范围内的所有组件；
- **线程组：**代表一定数量的用户，它可以用来模拟用户并发送请求，实际的请求内容在Sampler中定义，它被线程组包含；
- **配置原件：**维护Sampler需要的配置信息，并根据实际的需要修改请求的内容；
- **前置处理器：**负责在请求之前工作，常用来修改请求设置，在其作用范围内的每一个sampler元件之前执行；
- **定时器：**负责定义请求之间的延迟间隔，在其作用范围内的每一个sampler有效；
- **取样器(Sampler)：**是性能测试中向服务器发送请求，记录响应信息、响应时间的最小单元，如：HTTP Request、Sampler、FTP Request Sampler、TCP  Request Sampler、JDBC Request Sampler等。每一种不同类型的Sampler可以根据设置的参数向服务器发送不同类型的请求；
- **后置处理器：**负责请求之后的工作，常用获取返回的值，在其作用范围内的每一个sampler元件之后执行；
- **断言：**用来判断请求响应的结果是否如用户所期望的，在其作用范围内的对每一个sampler元件执行后的结果进行校验；
- **监听器：**负责收集测试结果，同时确定结果显示的方式，在其作用范围内对每一个sampler元件的信息收集并呈现；
- **逻辑控制器：**可以定义Jmeter发送请求的行为逻辑，它与Sampler结合使用可以模拟复杂的请求序列。



### 4.2.执行顺序和接口测试流程

**它们的执行顺序为：**

**配置元件->前置处理器->定时器->取样器->后置处理程序->断言->监听器**。

注意事项：

1.前置处理器、后置处理器和断言等组件只能对取样器起作用，因此，如果在它们的作用域内没有任何取样器，则不会被执行。

2.如果在同一作用域内有多个同一类型的元件，则这些元件按照它们在测试计划中的上下顺序依次执行。



**使用Jmeter进行接口测试的基本步骤如下：**

1. 测试计划
2. 线程组
3. HTTP Cookie管理器
4. HTTP请求默认值
5. HTTP请求(Sampler)
6. 断言
7. 监听(查看结果树，图形结果，聚合报告等。



## 5.测试流程详解



### 5.1.测试计划

打开Jmeter，在菜单左侧出现"测试计划Test Plan"在这里测试计划我们可以把它理解成新建的空白项目，在这个空白项目下可以添加一系列的接口。

<img src="https://gitee.com/zou_tangrui/note-pic/raw/master/img/202302171721146.png" alt="image-20220602185737275" style="zoom: 50%;" />



### 5.2.线程组

添加方法：右键单击Test Plan，找到添加，添加线程--线程组。一个线程组可以看作一个虚拟用户，线程组中的每个线程都可以理解为一个虚拟用户。

![image-20220602190223453](https://gitee.com/zou_tangrui/note-pic/raw/master/img/202302171721147.png)

- **线程数：**即虚拟用户数，设置多少个线程数也就是设置多少虚拟用户；
- **Ramp-Up时间(秒)：**设置虚拟用户数全部启动的时长，如果线程数为20秒，准备时长为10秒，那么需要10秒启动20个线程；
- **循环次数：**每个线程发动请求的个数，如果线程数为20，循环次数为10，那么每个线程发送10次请求。总请求数为10*20=200。如果勾选了“永远”，那么所有的线程会一直发送请求，直到手动点击菜单栏的停止按钮，或者设置线程时间结束。



### 5.3.HTTP Cookie管理器

添加方法：右键线程组--添加--配置元件--HTTP Cookie管理器。

HTTP Cookie管理器可以像浏览器一样存储和发送Cookie，如果你要发送一个带Cookie的HTTP请求，Cookie Manager会自动存储该请求的Cookies，并且发送同源站点的HTTP请时都可以使用该Cookie。

![image-20220602191631500](https://gitee.com/zou_tangrui/note-pic/raw/master/img/202302171721148.png)





### 5.4.HTTP请求默认值

添加方法：右键线程组--添加--配置元件--HTTP请求默认值。

HTTP请求默认值是为了方便填写后续内容而设置，主要填写IP和端口，后续的HTTP请求就不用每次都填写IP地址和端口号了。

![image-20220602192013469](https://gitee.com/zou_tangrui/note-pic/raw/master/img/202302171721149.png)





### 5.5.HTTP请求

添加方法：右键线程组--添加--取样器--HTTP请求。

HTTP请求包括接口请求方法、请求路径、请求参数等。

![image-20220602192159021](https://gitee.com/zou_tangrui/note-pic/raw/master/img/202302171721150.png)



**HTTP请求各填写位置详解：**

- 名称：用于标识一个取样器，争取见名知义；
- 注释：无作用，仅用户记录可读的注释信息；
- 协议：发送的HTTP请求协议，默认是HTTP，也可以选用HTTPS；
- 服务器名称或IP：请求的服务名称或IP地址；
- 端口号：默认值80；
- 方法：请求所采用的方法，默认是GET，还可选择POST,DELETE等；
- 路径：目标URL路径；
- 内容编码：内容的编码方式，默认为iso8859；
- 自动重定向：如选中该选项，当发送HTTP请求后得到的响应是301/302时，Jmeter会自动重定向到新页面；
- 使用keepAlive：保持Jmeter和目标服务器的活跃状态，默认选中；
- 对POST请求使用multipart/from-data：当发送POST请求时，使用multipart/from-data方法发送，默认不选中；
- 同请求一起发送参数：带参数的URL请求，Jmeter提供了一个简单的对参数化的方法。用户可以将URL中所有参数设置在本表中，表中的一行是参数的键值对信息。



### 5.6.响应断言

添加方法：右键HTTP响应--添加--断言--响应断言。

检查接口是否访问成功，如果检查失败会提示找不到断言内容，没提示表示成功。

![image-20220602193457461](https://gitee.com/zou_tangrui/note-pic/raw/master/img/202302171721151.png)

- **Apply to:**指断言的作用范围，通常发送一个请求只触发一个请求，所以勾选Main sample only就可以了；若发送一个请求可以触发多个服务器请求，就有main sample and sub-samples了。
  - Main sample and sub-samples：作用于主请求和子请求；
  - Main sample only：仅作用于主请求；
  - Sub-samples only：仅作用于子请求；
  - Jmeter Variable Name to use：作用Jmeter变量（输入Jmeter变量名称）。
- **测试字段：**

  - 响应文本：匹配返回json数据；
  - 响应代码：返回200，404，500等状态码；
  - 响应信息：返回成功或失败的字样；
  - 响应头：匹配响应头；
  - 请求头：匹配请求头；
  - URL样本：匹配请求的URL连接，如果有重定向则则包含请求URL和 重定向URL；
  - 文档（文本）：匹配响应数据的文本形式；
  - 忽略状态：一个请求有多个响应断言，第一个响应断言选中此项，当第一个响应断言失败时可以忽略此响应结果，继续进行下一个断言，如果下一个断言成功则判定断言是成功的；
  - 请求数据：匹配请求数据。
- **模式匹配规则：**

  - 包括：响应内容包含需要匹配的内容即代表响应成功，支持正则表达式；
  - 匹配：响应内容要完全匹配需要匹配的内容即代表响应成功，大小写不敏感，支持正则表达式；
  - 相等：响应内容要完全等于需要匹配的内容才代表成功，大小写敏感，不支持正则表达式；
  - 字符串：响应内容包含响应的内容才代表成功，大小写不敏感，不支持正则表达式；
  - 否：相当于取反，结果为True，勾上就是False；
  - 或者：如果不想用AND连接，用OR选项可以用于将多个断言模式进行OR连接（只要一个模式匹配，断言就是成功）。
- **测试模式：**断言的数据，点击“添加”就可以添加断言的数据。



### 5.7.添加监听器

添加方法：线程组--右键添加--监听器--查看结果树，一般还须添加图形报告、聚合报告。

树状形式显示接口的访问结果，包括请求结果、请求内容、服务器的响应内容。

![image-20220602201221689](https://gitee.com/zou_tangrui/note-pic/raw/master/img/202302171721152.png)







相关参考：https://blog.csdn.net/pengjiangchun/article/details/105707405?ops_request_misc=%257B%2522request%255Fid%2522%253A%2522165414737616781432979561%2522%252C%2522scm%2522%253A%252220140713.130102334..%2522%257D&request_id=165414737616781432979561&biz_id=0&utm_medium=distribute.pc_search_result.none-task-blog-2~all~top_positive~default-1-105707405-null-null.142^v11^control,157^v12^control&utm_term=jmeter&spm=1018.2226.3001.4187

http://www.jmeter.com.cn/2754.html







## 6.测试实例

### 6.1.Jmeter发送get请求

步骤：**测试计划---添加线程组---添加取样器---HTTP请求**

![](https://gitee.com/zou_tangrui/note-pic/raw/master/img/202303201139980.png)



接口参考网站：https://www.juhe.cn/docs/api/id/65，添加信息如下：

![image-20230320120436911](https://gitee.com/zou_tangrui/note-pic/raw/master/img/202303201204067.png)



key：21f3d56edc75e82afaede5d8bbc2f6b1

在线程组中：添加**监听器---察看结果树**

![](https://gitee.com/zou_tangrui/note-pic/raw/master/img/202303201159064.png)





### 6.2.Jmeter发送post请求

步骤：**线程组---添加取样器---HTTP请求**，将请求方式设置为post请求。

![image-20230320145649277](https://gitee.com/zou_tangrui/note-pic/raw/master/img/202303201456686.png)



### 6.3.HTTP请求默认值

​	目的：有时候，请求的数据有很多个，且大部分的请求路径、参数、端口等数据相同，所以不必要每个请求都添加这些数据，我们可以添加HTTP请求默认值。

​	步骤：**选择线程组---添加配置元件---HTTP请求默认值**。配置请求的默认值即可，这样该线程组的数据都能共享到该默认值。

​	如上面的get和post请求的服务器名称、路径、参数key都相同，我们就可以创建HTTP请求默认值，把这些数据提取出来，然后请求也是可达的。

![image-20230320150319824](https://gitee.com/zou_tangrui/note-pic/raw/master/img/202303201503999.png)





### 6.4.响应断言

​	Jmeter中有个元件叫做断言（Assertion），它的作用和loadrunner中检查点类似：用于检查测试中得到的响应数据等是否符合预期，用以保证性能测试过程中数据交互与预期一致。

​	使用断言的目的是：在request返回层面增加一层判断机制，因为request成功了，并不代表结果一定正确。所以通过断言，我们不再会被200所迷惑，而是可以通过断言，看到我们请求是否真正成功。

- 我们添加一个http请求，请求url为`abc.oracleoaec.com`。并在这个请求中添加`响应断言`。添加方式为：**HTTP请求---断言---响应断言**

<img src="https://gitee.com/zou_tangrui/note-pic/raw/master/img/202303261053804.png" alt="image-20230326105339589" style="zoom:50%;" />

![](https://gitee.com/zou_tangrui/note-pic/raw/master/img/202303261056959.png)

- 如需查看到响应断言的结果，需要添加断言结果，步骤为：**HTTP请求---监听器---断言结果**。点击HTTP请求运行，在断言结果中查看断言结果：

<img src="https://gitee.com/zou_tangrui/note-pic/raw/master/img/202303261100085.png" style="zoom:50%;" />

![](https://gitee.com/zou_tangrui/note-pic/raw/master/img/202303261101980.png)



### 6.5.用户自定义变量

​	之前我们在做“老黄历”的HTTP请求时，只有添加的日期是不固定的，每次都需要修改的。我们可以添加一组用户自定义变量，以方便测试。添加步骤为：**线程组---添加配置元件---用户自定义变量**。点击下面的添加，添加需要测试的自定义变量，变量以k-v形式存在：

<img src="https://gitee.com/zou_tangrui/note-pic/raw/master/img/202303261117082.png" alt="image-20230326111748934" style="zoom:50%;" />



​	添加完自定义变量后，在"HTTP请求"中，添加的请求变量名仍然使用原有的名字，值用**$(自定义变量名)**引入：

![](https://gitee.com/zou_tangrui/note-pic/raw/master/img/202303261121450.png)





### 6.6.参数化txt

​	请求登录地址：https://abc.oracleoaec.com/，传递两个参数：**mobilephone**和**pwd**。

​	我们针对这个登录接口设计5条测试用例：

- 正常登录
- 正确的用户名和错误的密码
- 不输入用户名
- 不输入密码
- 输入错误的用户名

测试步骤：

- 添加一个新的线程，取名“登录接口”，添加一个HTTP请求，取名“正常登录”。选择**“登录接口”的请求---添加配置元件---HTTP请求默认值**：

![image-20230326115626859](https://gitee.com/zou_tangrui/note-pic/raw/master/img/202303261157764.png)

- 在“正确登录”窗口，添加两个参数mobilephone和pwd：

![image-20230326115706013](https://gitee.com/zou_tangrui/note-pic/raw/master/img/202303261157727.png)

- 添加察看结果树：**登录接口---添加监听器---察看结果树**，后点击运行，查看到结果：

![image-20230326115751960](https://gitee.com/zou_tangrui/note-pic/raw/master/img/202303261157109.png)

- 选中“正常登录”，按下Ctrl+Shift+C复制5条测试用例，并修改名字为我们需要的测试用例：

![image-20230326120038658](https://gitee.com/zou_tangrui/note-pic/raw/master/img/202303261200726.png)

- 根据测试用例的需求，修改参数的值，如不输入用户名，就把用户名清空。点击察看结果树，再点击上方导航栏的清除（扫把按钮），清除之前的结果，再点击运行：

![image-20230326120434524](https://gitee.com/zou_tangrui/note-pic/raw/master/img/202303261204623.png)





​	此时我们发现，随着测试用例的增多，我们测试人员的工作也会陡然增加，那么这时我们就可以引入**text元件**来管理测试用例，它可以用来存储测试用例。添加步骤如下：

- **点击登录接口--添加配置元件---CSV数据文件设置**，禁用其他用例，只保留一个，并修改名为测试用例：

![image-20230326121107379](https://gitee.com/zou_tangrui/note-pic/raw/master/img/202303261211451.png)

- CSV数据文件设置需要引入一个文件作为测试用例的数据，我们起名为`jmeter_csv.txt`，数据为k-v结构，文件内容如下：

![](https://gitee.com/zou_tangrui/note-pic/raw/master/img/202303261217947.png)

- 在“CSV数据文件设置”中引入文件：

![](https://gitee.com/zou_tangrui/note-pic/raw/master/img/202303261221161.png)

- 在“登录接口”的页面，将循环次数改为5次（匹配jmeter_csv.txt数据个数）：

![](https://gitee.com/zou_tangrui/note-pic/raw/master/img/202303261224728.png)

- 在“测试用例”页面用**$(变量名)**（该变量名为csv数据文件设置中的变量名称）的方式引入变量：

![](https://gitee.com/zou_tangrui/note-pic/raw/master/img/202303261226501.png)

- 点击运行，察看结果树得到想要的结果（对应之前的数据）：

![image-20230326122716281](https://gitee.com/zou_tangrui/note-pic/raw/master/img/202303261227429.png)



​	**这种方法适合批量数据的测试。**但还有一种方式更加简便，那就是在本地创建一个表格，将数据保存到表格中，然后将数据另存为csv格式，引入方式和txt方式一样。

![image-20230326201056374](https://gitee.com/zou_tangrui/note-pic/raw/master/img/202303262011527.png)





## 7.Jmeter录制web脚本

​	想要完成脚本录制，我们需要完成如下两步：

- 设置Jmeter相关参数

  - 创建一个线程：**测试计划---添加线程组**
  - **测试计划---添加非测试单元---HTTP代理服务器**。要注意设置好端口和对应分组，方便后期收集脚本。
    - 此处的分组我们要匹配上面添加的线程组，端口要是本地没有被占用的线程组。
  
  ![](https://gitee.com/zou_tangrui/note-pic/raw/master/img/202303272016307.png)
  
  

- 设置浏览器

  - 打开控制面板---点击Internet选项---连接---局域网设置---点击高级---HTTP地址设置为`127.0.0.1`，端口设置为`8888`.（也可在浏览器设置--系统--打开代理设置设置）

  ![image-20230327203116495](https://gitee.com/zou_tangrui/note-pic/raw/master/img/202303272031600.png)

![image-20230327205825397](https://gitee.com/zou_tangrui/note-pic/raw/master/img/202303272058564.png)

- 点击HTTP代理服务器的`运行`按钮，再点击整数确定按钮

![](https://gitee.com/zou_tangrui/note-pic/raw/master/img/202303272047478.png)

![image-20230327204453806](https://gitee.com/zou_tangrui/note-pic/raw/master/img/202303272044172.png)

![](C:/Users/HP/Desktop/脚本成功录制.png)





### 7.1.登录脚本录制实战

登录网站：http://cfgjt.cn:8981/devt-web

账号：admin

密码：11111111

- 创建代理：**测试计划---线程组---添加非测试元件---HTTP代理服务器**，设置如下：

<img src="https://gitee.com/zou_tangrui/note-pic/raw/master/img/202303272102404.png" alt="image-20230327210257234" style="zoom:50%;" />

- 打开浏览器，设置代理服务器为`127.0.0.1`，端口为`8888`:

<img src="https://gitee.com/zou_tangrui/note-pic/raw/master/img/202303272103377.png" alt="image-20230327210347267" style="zoom:50%;" />

- 开启录制脚本，并在浏览器请求网站http://cfgjt.cn:8981/devt-web进行登录：

<img src="https://gitee.com/zou_tangrui/note-pic/raw/master/img/202303272105921.png" alt="image-20230327210501823" style="zoom:50%;" />

- 停止录制，生成录制结果

![image-20230327211323934](https://gitee.com/zou_tangrui/note-pic/raw/master/img/202303272113029.png)
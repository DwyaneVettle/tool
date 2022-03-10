# Git

git是一个免费的、开源的分布式版本控制系统，可以快速高效的处理从小型到大型的项目。

## 1.什么是版本控制

​	版本控制是一种记录一个或若干个文件内容变化，以便将来查阅特定版本修订情况的系统。



## 2.为什么使用版本控制

​	软件开发中，采用版本控制是一个明智的选中。有了它就可以将某个文件回溯到之前的状态，甚至将整个项目都退回到过去某个时间点的状态。就算你把整个项目乱增删改，也可以轻松恢复到原来的样子。你也可以比较文件的变化细节，查出最后是谁修改了哪个地方，从而找出问题的所在，又是谁在何时报告了问题的缺陷。



## 3.版本控制的分类

​	版本控制分为集中化版本控制和分布式版本控制，集中化版本控制如SVN、CVS，它们都有一个单一的集中管理的服务器，保存所有文件的修订版本，而协同工作的人们都是通过客户端连接到这台服务器的。它有一个最大的缺点就是中央服务器的单点故障，使得所有客户机无法连接。

​	分布式版本控制系统的代表就是Git。客户端并不只提取最新版本的文件快照，而是把代码仓库完整的镜像下来。



## 4.git的发展

![image-20220208093556350](Git.assets/image-20220208093556350.png)



## 5.git安装

​	今日git官网https://git-scm.com/下载

![image-20220208093844912](Git.assets/image-20220208093844912.png)

- **安装步骤**

![image-20220208101213216](Git.assets/image-20220208101213216.png)

![image-20220208101251779](Git.assets/image-20220208101251779.png)

![image-20220208101329240](Git.assets/image-20220208101329240.png)

![image-20220208101409541](Git.assets/image-20220208101409541.png)

![image-20220208101437455](Git.assets/image-20220208101437455.png)

![image-20220208101455559](Git.assets/image-20220208101455559.png)

![image-20220208101516960](Git.assets/image-20220208101516960.png)

![image-20220208101925519](Git.assets/image-20220208101925519.png)

​	安装完成后，在桌面点击鼠标右键，看到git gui here和git bash here就表示已经安装成功。

![image-20220208102039775](Git.assets/image-20220208102039775.png)

​	点击git bash here打开git终端。

## 6.git结构

![image-20220208102310854](Git.assets/image-20220208102310854.png)

## 7.初始化本地仓库

1. 在本地创建项目文件夹
2. 在该文件夹中点击git bash here

```git
git --version        // 查看git安装版本
clear				// 清屏
```

- **设置用户名和邮箱**

```git
git config --global user.name 'micheazou'			// 设置用户名
git config --global user.email 'Michealzou@126.com'  // 设置邮箱
```

- **初始化本地仓库**

```git
git init					// 初始化本地仓库
// 初始化后本地生成了一个文件夹.git
ll -a .git					// 查看.git文件夹下文件，config为配置文件信息
```

<img src="Git.assets/image-20220208104155804.png" alt="image-20220208104155804" style="zoom: 80%;" />

## 8.git常用命令

- **将本地仓库中的文件提交到暂存区**

```git
git add 文件名				// 提交指定文件
git add .				// 提交所有文件
```

- **将暂存区文件提交到本地库**

```git
git commit -m "初次提交" 提交文件名			// -m选项为Message，双引号中的内容为本次提交的注释
```

- **查看工作区和暂存区的状态**

```git
git status
// 提交后暂存区为空-nothing to commit,working tree clean
```

- **log命令查看历史记录**

```git
git log
git log --oneline     // 日志一行展示
git reflog			  // 可以按数字步骤回退
```

- **reset前进后退版本**

```git
git reset --hard reflog中的黄色hash值			// 回退、前进都某一版本
```

## 9.分支

​		在版本控制过程中，使用多条线同时推进多个任务，这里的多条线就是多分支。多分支可以并行开发，互不干扰，提高开发效率。如果有一个分支功能开发失败，直接删除这个分支就可以了，不会对其他分支产生影响。

![image-20220208114129063](Git.assets/image-20220208114129063.png)

```git
git branch -v               // 查看分支
git branch dev          // 创建dev分支
git checkout dev   		  // 切换dev分支
```

**分别在master分支和dev分支中增加内容，你会看到它们分属各个分支，会不干扰**

```git
// 在master分支中增加一个文本文档，并增加内容aa保存
git checkout master			// 切换到主分支中增加aa
git add 					// 提交到暂存区
git commit -m "增加aa到master分支"  // 提交到本地库
// 在dev分支中增加一个文本文档，并增加内容bb保存
git cheakout dev			// 切换到dev分支，此时你会看到在master增加的文本文档消失
git add 
git commit -m "增加bb到dev分支"
// 来回切换master和dev分支，你会看到两个分支的内容互不干扰
```

**合并两个分支的内容**

```git
git checkout master
git merge dev				// 合并dev分支
```

**如果在同一个文件的同一个位置修改内容，合并后会出现冲突。可以人为决定留下想要的内容**



## 10.创建远程库

​	在码云或者github上创建自己的账号，并创建远程库。此处以githubhttps://github.com/为例。

![image-20220208122031585](Git.assets/image-20220208122031585.png)

![image-20220208122206455](Git.assets/image-20220208122206455.png)

生成后就可以看到远程库的地址，但是地址较长，我们可以通过git设置远程库的别名：

```git
git remote -v 									// 查看远程库的别名
git remote add origin  https://github.com/DwyaneVettle/HTML.git
// 起别名origin
```

## 11.推送操作

```git
git push -u 仓库地址 master
```

推送上去后会要求输入github的用户名和密码，然后输入github的注册名和token。

我的github的token--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，不知道怎么生成token可以查阅https://blog.csdn.net/weixin_41010198/article/details/119698015

![image-20220208132502356](Git.assets/image-20220208132502356.png)



## 12.克隆操作

```git
git clone 远程仓库地址
```



## 13.邀请加入团队



## 14.冲突解决

​	如果发生冲突，需要先拉取下来，然后再修改冲突的地方，然后再推送上去。





## 拓展一、生成SSH免密提交

```git
ssh-keygen -t rsa -C "Michealzou@163.com"
// 按三次enter
cd ~/.ssh
ls
cat id_rsa.pub
// 复制SSH粘贴到github的setting中的SSH
```

## 拓展二、出现RPC错误

```git 
#首先设置通信缓存大小
git config http.postBuffer 524288000  
#然后把缓存清除
git filter-branch --index-filter 'git rm -r --cached --ignore-unmatch <file/dir>' HEAD
# 配置最低速度和最低速度时间
git config --global http.lowSpeedLimit 0
git config --global http.lowSpeedTime 999999
```



## 拓展三、切换账号输入账号密码

​	如果切换账号没有要求用户输入用户名和密码，需要清理缓存，打开控制面板----用户账户---管理windows凭证----删除对应的缓存
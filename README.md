<center><h1>GiftHaven 礼物港</h1></center>

<h5>如题这是一个礼品管理系统

练手用的前后端分离项目，也是大学专业课无聊的作业

- 前端采用Vue、Element UI。
- 后端采用Spring Boot、~~Spring Security~~、Redis 、Mysql。
- 权限认证使用Jwt。

<h5>使用方法
</h5>

```
#启动前提
	JDK >= 1.8
	Mysql >= 5.7.0
	Maven >= 3.0
	Node.js >= 16

#下载项目
	git clone https://github.com/yiyezq162/GiftMaven

#idea打开项目
	gift-haven-backend	是后端文件夹
	gift-haven-frontend	是前端文件夹
	
#配置Mysql数据库
	打开 gift-haven-backend/src/main/resources/application.yml
    	username: root
    	password: 123456

#记得导入数据库
	sql文件在根目录的sql文件里面


#启动后端
	在后端文件夹中找到Spring boot启动类
	com/example/gifthavenbackend/GiftHavenBackendApplication.java
	点击运行

#启动前端
	在前端文件夹内打开命令窗口
	npm isntall
	npm run dev

#如果没有端口占用应该就启动成功啦！
```


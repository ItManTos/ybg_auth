# ybg_auth 基于标准oauth2权限模型开发框架。

#### 项目介绍

基于开源的pig框架进行改造，使用springboot 开发。入手难度：2年以上开发经验
#### 框架需求
一般来说传统的springmvc足以应付各种各样的小系统。随着公司发展,又会开发其他系统，然后吧部分权限的代码、拷过来用，加上自己的逻辑又是一个新系统。但是又出现了一个新的问题，那就是权限和业务耦合的太严谨了，无法拆分旧的权限系统，自己又没能力去搭建一个权限系统或者重复搭建一个权限系统是一个十分麻烦的事情，又要用以前的系统账号等复杂因素，本框架就由此产生。
采用标准oauth2开发。实现权限和业务相分离。一个点点配置 便可控制业务权限。

#### 软件架构
采用架构，springboot,springSecurityOauth2,redis,MybatisPlus,mysql,nodejs,vue-cli,elementUI .

#### 目录说明
 * ybg_auth 授权中心 （授权服务器,以及权限管理）

 * ~~- ybg_auth_admin  用户角色权限管理后端（相当于资源服务器）-~~（4.2版本已把功能迁移到授权中心）

 * ybg_weixin 多微信号管理平台，集成了多微信订阅号，多微信公众号，多第三方开放平台，多小程序，多微信支付于一体的基本框架，支持根据公众号分表存储数据（只开发了粉丝管理和打标签的分表功能，企业可以自行定制功能是否分表）

 * ybg_auth_adminUI node.js项目搭建的用户角色权限管理前端，默认端口8000


架构设计图


![输入图片说明](https://images.gitee.com/uploads/images/2018/1006/212322_937ce663_880593.png "Untitled Diagram.png")

#### 开发环境
1. eclipse 最近版
2. maven 3.3.9+
3. jdk8
4. redis 和redis客户端（RedisDesktopManager）
5. mysql5.7 以及navicat 
6. tortoise svn
7. python
8. node.js

#### 部署环境
1. maven 3.3.9+
2. jdk8
3. redis 
4. mysql5.7 
5. jenkins
6. nginx

### 安装教程

1. 如何导入项目？参考 https://gitee.com/YYDeament/88ybg/wikis/Home
2. 安装环境 redis node.js maven 等环境？ 请百度
3. 启动顺序：ybg_auth ->  ~~- ybg_auth_admin （4.2版本已不需要）-~~  -> ybg_auth_adminUI 
4. 开发项目页面：http://localhost:8000 

### 部署教程
1. 暂不公布（收费）


#### 使用说明
1. 安装好开发环境必要环境，并且确保redis，mysql 能启动，如果是远程的redis和Mysql 请确保能远程访问的权限
2. 导入数据库文件，如果导不进 把字符varchar 的字节调小 导完后再改回来

先导入到数据库，库的名称叫uplus_auth 数据库编码是utf8mb4

![输入图片说明](https://images.gitee.com/uploads/images/2018/1007/091517_aec68b78_880593.png "屏幕截图.png")

3. 启动ybg_auth项目
   - 导入项目的方式参考 https://gitee.com/YYDeament/88ybg/wikis/Home
   - 修改ybg_auth项目中的application-dev.properties 文件 修改数据库配置和你的redis配置
   - 右键   /uplus-auth/src/main/java/com/uplus/AuthApplication.java    run as java application ,启动项目即可

4. ~~- 启动ybg_auth_admin项目 （项目已删除）-~~
   ~~-  导入项目的方式参考 https://gitee.com/YYDeament/88ybg/wikis/Home -~~
   ~~-  修改ybg_auth_admin项目中的application-dev.properties 文件 修改数据库配置和你的redis配置 -~~
   ~~-  右键  /uplus-auth-admin/src/main/java/com/uplus/AuthAdminApplication.java    run as java application ,启动项目即可 -~~

5. 启动ybg_auth_adminUI 
   - 导入项目后，进入ybg_auth_adminUI 代码目录
   -  shift+右键 如图所示 
![输入图片说明](https://images.gitee.com/uploads/images/2018/1007/094404_4aceac98_880593.png "屏幕截图.png")
   -  输入 npm i 先执行npm install命令 (如果node版本(8以上)过高，需要安装最新版python)
   -  执行完 输入 npm run dev 再执行npm run dev 启动本地调试



## 当前版本：4.2

## 版本更变历史：
 - v4.2.0 2019年4月6日 更新授权中心和认证中心合并成一个，不需要跑两个服务去管理权限。添加多微信管理平台基础版，各微信号支持分表存储数据。

 - v4.1.0 2018年11月24日 更新spirngboot版本从 1.5.9 变成 springboot2,mybatis plus 2.3版本变成3.0.6 我认为把请求的客户端放在前端 是不安全的，所以本作者把请求用户信息的东西放在了资源服务器，
 资源服务器再去请求认证服务器去拿到access_token，保证系统的安全性。这是4.1版本重点的内容。另外 4.1版本使用的是springboot2 它和springboot1.5的oauth2对于密码的认证方式不一样。
springboot 1.5.9请求需要客户端传递basic的请求头，而springboot2.0的方式不承认这种方式了 只能传clientId和clientSecret作为参数传给认证服务器。这是一个很大的坑，需要查找源码或者看资料才能知道。
此外 springboot1.5的redis默认实现是jredis而2.0的不是。所以也是要改造才可以正常使用redis.更多细节请看代码提交记录。
    

#### 更多项目文档尽在wiki 或者 老项目中
 - https://gitee.com/SYDeament/ybg_auth/wikis/Home
 - https://gitee.com/YYDeament/88ybg/wikis/Home

#### QQ交流群：（女生或一个太阳以下拒绝加入）468054855


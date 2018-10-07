# ybg_auth 基于标准oauth2权限模型开发框架。

#### 项目介绍
基于开源的pig框架进行改造，使用springboot 开发。入手难度？2年以上开发经验

#### 软件架构
软件架构说明

采用架构，springboot,springSecurityOauth2,redis,MybatisPlus,mysql,nodejs,vue-cli,elementUI .

#### 目录说明
ybg_auth 授权中心（授权服务器）

ybg_auth_admin 用户角色权限管理后端（相当于资源服务器）

ybg_auth_adminUI node.js项目搭建的用户角色权限管理前端，默认端口8000


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
3. 启动顺序：ybg_auth ->  ybg_auth_admin  -> ybg_auth_adminUI 
4. 开发项目页面：http://localhost:8000 

### 部署教程
1. 暂不公布（收费）


#### 使用说明
1. 安装好开发环境必要环境，并且确保redis，mysql 能启动，如果是远程的redis和Mysql 请确保能远程访问的权限
2. 导入数据库文件，如果导不进 把字符varchar 的字节调小 导完后再改回来

先导入到数据库，库的名称叫uplus_auth 数据库编码是utf8mb4

![输入图片说明](https://images.gitee.com/uploads/images/2018/1007/091517_aec68b78_880593.png "屏幕截图.png")

3. 启动ybg_auth项目
    导入项目的方式参考 https://gitee.com/YYDeament/88ybg/wikis/Home
    修改ybg_auth项目中的application-dev.properties 文件 修改数据库配置和你的redis配置
    右键   /uplus-auth/src/main/java/com/uplus/AuthApplication.java    run as java application ,启动项目即可

4. 启动ybg_auth_admin项目
    导入项目的方式参考 https://gitee.com/YYDeament/88ybg/wikis/Home
    修改ybg_auth_admin项目中的application-dev.properties 文件 修改数据库配置和你的redis配置
    右键  /uplus-auth-admin/src/main/java/com/uplus/AuthAdminApplication.java    run as java application ,启动项目即可

5. 启动ybg_auth_adminUI 
    导入项目后，进入ybg_auth_adminUI 代码目录
    shift+右键 如图所示
    先执行npm install命令
    执行完 再执行npm run dev 启动本地调试

#### 参与贡献

1. Fork 本项目
2. 新建 Feat_xxx 分支
3. 提交代码
4. 新建 Pull Request



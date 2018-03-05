# JavaWeb 电商计划 v2.0

[![forthebadge](https://forthebadge.com/images/badges/made-with-java.svg)](https://forthebadge.com)&emsp;[![forthebadge](https://forthebadge.com/images/badges/powered-by-responsibility.svg)](https://forthebadge.com)&emsp;[![forthebadge](https://forthebadge.com/images/badges/uses-git.svg)](https://forthebadge.com)&emsp;[![forthebadge](https://forthebadge.com/images/badges/uses-js.svg)](https://forthebadge.com)

## 进度
> 02/24/18 2.0开工，覆盖Tomcat集群+Nginx负载均衡+Redis分布式

![section2](http://oxgw3nd2b.bkt.clouddn.com/section2.jpg)

> 02/24/18 按照阿里巴巴Java代码规约插件规范代码，一期后端杀青

> 02/22/18 更新：Code Review & 添加“纯小白”风格注释中，勿黑

JavaWeb 初学项目，参考慕课网：https://coding.imooc.com/class/96.html
> 上线地址（备案中……🤣）：http://shuwuai.tech

### 环境
* 操作系统：macOS 10.13.3
* IDE：IntelliJ IDEA 2017.3.4
* JDK：JDK 1.8
* Web容器：Tomcat 9.0.2
* 数据库：Mysql 5.7.20 Community Server
* 依赖管理工具：Maven 3.5.2
* [Mybatis-PageHelper](https://github.com/pagehelper/Mybatis-PageHelper)
* [MyBatis Generator](http://www.mybatis.org/generator/index.html)
* [Mybatis plugin](https://www.codesmagic.com/mybatisplugin)
* 前后端接口测试  [Restlet Client - REST API Testing](https://restlet.com/modules/client/) 或  [Postman](https://www.getpostman.com/)

![Restlet Client](http://oxgw3nd2b.bkt.clouddn.com/2018-02-21_19-34-18副本.png)

* [支付宝 SDK](https://openhome.alipay.com/platform/home.htm)
* 其他依赖参见 `pom.xml`

### 部署环境
阿里云 ECS CentOS7 + Nginx + Tomcat + Redis + vsftpd + Git + JDK + MySQL + Node.js

## 后端部分一期核心
* SpringMVC + MyBatis
* 前后端分离
* 高复用服务响应对象的设计思想及抽象分装，ServerResponse<T> 对象承载，类比 `ModelAndView`

![ServerResponse](http://oxgw3nd2b.bkt.clouddn.com/ServerResponse.png)

### 模块构成
* 用户（登入登出、鉴权、注册、重置密码、获取/更新用户信息）
* 商品分类（详情页、商品列表分页）
* 购物车（商品总价计算、价格运算丢失精度问题）
* 订单（订单号生成规则、安全漏洞解决）
* 发货（SpringMVC 对象绑定、MyBatis 自动主键生成）
* 支付（支付宝对接、二维码沙箱测试）
* 线上部署（JDK/Maven/Tomcat/Nginx/vsftpd/Git、自动化发布）
* 接口测试  

### v1.0 / 部分2.0 测试用接口：[By Restlet_Client](https://github.com/shuwuai/tmall/wiki/%E6%8E%A5%E5%8F%A3%E6%B5%8B%E8%AF%95-By-Restlet_Client)

![tmall](http://oxgw3nd2b.bkt.clouddn.com/tmall.png)

> 03/04/18 梳理用户模块逻辑

```
Request
Content-Type: 
application/x-www-form-urlencoded
Response 
Content-Type: 
application/json;charset=UTF-8
```

## 私货 🍻

#### 码字：
[MWeb](http://www.mweb.im/) —— 所见即所得，无缝连接图床拖拽图片即可返回 MD 格式链接
![mweb](http://oxgw3nd2b.bkt.clouddn.com/mweb.png)

[Quiver](http://happenapps.com/) —— 代码笔记本，cell 形式组织内容，多种语言高亮语法支持，Dropbox 同步（主题：https://github.com/Goston/Quiver-Theme）
![2018-03-04_15-16-23](http://oxgw3nd2b.bkt.clouddn.com/2018-03-04_15-16-23.png)

[iText](https://toolinbox.net/iText/) —— 看 PDF 记笔记截图识字最佳

#### 码码：
Dash
![dash](http://oxgw3nd2b.bkt.clouddn.com/dash.png)

Sublime Text 3
![sublime](http://oxgw3nd2b.bkt.clouddn.com/sublime.png)

#### 插件：
AceJump —— 尝试不用鼠标……
![acejump](http://oxgw3nd2b.bkt.clouddn.com/acejump.png)

[Material UI](https://github.com/ChrisRM/material-theme-jetbrains) —— 最爱的 UI

[Lombok](https://projectlombok.org/) —— 省却一堆 Getter/Setter、constructor
![lombok](http://oxgw3nd2b.bkt.clouddn.com/lombok.png)

Translation
![translation](http://oxgw3nd2b.bkt.clouddn.com/translation.png)

#### VCS：
[SourceTree](https://www.sourcetreeapp.com/) —— 好用过官方
![sourcetree](http://oxgw3nd2b.bkt.clouddn.com/sourcetree.png)

#### 终端：
[iTerm 2](https://www.iterm2.com/)（Powerline、字体：Droid Sans Mono Nerd Font、ColorPreset: https://github.com/MartinSeeler/iterm2-material-design）
![iterm2](http://oxgw3nd2b.bkt.clouddn.com/iterm2.png)

[tmux](https://github.com/tmux/tmux) —— 终端复用，ssh 多个远程服务器，多开 Redis
![tmux](http://oxgw3nd2b.bkt.clouddn.com/tmux.png)

[FinderGo](https://github.com/onmyway133/FinderGo) —— Finder 内直接打开终端

[space-vim](https://github.com/liuchengxu/space-vim) —— 懒癌开箱即用
![spacevim](http://oxgw3nd2b.bkt.clouddn.com/spacevim.png)

[Homebrew](https://brew.sh/) 

#### 数据库：
[mycli](https://github.com/dbcli/mycli) —— 终端用 MySQL 自动补全
 ![mycli](http://oxgw3nd2b.bkt.clouddn.com/mycli.png)
 
[Sequel Pro](https://www.sequelpro.com/) —— 轻量免费数据库管理
![sequel](http://oxgw3nd2b.bkt.clouddn.com/sequel.png)

[Medis](https://github.com/luin/medis) —— Redis GUI 管理
![medis](http://oxgw3nd2b.bkt.clouddn.com/medis.png)

#### 网络：
[Charles](https://www.charlesproxy.com/) —— 抓包、地址映射、DNS 欺骗
![charles](http://oxgw3nd2b.bkt.clouddn.com/charles.png)

[Gas Mask](https://github.com/2ndalpha/gasmask/) —— 本地测试管理 Hosts
![gasmask](http://oxgw3nd2b.bkt.clouddn.com/gasmask.png)

#### JVM：
[VisualVM](https://visualvm.github.io/) —— 各种监控，比如内存 CPU、线程、GC
![jvm](http://oxgw3nd2b.bkt.clouddn.com/jvm.png)

#### Linux：
[Transmit](https://panic.com/transmit/) —— FTP
[VMware Fusion](https://www.vmware.com/products/fusion.html) —— 虚拟机
![vmware](http://oxgw3nd2b.bkt.clouddn.com/vmware.png)


#### 效率：
[Karabiner-Elements](https://pqrs.org/osx/karabiner/) —— 改键，caps lock 改为长按——左 shift+control+option+command、短按——esc，增强 IDEA 的 keymap


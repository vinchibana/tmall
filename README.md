# JavaWeb 电商计划 v1.0

[![url](https://img.shields.io/badge/URL-shuwuai.cc-ff69b4.svg?style=flat-square)](http://shuwuai.cc)  [![Telegram](https://img.shields.io/badge/Add-Telegram-green.svg?style=flat-square&logo=telegram)](https://telegram.me/shuwuai) [![Twitter Follow](https://img.shields.io/twitter/follow/espadrine.svg?style=social&label=Follow&logo=twitter)](https://twitter.com/shuwuai)

JavaWeb 初学项目，参考慕课网：https://coding.imooc.com/class/96.html
上线地址（备案中……🤣）：http://shuwuai.tech

### 开发环境
* 操作系统：macOS 10.13.3
* IDE：IntelliJ IDEA 2017.3.4
* JDK：JDK 1.8
* Web容器：Tomcat 9.0.5
* 数据库：Mysql 5.7.20 Community Server
* 依赖管理工具：Maven 3.5.2
* [Mybatis-PageHelper](https://github.com/pagehelper/Mybatis-PageHelper)
* [MyBatis Generator](http://www.mybatis.org/generator/index.html)
* [Mybatis plugin](https://www.codesmagic.com/mybatisplugin)
* 前后端接口测试  [Restlet Client - REST API Testing](https://restlet.com/modules/client/)

![2018-02-21_19-34-18副本](http://oxgw3nd2b.bkt.clouddn.com/2018-02-21_19-34-18副本.png)

* [支付宝 SDK](https://openhome.alipay.com/platform/home.htm)
* 其他依赖参见 `pom.xml`

## 后端部分一期核心
SpringMVC + MyBatis
前后端分离
高服用服务响应对象的设计思想及抽象分装，ServerResponse<T> 对象承载

![2018-02-21_19-49-41](http://oxgw3nd2b.bkt.clouddn.com/2018-02-21_19-49-41.png)

## 模块构成
* 用户（登入登出、鉴权、注册、重置密码、获取/更新用户信息）
* 商品分类（详情页、商品列表分页）
* 购物车（商品总价计算、价格运算丢失精度问题）
* 订单（订单号生成规则、安全漏洞解决）
* 发货（SpringMVC 对象绑定、MyBatis 自动主键生成）
* 支付（支付宝对接、二维码沙箱测试）
* 线上部署（JDK/Maven/Tomcat/Nginx/vsftpd/Git、自动化发布）
* 接口测试  

```
Request 
Content-Type: 
application/x-www-form-urlencoded
Response 
Content-Type: 
application/json;charset=UTF-8
```





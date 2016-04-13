##说明
+ org.json.jar，xom-1.2.10.jar是我们编程中用到的第三方jar包。其他jar包是我们导出的可复用构件，构件jar包中包含源码。



+ config-1.0.jar用于对解析配置文件，编辑配置文件的相关操纵,配置文件为json格式，该包依赖于org.json.jar包

+ record-1.0.jar用于记录日志等操作，可以设置记录日志的周期

+ license.ctrl-1.0.jar用于许可管理

+ pwd.ctrl-1.0.jar用于与用户数据库有关的相关操作，可用于验证密码是否正确，以及新建用户。用户数据库为一json文件，因此该包也依赖于org.json.jar包

+ packedController.jar包为以上四个功能的整合

+ encrypt.jar用于数据的加密


##说明
#### org.json.jar，xom-1.2.10.jar是我们编程中用到的第三方jar包。其他jar包是我们导出的可复用构件，构件jar包中包含源码。


#### config-1.0.jar用于对解析配置文件，编辑配置文件的相关操纵,配置文件为json格式，该包依赖于org.json.jar包

+ 配置文件为json格式
+ 用户可以指定配置文件位置，默认为data/config.json
+ value值可以为int或String

[文档](http://weiyichen.github.io/SoftwareReuse-Project/config-1.0-doc/index.html)

#### record-1.0.jar用于记录日志等操作，可以设置记录日志的周期(Performance Monitor)

+ 用户可以设定记录的周期，单位是秒

```
public void setAndStart(int saveCycle){
		this.saveCycle = saveCycle;
		saveRecordThread.start();
}
```

[文档](http://weiyichen.github.io/SoftwareReuse-Project/record-1.0-doc/index.html)

#### license.ctrl-1.0.jar用于许可管理

```
public void reset(String user)
public static void setLimit(int msgsPerLogin, int msgsPerSecong)

/**
	 * @param user String of user's ID
	 * @return
	 * 0 for receive the massage normally. <br>
	 * 1 for reaching the limit of max number of message in one second. <br>
	 * 2 for reaching the limit of max number of message of one author authorization.
	 */
public int receivedMessage(String user)
```

[文档](http://weiyichen.github.io/SoftwareReuse-Project/lincense.ctrl-1.0-doc/index.html)

#### pwd.ctrl-1.0.jar用于与用户数据库有关的相关操作，可用于验证密码是否正确，以及新建用户。用户数据库为一json文件，因此该包也依赖于org.json.jar包

```
public boolean passwordCheck(String user, String password)
public boolean addUser(String user, String password)
```

[文档](http://weiyichen.github.io/SoftwareReuse-Project/pwd.ctrl-1.0-doc/index.html)

#### packedController.jar包为以上四个功能的整合

#### encrypt.jar用于数据的加密


package edu.tongji.reuse.teameleven.server.ctrl;

import edu.tongji.reuse.teameleven.codependent.base.JsonBuilderBase;
import edu.tongji.reuse.teameleven.rls.encrypt.message.EncryptImpl;
import edu.tongji.reuse.teameleven.rls.encrypt.message.IEncrypt;
import edu.tongji.reuse.teameleven.codependent.model.User;
import edu.tongji.reuse.teameleven.server.json.JsonAnalizerServer;
import edu.tongji.reuse.teameleven.server.json.JsonBuilderServer;
import octoteam.tahiti.config.ConfigManager;
import octoteam.tahiti.config.loader.JsonAdapter;

import java.io.IOException;
import java.util.Date;

// todo move static fields to a new class using singleton pattern
public class MessageController {
    private static PasswordController passwordController = new PasswordController(
            ServerConfigEnum.defaultUserPwdMap
    );

    private static GroupController groupController = new GroupController(
            ServerConfigEnum.defaultUserGroupMap
    );


    private static ConfigManager configManager = new ConfigManager(
            new JsonAdapter(), "data/config.json"
    );

    private static ServerConfigBean configBean;

    private static ZipLogController zipLogController = new ZipLogController();

    private static ReZipLogController reZipLogController = new ReZipLogController();

    private static IEncrypt encrypt = new EncryptImpl();

    static{
        try{
            configBean = configManager.loadToBean(ServerConfigBean.class);
            ServerMonitorController.setSaveCycle(configBean.getSaveCycle());
            LicenseCtrl.setLimit(configBean.getMaxMessagesPerLogin(), configBean.getMaxMessagesPerSecond());
            zipLogController.setAndStart(configBean.getZipCycle());
            reZipLogController.setAndStart(configBean.getReZipCycle());
        }catch(IOException ioe){
            throw new RuntimeException(ioe);
        }
    }

    public static void startRecordThread(){
        ServerMonitorController.startRecord();
    }

    private LicenseCtrl licenseController;

    private User user;

    public MessageController(){
        licenseController = new LicenseCtrl();
        user = new User();
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String dealWithMessage(String jsonString){
        String type = JsonAnalizerServer.getMessageType(jsonString);
        if(type.equals(JsonBuilderBase.message)){
            return this.dealWithTextMessage(jsonString);
        }
        if(type.equals(JsonBuilderBase.password)){
            return this.dealWithPwdMessage(jsonString);
        }
        return JsonBuilderServer.getTypeNoFoundError();
    }

    public String dealWithTextMessage(String jsonString){
        String user = JsonAnalizerServer.getUser(jsonString);
        int licenseResult = licenseController.receivedMessage(user);
        if (licenseResult != 0) {
            // recordController.ignoredNumberAdd();
            ServerMonitorController.increaseIgnoredNumber();
            if (licenseResult == 1) {
                return JsonBuilderServer.getMessageBusyError();
            }
            if (licenseResult == 2) {
                return JsonBuilderServer.getNeedReloginError();
            }
            if (licenseResult == 3) {
                // how can this happens, I don't know.
                licenseController.stopCounting();
                return JsonBuilderServer.getNeedReloginError();
            }
        }
        ServerMonitorController.increaseReceivedNumber();
        return jsonString;
    }

    public String dealWithPwdMessage(String jsonString){
        String user = JsonAnalizerServer.getUser(jsonString);
        String password = encrypt.decryptToTMD5(JsonAnalizerServer.getPassword(jsonString));
        if(passwordController.passwordCheck(user, password)){
            licenseController.reset(user);
            this.user.setUserId(user);
            String group = groupController.getGroup(user);
            this.user.setGroup(group);
            this.user.setOnLine(true);
            this.user.setLoginTime(new Date().getTime());
            ServerMonitorController.increaseLogsucceedNumber();
            return JsonBuilderServer.getLoginSucceedJson();
        }
        ServerMonitorController.increaseLogfailedNumber();
        return JsonBuilderServer.getLoginFailedJson();
    }

    public void addSendRecord(){
        ServerMonitorController.increaseLogfailedNumber();
    }

    public String getGroup(){
        return this.user.getGroup();
    }

    public boolean hasCurrentUser(){
        return ("").equals(this.user.getUserId());
    }

    public static void quit(){
        ServerMonitorController.getMonitor().stop();
        passwordController.quit();
        groupController.quit();
        zipLogController.quit();
        reZipLogController.quit();
    }



}

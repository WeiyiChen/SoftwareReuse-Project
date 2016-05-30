package edu.tongji.reuse.teameleven.authentication.transport;

import edu.tongji.reuse.teameleven.authentication.ctrl.PasswordController;
import edu.tongji.reuse.teameleven.codependent.base.JsonAnalizerBase;
import edu.tongji.reuse.teameleven.codependent.base.JsonBuilderBase;
import edu.tongji.reuse.teameleven.codependent.model.NetInfo;
import edu.tongji.reuse.teameleven.codependent.model.User;
import edu.tongji.reuse.teameleven.coserver.ctrl.GroupController;
import edu.tongji.reuse.teameleven.coserver.util.JsonAnalizerServer;
import edu.tongji.reuse.teameleven.coserver.util.JsonBuilderServer;
import edu.tongji.reuse.teameleven.coserver.util.ServerConfigEnum;
import edu.tongji.reuse.teameleven.coserver.util.SocketWrapper;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.Date;

/**
 * Created by daidongyang on 5/29/16.
 */
public class LoginMsgHandler extends Thread {
    SocketWrapper socketWrapper;
    SocketListener socketListener;

    public LoginMsgHandler(SocketWrapper socketWrapper, SocketListener socketListener){
        this.socketWrapper = socketWrapper;
        this.socketListener = socketListener;
    }

    @Override
    public void run(){
        if(checkpwd()){
            // todo add init contacts and lost messages, and quit loginSocket safely

        }
    }

    /**
     *
     * @return - is continue with last produces
     */
    public boolean checkpwd(){
        BufferedReader bufferedReader = socketWrapper.getBufferedReader();
        while(!Thread.currentThread().isInterrupted()){
            try{
                String jsonString = bufferedReader.readLine();

                // the client has been closed
                if(null == jsonString){
                    safeClose();
                    break;
                }

                if(JsonAnalizerBase.getMessageType(jsonString).equals(JsonBuilderBase.authorization)){
                    String userId = JsonAnalizerServer.getUser(jsonString);
                    String password = ServerConfigEnum.encrypt
                            .decryptToTMD5(JsonAnalizerServer.getPassword(jsonString));

                    if(PasswordController.getInstance().passwordCheck(userId, password)){
                        User user = new User();
                        user.setLoginTime(new Date().getTime());
                        user.setUserId(userId);
                        user.setGroup(GroupController.getInstance().getGroup(userId));
                        user.setOnLine(true);

                        // todo setuser for other modules

                        //
                        NetInfo netInfo = new NetInfo();

                        // todo make the port configurable
                        netInfo.setIp("127.0.0.1");
                        netInfo.setPort(15601);

                        socketWrapper.sendText(netInfo.toJsonString());

                        socketWrapper.sendText(JsonBuilderServer.getLoginSucceedJson());

                        return true;
                    }else{
                        socketWrapper.sendText(JsonBuilderBase.getLoginFailedJson());
                    }
                }
                else{
                    continue;
                }
            }catch(IOException e){
                e.printStackTrace();
                return false;
            }
        }
        return false;
    }

    public void safeClose(){
        try {

            socketWrapper.quit();
            socketListener.removeHandler(this);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

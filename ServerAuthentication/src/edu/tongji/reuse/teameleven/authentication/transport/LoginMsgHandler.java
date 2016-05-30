package edu.tongji.reuse.teameleven.authentication.transport;

import edu.tongji.reuse.teameleven.authentication.ctrl.PasswordController;
import edu.tongji.reuse.teameleven.codependent.base.JsonAnalizerBase;
import edu.tongji.reuse.teameleven.codependent.base.JsonBuilderBase;
import edu.tongji.reuse.teameleven.codependent.model.NetInfo;
import edu.tongji.reuse.teameleven.codependent.model.User;
import edu.tongji.reuse.teameleven.coserver.util.JsonAnalizerServer;
import edu.tongji.reuse.teameleven.coserver.util.JsonBuilderServer;
import edu.tongji.reuse.teameleven.coserver.util.ServerConfigEnum;
import edu.tongji.reuse.teameleven.coserver.util.SocketWrapper;
import edu.tongji.reuse.teameleven.processor.stub.ContactsCtrlIntf;

import java.io.BufferedReader;
import java.io.IOException;
import java.rmi.RemoteException;
import java.util.Date;
import java.util.List;

/**
 * Created by daidongyang on 5/29/16.
 */
public class LoginMsgHandler extends Thread {
    SocketWrapper socketWrapper;
    SocketListener socketListener;
    String user;

    public LoginMsgHandler(SocketWrapper socketWrapper, SocketListener socketListener){
        super();
        this.socketWrapper = socketWrapper;
        this.socketListener = socketListener;

    }

    @Override
    public void run(){
        if(checkpwd()){
            // todo add init lost messages, and quit loginSocket safely
            if(user == null){
                return;
            }
            ContactsCtrlIntf contactsCtrl = ProcessorRef.getContactsCtrl();
            try{
                List<String> contacts = contactsCtrl.getInitContacts(user);
                contactsCtrl.addUser(user);
                if(contacts != null){
                    String jsonString = JsonBuilderServer.getInitContactsJson(contacts);
                    socketWrapper.sendText(jsonString, 1000);
                }

            }catch(RemoteException e){
                e.printStackTrace();
            }

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
                System.out.println("Receive : " + jsonString);
                // the client has been closed
                if(null == jsonString){
                    safeClose();
                    break;
                }

                if(JsonAnalizerBase.getMessageType(jsonString).equals(JsonBuilderBase.password)){
                    String userId = JsonAnalizerServer.getUser(jsonString);
                    String password = ServerConfigEnum.encrypt
                            .decryptToTMD5(JsonAnalizerServer.getPassword(jsonString));

                    if(PasswordController.getInstance().passwordCheck(userId, password)){
                        user = userId;

                        // todo setuser for other modules

                        //
                        NetInfo netInfo = new NetInfo();

                        // todo make the port configurable
                        netInfo.setIp("127.0.0.1");
                        netInfo.setPort(15601);

                        System.out.println("send : " + netInfo.toJsonString());
                        socketWrapper.sendText(netInfo.toJsonString());

                        System.out.println("send : " + JsonBuilderBase.getLoginSucceedJson());
                        socketWrapper.sendText(JsonBuilderServer.getLoginSucceedJson());

                        return true;
                    }else{
                        System.out.println("send : " + JsonBuilderBase.getLoginFailedJson());
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

package edu.tongji.reuse.teameleven.authentication.transport;

import edu.tongji.reuse.teameleven.authentication.ctrl.PasswordController;
import edu.tongji.reuse.teameleven.codependent.base.JsonAnalizerBase;
import edu.tongji.reuse.teameleven.codependent.base.JsonBuilderBase;
import edu.tongji.reuse.teameleven.codependent.model.NetInfo;
import edu.tongji.reuse.teameleven.codependent.model.User;
import edu.tongji.reuse.teameleven.coserver.ctrl.ConfigCtrl;
import edu.tongji.reuse.teameleven.coserver.ctrl.GroupController;
import edu.tongji.reuse.teameleven.coserver.util.JsonAnalizerServer;
import edu.tongji.reuse.teameleven.coserver.util.JsonBuilderServer;
import edu.tongji.reuse.teameleven.coserver.util.ServerConfigEnum;
import edu.tongji.reuse.teameleven.coserver.util.SocketWrapper;
import edu.tongji.reuse.teameleven.holder.stub.GetMissedMsgsIntf;
import edu.tongji.reuse.teameleven.processor.stub.ContactsCtrlIntf;
import edu.tongji.reuse.teameleven.record.stub.MonitorControllerIntf;

import java.io.BufferedReader;
import java.io.IOException;
import java.rmi.RemoteException;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * Created by daidongyang on 5/29/16.
 */
public class LoginMsgHandler extends Thread {
    SocketWrapper socketWrapper;
    SocketListener socketListener;
    User user;

    public LoginMsgHandler(SocketWrapper socketWrapper, SocketListener socketListener) {
        super();
        this.socketWrapper = socketWrapper;
        this.socketListener = socketListener;

    }

    @Override
    public void run() {

        if (checkpwd()) {
            if (user == null) {
                safeClose();
                return;
            }
            ContactsCtrlIntf contactsCtrl = RefsInAuth.getContactsCtrl();
            GetMissedMsgsIntf getMissedMsgs = RefsInAuth.getGetMissedMsgs();
            try {
                List<String> contacts = contactsCtrl.getInitContacts(user.getUserId());
                if (contacts != null) {
                    String jsonString = JsonBuilderServer.getInitContactsJson(contacts);
                    socketWrapper.sendText(jsonString, 1000);
                }
                contactsCtrl.addUser(user.getUserId());

                List<String> missedMesages = getMissedMsgs.getMissedMsgs(user);
                if (missedMesages != null) {
                    socketWrapper.sendTexts(missedMesages, 1000);
                }

                try {
                    TimeUnit.MILLISECONDS.sleep(2000);
                    safeClose();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
//                socketWrapper.sendText(JsonBuilderServer.getAddContactsJson(user.getUserId()));

            } catch (RemoteException e) {
                e.printStackTrace();
            }

        }
    }


    /**
     * @return - is continue with last produces
     */
    public boolean checkpwd() {
        // todo delete System.out
        System.out.println("in checkpwd");
        BufferedReader bufferedReader = socketWrapper.getBufferedReader();
        while (!Thread.currentThread().isInterrupted()) try {
            String jsonString = bufferedReader.readLine();
            System.out.println("Receive : " + jsonString);
            // the client has been closed
            if (null == jsonString) {
                safeClose();
                break;
            }
            if (JsonAnalizerBase.getMessageType(jsonString).equals(JsonBuilderBase.password)) {
                String userId = JsonAnalizerServer.getUser(jsonString);
                String password = ServerConfigEnum.encrypt
                        .decryptToTMD5(JsonAnalizerServer.getPassword(jsonString));
                MonitorControllerIntf monitorControllerRef = RefsInAuth.getMonitorControllerRef();
                if (PasswordController.getInstance().passwordCheck(userId, password)) {

                    monitorControllerRef.increaseLogsucceedNumber();
//                        user = userId;
                    user = new User();
                    user.setUserId(userId);
                    user.setOnLine(true);
                    user.setGroup(GroupController.getInstance().getGroup(userId));
                    user.setLoginTime(new Date().getTime());

                    // todo setuser for other modules

                    //
                    NetInfo netInfo = new NetInfo();

                    // todo make the port configurable
                    String handlersIp = ConfigCtrl.getConfig().getHandlersIP();
                    int handlersListenPort = ConfigCtrl.getConfig().getHandlersListenPort();
                    netInfo.setIp(handlersIp);
                    netInfo.setPort(handlersListenPort);

                    System.out.println("send : " + netInfo.toJsonString());
                    socketWrapper.sendText(netInfo.toJsonString());

                    System.out.println("send : " + JsonBuilderBase.getLoginSucceedJson());
                    socketWrapper.sendText(JsonBuilderServer.getLoginSucceedJson());

                    return true;
                } else {
                    monitorControllerRef.increaseLogfailedNumber();
                    System.out.println("send : " + JsonBuilderBase.getLoginFailedJson());
                    socketWrapper.sendText(JsonBuilderBase.getLoginFailedJson());
                }
            } else {
                continue;
            }
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
        return false;
    }

    public void safeClose() {
        try {
            socketWrapper.close();
            socketListener.removeHandler(this);
            this.interrupt();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

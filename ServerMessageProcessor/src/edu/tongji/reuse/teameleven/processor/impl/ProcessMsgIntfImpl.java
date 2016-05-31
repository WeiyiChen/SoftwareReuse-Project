package edu.tongji.reuse.teameleven.processor.impl;

import edu.tongji.reuse.teameleven.coserver.ctrl.GroupController;
import edu.tongji.reuse.teameleven.coserver.util.JsonAnalizerServer;
import edu.tongji.reuse.teameleven.coserver.util.JsonBuilderServer;
import edu.tongji.reuse.teameleven.handlers.stub.HandlersIntf;
import edu.tongji.reuse.teameleven.holder.stub.MissedMsgsIntf;
import edu.tongji.reuse.teameleven.processor.ctrl.LicenseCtrl;
import edu.tongji.reuse.teameleven.processor.ctrl.UsersInfoCtrl;
import edu.tongji.reuse.teameleven.processor.stub.ProcessMsgIntf;
import edu.tongji.reuse.teameleven.processor.transport.RefsInProcessor;

import java.rmi.RemoteException;
import java.util.Date;
import java.util.List;

/**
 * Created by daidongyang on 5/31/16.
 */
public class ProcessMsgIntfImpl implements ProcessMsgIntf {
    UsersInfoCtrl usersInfoCtrl;
    LicenseCtrl licenseCtrl;

    public UsersInfoCtrl getUsersInfoCtrl() {
        return usersInfoCtrl;
    }

    public void setUsersInfoCtrl(UsersInfoCtrl usersInfoCtrl) {
        this.usersInfoCtrl = usersInfoCtrl;
    }

    public void setLicenseCtrl(LicenseCtrl licenseCtrl) {
        this.licenseCtrl = licenseCtrl;
    }

    @Override
    public void processMsg(String jsonString) throws RemoteException {
        String userId = JsonAnalizerServer.getUser(jsonString);
        MissedMsgsIntf missedMsgsRef = RefsInProcessor.getMissedMsgsRef();

        if(!licenseCtrl.use(userId)){
            String jsonMsg = JsonBuilderServer.getReloginRequestJson();
            HandlersIntf handlersIntf = RefsInProcessor.getHandlersIntf();
            //todo delete System.out code
            System.out.println("send relogin msg");
            handlersIntf.relogin(jsonMsg, userId);
            usersInfoCtrl.logoutUser(userId);
            licenseCtrl.removeUser(userId);

            missedMsgsRef.setLogoutTime(userId, new Date().getTime());
            return;
        }
        //
        String group = GroupController.getInstance().getGroup(userId);
        missedMsgsRef.addMissedMsg(group, jsonString, usersInfoCtrl.getOnlineUsers(group));

        List<String> targets = usersInfoCtrl.getOnlineGroupMates(userId);
        if(targets == null || targets.size() <= 0){
            return;
        }
        HandlersIntf handlersIntfImpl = RefsInProcessor.getHandlersIntf();
        handlersIntfImpl.sendMsgs(targets, jsonString);
    }

    @Override
    public void logoutUser(String userId) throws RemoteException {
        usersInfoCtrl.logoutUser(userId);
        licenseCtrl.removeUser(userId);

        MissedMsgsIntf missedMsgsRef = RefsInProcessor.getMissedMsgsRef();
        missedMsgsRef.setLogoutTime(userId, new Date().getTime());

        List<String> targets = usersInfoCtrl.getOnlineGroupMates(userId);
        if(targets == null || targets.size() <= 0){
            return;
        }
        String jsonString = JsonBuilderServer.getRmContactsJson(userId);
        HandlersIntf handlersIntfImpl = RefsInProcessor.getHandlersIntf();
        handlersIntfImpl.sendMsgs(targets, jsonString);
    }


}

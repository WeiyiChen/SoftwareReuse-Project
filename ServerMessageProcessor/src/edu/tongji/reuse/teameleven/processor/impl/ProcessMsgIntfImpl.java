package edu.tongji.reuse.teameleven.processor.impl;

import edu.tongji.reuse.teameleven.coserver.util.JsonAnalizerServer;
import edu.tongji.reuse.teameleven.coserver.util.JsonBuilderServer;
import edu.tongji.reuse.teameleven.handlers.stub.HandlersIntf;
import edu.tongji.reuse.teameleven.processor.model.UsersInfo;
import edu.tongji.reuse.teameleven.processor.stub.ProcessMsgIntf;
import edu.tongji.reuse.teameleven.processor.transport.RefsInProcessor;

import java.rmi.RemoteException;
import java.util.List;

/**
 * Created by daidongyang on 5/31/16.
 */
public class ProcessMsgIntfImpl implements ProcessMsgIntf {
    UsersInfo usersInfo;

    public UsersInfo getUsersInfo() {
        return usersInfo;
    }

    public void setUsersInfo(UsersInfo usersInfo) {
        this.usersInfo = usersInfo;
    }

    @Override
    public void processMsg(String jsonString) throws RemoteException {
        String userId = JsonAnalizerServer.getUser(jsonString);
        // todo add message for messageholder module and check output

        List<String> targets = usersInfo.getOnlineGroupMates(userId);
        if(targets == null || targets.size() <= 0){
            return;
        }
        HandlersIntf handlersIntfImpl = RefsInProcessor.getHandlersIntf();
        handlersIntfImpl.sendMsgs(targets, jsonString);
    }

    @Override
    public void logoutUser(String userId) throws RemoteException {
        usersInfo.logoutUser(userId);

        List<String> targets = usersInfo.getOnlineGroupMates(userId);
        if(targets == null || targets.size() <= 0){
            return;
        }
        String jsonString = JsonBuilderServer.getRmContactsJson(userId);
        HandlersIntf handlersIntfImpl = RefsInProcessor.getHandlersIntf();
        handlersIntfImpl.sendMsgs(targets, jsonString);
    }
}

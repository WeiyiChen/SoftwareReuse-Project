package edu.tongji.reuse.teameleven.holder.impl;

import edu.tongji.reuse.teameleven.codependent.model.User;
import edu.tongji.reuse.teameleven.holder.ctrl.MissedMsgsCtrl;
import edu.tongji.reuse.teameleven.holder.stub.MissedMsgsIntf;

import java.rmi.RemoteException;
import java.util.List;
import java.util.Map;

/**
 * Created by daidongyang on 5/31/16.
 */
public class MissedMsgsIntfImpl implements MissedMsgsIntf {


    @Override
    public void setLogoutTime(String userId, Long logoutTime) throws RemoteException{
        MissedMsgsCtrl.getInstance().setLogoutTime(userId, logoutTime);
    }

    @Override
    public void addMissedMsg(String group, String jsonMsg, int groupOnlineCount) throws RemoteException {
        if(groupOnlineCount == -1){
            return;
        }
        MissedMsgsCtrl.getInstance().addMessage(group, jsonMsg, groupOnlineCount);
    }

    @Override
    public void setGroupSizes(Map<String, Integer> groupSizes) throws RemoteException {
        MissedMsgsCtrl.getInstance().setGroupSizes(groupSizes);
    }

    @Override
    public void updateGroupSizes(String group, Integer size) throws RemoteException {
        MissedMsgsCtrl.getInstance().updateGroupSizes(group, size);
    }
}

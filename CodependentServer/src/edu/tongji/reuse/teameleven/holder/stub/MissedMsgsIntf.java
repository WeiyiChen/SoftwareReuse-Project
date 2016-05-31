package edu.tongji.reuse.teameleven.holder.stub;

import edu.tongji.reuse.teameleven.codependent.model.User;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;
import java.util.Map;

/**
 * Created by daidongyang on 5/31/16.
 */
public interface MissedMsgsIntf extends Remote {
    void setLogoutTime(String userId, Long logoutTime) throws RemoteException;
    void addMissedMsg(String group, String jsonMsg, int groupOnlineCount) throws RemoteException;
    void setGroupSizes(Map<String, Integer> groupSizes) throws RemoteException;
    void updateGroupSizes(String group, Integer size) throws RemoteException;
}

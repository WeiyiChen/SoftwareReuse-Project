package edu.tongji.reuse.teameleven.handlers.stub;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

/**
 * Created by daidongyang on 5/29/16.
 */
public interface HandlersIntf extends Remote {
    void remove(String userId) throws RemoteException;
    void sendMsgs(List<String> targets, String jsonMsg) throws RemoteException;
    void relogin(String jsonMsg, String userId) throws RemoteException;
}

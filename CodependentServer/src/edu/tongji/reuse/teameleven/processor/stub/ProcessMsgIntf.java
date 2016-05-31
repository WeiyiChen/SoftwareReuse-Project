package edu.tongji.reuse.teameleven.processor.stub;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

/**
 * Created by daidongyang on 5/29/16.
 */
public interface ProcessMsgIntf extends Remote {
    void processMsg(String jsonString) throws RemoteException;
    void logoutUser(String user) throws RemoteException;
}

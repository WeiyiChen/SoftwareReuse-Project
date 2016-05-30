package edu.tongji.reuse.teameleven.processor.stub;


import edu.tongji.reuse.teameleven.codependent.model.User;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

/**
 * Created by daidongyang on 5/29/16.
 */
public interface LostMsgsIntf extends Remote {
    List<String> getLostMsgs(User u) throws RemoteException;
    List<String> getLostMsgs(User u, Long time) throws RemoteException;
}

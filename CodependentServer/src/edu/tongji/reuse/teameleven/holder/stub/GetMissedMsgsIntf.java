package edu.tongji.reuse.teameleven.holder.stub;

import edu.tongji.reuse.teameleven.codependent.model.User;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

/**
 * Created by daidongyang on 5/31/16.
 */
public interface GetMissedMsgsIntf extends Remote {
    List<String> getMissedMsgs(User user) throws RemoteException;
}

package edu.tongji.reuse.teameleven.processor.stub;

import edu.tongji.reuse.teameleven.codependent.model.User;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

/**
 * Created by daidongyang on 5/29/16.
 */
public interface ContactsCtrlIntf extends Remote{
    public List<String> getInitContacts(String u) throws RemoteException;
    public void addUser(User u) throws RemoteException;
}

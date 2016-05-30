package edu.tongji.reuse.teameleven.processor.stub;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

/**
 * Created by daidongyang on 5/29/16.
 */
public interface ProcessMsgIntf extends Remote {
    List<String> processMsg(String jsonString) throws RemoteException;
}

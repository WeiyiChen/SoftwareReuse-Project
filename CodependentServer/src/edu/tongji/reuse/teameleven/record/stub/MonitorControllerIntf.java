package edu.tongji.reuse.teameleven.record.stub;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * Created by daidongyang on 6/1/16.
 */
public interface MonitorControllerIntf extends Remote {
    void increaseReceivedNumber() throws RemoteException;

    void increaseIgnoredNumber() throws RemoteException;

    void increaseForwardedNumber() throws RemoteException;

    void increaseLogsucceedNumber() throws RemoteException;


    void increaseLogfailedNumber() throws RemoteException;

    void setSaveCycle(int _saveCycle) throws RemoteException;

    void startRecord() throws RemoteException;

    void stopRecord() throws RemoteException;

    void reset() throws RemoteException;

}

package edu.tongji.reuse.teameleven.record.stub;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * Created by daidongyang on 6/8/16.
 */
public interface Logger extends Remote {
    void log() throws RemoteException;
}

package edu.tongji.reuse.teameleven.handlers.transport;

import edu.tongji.reuse.teameleven.processor.stub.ProcessMsgIntf;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

/**
 * Created by daidongyang on 5/31/16.
 */
public class RefsInHandlers {
    private static ProcessMsgIntf processMsgIntfImpl;
    public static synchronized void createProcessMsgRef(){
        if(null == processMsgIntfImpl){
            try {
                Registry registry = LocateRegistry.getRegistry("127.0.0.1", 15820);
                processMsgIntfImpl = (ProcessMsgIntf)registry.lookup("processMsgIntf");
            } catch (RemoteException e) {
                e.printStackTrace();
            } catch (NotBoundException e) {
                e.printStackTrace();
            }
        }
    }

    public static ProcessMsgIntf getProcessMsgIntfRef(){
        if(null == processMsgIntfImpl){
            createProcessMsgRef();
        }
        return processMsgIntfImpl;
    }
}

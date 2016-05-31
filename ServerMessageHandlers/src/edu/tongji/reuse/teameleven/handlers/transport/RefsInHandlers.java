package edu.tongji.reuse.teameleven.handlers.transport;

import edu.tongji.reuse.teameleven.coserver.ctrl.ConfigCtrl;
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
                String processIp = ConfigCtrl.getConfig().getProcessorIP();
                int processRegPort = ConfigCtrl.getConfig().getProcessorRegistryPort();
                Registry registry = LocateRegistry.getRegistry(processIp, processRegPort);

                String processMsgRegKey = ConfigCtrl.getConfig().getProcessorProcessMsgRegKey();
                processMsgIntfImpl = (ProcessMsgIntf)registry.lookup(processMsgRegKey);
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

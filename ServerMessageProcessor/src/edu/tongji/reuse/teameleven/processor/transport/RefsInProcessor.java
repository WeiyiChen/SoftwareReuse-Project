package edu.tongji.reuse.teameleven.processor.transport;

import edu.tongji.reuse.teameleven.coserver.ctrl.ConfigCtrl;
import edu.tongji.reuse.teameleven.handlers.stub.HandlersIntf;
import edu.tongji.reuse.teameleven.holder.stub.MissedMsgsIntf;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

/**
 * Created by daidongyang on 5/31/16.
 */
public class RefsInProcessor {
    private static HandlersIntf handlersIntfImp;

    public synchronized static void createHandlersIntf(){
        if(handlersIntfImp == null){
            try {
                String handlersIP = ConfigCtrl.getConfig().getHandlersIP();
                int handlersRegPort = ConfigCtrl.getConfig().getHandlersRegistryPort();
                Registry registry = LocateRegistry.getRegistry(handlersIP, handlersRegPort);

                String handlersRegKey = ConfigCtrl.getConfig().getHandlersRegKey();
                handlersIntfImp = (HandlersIntf)registry.lookup(handlersRegKey);
            } catch (RemoteException e) {
                e.printStackTrace();
                throw new RuntimeException(e);
            } catch (NotBoundException e) {
                e.printStackTrace();
            }
        }

    }

    public static HandlersIntf getHandlersIntf(){
        if(null == handlersIntfImp){
            createHandlersIntf();
        }
        return handlersIntfImp;
    }

    private static MissedMsgsIntf missedMsgsRef;

    public synchronized static void createMissedMsgsRef(){
        if(null == missedMsgsRef){
            try{
                String holderIp = ConfigCtrl.getConfig().getHolderIP();
                int holderRegPort = ConfigCtrl.getConfig().getHolderRegistryPort();
                Registry registry = LocateRegistry.getRegistry(holderIp, holderRegPort);

                String missedMsgsRegKey = ConfigCtrl.getConfig().getHolderMissedMsgsRegKey();
                missedMsgsRef = (MissedMsgsIntf)registry.lookup(missedMsgsRegKey);
            } catch (RemoteException e) {
                e.printStackTrace();
            } catch (NotBoundException e) {
                e.printStackTrace();
            }
        }
    }
}

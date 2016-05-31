package edu.tongji.reuse.teameleven.processor.transport;

import edu.tongji.reuse.teameleven.handlers.stub.HandlersIntf;

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
                Registry registry = LocateRegistry.getRegistry("127.0.0.1", 15700);
                handlersIntfImp = (HandlersIntf)registry.lookup("handlers");
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
}

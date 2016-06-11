package edu.tongji.reuse.teameleven.handlers.ctrl;

import edu.tongji.reuse.teameleven.coserver.ctrl.ConfigCtrl;
import edu.tongji.reuse.teameleven.handlers.impl.HandlersIntfImpl;
import edu.tongji.reuse.teameleven.handlers.stub.HandlersIntf;
import edu.tongji.reuse.teameleven.handlers.transport.HandlersManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

/**
 * Created by daidongyang on 5/31/16.
 */
public class StubLoader {
    Logger logger = LoggerFactory.getLogger(StubLoader.class);
    public void load(HandlersManager handlersManager){
        logger.info("start load");
        HandlersIntfImpl handlersIntfImpl = new HandlersIntfImpl(handlersManager);
        try {
            int handlersRegPort = ConfigCtrl.getConfig().getHandlersRegistryPort();

            int handlersInvokePort = ConfigCtrl.getConfig().getHandlersInvokePort();
            HandlersIntf handlersIntf = (HandlersIntf)
                    UnicastRemoteObject.exportObject(handlersIntfImpl, handlersInvokePort);
            Registry registry = LocateRegistry.createRegistry(handlersRegPort);

            String handlersRegKey = ConfigCtrl.getConfig().getHandlersRegKey();
            registry.rebind(handlersRegKey, handlersIntf);
        } catch (RemoteException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }
}

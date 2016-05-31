package edu.tongji.reuse.teameleven.record.ctrl;

import edu.tongji.reuse.teameleven.coserver.ctrl.ConfigCtrl;
import edu.tongji.reuse.teameleven.record.stub.MonitorControllerIntf;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

/**
 * Created by daidongyang on 6/1/16.
 */
public class StubLoader {
    MonitorController monitorController;

    public MonitorController getMonitorController() {
        return monitorController;
    }

    public void load(){
        monitorController = new MonitorController();
        try {
            int monitorContorllerInvokePort = ConfigCtrl.getConfig().getLoggerMonitorCtrlInvokePort();
            MonitorControllerIntf monitorControllerIntf =
                    (MonitorControllerIntf) UnicastRemoteObject.exportObject(monitorController, monitorContorllerInvokePort);

            int loggerRegPort = ConfigCtrl.getConfig().getLoggerRegistryPort();
            Registry registry = LocateRegistry.createRegistry(loggerRegPort);

            String monitorControllerRegKey = ConfigCtrl.getConfig().getLoggerMonitorCtrlRegKey();
            registry.rebind(monitorControllerRegKey, monitorControllerIntf);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

}

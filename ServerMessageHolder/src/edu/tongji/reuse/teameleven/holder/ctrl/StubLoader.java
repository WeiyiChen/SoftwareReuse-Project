package edu.tongji.reuse.teameleven.holder.ctrl;

import edu.tongji.reuse.teameleven.coserver.ctrl.ConfigCtrl;
import edu.tongji.reuse.teameleven.holder.impl.GetMissedMsgsIntfImpl;
import edu.tongji.reuse.teameleven.holder.impl.MissedMsgsIntfImpl;
import edu.tongji.reuse.teameleven.holder.stub.GetMissedMsgsIntf;
import edu.tongji.reuse.teameleven.holder.stub.MissedMsgsIntf;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

/**
 * Created by daidongyang on 5/31/16.
 */
public class StubLoader {
    public void load(){
        GetMissedMsgsIntfImpl getMissedMsgsIntfImpl = new GetMissedMsgsIntfImpl();
        MissedMsgsIntfImpl missedMsgsIntfImpl = new MissedMsgsIntfImpl();
        try {

            int getMissedMsgsInvokePort = ConfigCtrl.getConfig().getHolderGetMissedMsgsInvokePort();
            GetMissedMsgsIntf getMissedMsgsIntf = (GetMissedMsgsIntf)
                    UnicastRemoteObject.exportObject(getMissedMsgsIntfImpl, getMissedMsgsInvokePort);

            int missedMsgsInvokePort = ConfigCtrl.getConfig().getHolderMissedMsgsInvokePort();
            MissedMsgsIntf missedMsgsIntf = (MissedMsgsIntf)
                    UnicastRemoteObject.exportObject(missedMsgsIntfImpl, missedMsgsInvokePort);

            int holderRegPort = ConfigCtrl.getConfig().getHolderRegistryPort();
            Registry registry = LocateRegistry.createRegistry(holderRegPort);

            String getMissedMsgsRegKey = ConfigCtrl.getConfig().getHolderGetMissedMsgsRegKey();
            registry.rebind(getMissedMsgsRegKey, getMissedMsgsIntf);

            String missedMsgsRegKey = ConfigCtrl.getConfig().getHolderMissedMsgsRegKey();
            registry.rebind(missedMsgsRegKey, missedMsgsIntf);
            System.out.println("Holder module stub load successfully!");
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }
}

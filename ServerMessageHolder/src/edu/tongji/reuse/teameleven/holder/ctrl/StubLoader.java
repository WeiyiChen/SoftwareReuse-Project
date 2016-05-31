package edu.tongji.reuse.teameleven.holder.ctrl;

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

        try {
            GetMissedMsgsIntfImpl getMissedMsgsIntfImpl = new GetMissedMsgsIntfImpl();
            GetMissedMsgsIntf getMissedMsgsIntf = (GetMissedMsgsIntf)
                    UnicastRemoteObject.exportObject(getMissedMsgsIntfImpl, 15841);
            MissedMsgsIntfImpl missedMsgsIntfImpl = new MissedMsgsIntfImpl();
            MissedMsgsIntf missedMsgsIntf = (MissedMsgsIntf)
                    UnicastRemoteObject.exportObject(missedMsgsIntfImpl, 15842);
            Registry registry = LocateRegistry.createRegistry(15840);
            registry.rebind("getMissedMsgsIntf", getMissedMsgsIntf);
            registry.rebind("missedMsgsIntf", missedMsgsIntf);
            System.out.println("Holder module stub load successfully!");
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }
}

package edu.tongji.reuse.teameleven.processor.ctrl;

import edu.tongji.reuse.teameleven.processor.impl.ContactsCtrlIntfImpl;
import edu.tongji.reuse.teameleven.processor.model.UsersInfo;
import edu.tongji.reuse.teameleven.processor.stub.ContactsCtrlIntf;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

/**
 * Created by daidongyang on 5/30/16.
 */
public class StubLoader {
    UsersInfo usersInfo;
    public StubLoader(UsersInfo usersInfo){
        this.usersInfo = usersInfo;
    }

    public void load(){
        // todo add other remote object
        ContactsCtrlIntfImpl contactsCtrlIntfImpl =
                new ContactsCtrlIntfImpl(usersInfo.getGroupOnLineUsers());
        try {
            // port : 15821
            ContactsCtrlIntf contactsCtrlIntf =
                    (ContactsCtrlIntf)UnicastRemoteObject.exportObject(contactsCtrlIntfImpl, 15821);

            // port for registry: 15820
            Registry registry = LocateRegistry.createRegistry(15820);
            registry.rebind("contactsCtrl", contactsCtrlIntf);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }
}

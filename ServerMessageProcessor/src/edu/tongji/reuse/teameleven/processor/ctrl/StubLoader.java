package edu.tongji.reuse.teameleven.processor.ctrl;

import edu.tongji.reuse.teameleven.coserver.ctrl.ConfigCtrl;
import edu.tongji.reuse.teameleven.processor.impl.ContactsCtrlIntfImpl;
import edu.tongji.reuse.teameleven.processor.impl.ProcessMsgIntfImpl;
import edu.tongji.reuse.teameleven.processor.model.UsersInfo;
import edu.tongji.reuse.teameleven.processor.stub.ContactsCtrlIntf;
import edu.tongji.reuse.teameleven.processor.stub.ProcessMsgIntf;

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
        ProcessMsgIntfImpl processMsgIntfImpl =
                new ProcessMsgIntfImpl();
        processMsgIntfImpl.setUsersInfo(usersInfo);
        try {
            int contactsCtrlInvokePort = ConfigCtrl.getConfig().getProcessorContactsCtrlInvokePort();
            ContactsCtrlIntf contactsCtrlIntf =
                    (ContactsCtrlIntf)UnicastRemoteObject.exportObject(contactsCtrlIntfImpl, contactsCtrlInvokePort);

            int processMsgInvokePort = ConfigCtrl.getConfig().getProcessorProcessMsgInvokePort();
            ProcessMsgIntf processMsgIntf =
                    (ProcessMsgIntf)UnicastRemoteObject.exportObject(processMsgIntfImpl, processMsgInvokePort);

            int processorRegPort = ConfigCtrl.getConfig().getProcessorRegistryPort();
            Registry registry = LocateRegistry.createRegistry(processorRegPort);

            String contactsCtrlRegKey = ConfigCtrl.getConfig().getProcessorContactsCtrlRegKey();
            registry.rebind(contactsCtrlRegKey, contactsCtrlIntf);

            String processMsgRegKey = ConfigCtrl.getConfig().getProcessorProcessMsgRegKey();
            registry.rebind(processMsgRegKey, processMsgIntf);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }
}

package edu.tongji.reuse.teameleven.processor.impl;

import edu.tongji.reuse.teameleven.coserver.ctrl.GroupController;
import edu.tongji.reuse.teameleven.coserver.util.JsonBuilderServer;
import edu.tongji.reuse.teameleven.processor.ctrl.LicenseCtrl;
import edu.tongji.reuse.teameleven.processor.stub.ContactsCtrlIntf;
import edu.tongji.reuse.teameleven.processor.transport.RefsInProcessor;

import java.rmi.RemoteException;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * Created by daidongyang on 5/30/16.
 */
public class ContactsCtrlIntfImpl implements ContactsCtrlIntf {

    private Map<String, List<String>> groupOnLineUsers;
    private LicenseCtrl licenseCtrl;
    public ContactsCtrlIntfImpl(Map<String, List<String>> groupOnLineUsers, LicenseCtrl licenseCtrl){
        this.groupOnLineUsers = groupOnLineUsers;
        this.licenseCtrl = licenseCtrl;
    }

    @Override
    public List<String> getInitContacts(String u) throws RemoteException{
        // todo delete the System.out code after debug
//        System.out.println(u);
        String group = GroupController.getInstance().getGroup(u);

        // todo delete the System.out code after debug
//        System.out.println(group);
//        System.out.println(groupOnLineUsers.get(group));
        return groupOnLineUsers.get(group);
    }

    @Override
    public void addUser(String u) throws RemoteException {
        String group = GroupController.getInstance().getGroup(u);
        List<String> contacts = groupOnLineUsers.get(group);
        if(contacts == null){
            contacts = new LinkedList<String>();
        }
        contacts.add(u);
        groupOnLineUsers.put(group, contacts);
        licenseCtrl.addUser(u);
        final String userId = u;
        final List<String> finalContacts = contacts;
        new Thread(()->{
            try {
                TimeUnit.MILLISECONDS.sleep(500);
                RefsInProcessor.getHandlersIntf().sendMsgs(finalContacts, JsonBuilderServer.getAddContactsJson(userId));
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (RemoteException e) {
                e.printStackTrace();
            }

        }).start();


        // todo delte the Sytem.out code after debug
//        System.out.println(groupOnLineUsers);

        // todo send message to every client in the same group
    }
}

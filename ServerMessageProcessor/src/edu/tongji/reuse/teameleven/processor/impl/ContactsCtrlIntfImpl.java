package edu.tongji.reuse.teameleven.processor.impl;

import edu.tongji.reuse.teameleven.codependent.model.User;
import edu.tongji.reuse.teameleven.processor.ctrl.GroupController;
import edu.tongji.reuse.teameleven.processor.stub.ContactsCtrlIntf;

import java.rmi.RemoteException;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * Created by daidongyang on 5/30/16.
 */
public class ContactsCtrlIntfImpl implements ContactsCtrlIntf {

    private Map<String, List<String>> groupOnLineUsers;

    public ContactsCtrlIntfImpl(Map<String, List<String>> groupOnLineUsers){
        this.groupOnLineUsers = groupOnLineUsers;
    }

    @Override
    public List<String> getInitContacts(User u) throws RemoteException{
        String group = GroupController.getInstance().getGroup(u.getUserId());
        return groupOnLineUsers.get(group);
    }

    @Override
    public void addUser(User u) throws RemoteException {
        String group = GroupController.getInstance().getGroup(u.getUserId());
        List<String> contacts = groupOnLineUsers.get(group);
        if(contacts == null){
            contacts = new LinkedList<String>();
        }
        contacts.add(u.getUserId());
        groupOnLineUsers.put("group", contacts);

        // todo send message to every client in the same group
    }
}

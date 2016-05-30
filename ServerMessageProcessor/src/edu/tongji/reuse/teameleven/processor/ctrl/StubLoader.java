package edu.tongji.reuse.teameleven.processor.ctrl;

import edu.tongji.reuse.teameleven.processor.impl.ContactsCtrlIntfImpl;
import edu.tongji.reuse.teameleven.processor.model.UsersInfo;

/**
 * Created by daidongyang on 5/30/16.
 */
public class StubLoader {
    UsersInfo usersInfo;
    public StubLoader(UsersInfo usersInfo){
        this.usersInfo = usersInfo;
    }

    public void load(){
        ContactsCtrlIntfImpl contactsCtrlIntf =
                new ContactsCtrlIntfImpl(usersInfo.getGroupOnLineUsers());
    }
}

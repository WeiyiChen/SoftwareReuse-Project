package edu.tongji.reuse.teameleven.processor.stub;

import edu.tongji.reuse.teameleven.codependent.model.User;

import java.util.List;

/**
 * Created by daidongyang on 5/29/16.
 */
public interface ContactsCtrlIntf {
    public List<String> getInitContacts(User u);
    public void addUser(User u);
}

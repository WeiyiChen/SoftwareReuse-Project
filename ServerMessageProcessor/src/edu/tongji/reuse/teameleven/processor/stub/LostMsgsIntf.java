package edu.tongji.reuse.teameleven.processor.stub;


import edu.tongji.reuse.teameleven.codependent.model.User;

import java.util.List;

/**
 * Created by daidongyang on 5/29/16.
 */
public interface LostMsgsIntf {
    List<String> getLostMsgs(User u);
    List<String> getLostMsgs(User u, Long time);
}

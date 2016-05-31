package edu.tongji.reuse.teameleven.holder.impl;

import edu.tongji.reuse.teameleven.codependent.model.User;
import edu.tongji.reuse.teameleven.holder.ctrl.MissedMsgsCtrl;
import edu.tongji.reuse.teameleven.holder.stub.GetMissedMsgsIntf;

import java.rmi.RemoteException;
import java.util.List;

/**
 * Created by daidongyang on 5/31/16.
 */
public class GetMissedMsgsIntfImpl implements GetMissedMsgsIntf {
    @Override
    public List<String> getMissedMsgs(User user) throws RemoteException {
        return MissedMsgsCtrl.getInstance().getMissedMsgs(user);
    }
}

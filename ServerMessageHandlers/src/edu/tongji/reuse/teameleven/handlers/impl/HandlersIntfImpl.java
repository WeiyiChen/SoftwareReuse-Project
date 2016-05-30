package edu.tongji.reuse.teameleven.handlers.impl;

import edu.tongji.reuse.teameleven.handlers.stub.HandlersIntf;
import edu.tongji.reuse.teameleven.handlers.transport.HandlersManager;

import java.rmi.RemoteException;
import java.util.List;

/**
 * Created by daidongyang on 5/31/16.
 */
public class HandlersIntfImpl implements HandlersIntf {
    private HandlersManager handlersManager;

    public HandlersIntfImpl(HandlersManager handlersManager) {
        this.handlersManager = handlersManager;
    }

    @Override
    public void remove(String userId) throws RemoteException {
        try{
            handlersManager.getMessageHandler(userId).safeQuit();
        }catch(NullPointerException e){
            e.printStackTrace();
        }
        handlersManager.removeUserHandler(userId);
    }

    @Override
    public void sendMsgs(List<String> targets, String jsonMsg) {
        handlersManager.sendMsgs(targets, jsonMsg);
    }
}

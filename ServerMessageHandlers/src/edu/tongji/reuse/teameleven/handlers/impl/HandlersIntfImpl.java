package edu.tongji.reuse.teameleven.handlers.impl;

import edu.tongji.reuse.teameleven.handlers.stub.HandlersIntf;
import edu.tongji.reuse.teameleven.handlers.transport.HandlersManager;
import edu.tongji.reuse.teameleven.handlers.transport.MessageHandler;

import java.rmi.RemoteException;
import java.util.List;
import java.util.concurrent.TimeUnit;

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
            MessageHandler messageHandler = handlersManager.getMessageHandler(userId);
            messageHandler.safeQuit();
        }catch(NullPointerException e){
            e.printStackTrace();
        }
        handlersManager.removeUserHandler(userId);
    }

    @Override
    public void sendMsgs(List<String> targets, String jsonMsg) throws RemoteException {
        System.out.println("targets : " + targets);
        System.out.println("msg : " + jsonMsg);
        handlersManager.sendMsgs(targets, jsonMsg);
    }

    @Override
    public void relogin(String jsonMsg, String userId) throws RemoteException {
        try{
            final MessageHandler messageHandler = handlersManager.getMessageHandler(userId);
            System.out.println("Send : " + jsonMsg);
            messageHandler.sendText(jsonMsg);
            new Thread(()->{
                try {
                    TimeUnit.MILLISECONDS.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                messageHandler.safeQuit();
            }).start();

        }catch(NullPointerException e){
            e.printStackTrace();
        }
        handlersManager.removeUserHandler(userId);
    }
}

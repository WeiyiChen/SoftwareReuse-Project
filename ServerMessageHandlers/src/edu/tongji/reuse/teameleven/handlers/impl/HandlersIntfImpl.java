package edu.tongji.reuse.teameleven.handlers.impl;

import edu.tongji.reuse.teameleven.handlers.stub.HandlersIntf;
import edu.tongji.reuse.teameleven.handlers.transport.HandlersManager;
import edu.tongji.reuse.teameleven.handlers.transport.MessageHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.rmi.RemoteException;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * Created by daidongyang on 5/31/16.
 */
public class HandlersIntfImpl implements HandlersIntf {
    private HandlersManager handlersManager;
    private Logger logger;

    public HandlersIntfImpl(HandlersManager handlersManager) {
        this.handlersManager = handlersManager;
        logger = LoggerFactory.getLogger(this.getClass());
    }

    @Override
    public void remove(String userId) throws RemoteException {
        logger.trace("remove " + userId);
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
        logger.trace("send message");
        logger.trace("targets : " + targets);
        logger.trace("msg : " + jsonMsg);
        handlersManager.sendMsgs(targets, jsonMsg);
    }

    @Override
    public void relogin(String jsonMsg, String userId) throws RemoteException {
        try{
            final MessageHandler messageHandler = handlersManager.getMessageHandler(userId);
            logger.trace("relogin");
            logger.trace("Send : " + jsonMsg);
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

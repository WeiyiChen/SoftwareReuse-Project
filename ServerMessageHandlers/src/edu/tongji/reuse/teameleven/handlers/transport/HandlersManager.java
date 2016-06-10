package edu.tongji.reuse.teameleven.handlers.transport;

import edu.tongji.reuse.teameleven.codependent.base.LoopThread;
import edu.tongji.reuse.teameleven.coserver.ctrl.ConfigCtrl;
import edu.tongji.reuse.teameleven.coserver.util.SocketWrapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by daidongyang on 5/30/16.
 */
public class HandlersManager extends LoopThread {
    private Map<String, MessageHandler> userHandlers = new HashMap<>();

    private ServerSocket serverSocket;

    private Logger logger = LoggerFactory.getLogger(HandlersManager.class);

    public void addUserHandler(MessageHandler messageHandler){
        logger.trace("add userhandler " + messageHandler);
        userHandlers.put(messageHandler.getUser(), messageHandler);
    }

    public void removeUserHandler(String user){
        logger.trace("remove user : " + user);
        userHandlers.remove(user);
    }

    public MessageHandler getMessageHandler(String user){
        logger.trace("get userHandler : " + user);
        return userHandlers.get(user);
    }

    public void sendMsgs(List<String> tagets, String jsonMsg){
        logger.info("handlesManager : " + userHandlers);
        for(String user : tagets){
            try{
                MessageHandler mhl = userHandlers.get(user);
                mhl.sendText(jsonMsg);
            }catch(RuntimeException e){
                e.printStackTrace(System.out);
            }
        }
    }

    @Override
    public void beforeLoop(){
//        userHandlers = new HashMap<>();
        try {
            int handlersListenPort = ConfigCtrl.getConfig().getHandlersListenPort();
            serverSocket = new ServerSocket(handlersListenPort);
        } catch (IOException e) {
            e.printStackTrace();
            safeQuit();
        }
    }

    @Override
    public void loop(){
        try {
            Socket socket = serverSocket.accept();
            SocketWrapper socketWrapper = new SocketWrapper(socket);
            MessageHandler messageHandler = new MessageHandler(this, socketWrapper);
            messageHandler.start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void safeQuit(){
        logger.info("safeQuit");
        for(MessageHandler mhl : userHandlers.values()){
            mhl.safeQuit();
        }
        super.safeQuit();
    }
}

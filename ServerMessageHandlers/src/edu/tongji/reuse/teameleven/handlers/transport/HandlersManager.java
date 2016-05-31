package edu.tongji.reuse.teameleven.handlers.transport;

import edu.tongji.reuse.teameleven.codependent.base.LoopThread;
import edu.tongji.reuse.teameleven.coserver.util.SocketWrapper;

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

    public void addUserHandler(MessageHandler messageHandler){
        userHandlers.put(messageHandler.getUser(), messageHandler);
    }

    public void removeUserHandler(String user){
        userHandlers.remove(user);
    }

    public MessageHandler getMessageHandler(String user){
        return userHandlers.get(user);
    }

    public void sendMsgs(List<String> tagets, String jsonMsg){
        System.out.println("handlesManager : " + userHandlers);
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
            serverSocket = new ServerSocket(15601);
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
        for(MessageHandler mhl : userHandlers.values()){
            mhl.safeQuit();
        }
        super.safeQuit();
    }
}

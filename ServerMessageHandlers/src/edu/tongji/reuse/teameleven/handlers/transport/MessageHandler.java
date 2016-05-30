package edu.tongji.reuse.teameleven.handlers.transport;

import edu.tongji.reuse.teameleven.coserver.util.SocketWrapper;

import java.io.BufferedReader;
import java.io.IOException;

/**
 * Created by daidongyang on 5/30/16.
 */
public class MessageHandler extends Thread{
    private HandlersManager handlersManager;
    private SocketWrapper socketWrapper;
    private String user;
    private boolean isAddedToManager = false;

    public MessageHandler(HandlersManager handlersManager, SocketWrapper socketWrapper) {
        super();
        this.handlersManager = handlersManager;
        this.socketWrapper = socketWrapper;
    }

    public String getUser() {
        return user;
    }

    @Override
    public void run(){
        BufferedReader bufferedReader = socketWrapper.getBufferedReader();
        while(!Thread.currentThread().isInterrupted()){
            try {
                String jsonMsg = bufferedReader.readLine();
                if(jsonMsg == null){
                    safeQuit();
                    return;
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void safeQuit() {
        handlersManager.removeUserHandler(user);
        this.interrupt();
        try{
            socketWrapper.quit();
        }catch(IOException e){
            e.printStackTrace();
        }
    }

    public void sendText(String jsonMsg) {
        socketWrapper.sendText(jsonMsg);
    }
}

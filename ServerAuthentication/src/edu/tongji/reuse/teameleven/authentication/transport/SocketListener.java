package edu.tongji.reuse.teameleven.authentication.transport;

import edu.tongji.reuse.teameleven.codependent.base.LoopThread;
import edu.tongji.reuse.teameleven.coserver.ctrl.ConfigCtrl;
import edu.tongji.reuse.teameleven.coserver.util.SocketWrapper;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by daidongyang on 5/30/16.
 */
public class SocketListener extends LoopThread {
    // todo maybe need to close the serversocket
    ServerSocket serverSocket;

    List<LoginMsgHandler> loginMsgHandlerList = new LinkedList<LoginMsgHandler>();

    public boolean removeHandler(LoginMsgHandler loginMsgHandler){
        return loginMsgHandlerList.remove(loginMsgHandler);
    }

    public void addHandler(LoginMsgHandler loginMsgHandler){
        loginMsgHandlerList.add(loginMsgHandler);
    }

    @Override
    public void beforeLoop(){
        // todo make the listen port configurable
        try {
            int authListenPort = ConfigCtrl.getConfig().getAuthListenPort();
            serverSocket = new ServerSocket(authListenPort);
            System.out.println(serverSocket);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void loop(){
        try {
            Socket socket = serverSocket.accept();
            SocketWrapper socketWrapper = new SocketWrapper(socket);
//            LoginMsgHandler loginMsgHandler = new LoginMsgHandler();
            LoginMsgHandler loginMsgHandler = new LoginMsgHandler(socketWrapper, this);
            addHandler(loginMsgHandler);
            loginMsgHandler.start();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void safeQuit(){
        for(LoginMsgHandler handler : loginMsgHandlerList){
            handler.safeClose();
        }
        super.safeQuit();
    }
}

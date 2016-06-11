package edu.tongji.reuse.teameleven.authentication.transport;

import edu.tongji.reuse.teameleven.codependent.base.LoopThread;
import edu.tongji.reuse.teameleven.coserver.ctrl.ConfigCtrl;
import edu.tongji.reuse.teameleven.coserver.util.SocketWrapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

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

    Logger logger = LoggerFactory.getLogger(SocketListener.class.getName());

    public boolean removeHandler(LoginMsgHandler loginMsgHandler){
        logger.trace("remove : " + loginMsgHandler);
        return loginMsgHandlerList.remove(loginMsgHandler);
    }

    public void addHandler(LoginMsgHandler loginMsgHandler){
        loginMsgHandlerList.add(loginMsgHandler);
    }

    @Override
    public void beforeLoop(){

        try {
            int authListenPort = ConfigCtrl.getConfig().getAuthListenPort();
            serverSocket = new ServerSocket(authListenPort);
//            System.out.println(serverSocket);
            logger.info(serverSocket.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void loop(){
        try {
            Socket socket = serverSocket.accept();
            logger.info("new socket : " + socket);
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
        logger.info("quit socketlistner");
        for(LoginMsgHandler handler : loginMsgHandlerList){
            handler.safeClose();
        }
        super.safeQuit();
    }
}

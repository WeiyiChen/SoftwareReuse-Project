package edu.tongji.reuse.teameleven.server.transport;


import edu.tongji.reuse.teameleven.base.SafeQuiteThread;
import edu.tongji.reuse.teameleven.server.ctrl.MessageController;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketTimeoutException;
import java.util.LinkedList;
import java.util.List;

public class ServerEntry extends SafeQuiteThread{

    private int port = 2345;

    private ServerSocket serverSocket;

    private MessageDispatcher messageDispatcher;

    public int getPort(){
        return port;
    }

    public void setPort(int port){
        this.port = port;
    }

    @Override
    public void run(){
        MessageController.startRecordThread();
        try{
            // init class member
            serverSocket = new ServerSocket(port);
            serverSocket.setSoTimeout(1000);
            messageDispatcher = new MessageDispatcher();

            while(!Thread.currentThread().isInterrupted()){
                try{
                    Socket socket = serverSocket.accept();
                    SocketWrapper socketWrapper = new SocketWrapper(socket);
                    System.out.println("Client connected to the server!"
                            + socket.getLocalAddress().toString());

                    // allocate a server for new socket
                    MessageHandler messageHandler = new MessageHandler(socketWrapper, messageDispatcher);
                    messageDispatcher.addMessageHandler(messageHandler);

                    messageHandler.start();
                }catch(SocketTimeoutException ste){
                    continue;
                }
            }
        }catch(IOException e){
            e.printStackTrace();
        }finally{
            if(serverSocket != null){
                try{
                    serverSocket.close();
                    MessageController.quit();
                }catch(IOException e){
                    e.printStackTrace();
                }
            }
            System.out.println("server socket stop running");
        }
    }

    @Override
    public void safeQuit() {
        messageDispatcher.quit();
        interrupt();
    }
}

package edu.tongji.reuse.teameleven.server.transport;

import edu.tongji.reuse.teameleven.base.JsonBuilderBase;
import edu.tongji.reuse.teameleven.base.SafeQuiteThread;
import edu.tongji.reuse.teameleven.server.ctrl.MessageController;
import edu.tongji.reuse.teameleven.server.json.JsonAnalizerServer;
import edu.tongji.reuse.teameleven.server.json.JsonBuilderServer;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;


public class MessageHandler extends SafeQuiteThread{

    private SocketWrapper socketWrapper;
    private MessageDispatcher messageDispatcher;
    private MessageController messageController;
    private List<String> msgsToStored;


    public MessageHandler(SocketWrapper socketWrapper, MessageDispatcher messageDispatcher){
        super();
        this.socketWrapper = socketWrapper;
        this.messageDispatcher = messageDispatcher;
        messageController = new MessageController();
        msgsToStored = new ArrayList<String>();
    }

    public MessageController getMessageController() {
        return messageController;
    }

    public void setMessageController(MessageController messageController) {
        this.messageController = messageController;
    }

    public SocketWrapper getSocketWrapper() {
        return socketWrapper;
    }

    public void setSocketWrapper(SocketWrapper socketWrapper) {
        this.socketWrapper = socketWrapper;
    }

    public MessageDispatcher getMessageDispatcher() {
        return messageDispatcher;
    }

    public void setMessageDispatcher(MessageDispatcher messageDispatcher) {
        this.messageDispatcher = messageDispatcher;
    }

    @Override
    public void run(){

//            String message = null;
            while(!Thread.currentThread().isInterrupted()){
                try {

                    String message = socketWrapper.getBufferedReader().readLine();
                    System.out.println(message);

                    // todo improve the store message method
                    msgsToStored.add(message);
                    saveServerMsg(msgsToStored);

                    System.out.println("receive: " + message);

                    // the client has been closed
                    if(message == null){
//                        break;
                        safeQuit();
                        continue;
                    }

                    if(message.equals("bye")){
                        safeQuit();
                        continue;
                    }

                    String result = messageController.dealWithMessage(message);

                    if(result.equals(JsonBuilderServer.getNeedReloginError())){
                        result = JsonBuilderServer.getReloginRequestJson();
                    }

                    if(result.equals(JsonBuilderServer.getMessageBusyError())){
                        continue;
                    }

                    System.out.println("Send : " + result);

                    processMessage(result);
                    messageController.addSendRecord();
                } catch (IOException e) {
                    e.printStackTrace();
                    break;
                }

            }

    }

    @Override
    public void safeQuit() {
        try{
            socketWrapper.quit();
        }catch(IOException e){
            e.printStackTrace();
        }
        messageDispatcher.removeMessageHandler(this);
        interrupt();
    }

    public void notified(String jsonString){
        socketWrapper.sendText(jsonString);
    }

    public void notified(String jsonString, String flag, MessageNotifyType notifyType){
        if(notifyType == MessageNotifyType.GROUP){
            if(flag.equals(messageController.getUser().getGroup())){
                socketWrapper.sendText(jsonString);
            }
        }else if(notifyType == MessageNotifyType.USER){
            if(flag.equals(messageController.getUser().getUserId())){
                socketWrapper.sendText(jsonString);
            }
        }
    }

    public void saveServerMsg(List<String> msgs){

    }

    public void processMessage(String message){
        // todo add process for get online users
        if(JsonAnalizerServer.getMessageType(message).equals(JsonBuilderBase.message)){
            messageDispatcher.notify(message,
                    messageController.getUser().getGroup(), MessageNotifyType.GROUP);
        }else if((JsonBuilderServer.getReloginRequestJson()).equals(message)){

            // add update user contact list command
            messageDispatcher.notify(JsonBuilderServer.getRmContactsJson(this.getUserId()),
                    this.getUserGroup(), MessageNotifyType.GROUP);

            // relogin
            setUserOnLine(false);
            socketWrapper.sendText(message);
        }else if((JsonBuilderBase.getLoginSucceedJson()).equals(message)){
            socketWrapper.sendText(message);
            List<String> contacts = messageDispatcher.getOnLineUsersWithGroup(this.getUserGroup());
            this.sendInitContacts(contacts);

            messageDispatcher.notify(JsonBuilderServer.getAddContactsJson(this.getUserId()),
                    this.getUserGroup(), MessageNotifyType.GROUP);

        }
        else{
            System.out.print("Listener's size : ");
            System.out.println(messageDispatcher.getMessageHandlers().size());
            socketWrapper.sendText(message);
        }
    }

    private void sendInitContacts(final List<String> contacts) {
        new Thread(new Runnable(){

            @Override
            public void run() {
                try {
                    TimeUnit.MILLISECONDS.sleep(1000);
                    String jsonMessage = JsonBuilderServer.getInitContactsJson(contacts);
                    socketWrapper.sendText(jsonMessage);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public void setUserOnLine(boolean isOnLine){
        messageController.getUser().setOnLine(isOnLine);
    }

    public boolean isUserOnLine(){
        try{
            return messageController.getUser().isOnLine();
        }catch(NullPointerException e){
            return false;
        }
    }

    public String getUserId(){
        return messageController.getUser().getUserId();
    }

    public String getUserGroup(){
        return messageController.getUser().getGroup();
    }


}

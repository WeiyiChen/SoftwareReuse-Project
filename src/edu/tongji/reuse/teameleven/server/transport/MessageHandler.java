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

                    // why ?
                    if(message == null){
//                        break;
                        safeQuit();
                    }

                    if(message.equals("bye")){
                        safeQuit();
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
        }else{
            socketWrapper.sendText(message);
        }
    }



}

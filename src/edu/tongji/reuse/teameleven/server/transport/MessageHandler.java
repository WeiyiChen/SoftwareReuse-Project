package edu.tongji.reuse.teameleven.server.transport;

import edu.tongji.reuse.teameleven.base.JsonBuilderBase;
import edu.tongji.reuse.teameleven.base.LoopThread;
import edu.tongji.reuse.teameleven.server.ctrl.MessageController;
import edu.tongji.reuse.teameleven.server.ctrl.MissedMsgsCtrl;
import edu.tongji.reuse.teameleven.server.json.JsonAnalizerServer;
import edu.tongji.reuse.teameleven.server.json.JsonBuilderServer;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;


public class MessageHandler extends LoopThread {

    private SocketWrapper socketWrapper;
    private MessageDispatcher messageDispatcher;
    private MessageController messageController;
    private List<String> msgsToStored;
    private boolean isIgnoreNotify = false;


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

    public boolean isIgnoreNotify() {
        return isIgnoreNotify;
    }

    public void setIgnoreNotify(boolean ignoreNotify) {
        isIgnoreNotify = ignoreNotify;
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
                        if(this.getUserGroup()!=null){
                            // remove the user in other's contact
                            messageDispatcher.notify(JsonBuilderServer.getRmContactsJson(this.getUserId()),
                                    this.getUserGroup(), MessageNotifyType.GROUP);
                            // subtract the
                            MissedMsgsCtrl.getInstance().subGroupOnLineCount(this.getUserGroup());
                            // mark the user logout time
                            MissedMsgsCtrl.getInstance().setLogoutTime(this.getUserId());
                        }

                        safeQuit();
                        continue;
                    }

                    if(message.equals("bye")){
                        if(this.getUserGroup()!=null){
                            // remove the user in other's contact
                            messageDispatcher.notify(JsonBuilderServer.getRmContactsJson(this.getUserId()),
                                    this.getUserGroup(), MessageNotifyType.GROUP);
                            // sub the online count
                            MissedMsgsCtrl.getInstance().subGroupOnLineCount(this.getUserGroup());
                            // mark the user's logout time
                            MissedMsgsCtrl.getInstance().setLogoutTime(this.getUserId());
                        }

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
//        MissedMsgsCtrl.getInstance().subGroupOnLineCount(this.getUserGroup());
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

    public void notified(String jsonString, String flag, MessageNotifyType notifyType, MessageHandler sender) {
        if(sender == this){
            return;
        }
        notified(jsonString, flag, notifyType);
    }

    public void saveServerMsg(List<String> msgs){

    }

    public void processMessage(String message){
        // todo add process for get online users
        if(JsonAnalizerServer.getMessageType(message).equals(JsonBuilderBase.message)){
            messageDispatcher.notify(message,
                    messageController.getUser().getGroup(), MessageNotifyType.GROUP);

            MissedMsgsCtrl.getInstance().addMessage(this.getUserGroup(), message);
        }else if((JsonBuilderServer.getReloginRequestJson()).equals(message)){

            // add update user contact list command
            messageDispatcher.notify(JsonBuilderServer.getRmContactsJson(this.getUserId()),
                    this.getUserGroup(), MessageNotifyType.GROUP,this);

            // relogin
            setUserOnLine(false);

            // mark the user's logout time
            MissedMsgsCtrl.getInstance().setLogoutTime(this.getUserId(), new Date().getTime());

            // sub the online user's count
            MissedMsgsCtrl.getInstance().subGroupOnLineCount(this.getUserGroup());

            socketWrapper.sendText(message);
        }else if((JsonBuilderBase.getLoginSucceedJson()).equals(message)){
            socketWrapper.sendText(message);

            // init contacts
            List<String> contacts = messageDispatcher.getOnLineUsersWithGroup(this.getUserGroup());
            this.sendInitContacts(contacts);

            // tell other client to add this user
            messageDispatcher.notify(JsonBuilderServer.getAddContactsJson(this.getUserId()),
                    this.getUserGroup(), MessageNotifyType.GROUP, this);

            // send missed messages
            MissedMsgsCtrl.getInstance().initMsgList(this.getUserGroup(), contacts.size());

            List<String> jsonMsgs =
                    MissedMsgsCtrl.getInstance().getMissedMsgsAndUpdateUser(messageController.getUser());

            System.out.println("missed messages : " + jsonMsgs);
            socketWrapper.sendTexts(jsonMsgs);



        }
        else{
            System.out.print("Listener's size : ");
            System.out.println(messageDispatcher.getMessageHandlers().size());
            socketWrapper.sendText(message);
        }
    }

    private void sendInitContacts(final List<String> contacts) {
        System.out.print("contacts size : ");
        System.out.println(contacts.size());
        if(contacts.size()<1){
            return;
        }
        new Thread(new Runnable(){

            @Override
            public void run() {
                try {
                    TimeUnit.MILLISECONDS.sleep(1000);
                    String jsonMessage = JsonBuilderServer.getInitContactsJson(contacts);
                    System.out.println(jsonMessage);
                    socketWrapper.sendText(jsonMessage);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
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

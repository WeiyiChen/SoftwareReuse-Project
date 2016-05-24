package edu.tongji.reuse.teameleven.server.transport;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;


public class MessageDispatcher {
    private List<MessageHandler> messageHandlers;
    private Map<String, List<String>> groupUsesrMap;


    public MessageDispatcher() {
        this.messageHandlers = new LinkedList<>();
    }

    public MessageDispatcher(List<MessageHandler> messageHandlers) {
        this.messageHandlers = messageHandlers;
    }

    public List<MessageHandler> getMessageHandlers() {
        return messageHandlers;
    }


    public void addMessageHandler(MessageHandler mh) {
        messageHandlers.add(mh);
    }

    public boolean removeMessageHandler(MessageHandler mh) {
        return messageHandlers.remove(mh);
    }

    public void quit(){
        for(MessageHandler mh : messageHandlers){
            mh.safeQuit();
        }
    }

    public void notify(String jsonString){
        for(MessageHandler messageHandler : messageHandlers){
            messageHandler.notified(jsonString);
        }
    }

    public void notify(String jsonString, String flag, MessageNotifyType notifyType){
        for(MessageHandler messageHandler : messageHandlers){
            messageHandler.notified(jsonString, flag, notifyType);
        }
    }

    public void notify(String jsonString, String flag, MessageNotifyType notifyType, MessageHandler sender){
        for(MessageHandler mhl : messageHandlers){
            mhl.notified(jsonString, flag, notifyType, sender);
        }
    }

    public List<String> getOnLineUsersWithGroup(String userGroup) {
        List<String> contacts = new ArrayList<String>();
        for(MessageHandler messageHandler : messageHandlers){
            if(messageHandler.isUserOnLine()){
                contacts.add(messageHandler.getUserId());
            }
        }
        return contacts;
    }
}



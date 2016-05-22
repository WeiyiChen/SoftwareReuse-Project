package edu.tongji.reuse.teameleven.server.transport;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by daidongyang on 5/22/16.
 */
public class MessageDispatcher {
    private List<MessageHandler> messageHandlers;

    public MessageDispatcher() {
        this.messageHandlers = new LinkedList<>();
    }

    public MessageDispatcher(List<MessageHandler> messageHandlers) {
        this.messageHandlers = messageHandlers;
    }

    public void addMessageHandler(MessageHandler mh){
        messageHandlers.add(mh);
    }

    public boolean removeMessageHandler(MessageHandler mh){
        return messageHandlers.remove(mh);
    }

}

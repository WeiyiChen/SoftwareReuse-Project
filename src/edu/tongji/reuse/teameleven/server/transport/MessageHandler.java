package edu.tongji.reuse.teameleven.server.transport;

import edu.tongji.reuse.teameleven.model.User;

/**
 * Created by daidongyang on 5/22/16.
 */
public class MessageHandler{
    private User user;
    private SocketWrapper socketWrapper;
    private MessageDispatcher messageDispatcher;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
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
}

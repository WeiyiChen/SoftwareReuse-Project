package edu.tongji.reuse.teameleven.server.model;

import edu.tongji.reuse.teameleven.base.JsonAnalizerBase;
import edu.tongji.reuse.teameleven.model.User;

/**
 * Created by daidongyang on 5/24/16.
 */
public class MessageListItem {
    private String jsonMsg;
    private int remainCount;
    private long posixTime;
    private User sender;

    public MessageListItem(){
        super();
    }
    public MessageListItem(String jsonMsg) {
        super();
        this.jsonMsg = jsonMsg;
        this.posixTime = JsonAnalizerBase.getTime(jsonMsg);
//        this.sender = JsonAnalizerBase.getUser(jsonMsg);
    }

    public String getJsonMsg() {
        return jsonMsg;
    }

    public void setJsonMsg(String jsonMsg) {
        this.jsonMsg = jsonMsg;
    }

    public int getRemainCount() {
        return remainCount;
    }

    public void setRemainCount(int remainCount) {
        this.remainCount = remainCount;
    }

    public long getPosixTime() {
        return posixTime;
    }

    public void setPosixTime(long posixTime) {
        this.posixTime = posixTime;
    }

    public User getSender() {
        return sender;
    }

    public void setSender(User sender) {
        this.sender = sender;
    }

    /**
     * sub remain count number
     * @return - if the message is still useful, return true, else return false
     */
    public boolean subRemainCount(){
        remainCount--;
        return remainCount<=0?false:true;
    }
}

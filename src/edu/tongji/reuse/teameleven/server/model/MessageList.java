package edu.tongji.reuse.teameleven.server.model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

public class MessageList extends LinkedList<MessageListItem> {

    private String group;
    private int groupSize;
    private int groupOnlineCount;

    public MessageList(String group, int groupSize) {
        super();
        this.group = group;
        this.groupSize = groupSize;
    }

    public MessageList(Collection<? extends MessageListItem> c, String group, int groupSize) {
        super(c);
        this.group = group;
        this.groupSize = groupSize;
    }

    public String getGroup() {
        return group;
    }

    public void setGroup(String group) {
        this.group = group;
    }

    public int getGroupSize() {
        return groupSize;
    }

    public void setGroupSize(int groupSize) {
        this.groupSize = groupSize;
    }

    public int getGroupOnlineCount() {
        return groupOnlineCount;
    }

    public void setGroupOnlineCount(int groupOnlineCount) {
        this.groupOnlineCount = groupOnlineCount;
    }

    public void addGroupOnLineCount(){
        groupOnlineCount++;
    }

    public void subGroupConLineCount(){
        groupOnlineCount--;
    }

    // get lost message according to logoutTime and login time
    public List<String> getMissedMsgs(long logoutTime, long loginTime){
        List<String> msgs = new ArrayList<>();
        System.out.println("message list: " + super.toString());
        System.out.println("longoutTime : " + logoutTime);
        System.out.println("loginTime : " + loginTime);
        for(MessageListItem messageListItem : new ArrayList<MessageListItem>(this)){
            if(logoutTime < messageListItem.getPosixTime() && loginTime >= messageListItem.getPosixTime()){
                msgs.add(messageListItem.getJsonMsg());
                if(!messageListItem.subRemainCount()){
                    super.remove(messageListItem);
//                    this.remove(messageListItem);
                }
            }
        }
        System.out.println("messages : " + msgs);
        return msgs;
    }

    public boolean addMessage(String jsonMsg){
        MessageListItem messageListItem = new MessageListItem(jsonMsg);
        return this.add(messageListItem);
    }

    @Override
    public boolean add(MessageListItem messageListItem){
        int remainCount = groupSize - groupOnlineCount;
        if(remainCount == 0){
            return true;
        }else if(remainCount < 0){
            return false;
        }
        messageListItem.setRemainCount(remainCount);
        return super.add(messageListItem);
    }



}

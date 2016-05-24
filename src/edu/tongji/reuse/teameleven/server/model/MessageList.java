package edu.tongji.reuse.teameleven.server.model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

public class MessageList extends LinkedList<MessageListItem> {

    private String group;
    private int groupSize;

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

    // get lost message according to logoutTime
    public List<String> getMissedMsg(long logoutTime){
        List<String> msgs = new ArrayList<>();
        for(MessageListItem messageListItem : new ArrayList<MessageListItem>(this)){
            if(logoutTime < messageListItem.getPosixTime()){
                msgs.add(messageListItem.getJsonMsg());
                if(!messageListItem.subRemainCount()){
                    this.remove(messageListItem);
                }
            }
        }
        return msgs;
    }

    @Override
    public boolean add(MessageListItem messageListItem){
        // if send message to itself
        messageListItem.setRemainCount(groupSize);
        // if ignore the message to client itself
        // messageListItem.setRemainCount(groupSize-1);
        return super.add(messageListItem);
    }

}

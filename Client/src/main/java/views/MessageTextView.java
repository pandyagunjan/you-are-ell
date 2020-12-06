package views;

import models.Message;

public class MessageTextView {

    Message msg;
    public MessageTextView(Message msgToDisplay) {
        this.msg=msgToDisplay;

    }

      @Override public String toString() {
         return String.format("From UserId:%s To UserID:%s Sequence:%s Message:%s TimeStamp:%s",msg.getFromid(),msg.getToid(),msg.getSequence(),msg.getMessage(),msg.getTimestamp());
    } 
}
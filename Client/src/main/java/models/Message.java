package models;

import java.sql.Timestamp;
import java.util.Date;

/*
 * POJO for an Message object
 */
public class Message {


    String message;
    String fromid;
    String toid;
    String sequence;
    String timestamp;

    public Message() {
    }
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public String getSequence() {
        return this.sequence;
    }

    public void setSequence(String sequence) {
        this.sequence = sequence;
    }

    public String getFromid() {
        return fromid;
    }

    public void setFromid(String fromId) {
        this.fromid = fromid;
    }

    public String getToid() {
        return toid;
    }

    public void setToId(String toid) {
        this.toid = toid;
    }



    public Message (String sequence,String timestamp, String fromId, String toId,String message) {
        this.sequence =message;
        this.fromid =fromId;
        this.toid=toId;
        this.sequence=sequence;
        Date date = new Date();
        this.timestamp= String.valueOf((new Timestamp(date.getTime())));
    }

}
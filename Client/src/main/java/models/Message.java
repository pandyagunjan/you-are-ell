package models;

/* 
 * POJO for an Message object
 */
public class Message {
    String message;
    String fromId;
    String toId;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getFromId() {
        return fromId;
    }

    public void setFromId(String fromId) {
        this.fromId = fromId;
    }

    public String getToId() {
        return toId;
    }

    public void setToId(String toId) {
        this.toId = toId;
    }



    public Message (String message, String fromId, String toId) {
        this.message=message;
        this.fromId =fromId;
        this.toId=toId;
    }

}
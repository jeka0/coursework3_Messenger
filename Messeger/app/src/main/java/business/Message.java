package business;
import java.io.Serializable;
import java.util.Date;

public class Message implements Serializable {
    private String userName;
    private String textMessage;
    private long messageTime;
    public Message(){}
    public Message(String userName,String textMessage)
    {
        this.userName=userName;
        this.textMessage=textMessage;
        this.messageTime = new Date().getTime();
    }
    public void setUserName(String userName) { this.userName = userName; }
    public void setMessageTime(long messageTime) { this.messageTime = messageTime; }
    public void setTextMessage(String textMessage) { this.textMessage = textMessage; }
    
    public String getUserName() { return userName; }
    public String getTextMessage() { return textMessage; }
    public long getMessageTime() { return messageTime; }
}

package business;
import java.io.Serializable;
import java.util.Date;

public class Message implements Serializable {
    private String userName;
    private String chatName;
    private String textMessage;
    private long messageTime;
    private byte[] image;
    private String imagePath;
    public Message(){}
    public Message(String userName,String chatName,String textMessage)
    {
        this.chatName = chatName;
        this.userName=userName;
        this.textMessage=textMessage;
        this.messageTime = new Date().getTime();
    }
    public void setUserName(String userName) { this.userName = userName; }
    public void setMessageTime(long messageTime) { this.messageTime = messageTime; }
    public void setTextMessage(String textMessage) { this.textMessage = textMessage; }

    public void setChatName(String chatName) {
        this.chatName = chatName;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }

    public byte[] getImage() {
        return image;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    public String getImagePath() {
        return imagePath;
    }

    public String getChatName() {
        return chatName;
    }

    public String getUserName() { return userName; }
    public String getTextMessage() { return textMessage; }
    public long getMessageTime() { return messageTime; }
}


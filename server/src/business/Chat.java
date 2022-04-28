package business;

import java.io.Serializable;
import java.util.ArrayList;

public class Chat implements Serializable {
    private String Name;
    private ArrayList<Message> messages = new ArrayList<>();
    private ArrayList<User> users = new ArrayList<>();
    public Chat(){}
    public Chat(String Name)
    {
        this.Name=Name;
    }
    public String getName() {
        return Name;
    }
    public void setName(String name) {
        Name = name;
    }
    public void addMessage(Message message)
    {
        messages.add(message);
    }
    public ArrayList<Message> getMessages()
    {
        return messages;
    }
    public ArrayList<User> getUsers() {
        return users;
    }
    public void addUser(User user)
    {
        users.add(user);
    }
}

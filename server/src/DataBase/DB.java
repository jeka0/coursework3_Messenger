package DataBase;

import business.Message;
import business.User;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

public class DB {
    private String usersPath ="database\\users.json";
    private File userChatsDir = new File("database\\userChats"), ChatsDir = new File("database\\Chats"), usersFile = new File(usersPath);
    private static DB db;
    private DB(){
        try {
        userChatsDir.mkdirs();
        ChatsDir.mkdirs();
        usersFile.createNewFile();
        }catch (IOException e){System.out.println(e.getMessage());}
    }
    public static DB getInstance(){
        if(db==null)return new DB();
        else return db;
    }
    public boolean CheckUserPassword(User user)
    {
        if(usersFile.exists()) {
            User[] users = getUsers();
            for (User nowUser : users)
                if (nowUser.getName().equals(user.getName()))
                    if (nowUser.getPassword().equals(user.getPassword())) return true;
                    else return false;
        }
        return false;
    }
    public boolean UserRegistration(User user)
    {
        if(usersFile.exists()) {
            User[] users = getUsers();
            for (User nowUser : users)
                if (nowUser.getName().equals(user.getName()))
                    return false;
        }
        return true;
    }
    public void CreateUser(User newUser)
    {
        try {
        JsonWork json = new JsonWork(usersPath);
        File file = new File("database\\userChats\\" + newUser.getName() + ".json");
        ArrayList<User> users = new ArrayList<>(Arrays.asList(getUsers()));

        users.add(newUser);
        file.createNewFile();
        json.Write(users);
        }catch (IOException e){System.out.println(e.getMessage());}
    }
    private User[] getUsers()
    {
        JsonWork json = new JsonWork(usersPath);
        User[] users =json.Read(User[].class);
        if(users!=null)return users;else return new User[0];
    }
    public void addMessage(String path, Message message)
    {
        JsonWork json = new JsonWork(path);
        ArrayList<Message> messages = new ArrayList<>(Arrays.asList(getMessages(path)));
        messages.add(message);
        json.Write(messages);
    }
    public Message[] getMessages(String path)
    {
        JsonWork json = new JsonWork(path);
        Message[] messages = json.Read(Message[].class);
        if(messages!=null)return messages;else return new Message[0];
    }
}

package DataBase;

import business.Chat;
import business.Message;
import business.User;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

public class DB implements IDB{
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
        CreateUser(user);
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
    public void addMessage(Message message)
    {
        String path = "database\\Chats\\"+message.getChatName()+".json";
        JsonWork json = new JsonWork(path);
        Chat chat = getChat(message.getChatName());
        /*ArrayList<Message> messages = chat.getMessages();
        messages.add(message);*/
        chat.addMessage(message);
        json.Write(chat);
    }
    public void addChat(Chat chat)
    {
        try {
            String chatPath = "database\\Chats\\" + chat.getName() + ".json";
            JsonWork json = new JsonWork(chatPath);
            File file = new File(chatPath);
            file.createNewFile();
            json.Write(chat);
            for (String user : chat.getUsers()) {
                String userChats = "database\\userChats\\" + user  + ".json";
                File userFile = new File(userChats);
                if(!userFile.exists())userFile.createNewFile();
                JsonWork userChatsJson = new JsonWork(userChats);
                ArrayList<String> chats = new ArrayList<>(Arrays.asList(getChatsNames(user)));
                chats.add(chat.getName());
                userChatsJson.Write(chats);
            }
        }catch (IOException e){System.out.println(e.getMessage());}
    }
    public Chat[] getChats(String user)
    {
        ArrayList<String> chatsNames = new ArrayList<>(Arrays.asList(getChatsNames(user)));
        ArrayList<Chat> chats = new ArrayList<>();
        for(String name : chatsNames)
        {
            try {
                String chatPath = "database\\Chats\\" + name + ".json";
                File userFile = new File(chatPath);
                if (!userFile.exists()) userFile.createNewFile();
                JsonWork json = new JsonWork(chatPath);
                chats.add(json.Read(Chat.class));
            }catch (IOException e){System.out.println(e.getMessage());}
        }
        return chats.toArray(Chat[]::new);
    }
    public String[] getChatsNames(String user)
    {
        String userChats = "database\\userChats\\" + user + ".json";
        JsonWork json = new JsonWork(userChats);
        String[] strs = json.Read(String[].class);
        if(strs!=null)return strs;else return new String[0];
    }
    private Chat getChat(String chatName)
    {
        String path = "database\\Chats\\"+chatName+".json";
        JsonWork json = new JsonWork(path);
       Chat chat = json.Read(Chat.class);
       if(chat!=null)return chat;else return new Chat();
    }
    public Message[] getMessages(String chat)
    {
        Message[] messages = getChat(chat).getMessages().toArray(new Message[0]);
        try {
            FileInputStream inF = new FileInputStream("database\\1.jpg");
            messages[0].setImage(inF.readAllBytes());
        }catch (FileNotFoundException e){}catch (IOException e){}
        if(messages!=null)return messages;else return new Message[0];
    }
}

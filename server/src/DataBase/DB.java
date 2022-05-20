package DataBase;

import business.Chat;
import business.Message;
import business.MyFIle;
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
    public User[] GetUsersWithoutPasswords()
    {
        User[] users = getUsers();
        for(int i=0;i<users.length;i++) users[i].setPassword("");
        return users;
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
        byte[] image = message.getImage();
        MyFIle myFIle = message.getFile();
        if(myFIle!=null&&myFIle.getData()!=null)
        {
            String filePath = myFIle.getData().hashCode()+"." + myFIle.getExtension();
            WorkWithFile work = new WorkWithFile(filePath);
            work.WriteFile(myFIle.getData());
            myFIle.setPath(filePath);
            myFIle.setData(null);
        }
        if(image!=null)
        {
            String imagePath = image.hashCode()+".jpg";
            WorkWithFile work = new WorkWithFile(imagePath);
            work.WriteFile(image);
            message.setImagePath(imagePath);
            message.setImage(null);
        }
        chat.addMessage(message);
        json.Write(chat);
    }
    public boolean UpdateUser(User newUser)
    {
        ArrayList<User> users = new ArrayList<>(Arrays.asList(getUsers()));
        for(User user:users)
        {
            if(user.getName().equals(newUser.getName()))
            {
                if(!user.getPassword().equals(newUser.getPassword()))return false;
                user.setPassword(newUser.getNewPassword());
                break;
            }
        }
        JsonWork json = new JsonWork(usersPath);
        json.Write(users);
        return true;
    }
    public void AddChatToUser(Chat chat)
    {
        try {
            String user = chat.getUsers().get(0);
            String userChats = "database\\userChats\\" + user  + ".json";
            File userFile = new File(userChats);
            if(!userFile.exists())userFile.createNewFile();
            JsonWork userChatsJson = new JsonWork(userChats);
            ArrayList<String> chats = new ArrayList<>(Arrays.asList(getChatsNames(user)));
            if(!chats.contains(chat.getName())) {
                chats.add(chat.getName());
                userChatsJson.Write(chats);
            }
            JsonWork chatJson = new JsonWork("database\\Chats\\"+chat.getName()+".json");
            Chat nowChat = getChat(chat.getName());
            if(!nowChat.getUsers().contains(user)) {
                nowChat.addUser(user);
                chatJson.Write(nowChat);
            }
        }catch (IOException e){System.out.println(e.getMessage());}
    }

    public boolean addChat(Chat chat)
    {
        try {
            String chatPath = "database\\Chats\\" + chat.getName() + ".json";
            JsonWork json = new JsonWork(chatPath);
            File file = new File(chatPath);
            if(!file.createNewFile())return false;
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
            return true;
        }catch (IOException e){System.out.println(e.getMessage());}
        return false;
    }
    public Chat[] getChats(String[] chatsStrs)
    {
        ArrayList<String> chatsNames = new ArrayList<>(Arrays.asList(chatsStrs));
        ArrayList<Chat> chats = new ArrayList<>();
        for(String name : chatsNames)
        {
            try {
                String chatPath = "database\\Chats\\" + name + ".json";
                File userFile = new File(chatPath);
                if (!userFile.exists()) userFile.createNewFile();
                JsonWork json = new JsonWork(chatPath);
                Chat chat = json.Read(Chat.class);
                chat.getMessages().clear();
                chats.add(chat);
            }catch (IOException e){System.out.println(e.getMessage());}
        }
        return chats.toArray(Chat[]::new);
    }
    public ArrayList<Chat> getSelectedChats()
    {
        String chatPath = "database\\Chats";
        File dir = new File(chatPath);
        if (!dir.exists()) dir.mkdirs();
        String[] chatsNames = dir.list();
        for(int i=0;i<chatsNames.length;i++)chatsNames[i]=chatsNames[i].split("\\.")[0];
        return new ArrayList<>(Arrays.asList(getChats(chatsNames)));
    }
    public String[] getChatsNames(String user)
    {
        String userChats = "database\\userChats\\" + user + ".json";
        JsonWork json = new JsonWork(userChats);
        String[] strs = json.Read(String[].class);
        if(strs!=null)return strs;else return new String[0];
    }
    public Chat getChat(String chatName)
    {
        String path = "database\\Chats\\"+chatName+".json";
        JsonWork json = new JsonWork(path);
       Chat chat = json.Read(Chat.class);
       if(chat!=null)return chat;else return new Chat();
    }
    public Message[] getMessages(String chat)
    {
        Message[] messages = getChat(chat).getMessages().toArray(new Message[0]);
        for(Message message:messages)
        {
            String path = message.getImagePath();
            if(path!=null)
            {
                WorkWithFile work = new WorkWithFile(path);
                message.setImage(work.ReadFile());
                message.setImagePath(null);
            }
            MyFIle myFIle = message.getFile();
            if(myFIle!=null&&myFIle.getPath()!=null)
            {
                WorkWithFile work = new WorkWithFile(myFIle.getPath());
                myFIle.setData(work.ReadFile());
                myFIle.setPath(null);
            }
        }
        if(messages!=null)return messages;else return new Message[0];
    }

}

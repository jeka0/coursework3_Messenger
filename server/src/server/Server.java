package server;

import business.Chat;
import business.User;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
public class Server implements IServer{
    private ExecutorService executeIt;
    private ConcurrentHashMap<String,IMonoThreadClient> UsersMap = new ConcurrentHashMap<>();
    private ConcurrentHashMap<String,ArrayList<String>> ChatsMap = new ConcurrentHashMap<>();
    private int port;
    public Server(int port,int countThreads){
        this.port=port;
        executeIt = Executors.newFixedThreadPool(countThreads);
    }
    public void Start()
    {
        try (ServerSocket server= new ServerSocket(port)){
            while(!server.isClosed()) {
                Socket client = server.accept();
                IMonoThreadClient threadClient = new MonoThreadClient(client,this);
                executeIt.execute(threadClient);
            }
            executeIt.shutdown();
        }catch (IOException e){System.out.println(e.getMessage());}
    }
    public void UpdateChat(Chat chat)
    {
        ArrayList<String> users = ChatsMap.get(chat.getName());
        if(users!=null) {
            for (String user : users) {
                IMonoThreadClient client = UsersMap.get(user);
                if (client != null) {
                    client.setNameChat(chat.getName());
                    client.Notify("UpdatePosts");
                }
            }
        }
    }
    public void UpdateChatList(ArrayList<String> users)
    {
        for(String user:users)
        {
            if(UsersMap.containsKey(user)) {
                IMonoThreadClient client = UsersMap.get(user);
                if (client != null) client.Notify("UpdateChats");
            }
        }
    }
    public boolean isConnect(User user)
    {
        return UsersMap.containsKey(user.getName());
    }
    public void AddUser(User user,  String[] chats, IMonoThreadClient client)
    {
        if(!UsersMap.containsKey(user.getName()))
        {
            UsersMap.put(user.getName(),client);
            for(String chat:chats) AddToChat(user,chat);
        }
    }
    public void AddChat(Chat chat)
    {
        if(!ChatsMap.containsKey(chat.getName()))
        {
            ArrayList<String> users = new ArrayList();
            for(String str :chat.getUsers())if(UsersMap.containsKey(str))users.add(str);
            if(!users.isEmpty())ChatsMap.put(chat.getName(),users);
        }
    }

    public void AddToChat(User user, String chat)
    {
        ArrayList<String> users = ChatsMap.computeIfAbsent(chat, k -> new ArrayList<>());
        users.add(user.getName());
    }
    public void RemoveFromChat(User user, String chat)
    {
        ArrayList<String> users = ChatsMap.computeIfAbsent(chat, k -> new ArrayList<>());
        users.remove(user.getName());
        if(users.isEmpty()) ChatsMap.remove(chat);
    }
    public void RemoveChat(String chatName)
    {
        ArrayList<String> users = ChatsMap.computeIfAbsent(chatName, k -> new ArrayList<>());
        for(String user: users) {
          IMonoThreadClient client =  UsersMap.get(user);
          if(client!=null)client.DeleteChat(chatName);
        }
        ChatsMap.remove(chatName);
    }

    public void RemoveUser(User user,  String[] chats)
    {
        UsersMap.remove(user.getName());
        for(String chat:chats) RemoveFromChat(user,chat);
    }
}

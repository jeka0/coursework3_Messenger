package server;

import business.Chat;
import business.User;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.io.*;
import java.net.*;
import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
public class Server implements IServer{
    private ExecutorService executeIt;
    private ArrayList<IMonoThreadClient> clients = new ArrayList<>();
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
                clients.add(threadClient);
                executeIt.execute(threadClient);
            }
            executeIt.shutdown();
        }catch (IOException e){System.out.println(e.getMessage());}
    }
    public void UpdateChat(Chat chat)
    {
        ArrayList<String> users = chat.getUsers();
        for(IMonoThreadClient client:clients) {
            if(client.getUser()!=null && users.contains(client.getUser().getName())) {
                client.setNameChat(chat.getName());
                client.Notify("UpdatePosts");
            }
        }
    }
    public void UpdateChatList(ArrayList<String> users)
    {
        for(IMonoThreadClient client:clients) {
            if(client.getUser()!=null && users.contains(client.getUser().getName())) {
                client.Notify("UpdateChats");
            }
        }
    }
    public int UserCount(User user)
    {
        int count =0;
        for(IMonoThreadClient client:clients) {
            if(client.getUser()!=null && client.getUser().getName().equals(user.getName()))count ++;
        }
        return count;
    }
    public void removeThread(IMonoThreadClient threadClient)
    {
        clients.remove(threadClient);
    }
}

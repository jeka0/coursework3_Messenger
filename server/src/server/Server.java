package server;

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
    public void UpdateFlags()
    {
        for(IMonoThreadClient client:clients)client.setUpdateMessagesFlag(true);
    }
    public void removeThread(IMonoThreadClient threadClient)
    {
        clients.remove(threadClient);
    }
}

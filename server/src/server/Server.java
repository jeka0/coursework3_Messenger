package server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.io.*;
import java.net.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
public class Server {
    private ExecutorService executeIt;
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

                executeIt.execute(new MonoThreadClient(client));
            }
            executeIt.shutdown();
        }catch (IOException e){System.out.println(e.getMessage());}
    }
}

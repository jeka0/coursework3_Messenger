package com.company;
import java.io.*;
import java.net.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
public class Main {
    static ExecutorService executeIt = Executors.newFixedThreadPool(2);
    public static void main(String[] args) {
        try (ServerSocket server= new ServerSocket(8090)){
            while(!server.isClosed()) {
                Socket client = server.accept();

                executeIt.execute(new MonoThreadClient(client));
            }
            executeIt.shutdown();
        }catch (IOException e){System.out.println(e.getMessage());}
    }
}

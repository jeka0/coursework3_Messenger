package Net;

import android.os.AsyncTask;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.InetSocketAddress;
import java.net.Socket;

public class Client {
    private Socket socket;
    private String ip;
    public Client(String ip)
    {
        this.ip = ip;
    }
   /* @Override
    public void run(){
       Connect();
       pushText();
    }*/
    public void Connect()
    {
        try {
        socket = new Socket(ip, 8090);
        }catch(IOException e){System.out.println(e.getMessage());}
    }
    public void pushText(String str)
    {
        try (PrintWriter out = new PrintWriter(socket.getOutputStream())) {
            out.println(str);
        }catch(IOException e){System.out.println(e.getMessage());}
    }
}

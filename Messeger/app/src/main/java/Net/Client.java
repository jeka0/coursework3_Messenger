package Net;

import android.os.AsyncTask;

import java.io.DataInputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.DataOutputStream;
import java.net.InetSocketAddress;
import java.net.Socket;

public class Client {
    private Socket socket;
    private DataOutputStream out;
    private DataInputStream in;
    private String ip;
    public Client(String ip)
    {
        this.ip = ip;
    }
    public void Connect()
    {
        try {
        socket = new Socket(ip, 8090);
            out = new  DataOutputStream(socket.getOutputStream());
            in = new DataInputStream(socket.getInputStream());
        }catch(IOException e){System.out.println(e.getMessage());}
    }
    public void pushText(String str)
    {
        try {
            out.writeUTF(str);
            out.flush();
        }catch(IOException e){System.out.println(e.getMessage());}
    }
}

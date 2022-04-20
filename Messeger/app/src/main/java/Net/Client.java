package Net;

import java.io.DataInputStream;
import java.io.IOException;
import java.io.DataOutputStream;
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
    public void pushMessage(String str) throws IOException
    {
        out.writeUTF(str);
        out.flush();
    }
    public String receiveMessage() throws IOException
    {
        return in.readUTF();
    }
    public boolean isOutputShutdown()
    {
        return socket.isOutputShutdown();
    }
}

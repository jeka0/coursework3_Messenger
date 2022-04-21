package Net;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class Client {
    private Socket socket;
    private ObjectOutputStream out;
    private ObjectInputStream in;
    private String ip;
    public Client(String ip)
    {
        this.ip = ip;
    }
    public void Connect()
    {
        try {
            socket = new Socket(ip, 8090);
            out = new  ObjectOutputStream(socket.getOutputStream());
            in = new ObjectInputStream(socket.getInputStream());
        }catch(IOException e){System.out.println(e.getMessage());}
    }
    public void pushObject(Object obj) throws IOException
    {
        out.writeObject(obj);
        out.flush();
    }
    public Object receiveObject() throws IOException, ClassNotFoundException
    {
        return in.readObject();
    }
    public boolean isOutputShutdown()
    {
        return socket.isOutputShutdown();
    }
}

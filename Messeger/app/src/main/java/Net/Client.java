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
    private boolean connected = false;
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
            connected = true;
        }catch(IOException e){System.out.println(e.getMessage());}
    }
    public void pushObject(Object obj) throws IOException
    {
        if(out!=null) {
            out.writeObject(obj);
            out.flush();
        }
    }
    public Object receiveObject() throws IOException, ClassNotFoundException
    {
        return in.readObject();
    }
    public boolean isOutputShutdown()
    {
        return socket.isOutputShutdown();
    }
    public void setConnection(boolean flag){connected=flag;}
    public boolean isConnected()
    {
        return connected;
    }
    public void Close() {
        try {
            if(socket!=null)
            {socket.close();
            socket=null;}
            setConnection(false);
        }catch(IOException e){System.out.println(e.getMessage());}
    }
}

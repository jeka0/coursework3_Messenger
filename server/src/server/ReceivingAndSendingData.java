package server;

import java.io.*;
import java.net.Socket;

public class ReceivingAndSendingData {
    private Socket client;
    private ObjectOutputStream out;
    private ObjectInputStream in;
    public ReceivingAndSendingData(Socket client)
    {
        try {
            this.client = client;
            out = new ObjectOutputStream(client.getOutputStream());
            in = new ObjectInputStream(client.getInputStream());
        }catch (IOException e){System.out.println(e.getMessage());}
    }
    public synchronized void pushObject(Object obj) throws IOException
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
    public void ClosingStreams() throws IOException
    {
        if(out!=null) {
            out.close();
            in.close();
        }
    }
}

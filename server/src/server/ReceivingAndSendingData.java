package server;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class ReceivingAndSendingData {
    private Socket client;
    private DataOutputStream out;
    private DataInputStream in;
    public ReceivingAndSendingData(Socket client)
    {
        try {
            this.client = client;
            out = new DataOutputStream(client.getOutputStream());
            in = new DataInputStream(client.getInputStream());
        }catch (IOException e){System.out.println(e.getMessage());}
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
    public void ClosingStreams() throws IOException
    {
        out.close();
        in.close();
    }
}

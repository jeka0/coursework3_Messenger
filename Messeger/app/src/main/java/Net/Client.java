package Net;

import com.example.messeger.MainActivity;

import java.io.DataInputStream;
import java.io.IOException;
import java.io.DataOutputStream;
import java.net.Socket;

import business.Message;
import business.Serializer;

public class Client {
    public MainActivity activity;
    private Socket socket;
    private DataOutputStream out;
    private DataInputStream in;
    private String ip;
    private Message[] messages = new Message[0];
    private Serializer serializer = new Serializer();
    private Thread Event;
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
            /*new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        messages = serializer.deserialize(in.readUTF(), Message[].class);
                        activity.runOnUiThread(new Runnable() {
                            @Override
                            public final void run(){ activity.loadMessages();}});
                    }catch (IOException e){System.out.println(e.getMessage());}
                }
            }).start();*/

        }catch(IOException e){System.out.println(e.getMessage());}
    }
    public void pushMessage(String str)
    {
        try {
            out.writeUTF(serializer.serialize(new Message("z",str)));
            out.flush();
            messages = serializer.deserialize(in.readUTF(),Message[].class);

        }catch(IOException e){System.out.println(e.getMessage());}
    }

    public Message[] getMessages() {
        return messages;
    }

}

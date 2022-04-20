package Net;

import com.example.messeger.MainActivity;

import java.io.IOException;

import business.Message;
import business.Serializer;

public class ClientAccess{
    private MainActivity activity;
    private Message[] messages = new Message[0];
    private Serializer serializer = new Serializer();
    private Client client;
    public ClientAccess(String ip, MainActivity activity)
    {
        super();
        this.activity = activity;
        client = new Client(ip);
        new Thread(()->
        {
            client.Connect();
            updateMessages();
        }).start();
    }

    public void pushMessage(String message) {
        try {
            client.pushMessage(serializer.serialize(new Message("z",message)));
        }catch(IOException e){System.out.println(e.getMessage());}
    }
    public void updateMessages()
    {
        try {
            while(!client.isOutputShutdown()) {
                messages = serializer.deserialize(client.receiveMessage(), Message[].class);
                activity.runOnUiThread(() -> activity.loadMessages());
            }
        }catch(IOException e){System.out.println(e.getMessage());}
    }

    public Message[] getMessages() {
        return messages;
    }

}

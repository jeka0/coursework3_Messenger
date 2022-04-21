package Net;

import com.example.messeger.MainActivity;

import java.io.IOException;

import business.Message;

public class ClientAccess{
    private MainActivity activity;
    private Message[] messages = new Message[0];
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
            client.pushObject(new Message("z",message));
        }catch(IOException e){System.out.println(e.getMessage());}
    }
    public void updateMessages()
    {
        try {
            while(!client.isOutputShutdown()) {
                messages = (Message[])client.receiveObject();
                activity.runOnUiThread(() -> activity.loadMessages());
            }
        }catch(IOException e){System.out.println(e.getMessage());}
        catch (ClassNotFoundException e){System.out.println(e.getMessage());}
    }

    public Message[] getMessages() {
        return messages;
    }

}

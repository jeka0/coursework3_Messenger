package Net;

import android.content.Intent;

import androidx.appcompat.app.AppCompatActivity;

import com.example.messeger.MainActivity;

import java.io.IOException;

import Handlers.RequestHandler;
import business.Message;
import business.Request;
import business.User;

public class ClientAccess implements Internet{
    private MainActivity activity ;
    private RequestHandler requestHandler = new RequestHandler(this);
    private Message[] messages = new Message[0];
    private Client client;
    public ClientAccess(String ip)
    {
        super();
        client = new Client(ip);
        new Thread(()->
        {
            client.Connect();
            Listen();
        }).start();
    }

    public void pushMessage(String message) {
        try {
           if(activity!=null&&client.isConnected())
           {
               client.pushObject(new Request("Add"));
               client.pushObject(new Message(activity.user.getName(),message));
           }
        }catch(IOException e){System.out.println(e.getMessage());}
    }

    public void checkUser(User user)
    {
        try {
            if(client.isConnected())
            {
                client.pushObject(new Request("CheckUser"));
                client.pushObject(user);
            }
        }catch(IOException e){System.out.println(e.getMessage());}
    }
    public void UserRegistration(User user)
    {
        try {
            if(client.isConnected())
            {
                client.pushObject(new Request("Registration"));
                client.pushObject(user);
            }
        }catch(IOException e){System.out.println(e.getMessage());}
    }
    public void Listen()
    {
        try {
            while (!client.isOutputShutdown()) {
                Request request = (Request) client.receiveObject();
                Object object = client.receiveObject();
                requestHandler.handle(request,object);
            }
        }catch(IOException e){System.out.println(e.getMessage());}
        catch (ClassNotFoundException e){System.out.println(e.getMessage());}
    }
    public void setIntent(Intent intent){requestHandler.setIntent(intent);}
    public void setAppActivity(AppCompatActivity activity){requestHandler.setAppActivity(activity);}

    public void setMainActivity(MainActivity activity) {
        this.activity = activity;
        requestHandler.setActivity(activity);
    }

    public Message[] getMessages() {
        return messages;
    }

    public void setMessages(Message[] messages) {
        this.messages = messages;
    }
}

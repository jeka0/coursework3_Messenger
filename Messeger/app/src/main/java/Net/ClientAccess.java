package Net;

import android.content.Intent;

import androidx.appcompat.app.AppCompatActivity;

import com.example.messeger.MainActivity;

import java.io.IOException;

import Handlers.IRequestHandler;
import Handlers.RequestHandler;
import ViewModels.MainViewModel;
import business.Message;
import business.Request;
import business.User;

public class ClientAccess implements IInternet {
    private MainActivity activity ;
    private IRequestHandler requestHandler = new RequestHandler(this);
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
    public void UpdatePosts()
    {
        try {
            if(client.isConnected())
            {
                client.pushObject(new Request("UpdatePosts"));
                client.pushObject(true);
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

    public void setMessages(Message[] messages) {
        activity.getMainViewModel().setMessages(messages);
    }
}

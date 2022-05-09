package Net;

import android.content.Intent;

import androidx.appcompat.app.AppCompatActivity;

import com.example.messeger.MainActivity;

import java.io.IOException;

import Handlers.IHandlers.IRequestHandler;
import Handlers.RequestHandler;
import ViewModels.IViewModels.IMessengerViewModel;
import ViewModels.IViewModels.IMyViewModel;
import business.Message;
import business.Request;
import business.User;

public class ClientAccess implements IInternet {
    private MainActivity activity ;
    private IMyViewModel model;
    private IRequestHandler requestHandler = new RequestHandler(this);
    private Client client;
    public ClientAccess(String ip, IMyViewModel model)
    {
        super();
        this.model = model;
        client = new Client(ip);
        new Thread(()->
        {
            while(!client.isConnected()) {
                client.Connect();
                if (client.isConnected()) Listen();
            }
        }).start();
    }

    public void pushMessage(String chat, String message) {
        try {
           if(activity!=null&&client.isConnected())
           {
               client.pushObject(new Request("Add",new Message(model.getUser().getName(),chat,message)));
           }
        }catch(IOException e){System.out.println(e.getMessage());}
    }

    public void checkUser(User user)
    {
        try {
            if(client.isConnected())
            {
                client.pushObject(new Request("CheckUser",user));
            }
        }catch(IOException e){System.out.println(e.getMessage());}
    }
    public void UserRegistration(User user)
    {
        try {
            if(client.isConnected())
            {
                client.pushObject(new Request("Registration",user));
            }
        }catch(IOException e){System.out.println(e.getMessage());}
    }
    public void UpdatePosts(String chat)
    {
        try {
            if(client.isConnected())
            {
                client.pushObject(new Request("UpdatePosts",chat));
            }
        }catch(IOException e){System.out.println(e.getMessage());}
    }
    public void UpdateChats(User user)
    {
        try {
            if(client.isConnected())
            {
                client.pushObject(new Request("GetChats",user.getName()));
            }
        }catch(IOException e){System.out.println(e.getMessage());}
    }
    public void Listen()
    {
        try {
            while (!client.isOutputShutdown()) {
                Request request = (Request) client.receiveObject();
                requestHandler.handle(request);
            }
        }catch(IOException e){client.setConnection(false);}
        catch (ClassNotFoundException e){System.out.println(e.getMessage());}
    }
    public void setIntent(Intent intent){requestHandler.setIntent(intent);}
    public void setAppActivity(AppCompatActivity activity){requestHandler.setAppActivity(activity);}

    public void setMainActivity(MainActivity activity) {
        this.activity = activity;
        requestHandler.setActivity(activity);
    }
    public void setMessengerModel(IMessengerViewModel messengerModel) {
        requestHandler.setMessengerModel(messengerModel);
    }

    public void setMessages(Message[] messages) {
        activity.getMainViewModel().setMessages(messages);
    }
    public boolean isConnected()
    {
        return client.isConnected();
    }

}

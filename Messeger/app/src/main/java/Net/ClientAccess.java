package Net;

import android.content.Intent;

import androidx.appcompat.app.AppCompatActivity;

import com.example.messeger.MainActivity;

import java.io.IOException;

import Handlers.IHandlers.IRequestHandler;
import Handlers.RequestHandler;
import ViewModels.IViewModels.IAccountViewModel;
import ViewModels.IViewModels.IAddChatModel;
import ViewModels.IViewModels.IMainViewModel;
import ViewModels.IViewModels.IMessengerViewModel;
import ViewModels.IViewModels.IMyViewModel;
import ViewModels.MainViewModel;
import business.Chat;
import business.Message;
import business.Request;
import business.User;

public class ClientAccess implements IInternet {
    private MainActivity activity ;
    private IMyViewModel model;
    private IMainViewModel mainViewModel;
    private IRequestHandler requestHandler = new RequestHandler(this);
    private Client client;
    private Thread Listener;
    public ClientAccess(String ip, IMyViewModel model)
    {
        super();
        this.model = model;
        initClient(ip);
    }

    public void pushMessage(Message message) {
        try {
           if(activity!=null&&client.isConnected())
           {
               client.pushObject(new Request("Add",message));
           }
        }catch(IOException e){System.out.println(e.getMessage());}
    }
    public void getUsers()
    {
        try {
            if(client.isConnected())
            {
                client.pushObject(new Request("GetUsers"));
            }
        }catch(IOException e){System.out.println(e.getMessage());}
    }
    public void DeleteUser()
    {
        try {
            if(client.isConnected())
            {
                client.pushObject(new Request("DeleteUser",model.getUser()));
            }
        }catch(IOException e){System.out.println(e.getMessage());}
    }
    public void DeleteChatToUser(Chat chat)
    {
        try {
            if(client.isConnected())
            {
                client.pushObject(new Request("DeleteChatToUser",chat));
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
    private void checkAndSetUser()
    {
        try {
            if(client.isConnected())
            {
                client.pushObject(new Request("SetUser",model.getUser()));
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
    public void AddChat(Chat chat)
    {
        try {
            if(client.isConnected())
            {
                client.pushObject(new Request("AddChat",chat));
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
    public void UpdateUser(User user)
    {
        try {
            if(client.isConnected())
            {
                client.pushObject(new Request("UpdateUser",user));
            }
        }catch(IOException e){System.out.println(e.getMessage());}
    }
    public void AddChatToUser(Chat chat)
    {
        try {
            if(client.isConnected())
            {
                client.pushObject(new Request("AddChatToUser",chat));
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
    public void UpdateSelectedChats()
    {
        try {
            if(client.isConnected())
            {
                client.pushObject(new Request("GetSelectedChats",null));
            }
        }catch(IOException e){System.out.println(e.getMessage());}
    }
    public void DeleteChat(String chatName)
    {
        try {
            if(client.isConnected())
            {
                client.pushObject(new Request("DeleteChat",chatName));
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

    public void setMainViewModel(IMainViewModel mainViewModel) {
        this.mainViewModel = mainViewModel;
        requestHandler.setMainViewModel(mainViewModel);
    }

    public void setAddChatModel(IAddChatModel addChatModel) {
        requestHandler.setAddChatModel(addChatModel);
    }
    public boolean isConnected()
    {
        return client.isConnected();
    }
    public void setAccountViewModel(IAccountViewModel accountViewModel)
    {
        requestHandler.setAccountViewModel(accountViewModel);
    }
    private void initClient(String ip)
    {
        client = new Client(ip);
        Listener =new Thread(()->
        {
            while(!client.isConnected()) {
                client.Connect();
                model.setRefresh(true);
                if(model.getUser()!=null)checkAndSetUser();
                if (client.isConnected()){
                    if(model.getUser()!=null&&model!=null)UpdateChats(model.getUser());
                    if(mainViewModel!=null)mainViewModel.Update();
                    Listen();
                }
            }
        });
        Listener.start();
    }
    public void setNewIP(String ip)
    {
        client.Close();
        client = new Client(ip);
    }

}

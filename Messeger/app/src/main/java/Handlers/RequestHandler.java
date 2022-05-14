package Handlers;

import android.content.Intent;

import androidx.appcompat.app.AppCompatActivity;

import com.example.messeger.MainActivity;

import Handlers.IHandlers.IRequestHandler;
import Net.IInternet;
import ViewModels.IViewModels.IMessengerViewModel;
import business.Chat;
import business.Message;
import business.Request;

public class RequestHandler implements IRequestHandler {
    private MainActivity activity;
    private IInternet IInternet;
    private IMessengerViewModel messengerModel;
    private AppCompatActivity appActivity;
    private Intent intent;
    public RequestHandler(IInternet IInternet)
    {
        this.IInternet = IInternet;
    }
    public void handle(Request request)
    {
        switch (request.getRequest())
        {
            case "UpdateMessages":
                if(messengerModel!=null&&activity!=null) {
                    messengerModel.setMessages((Message[]) request.getData());
                    activity.runOnUiThread(() -> activity.loadMessages());
                }
                break;
            case "Answer":
                if(appActivity!=null&&intent!=null) {
                    if ((boolean) request.getData()) {
                        appActivity.runOnUiThread(() -> appActivity.startActivity(intent));
                    }
                }
                break;
            case "UpdateChats":
                if(messengerModel!=null) {
                    messengerModel.setChats((Chat[]) request.getData());
                    messengerModel.getMenuActivity().runOnUiThread(() -> messengerModel.getMessengerFragment().loadChats());
                }
                break;
        }
    }

    public void setActivity(MainActivity activity) {
        this.activity = activity;
    }

    public void setIntent(Intent intent) {
        this.intent = intent;
    }

    public void setAppActivity(AppCompatActivity appActivity) {
        this.appActivity = appActivity;
    }

    public void setMessengerModel(IMessengerViewModel messengerModel) {
        this.messengerModel = messengerModel;
    }
}

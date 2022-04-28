package Handlers;

import android.content.Intent;

import androidx.appcompat.app.AppCompatActivity;

import com.example.messeger.MainActivity;
import com.example.messeger.uimenu.messenger.MessengerFragment;

import java.io.IOException;

import Net.IInternet;
import business.Chat;
import business.Message;
import business.Request;

public class RequestHandler implements IRequestHandler {
    private MainActivity activity;
    private IInternet IInternet;
    private MessengerFragment messengerFragment;
    private AppCompatActivity appActivity;
    private Intent intent;
    public RequestHandler(IInternet IInternet)
    {
        this.IInternet = IInternet;
    }
    public void handle(Request request)throws IOException
    {
        switch (request.getRequest())
        {
            case "UpdateMessages":
                IInternet.setMessages((Message[]) request.getData());
                activity.runOnUiThread(() -> activity.loadMessages());
                break;
            case "Answer":
                if(appActivity!=null&&intent!=null) {
                    if ((boolean) request.getData()) {
                        appActivity.runOnUiThread(() -> appActivity.startActivity(intent));
                    }
                }
                break;
            case "UpdateChats":
                messengerFragment.setChats((Chat[]) request.getData());
                messengerFragment.messengerViewModel.getMenuActivity().runOnUiThread(() -> messengerFragment.loadChats());
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
    public void setMessengerFragment(MessengerFragment fragment){this.messengerFragment = fragment;}
}

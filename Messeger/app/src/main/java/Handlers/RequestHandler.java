package Handlers;

import android.content.Intent;

import androidx.appcompat.app.AppCompatActivity;

import com.example.messeger.MainActivity;

import java.io.IOException;

import Net.IInternet;
import business.Message;
import business.Request;

public class RequestHandler implements IRequestHandler {
    private MainActivity activity;
    private IInternet IInternet;
    private AppCompatActivity appActivity;
    private Intent intent;
    public RequestHandler(IInternet IInternet)
    {
        this.IInternet = IInternet;
    }
    public void handle(Request request, Object object)throws IOException
    {
        switch (request.getRequest())
        {
            case "UpdateMessages":
                IInternet.setMessages((Message[]) object);
                activity.runOnUiThread(() -> activity.loadMessages());
                break;
            case "Answer":
                if(appActivity!=null&&intent!=null) {
                    if ((boolean) object) {
                        appActivity.runOnUiThread(() -> appActivity.startActivity(intent));
                    }
                    break;
                }
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
}

package Handlers;

import androidx.appcompat.app.AppCompatActivity;

import com.example.messeger.MainActivity;

import java.io.IOException;

import Net.ClientAccess;
import business.Message;
import business.Request;
import business.User;

public class RequestHandler {
    private MainActivity activity;
    private ClientAccess clientAccess;
    private AppCompatActivity appActivity;
    public RequestHandler(ClientAccess clientAccess, MainActivity activity)
    {
        this.activity=activity;
        this.clientAccess = clientAccess;
    }
    public void handle(Request request, Object object)throws IOException
    {
        switch (request.getRequest())
        {
            case "UpdateMessages":
                clientAccess.setMessages((Message[]) object);
                activity.runOnUiThread(() -> activity.loadMessages());
                break;
            case "Answer":
                if((boolean)object)System.out.println("+");else System.out.println("-");
                break;

        }
    }

}

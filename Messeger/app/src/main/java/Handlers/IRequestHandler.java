package Handlers;

import android.content.Intent;

import androidx.appcompat.app.AppCompatActivity;

import com.example.messeger.MainActivity;
import com.example.messeger.uimenu.messenger.MessengerFragment;

import java.io.IOException;

import business.Request;

public interface IRequestHandler {
    void handle(Request request)throws IOException;
    void setActivity(MainActivity activity);
    void setIntent(Intent intent);
    void setAppActivity(AppCompatActivity appActivity);
    void setMessengerFragment(MessengerFragment fragment);
}

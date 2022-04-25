package Net;

import android.content.Intent;

import androidx.appcompat.app.AppCompatActivity;

import com.example.messeger.MainActivity;

import business.Message;
import business.User;

public interface IInternet {

    void pushMessage(String message);
    void checkUser(User user);
    void UserRegistration(User user);
    void Listen();
    void setIntent(Intent intent);
    void setAppActivity(AppCompatActivity activity);
    void setMainActivity(MainActivity activity);
    void UpdatePosts();
    void setMessages(Message[] messages);
}

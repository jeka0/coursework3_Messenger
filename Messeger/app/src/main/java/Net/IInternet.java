package Net;

import android.content.Intent;

import androidx.appcompat.app.AppCompatActivity;

import com.example.messeger.MainActivity;

import ViewModels.IViewModels.IMessengerViewModel;
import business.Message;
import business.User;

public interface IInternet {

    void pushMessage(Message message);
    void checkUser(User user);
    void UserRegistration(User user);
    void Listen();
    void UpdateSelectedChats();
    void setIntent(Intent intent);
    void setAppActivity(AppCompatActivity activity);
    void setMainActivity(MainActivity activity);
    void UpdatePosts(String chat);
    boolean isConnected();
    void UpdateChats(User user);
    void setMessengerModel(IMessengerViewModel messengerModel);
}

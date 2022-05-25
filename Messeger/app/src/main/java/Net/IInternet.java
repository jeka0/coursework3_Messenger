package Net;

import android.content.Intent;

import androidx.appcompat.app.AppCompatActivity;

import com.example.messeger.MainActivity;

import Handlers.IHandlers.IRequestHandler;
import ViewModels.IViewModels.IAccountViewModel;
import ViewModels.IViewModels.IAddChatModel;
import ViewModels.IViewModels.IMainViewModel;
import ViewModels.IViewModels.IMessengerViewModel;
import ViewModels.MainViewModel;
import business.Chat;
import business.Message;
import business.User;

public interface IInternet {

    void pushMessage(Message message);
    void checkUser(User user);
    void DeleteUser();
    void getUsers();
    void UserRegistration(User user);
    void Listen();
    void UpdateSelectedChats();
    void DeleteChatToUser(Chat chat);
    void setIntent(Intent intent);
    void setAppActivity(AppCompatActivity activity);
    void setMainActivity(MainActivity activity);
    void UpdatePosts(String chat);
    void AddChat(Chat chat);
    void AddChatToUser(Chat chat);
    void UpdateUser(User user);
    boolean isConnected();
    void setAccountViewModel(IAccountViewModel accountViewModel);
    void UpdateChats(User user);
    void setMessengerModel(IMessengerViewModel messengerModel);
    void setMainViewModel(IMainViewModel mainViewModel);
    void setAddChatModel(IAddChatModel addChatModel);
    void setNewIP(String ip);
}

package Handlers.IHandlers;

import android.content.Intent;

import androidx.appcompat.app.AppCompatActivity;

import com.example.messeger.MainActivity;

import java.io.IOException;

import ViewModels.IViewModels.IAccountViewModel;
import ViewModels.IViewModels.IAddChatModel;
import ViewModels.IViewModels.IMainViewModel;
import ViewModels.IViewModels.IMessengerViewModel;
import business.Request;

public interface IRequestHandler {
    void handle(Request request)throws IOException;
    void setActivity(MainActivity activity);
    void setIntent(Intent intent);
    void setAppActivity(AppCompatActivity appActivity);
    void setMessengerModel(IMessengerViewModel messengerModel);
    void setAddChatModel(IAddChatModel addChatModel);
    void setAccountViewModel(IAccountViewModel accountViewModel);
    void setMainViewModel(IMainViewModel mainViewModel);
}

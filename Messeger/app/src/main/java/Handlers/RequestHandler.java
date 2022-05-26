package Handlers;

import android.content.Intent;

import androidx.appcompat.app.AppCompatActivity;

import com.example.messeger.Authorization;
import com.example.messeger.IShowError;
import com.example.messeger.MainActivity;
import com.example.messeger.Registration;
import com.example.messeger.uimenu.gallery.AccountFragment;

import java.util.ArrayList;

import Handlers.IHandlers.IRequestHandler;
import Net.IInternet;
import ViewModels.IViewModels.IAccountViewModel;
import ViewModels.IViewModels.IAddChatModel;
import ViewModels.IViewModels.IMainViewModel;
import ViewModels.IViewModels.IMessengerViewModel;
import ViewModels.MainViewModel;
import ViewModels.SearchViewModel;
import business.Chat;
import business.Message;
import business.Request;
import business.User;

public class RequestHandler implements IRequestHandler {
    private MainActivity activity;
    private IMainViewModel mainViewModel;
    private IInternet IInternet;
    private IMessengerViewModel messengerModel;
    private AppCompatActivity appActivity;
    private IAddChatModel addChatModel;
    private Intent intent;
    private IAccountViewModel accountViewModel;
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
                        appActivity.runOnUiThread(() -> {appActivity.startActivity(intent); appActivity.finish();
                        });
                    }else
                    {
                        appActivity.runOnUiThread(() -> ((IShowError)appActivity).setError());
                    }
                }
                break;
            case "AnswerUser":
                if(!(boolean) request.getData())accountViewModel.getMenuActivity().runOnUiThread(()->((IShowError)accountViewModel.getAccountFragment()).setError());
                else accountViewModel.getMenuActivity().runOnUiThread(()->accountViewModel.getAccountFragment().Clear());
                break;
            case "UpdateChats":
                if(messengerModel!=null) {
                    messengerModel.setChats((Chat[]) request.getData());
                    messengerModel.UpdateChats();
                }
                break;
            case "UpdateSelectedChats":
                if(messengerModel!=null) {
                    SearchViewModel searchViewModel = messengerModel.getSearchViewModel();
                    if (searchViewModel != null) {
                        searchViewModel.setSelectedChats((ArrayList<Chat>) request.getData());
                        searchViewModel.UpdateSelectedChats();
                    }
                }
                break;
            case "UpdateUsers":
                if(addChatModel!=null)
                {
                    addChatModel.setUsers((User[])request.getData());
                }
                break;
            case "DeleteChat":
                if(mainViewModel!=null&&activity!=null) {
                    String nameChat = (String) request.getData();
                    mainViewModel.DeleteChat(nameChat);
                    IInternet.UpdateChats(mainViewModel.getUser());
                    if(mainViewModel.isThisChat(nameChat))activity.runOnUiThread(() -> activity.Close());
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

    public void setAddChatModel(IAddChatModel addChatModel) {
        this.addChatModel = addChatModel;
    }

    public void setAccountViewModel(IAccountViewModel accountViewModel) {
        this.accountViewModel = accountViewModel;
    }

    public void setMainViewModel(IMainViewModel mainViewModel) {
        this.mainViewModel = mainViewModel;
    }
}

package Handlers;

import android.content.Intent;
import android.view.View;

import com.example.messeger.ChatMenuActivity;
import com.example.messeger.MainActivity;

import Handlers.IHandlers.IChatClickHandler;
import ViewModels.IViewModels.ISearchViewModel;
import business.Chat;

public class AddChatHandler implements IChatClickHandler {
    private ISearchViewModel searchViewModel;
    private ChatMenuActivity activity;
    public AddChatHandler(ISearchViewModel searchViewModel)
    {
        this.searchViewModel=searchViewModel;
        this.activity=searchViewModel.getMenuActivity();
    }

    @Override
    public void onClick(View view, int position) {
        if(searchViewModel!=null&&searchViewModel.getNowSelectedChats()!=null) {
            Chat newChat = new Chat(searchViewModel.getNowSelectedChats().get(position).getName());
            newChat.addUser(searchViewModel.getUser().getName());
            new Thread(() -> searchViewModel.getClientAccess().AddChatToUser(newChat)).start();
            searchViewModel.GoToMessenger();
            searchViewModel.ClearSearchView();
        }

    }
}

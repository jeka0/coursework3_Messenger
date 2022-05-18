package Handlers;

import java.util.ArrayList;

import Handlers.IHandlers.ITextChangeHandler;
import ViewModels.ChatMenuModel;
import ViewModels.IViewModels.IChatMenuModel;
import ViewModels.IViewModels.IMessengerViewModel;
import ViewModels.IViewModels.ISearchViewModel;
import ViewModels.MessengerViewModel;
import ViewModels.SearchViewModel;
import business.Chat;

public class TextChangeHandler implements ITextChangeHandler {
    private ISearchViewModel searchViewModel;
    private IChatMenuModel chatMenuModel;
    public TextChangeHandler(IChatMenuModel chatMenuModel)
    {
        this.chatMenuModel=chatMenuModel;
    }

    @Override
    public boolean onQueryTextSubmit(String s) {
        return false;
    }

    @Override
    public boolean onQueryTextChange(String s) {
        this.searchViewModel= chatMenuModel.getSearchViewModel();
        if(searchViewModel!=null) {
            ArrayList<Chat> chats = searchViewModel.getSelectedChats();
            if (chats != null) {
                ArrayList<Chat> newChats = new ArrayList<>();
                for (Chat chat : chats)
                    if (chat.getName().toLowerCase().contains(s.toLowerCase())) newChats.add(chat);
                searchViewModel.UpdateSelectedChats(newChats);
            }
        }
        return false;
    }
}

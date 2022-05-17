package Handlers;

import java.util.ArrayList;

import Handlers.IHandlers.ITextChangeHandler;
import ViewModels.MessengerViewModel;
import business.Chat;

public class TextChangeHandler implements ITextChangeHandler {
    private MessengerViewModel messengerViewModel;
    public TextChangeHandler(MessengerViewModel messengerViewModel)
    {
        this.messengerViewModel = messengerViewModel;
    }

    @Override
    public boolean onQueryTextSubmit(String s) {
        return false;
    }

    @Override
    public boolean onQueryTextChange(String s) {
        ArrayList<Chat> chats = messengerViewModel.getSelectedChats();
        if(chats!=null) {
            ArrayList<Chat> newChats = new ArrayList<>();
            for (Chat chat : chats) if (chat.getName().toLowerCase().contains(s.toLowerCase())) newChats.add(chat);
            messengerViewModel.UpdateSelectedChats(newChats);
        }
        return false;
    }
}

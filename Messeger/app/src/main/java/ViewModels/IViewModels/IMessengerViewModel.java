package ViewModels.IViewModels;

import androidx.lifecycle.LiveData;

import com.example.messeger.uimenu.messenger.MessengerFragment;

import business.Chat;

public interface IMessengerViewModel extends IChatMenuModel{
    void setMessengerFragment(MessengerFragment messengerFragment);
    MessengerFragment getMessengerFragment();
    LiveData<String> getText();
    void setChats(Chat[] chats);
    Chat[] getChats();
}

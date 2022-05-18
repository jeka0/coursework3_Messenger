package ViewModels.IViewModels;

import androidx.lifecycle.LiveData;

import com.example.messeger.uimenu.messenger.MessengerFragment;

import java.util.ArrayList;

import business.Chat;
import business.Message;

public interface IMessengerViewModel extends IChatMenuModel{
    void setMessengerFragment(MessengerFragment messengerFragment);
    void setMessages(Message[] messages);
    LiveData<String> getText();
    void setChats(Chat[] chats);
    void UpdateChats();
    Chat[] getChats();
}

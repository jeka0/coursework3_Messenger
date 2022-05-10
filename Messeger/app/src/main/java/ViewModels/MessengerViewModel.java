package ViewModels;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.messeger.uimenu.messenger.MessengerFragment;

import ViewModels.IViewModels.IMessengerViewModel;
import business.Chat;
import business.Message;

public class MessengerViewModel extends ChatMenuModel implements IMessengerViewModel {
    private MessengerFragment messengerFragment;
    private final MutableLiveData<String> mText;
    private static Chat[] chats = new Chat[0];

    public MessengerViewModel() {
        super();
        mText = new MutableLiveData<>();
        mText.setValue("This is messenger fragment");
        super.getClientAccess().setMessengerModel(this);
    }

    public void setMessengerFragment(MessengerFragment messengerFragment) {
        this.messengerFragment = messengerFragment;
    }
    public void setMessages(Message[] messages)
    {
        Chat chat=null;
        for(Chat nowChat : chats) if(nowChat.getName().equals(messages[0].getChatName())){chat=nowChat;break;}
        if(chat!=null)chat.setMessages(messages);
    }

    public MessengerFragment getMessengerFragment() {
        return messengerFragment;
    }

    public LiveData<String> getText() {
        return mText;
    }

    public void setChats(Chat[] chats) {
        this.chats = chats;
    }

    public Chat[] getChats() {
        return chats;
    }
}
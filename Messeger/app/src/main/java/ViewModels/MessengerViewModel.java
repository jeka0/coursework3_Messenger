package ViewModels;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.messeger.uimenu.messenger.MessengerFragment;


import ViewModels.IViewModels.IMessengerViewModel;
import business.Chat;
import business.Message;

public class MessengerViewModel extends ChatMenuModel implements IMessengerViewModel {
    private static MessengerFragment messengerFragment;
    private  MessengerViewModel messengerViewModel;
    private final MutableLiveData<String> mText;
    private static Chat[] chats = new Chat[0];

    public MessengerViewModel() {
        super();
        setMessengerModel(this);
        mText = new MutableLiveData<>();
        mText.setValue("This is messenger fragment");
        super.getClientAccess().setMessengerModel(this);
    }

    public void setMessengerFragment(MessengerFragment messengerFragment) {
        MessengerViewModel.messengerFragment = messengerFragment;
        if(messengerViewModel==null|| Refresh()){new Thread(()->super.getClientAccess().UpdateChats(getUser())).start();setRefresh(false);}
        messengerViewModel = this;
    }
    public void setMessages(Message[] messages)
    {
        Chat chat=null;
        if(messages!=null && messages.length!=0) {
            for (Chat nowChat : chats)
                if (nowChat.getName().equals(messages[0].getChatName())) {
                    chat = nowChat;
                    break;
                }
            if (chat != null) chat.setMessages(messages);
        }
    }

    public void UpdateChats()
    {
        getMenuActivity().runOnUiThread(() -> messengerFragment.loadChats());
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
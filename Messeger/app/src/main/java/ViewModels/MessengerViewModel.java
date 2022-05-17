package ViewModels;

import android.view.View;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.messeger.uimenu.messenger.MessengerFragment;

import java.util.ArrayList;

import ViewModels.IViewModels.IMessengerViewModel;
import business.Chat;
import business.Message;

public class MessengerViewModel extends ChatMenuModel implements IMessengerViewModel {
    private static MessengerFragment messengerFragment;
    private final MutableLiveData<String> mText;
    private static Chat[] chats = new Chat[0];
    private static ArrayList<Chat> selectedChats;

    public MessengerViewModel() {
        super();
        setMessengerModel(this);
        mText = new MutableLiveData<>();
        mText.setValue("This is messenger fragment");
        super.getClientAccess().setMessengerModel(this);
    }

    public void setMessengerFragment(MessengerFragment messengerFragment) {
        MessengerViewModel.messengerFragment = messengerFragment;
        if(chats.length==0)new Thread(()->super.getClientAccess().UpdateChats(getUser())).start();
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

    public void setSelectedChats(ArrayList<Chat> selectedChats) {
        MessengerViewModel.selectedChats = selectedChats;
    }
    public void searchON() { messengerFragment.searchON();}
    public void searchOFF() { messengerFragment.searchOFF();}
    public void UpdateSelectedChats()
    {
        getMenuActivity().runOnUiThread(() -> messengerFragment.UpdateSelectedChats(selectedChats));
    }
    public void UpdateSelectedChats(ArrayList<Chat> selectedChats)
    {
        getMenuActivity().runOnUiThread(() -> messengerFragment.UpdateSelectedChats(selectedChats));
    }
    public void UpdateChats()
    {
        getMenuActivity().runOnUiThread(() -> messengerFragment.loadChats());
    }

    public LiveData<String> getText() {
        return mText;
    }

    public ArrayList<Chat> getSelectedChats() {
        return selectedChats;
    }

    public void setChats(Chat[] chats) {
        this.chats = chats;
    }

    public Chat[] getChats() {
        return chats;
    }
}
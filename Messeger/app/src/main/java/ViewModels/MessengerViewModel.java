package ViewModels;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.ArrayList;

import ViewModels.MyViewModel;
import business.Chat;
import business.User;

public class MessengerViewModel extends MyViewModel {

    private final MutableLiveData<String> mText;
    private Chat[] chats = new Chat[0];

    public MessengerViewModel() {
        super();
        mText = new MutableLiveData<>();
        mText.setValue("This is messenger fragment");
        //new Thread(()->super.getClientAccess().UpdateChats(getUser())).start();
    }

    public LiveData<String> getText() {
        return mText;
    }
    public void setUser(User user)
    {
        super.setUser(user);
        new Thread(()->super.getClientAccess().UpdateChats(getUser())).start();
    }

    public void setChats(Chat[] chats) {
        this.chats = chats;
    }

    public Chat[] getChats() {
        return chats;
    }
}
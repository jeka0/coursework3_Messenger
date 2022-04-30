package ViewModels;

import com.example.messeger.ChatMenuActivity;

import ViewModels.IViewModels.IChatMenuModel;
import business.User;

public class ChatMenuModel extends MyViewModel implements IChatMenuModel {
    private static ChatMenuActivity menuActivity;
    public ChatMenuModel()
    {
        super();
    }
    public void setUser(User user)
    {
        super.setUser(user);
        new Thread(()->super.getClientAccess().UpdateChats(getUser())).start();
    }
    public void setMenuActivity(ChatMenuActivity activity) {
        ChatMenuModel.menuActivity = activity;
    }

    public ChatMenuActivity getMenuActivity() {
        return ChatMenuModel.menuActivity;
    }
}

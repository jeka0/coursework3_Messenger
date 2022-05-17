package ViewModels;

import com.example.messeger.ChatMenuActivity;

import ViewModels.IViewModels.IChatMenuModel;
import business.User;

public class ChatMenuModel extends MyViewModel implements IChatMenuModel {
    private static ChatMenuActivity menuActivity;
    private static MessengerViewModel messengerModel;
    public ChatMenuModel()
    {
        super();
    }
    public void setUser(User user)
    {
        super.setUser(user);
    }
    public void setMenuActivity(ChatMenuActivity activity) {
        ChatMenuModel.menuActivity = activity;
    }

    public static void setMessengerModel(MessengerViewModel messengerModel) {
        ChatMenuModel.messengerModel = messengerModel;
    }

    public MessengerViewModel getMessengerModel() {
        return messengerModel;
    }

    public ChatMenuActivity getMenuActivity() {
        return ChatMenuModel.menuActivity;
    }
}

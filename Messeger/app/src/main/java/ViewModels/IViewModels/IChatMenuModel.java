package ViewModels.IViewModels;

import com.example.messeger.ChatMenuActivity;

import ViewModels.MessengerViewModel;
import business.User;

public interface IChatMenuModel extends IMyViewModel{
    void setUser(User user);
    void setMenuActivity(ChatMenuActivity activity);
    ChatMenuActivity getMenuActivity();
    MessengerViewModel getMessengerModel();
}

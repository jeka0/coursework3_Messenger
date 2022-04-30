package ViewModels.IViewModels;

import com.example.messeger.ChatMenuActivity;

import business.User;

public interface IChatMenuModel extends IMyViewModel{
    void setUser(User user);
    void setMenuActivity(ChatMenuActivity activity);
    ChatMenuActivity getMenuActivity();
}

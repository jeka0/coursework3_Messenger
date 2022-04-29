package ViewModels;

import com.example.messeger.ChatMenuActivity;

import Net.ClientAccess;
import Net.IInternet;
import business.User;

public class MyViewModel extends androidx.lifecycle.ViewModel {
    private static IInternet IInternet;
    private static User user;
    private static ChatMenuActivity menuActivity;
    public MyViewModel()
    {
       if(IInternet ==null) IInternet = new ClientAccess("192.168.43.254");
    }

    public IInternet getClientAccess() {
        return IInternet;
    }

    public void setUser(User user) {
        MyViewModel.user = user;
    }

    public User getUser() {
        return user;
    }

    public void setMenuActivity(ChatMenuActivity menuActivity) {
        MyViewModel.menuActivity = menuActivity;
    }

    public ChatMenuActivity getMenuActivity() {
        return menuActivity;
    }
}

package ViewModels;

import Net.ClientAccess;
import Net.IInternet;
import ViewModels.IViewModels.IMyViewModel;
import business.User;

public class MyViewModel extends androidx.lifecycle.ViewModel implements IMyViewModel {
    private static IInternet IInternet;
    private static User user;
    public MyViewModel()
    {
       if(IInternet ==null) IInternet = new ClientAccess("192.168.1.101",this);
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

}

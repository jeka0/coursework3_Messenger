package ViewModels.IViewModels;

import Net.IInternet;
import business.User;

public interface IMyViewModel {
    IInternet getClientAccess();
    void setUser(User user);
    User getUser();
}

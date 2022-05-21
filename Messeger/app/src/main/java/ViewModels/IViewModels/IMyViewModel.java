package ViewModels.IViewModels;

import Net.IInternet;
import business.User;

public interface IMyViewModel {
    boolean setNewIP(String ip);
    IInternet getClientAccess();
    void setUser(User user);
    User getUser();
}

package ViewModels;

import android.content.Context;
import android.content.SharedPreferences;

import com.example.messeger.R;

import java.net.InetAddress;
import java.net.UnknownHostException;

import Net.ClientAccess;
import Net.IInternet;
import ViewModels.IViewModels.IMyViewModel;
import business.User;

public class MyViewModel extends androidx.lifecycle.ViewModel implements IMyViewModel {
    private static IInternet IInternet;
    private static User user;
    private static boolean refresh=false;
    public MyViewModel()
    {
       if(IInternet ==null) IInternet = new ClientAccess("192.168.1.101",this);
    }
    public boolean setNewIP(String ip)
    {
        try {

            InetAddress address = InetAddress.getByName(ip);
            IInternet.setNewIP(address.getHostAddress());
            refresh = true;
            return true;
        }catch (UnknownHostException e){System.out.println(e.getMessage());}
        return false;
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
    public boolean Refresh(){return refresh;}

    public void setRefresh(boolean refresh) {
        MyViewModel.refresh = refresh;
    }
}

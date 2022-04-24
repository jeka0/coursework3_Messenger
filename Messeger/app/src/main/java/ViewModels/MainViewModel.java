package ViewModels;

import androidx.lifecycle.ViewModel;

import com.example.messeger.MainActivity;

import Net.ClientAccess;
import business.User;

public class MainViewModel extends MyViewModel {
    public User user;
    public MainViewModel()
    {
        super();
    }

    public ClientAccess getClientAccess() {
        return super.getClientAccess();
    }
}

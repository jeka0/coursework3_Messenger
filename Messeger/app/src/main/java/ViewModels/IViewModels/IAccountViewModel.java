package ViewModels.IViewModels;

import androidx.lifecycle.LiveData;

import com.example.messeger.uimenu.gallery.AccountFragment;

public interface IAccountViewModel extends IChatMenuModel{
    LiveData<String> getText();
    void setAccountFragment(AccountFragment accountFragment);
    AccountFragment getAccountFragment();
}

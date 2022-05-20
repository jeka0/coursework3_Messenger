package ViewModels;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.messeger.uimenu.gallery.AccountFragment;

import ViewModels.IViewModels.IAccountViewModel;

public class AccountViewModel extends ChatMenuModel implements IAccountViewModel {

    private final MutableLiveData<String> mText;
    private AccountFragment accountFragment;

    public AccountViewModel() {
        super();
        mText = new MutableLiveData<>();
        mText.setValue("This is account fragment");
    }

    public void setAccountFragment(AccountFragment accountFragment) {
        this.accountFragment = accountFragment;
    }

    public AccountFragment getAccountFragment() {
        return accountFragment;
    }

    public LiveData<String> getText() {
        return mText;
    }
}
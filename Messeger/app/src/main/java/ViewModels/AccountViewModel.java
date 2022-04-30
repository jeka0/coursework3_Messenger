package ViewModels;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import ViewModels.IViewModels.IAccountViewModel;

public class AccountViewModel extends ChatMenuModel implements IAccountViewModel {

    private final MutableLiveData<String> mText;

    public AccountViewModel() {
        super();
        mText = new MutableLiveData<>();
        mText.setValue("This is account fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}
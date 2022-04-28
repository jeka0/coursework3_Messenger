package ViewModels;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import ViewModels.MyViewModel;

public class AccountViewModel extends MyViewModel {

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
package ViewModels;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import ViewModels.MyViewModel;

public class SettingsViewModel extends MyViewModel {

    private final MutableLiveData<String> mText;

    public SettingsViewModel() {
        super();
        mText = new MutableLiveData<>();
        mText.setValue("This is settings fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}
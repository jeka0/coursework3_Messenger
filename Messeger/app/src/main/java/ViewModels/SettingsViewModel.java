package ViewModels;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import ViewModels.IViewModels.ISettingsViewModel;

public class SettingsViewModel extends ChatMenuModel implements ISettingsViewModel {

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
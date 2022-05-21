package ViewModels.IViewModels;

import androidx.lifecycle.LiveData;

public interface ISettingsViewModel extends IChatMenuModel{
    LiveData<String> getText();
}

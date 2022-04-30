package ViewModels.IViewModels;

import androidx.lifecycle.LiveData;

public interface ISettingsViewModel extends IMyViewModel{
    LiveData<String> getText();
}

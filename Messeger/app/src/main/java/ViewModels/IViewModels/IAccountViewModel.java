package ViewModels.IViewModels;

import androidx.lifecycle.LiveData;

public interface IAccountViewModel extends IChatMenuModel{
    LiveData<String> getText();
}

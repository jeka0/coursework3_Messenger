package ViewModels.IViewModels;

import androidx.appcompat.widget.SearchView;

import com.example.messeger.ChatMenuActivity;

import ViewModels.ChatMenuModel;
import ViewModels.MessengerViewModel;
import ViewModels.SearchViewModel;
import business.Chat;
import business.User;

public interface IChatMenuModel extends IMyViewModel{
    //void setUser(User user);
    void setMenuActivity(ChatMenuActivity activity);
    ChatMenuActivity getMenuActivity();
    MessengerViewModel getMessengerModel();
    void GoToSearch();
    void GoToMessenger();
    SearchViewModel getSearchViewModel();
    void setSearchView(SearchView searchView);
    void ClearSearchView();
}

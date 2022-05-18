package ViewModels;


import androidx.appcompat.widget.SearchView;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import com.example.messeger.ChatMenuActivity;
import com.example.messeger.R;

import ViewModels.IViewModels.IChatMenuModel;
import business.User;

public class ChatMenuModel extends MyViewModel implements IChatMenuModel {
    private static ChatMenuActivity menuActivity;
    private static MessengerViewModel messengerModel;
    private static SearchViewModel searchViewModel;
    private static androidx.appcompat.widget.SearchView searchView;
    public ChatMenuModel()
    {
        super();
    }
    public void setUser(User user)
    {
        super.setUser(user);
    }
    public void setMenuActivity(ChatMenuActivity activity) {
        ChatMenuModel.menuActivity = activity;
    }
    public void GoToSearch()
    {
        if (menuActivity != null) {
            NavController navController = Navigation.findNavController(menuActivity, R.id.nav_host_fragment_content_chat_menu);
            navController.navigate(R.id.nav_search);
        }
    }
    public void GoToMessenger()
    {
        if(menuActivity!=null)
        {
           NavController navController = Navigation.findNavController(menuActivity, R.id.nav_host_fragment_content_chat_menu);
           navController.navigate(R.id.nav_messenger);
        }
    }

    public static void setSearchViewModel(SearchViewModel searchViewModel)
    {
        ChatMenuModel.searchViewModel=searchViewModel;
    }

    public  SearchViewModel getSearchViewModel() {
        return searchViewModel;
    }

    public static void setMessengerModel(MessengerViewModel messengerModel) {
        ChatMenuModel.messengerModel = messengerModel;
    }

    public MessengerViewModel getMessengerModel() {
        return messengerModel;
    }

    public ChatMenuActivity getMenuActivity() {
        return ChatMenuModel.menuActivity;
    }
}

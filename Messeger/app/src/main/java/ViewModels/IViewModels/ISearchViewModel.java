package ViewModels.IViewModels;

import com.example.messeger.uimenu.search.SearchFragment;

import java.util.ArrayList;

import business.Chat;

public interface ISearchViewModel extends IChatMenuModel{
    void setSelectedChats(ArrayList<Chat> selectedChats);
    void setSearchFragment(SearchFragment searchFragment1);
    void UpdateSelectedChats();
    void UpdateSelectedChats(ArrayList<Chat> selectedChats);
    ArrayList<Chat> getSelectedChats();
    ArrayList<Chat> getNowSelectedChats();
}

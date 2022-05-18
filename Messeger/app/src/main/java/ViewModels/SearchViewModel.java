package ViewModels;


import androidx.appcompat.widget.SearchView;

import com.example.messeger.uimenu.search.SearchFragment;

import java.util.ArrayList;

import ViewModels.IViewModels.ISearchViewModel;
import business.Chat;

public class SearchViewModel extends ChatMenuModel implements ISearchViewModel {
    private SearchFragment searchFragment1;
    private static ArrayList<Chat> selectedChats = new ArrayList<>();
    private ArrayList<Chat> nowSelectedChats;
    public SearchViewModel()
    {
        super();
        setSearchViewModel(this);
    }
    public void setSelectedChats(ArrayList<Chat> selectedChats) {
        SearchViewModel.selectedChats = selectedChats;
    }
    public void setSearchFragment(SearchFragment searchFragment1) {
        this.searchFragment1 = searchFragment1;
    }
    public void UpdateSelectedChats()
    {
        nowSelectedChats=selectedChats;
        getMenuActivity().runOnUiThread(() -> searchFragment1.UpdateSelectedChats(selectedChats));
    }
    public void UpdateSelectedChats(ArrayList<Chat> selectedChats)
    {
        nowSelectedChats=selectedChats;
        getMenuActivity().runOnUiThread(() -> searchFragment1.UpdateSelectedChats(selectedChats));
    }

    public ArrayList<Chat> getSelectedChats() {
        return selectedChats;
    }
    public ArrayList<Chat> getNowSelectedChats(){return nowSelectedChats;}
}

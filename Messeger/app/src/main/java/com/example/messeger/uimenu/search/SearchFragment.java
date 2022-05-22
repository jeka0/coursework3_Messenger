package com.example.messeger.uimenu.search;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.messeger.databinding.FragmentSearchBinding;
import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;

import Adapter.ChatsAdapter;
import Handlers.AddChatHandler;
import ViewModels.IViewModels.ISearchViewModel;
import ViewModels.SearchViewModel;
import business.Chat;

public class SearchFragment extends Fragment {

    private FragmentSearchBinding binding;
    private RecyclerView recyclerChats;
    private ISearchViewModel searchViewModel;
    private ChatsAdapter chatsAdapter;
    private View root;
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        searchViewModel = new ViewModelProvider(this).get(SearchViewModel.class);
        searchViewModel.setSearchFragment(this);
        binding = FragmentSearchBinding.inflate(inflater, container, false);
        root = binding.getRoot();
        initRecyclerView();
        new Thread(()->searchViewModel.getClientAccess().UpdateSelectedChats()).start();
        searchViewModel.UpdateSelectedChats();
        return root;
    }
    public void UpdateSelectedChats(ArrayList<Chat> chats)
    {
        chatsAdapter.clearItems();
        chatsAdapter.setItems(chats);
    }
    private void initRecyclerView()
    {
        recyclerChats = binding.recyclerViewSearch;
        recyclerChats.setLayoutManager(new LinearLayoutManager(root.getContext()));
        chatsAdapter = new ChatsAdapter(new AddChatHandler(searchViewModel),searchViewModel);
        recyclerChats.setAdapter(chatsAdapter);
    }
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
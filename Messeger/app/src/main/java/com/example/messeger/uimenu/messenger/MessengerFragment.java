package com.example.messeger.uimenu.messenger;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.messeger.databinding.FragmentMessengerBinding;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;

import Adapter.ChatsAdapter;
import Handlers.ChatClickHandler;
import ViewModels.IViewModels.IMessengerViewModel;
import ViewModels.MessengerViewModel;
import business.Chat;

public class MessengerFragment extends Fragment {

    private FragmentMessengerBinding binding;
    private RecyclerView recyclerChats,recyclerChats2;
    private ChatsAdapter chatsAdapter,chatsAdapter2;
    private View root;
    private IMessengerViewModel messengerViewModel;
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        messengerViewModel = new ViewModelProvider(this).get(MessengerViewModel.class);
        messengerViewModel.setMessengerFragment(this);
        binding = FragmentMessengerBinding.inflate(inflater, container, false);
        root = binding.getRoot();
        initRecyclerView();
        initRecyclerView2();
        loadChats();
        return root;
    }
    public void UpdateSelectedChats(ArrayList<Chat> chats)
    {
        chatsAdapter2.clearItems();
        chatsAdapter2.setItems(chats);
    }
    public void searchON()
    {
        recyclerChats.setVisibility(View.GONE);
        recyclerChats2.setVisibility(View.VISIBLE);
    }
    public void searchOFF()
    {
        recyclerChats2.setVisibility(View.GONE);
        recyclerChats.setVisibility(View.VISIBLE);
    }
    public void loadChats()
    {
        chatsAdapter.clearItems();
        Collection<Chat> chats = getChats();
        chatsAdapter.setItems(chats);
    }
    private Collection<Chat> getChats()
    {
        ArrayList arrayList = new ArrayList<>(Arrays.asList(messengerViewModel.getChats()));
        if(arrayList==null)return new ArrayList<>();else return arrayList;
    }
    private void initRecyclerView()
    {
        recyclerChats = binding.recyclerView;
        recyclerChats.setLayoutManager(new LinearLayoutManager(root.getContext()));
        chatsAdapter = new ChatsAdapter(new ChatClickHandler(messengerViewModel));
        recyclerChats.setAdapter(chatsAdapter);
    }
    private void initRecyclerView2()
    {
        recyclerChats2 = binding.recyclerViewSearch;
        recyclerChats2.setLayoutManager(new LinearLayoutManager(root.getContext()));
        chatsAdapter2 = new ChatsAdapter(/*new ChatClickHandler(messengerViewModel)*/);
        recyclerChats2.setAdapter(chatsAdapter2);
    }
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
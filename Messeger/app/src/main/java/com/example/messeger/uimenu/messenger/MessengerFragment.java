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

import com.example.messeger.R;
import com.example.messeger.databinding.FragmentMessengerBinding;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;

import Adapter.ChatsAdapter;
import Adapter.MessagesAdapter;
import ViewModels.MessengerViewModel;
import business.Chat;
import business.Message;
import business.User;

public class MessengerFragment extends Fragment {

    private FragmentMessengerBinding binding;
    private RecyclerView recyclerChats;
    private ChatsAdapter chatsAdapter;
    private View root;
    public MessengerViewModel messengerViewModel;
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        messengerViewModel =
                new ViewModelProvider(this).get(MessengerViewModel.class);
        messengerViewModel.getClientAccess().setMessengerFragment(this);
        binding = FragmentMessengerBinding.inflate(inflater, container, false);
        root = binding.getRoot();
        initRecyclerView();
        loadChats();
        return root;
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
    public void setChats(Chat[] chats)
    {
        messengerViewModel.setChats(chats);
    }
    private void initRecyclerView()
    {
        recyclerChats = binding.recyclerView;
        recyclerChats.setLayoutManager(new LinearLayoutManager(root.getContext()));
        chatsAdapter = new ChatsAdapter();
        recyclerChats.setAdapter(chatsAdapter);
    }
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
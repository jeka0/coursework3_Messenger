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
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;

import Adapter.ChatsAdapter;
import Handlers.ChatClickHandler;
import Handlers.SwitchActivity;
import ViewModels.IViewModels.IMessengerViewModel;
import ViewModels.MessengerViewModel;
import business.Chat;

public class MessengerFragment extends Fragment {

    private FragmentMessengerBinding binding;
    private RecyclerView recyclerChats;
    private ChatsAdapter chatsAdapter;
    private View root;
    private FloatingActionButton button;
    private IMessengerViewModel messengerViewModel;
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        messengerViewModel = new ViewModelProvider(this).get(MessengerViewModel.class);
        messengerViewModel.setMessengerFragment(this);
        binding = FragmentMessengerBinding.inflate(inflater, container, false);
        root = binding.getRoot();
        button= binding.floatingActionButton2;
        button.setOnClickListener(new SwitchActivity(messengerViewModel.getMenuActivity(), messengerViewModel.getClientAccess()));
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
    private void initRecyclerView()
    {
        recyclerChats = binding.recyclerView;
        recyclerChats.setLayoutManager(new LinearLayoutManager(root.getContext()));
        chatsAdapter = new ChatsAdapter(new ChatClickHandler(messengerViewModel),messengerViewModel);
        recyclerChats.setAdapter(chatsAdapter);
    }
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
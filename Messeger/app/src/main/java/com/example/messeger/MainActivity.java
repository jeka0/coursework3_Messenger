package com.example.messeger;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;

import Adapter.MessagesAdapter;
import Handlers.SubmitClickListener;
import Net.ClientAccess;
import ViewModels.MainViewModel;
import business.Message;
import business.User;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerMessages;
    public static MainActivity activity;
    public User user;
    private MessagesAdapter messagesAdapter;
    private MainViewModel mainViewModel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        activity = this;
        user = (User)getIntent().getSerializableExtra("User");
        initRecyclerView();
        mainViewModel = new ViewModelProvider(this).get(MainViewModel.class);
        mainViewModel.getClientAccess().setMainActivity(this);
        findViewById(R.id.sendBut).setOnClickListener(new SubmitClickListener(this,mainViewModel.getClientAccess()));
    }
    private Collection<Message> getMessages()
    {
        ArrayList arrayList = new ArrayList<>(Arrays.asList(mainViewModel.getClientAccess().getMessages()));
        if(arrayList==null)return new ArrayList<>();else return arrayList;
    }
    public void loadMessages()
    {
        messagesAdapter.clearItems();
        Collection<Message> messages = getMessages();
        messagesAdapter.setItems(messages);
    }
    private void initRecyclerView()
    {
        recyclerMessages = findViewById(R.id.recyclerView);
        recyclerMessages.setLayoutManager(new LinearLayoutManager(this));
        messagesAdapter = new MessagesAdapter();
        recyclerMessages.setAdapter(messagesAdapter);
    }
}
package com.example.messeger;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;

import Adapter.MessagesAdapter;
import Handlers.SubmitClickListener;
import Net.ClientAccess;
import business.Message;
import business.User;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerMessages;
    public User user;
    private MessagesAdapter messagesAdapter;
    private ClientAccess clientAccess;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        user = new User(getIntent().getStringExtra("Name"),getIntent().getStringExtra("Password"));
        initRecyclerView();
        clientAccess = new ClientAccess("192.168.43.254",this);
        findViewById(R.id.sendBut).setOnClickListener(new SubmitClickListener(this,clientAccess));
    }
    private Collection<Message> getMessages()
    {
        ArrayList arrayList = new ArrayList<>(Arrays.asList(clientAccess.getMessages()));
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
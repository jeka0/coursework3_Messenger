package com.example.messeger;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;

import Adapter.MessagesAdapter;
import Net.Client;
import business.Message;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerMessages;
    private MessagesAdapter messagesAdapter;
    private ArrayList<Message> messages = new ArrayList<>();
    private Client client;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        FloatingActionButton button = findViewById(R.id.sendBut);
        initRecyclerView();
        client = new Client("192.168.43.254");
        new Thread(new Runnable() {
            @Override
            public void run() {
                client.Connect();
            }
        }).start();
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditText editText = findViewById(R.id.messageField);
                String text = editText.getText().toString();
                if(text.equals(""))return;
                messages.add(new Message("Я",text));
                new Thread(new Runnable() {
                    @Override
                    public void run() {

                        client.pushText(text);
                    }
                }).start();
                loadMessages();
                editText.setText("");
            }
        });
    }
    private Collection<Message> getMessages()
    {
        return Arrays.asList(new Message("R","Привет"),new Message("T","AAAAAAAA"));
    }
    private void loadMessages()
    {
        messagesAdapter.clearItems();
        //Collection<Message> messages = getMessages();
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
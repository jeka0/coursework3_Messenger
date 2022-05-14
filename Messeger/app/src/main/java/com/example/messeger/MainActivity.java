package com.example.messeger;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.ViewCompat;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;

import java.io.FileNotFoundException;
import java.net.URI;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;

import Adapter.MessagesAdapter;
import Data.WorkingWithFile;
import Handlers.ImageSelectionHandler;
import Handlers.SubmitClickListener;
import ViewModels.IViewModels.IMainViewModel;
import ViewModels.MainViewModel;
import business.Message;
import business.MyFIle;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerMessages;
    private MessagesAdapter messagesAdapter;
    private IMainViewModel mainViewModel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initRecyclerView();
        mainViewModel = new ViewModelProvider(this).get(MainViewModel.class);
        mainViewModel.getClientAccess().setMainActivity(this);
        mainViewModel.setPosition(getIntent().getIntExtra("position",0));
        findViewById(R.id.sendBut).setOnClickListener(new SubmitClickListener(this,mainViewModel));
        findViewById(R.id.imageAdd).setOnClickListener(new ImageSelectionHandler(this,mainViewModel));
        loadMessages();
    }

    private Collection<Message> getMessages()
    {
        ArrayList arrayList = new ArrayList<>(Arrays.asList(mainViewModel.getMessages()));
        if(arrayList==null)return new ArrayList<>();else return arrayList;
    }

    public IMainViewModel getMainViewModel() {
        return mainViewModel;
    }

    public void loadMessages()
    {
        messagesAdapter.clearItems();
        Collection<Message> messages = getMessages();
        messagesAdapter.setItems(messages);
        recyclerMessages.scrollToPosition(messages.size()-1);
    }
    private void initRecyclerView()
    {
        recyclerMessages = findViewById(R.id.recyclerView);
        recyclerMessages.setLayoutManager(new LinearLayoutManager(this));
        messagesAdapter = new MessagesAdapter();
        recyclerMessages.setAdapter(messagesAdapter);
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent imageReturnedIntent) {
        super.onActivityResult(requestCode, resultCode, imageReturnedIntent);
        switch(requestCode) {
            case 1:
                if(resultCode == RESULT_OK){
                    try {
                    WorkingWithFile working = new WorkingWithFile(getContentResolver().openInputStream(imageReturnedIntent.getData()));
                    mainViewModel.setImage(working.ReadImageBytes());
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    }
                }
                break;
            case 10:
                if(resultCode == RESULT_OK) {
                    try {
                        Uri uri = imageReturnedIntent.getData();
                        WorkingWithFile working = new WorkingWithFile(getContentResolver().openInputStream(uri));
                        String[] strs = imageReturnedIntent.getData().toString().split("\\.");
                        String ext = strs[strs.length-1];
                        if(ext.equals("jpg"))mainViewModel.setImage(working.ReadImageBytes());
                        else
                        {
                            MyFIle file = new MyFIle();
                            file.setName("AA");
                            file.setData(working.ReadBytes());
                            file.setExtension(ext);
                            mainViewModel.setFile(file);
                        }
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    }
                }
                break;
        }}
}
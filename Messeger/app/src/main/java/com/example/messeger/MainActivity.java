package com.example.messeger;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.view.ViewCompat;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.view.MenuItem;
import android.view.View;

import java.io.File;
import java.io.FileNotFoundException;
import java.net.URI;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;

import Adapter.MessagesAdapter;
import Data.WorkingWithFile;
import Handlers.FileSelectionHandler;
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
    private ConstraintLayout filesLayout, imageLayout, fileLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        initRecyclerView();
        mainViewModel = new ViewModelProvider(this).get(MainViewModel.class);
        mainViewModel.getClientAccess().setMainActivity(this);
        mainViewModel.setPosition(getIntent().getIntExtra("position",0));
        findViewById(R.id.sendBut).setOnClickListener(new SubmitClickListener(this,mainViewModel));
        findViewById(R.id.imageAdd).setOnClickListener(new ImageSelectionHandler(this,mainViewModel));
        findViewById(R.id.fileAdd).setOnClickListener(new FileSelectionHandler(this,mainViewModel));
        filesLayout = findViewById(R.id.constraintLayout3);
        imageLayout= findViewById(R.id.constraintLayoutIm);
        fileLayout = findViewById(R.id.constraintLayoutFile);
        findViewById(R.id.imageButton2).setOnClickListener((View view)-> {
            mainViewModel.setImage(null);
            UpdateFilesVisibility();
        });
        findViewById(R.id.imageButton3).setOnClickListener((View view)-> {
            mainViewModel.setFile(null);
            UpdateFilesVisibility();
        });
        loadMessages();
    }

    private Collection<Message> getMessages()
    {
        ArrayList arrayList = new ArrayList<>(Arrays.asList(mainViewModel.getMessages()));
        if(arrayList==null)return new ArrayList<>();else return arrayList;
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
        messagesAdapter = new MessagesAdapter(this);
        recyclerMessages.setAdapter(messagesAdapter);
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent imageReturnedIntent) {
        super.onActivityResult(requestCode, resultCode, imageReturnedIntent);
        switch(requestCode) {
            case 1:
                if(resultCode == RESULT_OK){
                    try {
                        Uri uri = imageReturnedIntent.getData();
                    WorkingWithFile working = new WorkingWithFile(getContentResolver().openInputStream(uri),0);
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
                        File nowfile  = new File(Environment.getExternalStorageDirectory()+uri.getPath().replace("/external_files",""));
                        WorkingWithFile working = new WorkingWithFile(getContentResolver().openInputStream(uri),nowfile.length());
                        String[] strs = nowfile.getName().split("\\.");
                        if(strs.length==2) {
                            String ext = strs[1];
                            if (ext.equals("jpg")) mainViewModel.setImage(working.ReadImageBytes());
                            else {
                                MyFIle file = new MyFIle();
                                file.setName(strs[0]);
                                file.setData(working.ReadBytes());
                                file.setExtension(ext);
                                mainViewModel.setFile(file);
                            }
                        }else mainViewModel.setImage(working.ReadImageBytes());
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    }
                }
                break;
        }
        UpdateFilesVisibility();
    }
    public void UpdateFilesVisibility()
    {
        if(mainViewModel.getFile()!=null||mainViewModel.getImage()!=null) filesLayout.setVisibility(View.VISIBLE);else filesLayout.setVisibility(View.GONE);
        if(mainViewModel.getImage()!=null)imageLayout.setVisibility(View.VISIBLE);else imageLayout.setVisibility(View.GONE);
        if(mainViewModel.getFile()!=null)fileLayout.setVisibility(View.VISIBLE);else fileLayout.setVisibility(View.GONE);
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
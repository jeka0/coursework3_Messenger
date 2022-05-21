package com.example.messeger;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.widget.EditText;
import android.widget.SearchView;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.Collection;

import Adapter.UserAdapter;
import Handlers.CreateNewChatHandler;
import ViewModels.AddChatModel;
import ViewModels.IViewModels.IAddChatModel;
import business.User;

public class AddChatActivity extends AppCompatActivity implements IShowError{
    private RecyclerView recyclerUsers;
    private UserAdapter userAdapter;
    private IAddChatModel addChatModel;
    private FloatingActionButton button;
    private EditText ChatName;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_chat);
        addChatModel = new ViewModelProvider(this).get(AddChatModel.class);
        initRecyclerView();
        addChatModel.setAddChatActivity(this);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        button = findViewById(R.id.CreateChat);
        ChatName = findViewById(R.id.editTextTextChatName);
        button.setOnClickListener(new CreateNewChatHandler(addChatModel));
        initSearch();
    }
    public void initSearch()
    {
        SearchView searchView = findViewById(R.id.searchView);
        searchView.setQueryHint("Search...");
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                if(addChatModel!=null) {
                    User[] users = addChatModel.getUsers();
                    if (users != null) {
                        ArrayList<User> newUsers = new ArrayList<>();
                        for (User user : users)
                            if (user.getName().toLowerCase().contains(newText.toLowerCase())) newUsers.add(user);
                        UpdateUsers(newUsers);
                        addChatModel.setNowUsers(newUsers);
                    }
                }
                return false;
            }
        });
    }
    public void UpdateUsers(Collection<User> users)
    {
        userAdapter.clearItems();
        userAdapter.setItems(users);
    }
    private void initRecyclerView()
    {
        recyclerUsers = findViewById(R.id.recycler_user);
        recyclerUsers.setLayoutManager(new LinearLayoutManager(this));
        userAdapter = new UserAdapter(addChatModel);
        recyclerUsers.setAdapter(userAdapter);
    }
    public void setError()
    {
        ChatName.setError("A chat with this name already exists!!!");
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
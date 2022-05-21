package com.example.messeger;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.appcompat.app.AppCompatActivity;

import com.example.messeger.databinding.ActivityChatMenuBinding;
import com.google.android.material.navigation.NavigationView;

import Handlers.CloseListener;
import Handlers.TextChangeHandler;
import ViewModels.ChatMenuModel;
import ViewModels.IViewModels.IChatMenuModel;
import business.User;

public class ChatMenuActivity extends AppCompatActivity {

    private AppBarConfiguration mAppBarConfiguration;
    private ActivityChatMenuBinding binding;
    private IChatMenuModel chatMenuModel;
    private MenuItem menuItem;
    private SharedPreferences settings;
    private SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        chatMenuModel = new ViewModelProvider(this).get(ChatMenuModel.class);
        User user = (User)getIntent().getSerializableExtra("User");
        chatMenuModel.setUser(user);
        settings = getSharedPreferences(getString(R.string.pfName), Context.MODE_PRIVATE);
        editor = settings.edit();
        editor.putString(getString(R.string.pfCodeForID), user.getName());
        editor.putString(getString(R.string.pfCodeForPassword), user.getPassword());
        editor.commit();
        chatMenuModel.setMenuActivity(this);
        binding = ActivityChatMenuBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        setSupportActionBar(binding.appBarChatMenu.toolbar);
        mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.nav_messenger, R.id.nav_account, R.id.nav_settings)
                .setOpenableLayout(binding.drawerLayout)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_chat_menu);
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
        NavigationUI.setupWithNavController(binding.navView, navController);
        updateUserName();
    }
    private void updateUserName()
    {
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        View headerView = navigationView.getHeaderView(0);
        TextView navUsername = (TextView) headerView.findViewById(R.id.login);
        navUsername.setText(chatMenuModel.getUser().getName());
    }
    public void passBack(){menuItem.collapseActionView();}
    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        getMenuInflater().inflate(R.menu.menu,menu);
        menuItem = menu.findItem(R.id.search);
        androidx.appcompat.widget.SearchView searchView = (androidx.appcompat.widget.SearchView) menuItem.getActionView();
        chatMenuModel.setSearchView(searchView);
        searchView.setQueryHint("Search...");
        searchView.setOnQueryTextListener(new TextChangeHandler(chatMenuModel));
        searchView.addOnAttachStateChangeListener(new CloseListener(chatMenuModel));
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_chat_menu);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        binding=null;
    }
}
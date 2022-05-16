package com.example.messeger;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.appcompat.app.AppCompatActivity;

import com.example.messeger.databinding.ActivityChatMenuBinding;
import com.google.android.material.navigation.NavigationView;

import ViewModels.ChatMenuModel;
import ViewModels.IViewModels.IChatMenuModel;
import business.User;

public class ChatMenuActivity extends AppCompatActivity {

    private AppBarConfiguration mAppBarConfiguration;
    private ActivityChatMenuBinding binding;
    private IChatMenuModel chatMenuModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        chatMenuModel = new ViewModelProvider(this).get(ChatMenuModel.class);
        chatMenuModel.setUser((User)getIntent().getSerializableExtra("User"));
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

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_chat_menu);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }
}
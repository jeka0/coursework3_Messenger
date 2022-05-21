package com.example.messeger;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Switch;

import androidx.appcompat.app.AppCompatDelegate;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.example.messeger.databinding.ActivitySettingsBinding;

import java.net.InetAddress;
import java.net.UnknownHostException;

import ViewModels.IViewModels.ISettingsViewModel;
import ViewModels.SettingsViewModel;

public class SettingsActivity extends AppCompatActivity {
    private SharedPreferences settings;
    private SharedPreferences.Editor editor;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        ISettingsViewModel settingsViewModel = new ViewModelProvider(this).get(SettingsViewModel.class);
        settings = getSharedPreferences(getString(R.string.pfName), Context.MODE_PRIVATE);
        editor = settings.edit();
        Switch mode= findViewById(R.id.switchMode);
        if(AppCompatDelegate.getDefaultNightMode()==AppCompatDelegate.MODE_NIGHT_NO)mode.setChecked(true);
        mode.setOnCheckedChangeListener((CompoundButton compoundButton, boolean b)-> {
            if(b){AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
                editor.putInt(getString(R.string.pfCodeForMode),AppCompatDelegate.MODE_NIGHT_NO);
            }
            else {AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
                editor.putInt(getString(R.string.pfCodeForMode),AppCompatDelegate.MODE_NIGHT_YES);
            }
            editor.commit();
        });
        findViewById(R.id.ChangeIPbutton).setOnClickListener((View view)->{
               EditText ip= findViewById(R.id.editTextIP);
                String strip = ip.getText().toString();
            if(!strip.isEmpty()) {
                new Thread(() ->
                {
                    if (settingsViewModel.setNewIP(strip)) runOnUiThread(() -> ip.setText(""));
                    else runOnUiThread(() -> ip.setError("invalid ip address!!!"));
                }).start();
            }else ip.setError("IP must not be empty!!!!");
        });
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
package com.example.messeger.uimenu.settings;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Switch;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.messeger.ChatMenuActivity;
import com.example.messeger.R;
import com.example.messeger.databinding.FragmentSettingsBinding;

import ViewModels.IViewModels.ISettingsViewModel;
import ViewModels.SettingsViewModel;

public class SettingsFragment extends Fragment {

    private FragmentSettingsBinding binding;
    private ChatMenuActivity menuActivity;
    private SharedPreferences settings;
    private SharedPreferences.Editor editor;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        ISettingsViewModel settingsViewModel = new ViewModelProvider(this).get(SettingsViewModel.class);
        binding = FragmentSettingsBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        menuActivity = settingsViewModel.getMenuActivity();
        settings = menuActivity.getSharedPreferences(getString(R.string.pfName), Context.MODE_PRIVATE);
        editor = settings.edit();
        Switch mode = binding.switchMode;
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
        binding.ChangeIPbutton.setOnClickListener((View view)->{
            EditText ip= binding.editTextIP;
            String strip = ip.getText().toString();
            if(!strip.isEmpty()) {
                new Thread(() ->
                {
                    if (settingsViewModel.setNewIP(strip))
                        menuActivity.runOnUiThread(() -> ip.setText(""));
                    else menuActivity.runOnUiThread(() -> ip.setError("invalid ip address!!!"));
                }).start();
            }else ip.setError("IP must not be empty!!!!");
        });
        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
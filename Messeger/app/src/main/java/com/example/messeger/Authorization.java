package com.example.messeger;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.lifecycle.ViewModelProvider;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import Handlers.SwitchActivity;
import Handlers.IHandlers.ISwitchHandler;
import ViewModels.AuthorizationAndRegistrationModel;
import ViewModels.IViewModels.IAuthorizationAndRegistrationModel;

public class Authorization extends AppCompatActivity implements IShowError {
    private IAuthorizationAndRegistrationModel model;
    private EditText name,pass;
    private SharedPreferences settings;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_authorization);
        model = new ViewModelProvider(this).get(AuthorizationAndRegistrationModel.class);
        ISwitchHandler ISwitchHandler = new SwitchActivity(this,model.getClientAccess());
        model.setSwitchActivity(ISwitchHandler);
        name=findViewById(R.id.editTextName);
        pass=findViewById(R.id.editTextPassword);
        Button login = findViewById(R.id.buttonLogin);
        login.setOnClickListener(ISwitchHandler);
        findViewById(R.id.buttonRegister).setOnClickListener(ISwitchHandler);
        findViewById(R.id.SettingButton).setOnClickListener((View view)->{
            Intent intent = new Intent(this,SettingsActivity.class);
            startActivity(intent);
        });
        settings = getSharedPreferences(getString(R.string.pfName), Context.MODE_PRIVATE);
        int mode = settings.getInt(getString(R.string.pfCodeForMode),-1);
        if(mode!=-1) AppCompatDelegate.setDefaultNightMode(mode);
        String id=settings.getString(getString(R.string.pfCodeForID),getString(R.string.pfNoStringPresent)),
                password= settings.getString(getString(R.string.pfCodeForPassword),getString(R.string.pfNoStringPresent));
        if(!id.equals(getString(R.string.pfNoStringPresent))&&!password.equals(getString(R.string.pfNoStringPresent)))
        {
            name.setText(id);pass.setText(password);
            new Thread(()->{
                try {
                    while (!model.getClientAccess().isConnected()) Thread.sleep(100);
                    login.callOnClick();
                }catch (InterruptedException e){System.out.println(e.getMessage());}
            }).start();
        }
    }
    public void setError()
    {
        name.setError("Неверный логин или пароль. Или данный аккаунт уже авторизован!!!");
        pass.setError("Неверный логин или пароль. Или данный аккаунт уже авторизован!!!");
    }
}
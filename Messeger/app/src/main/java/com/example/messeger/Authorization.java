package com.example.messeger;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import Handlers.SwitchActivity;
import Net.ClientAccess;
import ViewModels.AuthorizationAndRegistrationModel;
import ViewModels.MainViewModel;

public class Authorization extends AppCompatActivity {
    private AuthorizationAndRegistrationModel model;
    public ClientAccess clientAccess;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_authorization);
        model = new ViewModelProvider(this).get(AuthorizationAndRegistrationModel.class);
        clientAccess = model.getClientAccess();
        findViewById(R.id.buttonLogin).setOnClickListener(new SwitchActivity(this,clientAccess));
        findViewById(R.id.buttonRegister).setOnClickListener(new SwitchActivity(this,clientAccess));
    }
}
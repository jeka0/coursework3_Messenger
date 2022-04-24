package com.example.messeger;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import Handlers.SwitchActivity;
import Net.ClientAccess;
import ViewModels.AuthorizationAndRegistrationModel;

public class Registration extends AppCompatActivity {
    private AuthorizationAndRegistrationModel model;
    public ClientAccess clientAccess;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);
        model = new ViewModelProvider(this).get(AuthorizationAndRegistrationModel.class);
        clientAccess = model.getClientAccess();
        SwitchActivity switchActivity = new SwitchActivity(this,clientAccess);
        findViewById(R.id.buttonLogin).setOnClickListener(switchActivity);
        findViewById(R.id.buttonRegister).setOnClickListener(switchActivity);
    }
}
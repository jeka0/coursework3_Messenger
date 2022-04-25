package com.example.messeger;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import Handlers.SwitchActivity;
import Handlers.ISwitchHandler;
import ViewModels.AuthorizationAndRegistrationModel;

public class Registration extends AppCompatActivity {
    private AuthorizationAndRegistrationModel model;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);
        model = new ViewModelProvider(this).get(AuthorizationAndRegistrationModel.class);
        ISwitchHandler ISwitchHandler = new SwitchActivity(this,model.getClientAccess());
        model.setSwitchActivity(ISwitchHandler);
        findViewById(R.id.buttonLogin).setOnClickListener(ISwitchHandler);
        findViewById(R.id.buttonRegister).setOnClickListener(ISwitchHandler);
    }
}
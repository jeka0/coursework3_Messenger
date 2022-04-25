package com.example.messeger;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import Handlers.SwitchActivity;
import ViewModels.AuthorizationAndRegistrationModel;

public class Authorization extends AppCompatActivity {
    private AuthorizationAndRegistrationModel model;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_authorization);
        model = new ViewModelProvider(this).get(AuthorizationAndRegistrationModel.class);
        SwitchActivity switchActivity = new SwitchActivity(this,model.getClientAccess());
        model.setSwitchActivity(switchActivity);
        findViewById(R.id.buttonLogin).setOnClickListener(switchActivity);
        findViewById(R.id.buttonRegister).setOnClickListener(switchActivity);
    }
}
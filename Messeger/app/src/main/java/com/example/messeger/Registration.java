package com.example.messeger;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import Handlers.SwitchActivity;
import Handlers.IHandlers.ISwitchHandler;
import ViewModels.AuthorizationAndRegistrationModel;
import ViewModels.IViewModels.IAuthorizationAndRegistrationModel;

public class Registration extends AppCompatActivity implements IShowError {
    private IAuthorizationAndRegistrationModel model;
    private EditText name,pass,repPass;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);
        model = new ViewModelProvider(this).get(AuthorizationAndRegistrationModel.class);
        ISwitchHandler ISwitchHandler = new SwitchActivity(this,model.getClientAccess());
        model.setSwitchActivity(ISwitchHandler);
        name=findViewById(R.id.editTextName);
        pass=findViewById(R.id.editTextPassword);
        repPass = findViewById(R.id.editTextPasswordRepeat);
        findViewById(R.id.buttonLogin).setOnClickListener(ISwitchHandler);
        findViewById(R.id.buttonRegister).setOnClickListener(ISwitchHandler);
        findViewById(R.id.settingButton).setOnClickListener((View view)->{
            Intent intent = new Intent(this,SettingsActivity.class);
            startActivity(intent);
        });
    }
    public void setError()
    {
        name.setError("Пользователь с таким логином уже существует!!!");
    }

}
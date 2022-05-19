package com.example.messeger;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;
import android.widget.EditText;

import Handlers.SwitchActivity;
import Handlers.IHandlers.ISwitchHandler;
import ViewModels.AuthorizationAndRegistrationModel;
import ViewModels.IViewModels.IAuthorizationAndRegistrationModel;

public class Authorization extends AppCompatActivity implements IShowError {
    private IAuthorizationAndRegistrationModel model;
    private EditText name,pass;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_authorization);
        model = new ViewModelProvider(this).get(AuthorizationAndRegistrationModel.class);
        ISwitchHandler ISwitchHandler = new SwitchActivity(this,model.getClientAccess());
        model.setSwitchActivity(ISwitchHandler);
        name=findViewById(R.id.editTextName);
        pass=findViewById(R.id.editTextPassword);
        findViewById(R.id.buttonLogin).setOnClickListener(ISwitchHandler);
        findViewById(R.id.buttonRegister).setOnClickListener(ISwitchHandler);
    }
    public void setError()
    {
        name.setError("Неверный логин или пароль!!!");
        pass.setError("Неверный логин или пароль!!!");
    }
}
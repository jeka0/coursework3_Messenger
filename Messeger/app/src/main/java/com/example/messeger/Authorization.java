package com.example.messeger;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import Handlers.SwitchActivity;

public class Authorization extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_authorization);
        findViewById(R.id.buttonLogin).setOnClickListener(new SwitchActivity(this));
        findViewById(R.id.buttonRegister).setOnClickListener(new SwitchActivity(this));
    }
}
package com.example.messeger;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class AddChatActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_chat);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }
}
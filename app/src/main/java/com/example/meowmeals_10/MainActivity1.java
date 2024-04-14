package com.example.meowmeals_10;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class MainActivity1 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        getSupportActionBar().hide();
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main1);
        setContentView(new GameView(this));

    }
}
package com.room.database.activity;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

public abstract class BaseActivity extends AppCompatActivity {

    protected abstract void setContentView();
    protected abstract void initializeView();
    protected abstract void initializeObject();
    protected abstract void onTextChangedListener();
    protected abstract void initializeEvent();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView();
        initializeView();
        initializeObject();
        onTextChangedListener();
        initializeEvent();
    }
}
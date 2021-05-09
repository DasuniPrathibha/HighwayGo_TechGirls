package com.example.app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class AdminPage extends AppCompatActivity {
    public static final String EXTRA_MESSAGE = "com.example.app";

    private Button btnAddSchedule;
    private Button btnUpdateSchedule;
    private Button btnDeleteSchedule;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_page);
    }

    public void onClickAdd(View view){

        Intent intent = new Intent(this, AddBusActivity.class);
        startActivity(intent);
    }



    public void onClickView(View view){

        Intent intent = new Intent(this, ViewSchedule.class);
        startActivity(intent);

    }
}
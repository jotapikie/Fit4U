package com.example.fit4u;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.os.Parcelable;

import java.util.Calendar;

import androidx.appcompat.app.AppCompatActivity;

import domain.Training;

public class StartNewTrainingActivity extends AppCompatActivity {
    DBHandler dbHandler = new DBHandler(getBaseContext());
    Calendar calendar= Calendar.getInstance();
    int dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK);
    private int clientID;
    private Training training;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_training2);

        setup();
    }


    protected void setup(){
        Bundle bundle = getIntent().getExtras();
        clientID = bundle.getInt("clientID");
        training = dbHandler.getTraining(clientID, dayOfWeek);
        Intent intentTraining = new Intent(this,StarTrainingActivity.class);
        buttonStartTraining.setOnClickListener(view -> {
            intentTraining.putExtra("training", (Parcelable) training);
            startActivity(intentTraining);
        });
    }

}
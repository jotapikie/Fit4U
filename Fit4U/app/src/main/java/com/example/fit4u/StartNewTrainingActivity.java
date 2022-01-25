package com.example.fit4u;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.os.Parcelable;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.Calendar;

import androidx.appcompat.app.AppCompatActivity;

import domain.Training;

public class StartNewTrainingActivity extends AppCompatActivity {
    DBHandler dbHandler = new DBHandler(getBaseContext());
    Calendar calendar= Calendar.getInstance();
    private TextView buttonStartTraining;
    private TextView estimatedTimeUI;
    private TextView estimatedCaloriesUI;
    int dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK);
    private int clientID;
    private Training training;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_training);
        buttonStartTraining= findViewById(R.id.buttonStartTraining);
        estimatedTimeUI= findViewById(R.id.texto2);
        estimatedCaloriesUI=findViewById(R.id.texto3);

        setup();
    }


    protected void setup(){
        Bundle bundle = getIntent().getExtras();
        clientID = bundle.getInt("clientID");
        training = dbHandler.getTraining(clientID, dayOfWeek);
        //adapter = new ArrayAdapter<>(StartNewTrainingActivity.this, android.R.layout);
        String sTime="Estimated time:"+training.getDuration().first+":"+training.getDuration().second;
        String sCalories="Estimated calories to be burned: "+training.getTotalCaloriesBurned()+" kCal";
        estimatedTimeUI.setText(sTime);
        estimatedCaloriesUI.setText(sCalories);

        Intent intentTraining = new Intent(this,StartTraning.class);
        buttonStartTraining.setOnClickListener(view -> {
            intentTraining.putExtra("training", (Parcelable) training);
            startActivity(intentTraining);
        });
    }

}
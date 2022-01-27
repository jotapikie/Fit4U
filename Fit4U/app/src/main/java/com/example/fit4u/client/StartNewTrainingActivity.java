package com.example.fit4u.client;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.widget.TextView;

import java.util.Calendar;
import java.util.Locale;

import androidx.appcompat.app.AppCompatActivity;

import com.example.fit4u.DBHandler;
import com.example.fit4u.R;

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
        estimatedTimeUI= findViewById(R.id.estimatedTime);
        estimatedCaloriesUI=findViewById(R.id.estimatedCalories);
        setup();
    }


    protected void setup(){
        Bundle bundle = getIntent().getExtras();
        clientID = bundle.getInt("clientID");
        training = dbHandler.getTraining(clientID, dayOfWeek);
        if (training==null){
            finish();
        }
        String timeFormatted = String.format(Locale.getDefault(),"%02d:%02d", training.getDuration().first,training.getDuration().second);
        String sTime="Estimated time:  "+timeFormatted;
        String sCalories="Estimated calories to be burned: "+training.getTotalCaloriesBurned()+" kCal";
        estimatedTimeUI.setText(sTime);
        estimatedCaloriesUI.setText(sCalories);

        Intent intentTraining = new Intent(this, StartTraningTimerActivity.class);
        buttonStartTraining.setOnClickListener(view -> {
            intentTraining.putExtra("training",  training.getPlan());
            startActivity(intentTraining);
        });
    }

}
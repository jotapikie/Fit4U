package com.example.fit4u;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.util.Map;

import domain.Training;
import domain.User;

public class StartTraning extends AppCompatActivity {
    private Chronometer chronometer;
    private Button startStopButton;
    private TextView exerciseName;
    private boolean running=false;
    private Training training;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start_traning);
        chronometer= findViewById(R.id.chronometer);
        startStopButton= findViewById(R.id.startStopButton);
        exerciseName= findViewById((R.id.exerciseName1));
        setup();
    }

    public void setup(){
        Bundle bundle = getIntent().getExtras();
        training = bundle.getParcelable("training");
        for (Map.Entry entry: training.getPlan().entrySet()){
            exerciseName

        }
        startStopButton.setOnClickListener(view -> {

        });

    }
    public void startChronometer(View v) {
        //ao começar conta o tempo e o texto muda para "stop"
        //https://youtu.be/RLnb4vVkftc
    }

}
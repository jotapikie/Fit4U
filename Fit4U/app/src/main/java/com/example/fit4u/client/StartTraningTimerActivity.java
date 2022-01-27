package com.example.fit4u.client;

import androidx.appcompat.app.AppCompatActivity;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.TextView;
import android.widget.Toast;

import com.example.fit4u.R;

import org.w3c.dom.Text;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Locale;
import java.util.Map;
import java.util.concurrent.Semaphore;

import domain.Exercice;
import domain.Training;
import domain.User;

public class StartTraningTimerActivity extends AppCompatActivity {

    private long mTimeLeftInMillis;
    private CountDownTimer chronometer;
    private TextView timerView;
    private Button startStopButton;
    private TextView exerciseName;
    private boolean running=true;
    private Training training;
    private TextView congratzView;

    private MediaPlayer nextExerciseSound;
    private MediaPlayer congratz ;


    @Override
    protected synchronized void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start_traning);
        timerView= findViewById(R.id.chronometer);
        startStopButton= findViewById(R.id.startStopButton);
        exerciseName= findViewById((R.id.exerciseName1));
        congratzView= findViewById(R.id.congratz);
        try {
            setup();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


    public synchronized void setup() throws InterruptedException {
        Bundle bundle = getIntent().getExtras();
        nextExerciseSound= MediaPlayer.create(this, R.raw.next_exercise);
        congratz= MediaPlayer.create(this, R.raw.congratz);
        training = new Training((HashMap<Exercice, Integer>) bundle.getSerializable("training"));
        String caloriesMessage="Congratulations!\n\nYou lost "+training.getTotalCaloriesBurned()+" calories today!";
        congratzView.setText(caloriesMessage);
        for (Map.Entry<Exercice,Integer> entry: training.getPlan().entrySet()){
            mTimeLeftInMillis= entry.getValue()*60000;
            String exName= entry.getKey().getName();
            exerciseName.setText(exName);
            int minutes= (int) (mTimeLeftInMillis / 1000) / 60;
            int seconds= (int) (mTimeLeftInMillis / 1000) % 60;
            String timeLeftFormatted = String.format(Locale.getDefault(),"%02d:%02d", minutes,seconds);
            timerView.setText(timeLeftFormatted);
            nextExerciseSound.start();
        }
        startStopButton.setOnClickListener(view -> {
            if (running){
                startTimer();
            }else{
                stopTimer();
            }
        });

    }


    public synchronized void forEachExer() throws InterruptedException {

    }

    public synchronized void startTimer() {
        chronometer = new CountDownTimer(mTimeLeftInMillis, 1000) {
            @Override
            public void onTick(long l) {
                mTimeLeftInMillis= l;
                updateCountDownText();
            }

            @Override
            public void onFinish() {
                congratz.start();
                congratzView.setVisibility(View.VISIBLE);

            }
        }.start();
        running=false;
        startStopButton.setText("Pause");

    }


    private synchronized void stopTimer(){
        chronometer.cancel();
        running=true;
        startStopButton.setText("Resume");

    }

    private synchronized void updateCountDownText(){
        int minutes= (int) (mTimeLeftInMillis / 1000) / 60;
        int seconds= (int) (mTimeLeftInMillis / 1000) % 60;
        String timeLeftFormatted = String.format(Locale.getDefault(),"%02d:%02d", minutes,seconds);
        timerView.setText(timeLeftFormatted);
    }



}
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

import java.util.Locale;
import java.util.Map;

import domain.Exercice;
import domain.Training;
import domain.User;

public class StartTraningTimerActivity extends AppCompatActivity {

    private long mTimeLeftInMillis;
    private CountDownTimer chronometer;
    private TextView timerView;
    private Button startStopButton;
    private TextView exerciseName;
    private boolean running=false;
    private boolean next;

    final MediaPlayer nextExerciseSound = MediaPlayer.create(this, R.raw.next_exercise);
    final MediaPlayer congratz = MediaPlayer.create(this, R.raw.congratz);


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start_traning);
        timerView= findViewById(R.id.chronometer);
        startStopButton= findViewById(R.id.startStopButton);
        exerciseName= findViewById((R.id.exerciseName1));
        try {
            setup();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


    public void setup() throws InterruptedException {
        Bundle bundle = getIntent().getExtras();
        Training training = bundle.getParcelable("training");
        for (Map.Entry<Exercice,Integer> entry: training.getPlan().entrySet()){
            next=true;
            mTimeLeftInMillis= entry.getValue()*60000;
            String exName= entry.getKey().getName();
            exerciseName.setText(exName);
            int minutes= (int) (mTimeLeftInMillis / 1000) / 60;
            int seconds= (int) (mTimeLeftInMillis / 1000) % 60;
            String timeLeftFormatted = String.format(Locale.getDefault(),"%02d:%02d", minutes,seconds);
            timerView.setText(timeLeftFormatted);

        startStopButton.setOnClickListener(view -> {
            if (running){
                startTimer();
            }else{
                stopTimer();
            }
        });
        while(next){
            Thread.sleep(1000);
        }
        nextExerciseSound.start();
        betweenExercise();
        }

        congratz.start();


    }


    public void startTimer() {
        chronometer = new CountDownTimer(mTimeLeftInMillis, 1000) {
            @Override
            public void onTick(long l) {
                mTimeLeftInMillis= l;
                updateCountDownText();
            }

            @Override
            public void onFinish() {
                next=false;
            }
        }.start();
        running=true;
        startStopButton.setText("Pause");

    }


    private void stopTimer(){
        chronometer.cancel();
        running=false;
        startStopButton.setText("Resume");

    }

    private void updateCountDownText(){
        int minutes= (int) (mTimeLeftInMillis / 1000) / 60;
        int seconds= (int) (mTimeLeftInMillis / 1000) % 60;
        String timeLeftFormatted = String.format(Locale.getDefault(),"%02d:%02d", minutes,seconds);
        timerView.setText(timeLeftFormatted);
    }


    public void betweenExercise() throws InterruptedException {
        Thread.sleep(10000);
    }

}
package com.example.fit4u.client;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.fit4u.DBHandler;
import com.example.fit4u.MyListAdapter;
import com.example.fit4u.MyListData;
import com.example.fit4u.R;

import domain.Exercice;
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

        List<MyListData> myListData = new ArrayList<>();
        for(Map.Entry<Exercice, Integer> e: training.getPlan().entrySet()){
            int i= (int) (e.getValue()*e.getKey().getCaloriesBurnPerMin());
            myListData.add(new MyListData(e.getKey().getName(), e.getValue().toString(), String.valueOf(i)));
        }

        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        MyListAdapter adapter = new MyListAdapter(myListData);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);


    }

}
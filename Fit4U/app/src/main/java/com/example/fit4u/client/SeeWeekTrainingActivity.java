package com.example.fit4u.client;

import android.os.Bundle;
import android.os.Trace;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.fit4u.*;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Map;

import domain.Exercice;
import domain.Training;
import domain.TrainingPlan;

public class SeeWeekTrainingActivity extends AppCompatActivity {
    DBHandler dbHandler = new DBHandler(getBaseContext());
    Calendar calendar= Calendar.getInstance();
    int dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK);
    public int clientID;
    public Training trainingPlan;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_week_training);
        Bundle bundle = getIntent().getExtras();
        clientID = bundle.getInt("clientID");
        trainingPlan= dbHandler.getTraining(clientID, dayOfWeek);
        List<MyListData> myListData = new ArrayList<>();
        for(Map.Entry<Exercice, Integer> e: trainingPlan.getPlan().entrySet()){
                    int i= (int) (e.getValue()*e.getKey().getCaloriesBurnPerMin());
                    myListData.add(new MyListData(e.getKey().getName(), e.getValue().toString(), String.valueOf(i)));
                }

        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        MyListAdapter2 adapter = new MyListAdapter2(myListData);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);
    }
}
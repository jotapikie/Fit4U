package com.example.fit4u.client;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.fit4u.*;

public class SeeWeekTrainingActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_week_training);
        MyListData[] myListData = new MyListData[]{
                new MyListData("Ex1"),
                new MyListData("Ex2"),
                new MyListData("Ex3"),
                new MyListData("Ex4"),
                new MyListData("Ex5"),
                new MyListData("Ex6"),
                new MyListData("Ex7"),
        };

        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        MyListAdapter2 adapter = new MyListAdapter2(myListData);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);
    }
}
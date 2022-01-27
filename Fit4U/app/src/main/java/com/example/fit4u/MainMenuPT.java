package com.example.fit4u;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.example.fit4u.client.SeeNutritionalPlanActivity;
import com.example.fit4u.client.SeeWeekTrainingActivity;
import com.example.fit4u.client.StartNewTrainingActivity;

public class MainMenuPT extends AppCompatActivity {

    private String username;
    private TextView usernameView;
    private TextView buttonStartNewTraining;
    private TextView buttonSeeNutritionalPlan;
    private TextView buttonSeeWeekTraining;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu_pt);

        usernameView = findViewById(R.id.username);
        buttonStartNewTraining = findViewById(R.id.start_training_button);
        buttonSeeNutritionalPlan = findViewById(R.id.see_nutritional_button);
        buttonSeeWeekTraining = findViewById(R.id.see_training_button);

        setup();
    }


    protected void setup(){
        Bundle bundle = getIntent().getExtras();
        username = bundle.getString("username");
        usernameView.setText(username);
        Intent intentStartNewTraining = new Intent(this, StartNewTrainingActivity.class);
        Intent intentSeeNutritionalPlan = new Intent(this, SeeNutritionalPlanActivity.class);
        Intent intentSeeWeekTrainings = new Intent(this, SeeWeekTrainingActivity.class);

        buttonStartNewTraining.setOnClickListener(view -> {
            intentStartNewTraining.putExtra("key", username);
            startActivity(intentStartNewTraining);
        });

        buttonSeeNutritionalPlan.setOnClickListener(view -> {
            intentSeeNutritionalPlan.putExtra("key", username);
            startActivity(intentSeeNutritionalPlan);
        });

        buttonSeeWeekTraining.setOnClickListener(view -> {
            intentSeeWeekTrainings.putExtra("key", username);
            startActivity(intentSeeWeekTrainings);
        });


    }
}
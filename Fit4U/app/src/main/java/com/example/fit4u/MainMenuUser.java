package com.example.fit4u;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Pair;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainMenuUser extends AppCompatActivity {

    private String username;
    private int clientID;
    private TextView usernameView;
    private TextView buttonStartNewTraining;
    private TextView buttonSeeNutritionalPlan;
    private TextView buttonSeeWeekTraining;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu_user);

        usernameView = findViewById(R.id.username);
        buttonStartNewTraining = findViewById(R.id.start_training_button);
        buttonSeeNutritionalPlan = findViewById(R.id.see_nutritional_button);
        buttonSeeWeekTraining = findViewById(R.id.see_training_button);

        setup();
    }


    protected void setup(){
        Bundle bundle = getIntent().getExtras();
        username = bundle.getString("username");
        clientID = bundle.getInt("clientID");
        usernameView.setText(username);
        Intent intentStartNewTraining = new Intent(this,StartNewTrainingActivity.class);
        Intent intentSeeNutritionalPlan = new Intent(this, SeeNutritionalPlanActivity.class);
        Intent intentSeeWeekTrainings = new Intent(this, SeeWeekTrainingActivity.class);

        buttonStartNewTraining.setOnClickListener(view -> {
            intentStartNewTraining.putExtra("clientID", clientID);
            startActivity(intentStartNewTraining);
        });

        buttonSeeNutritionalPlan.setOnClickListener(view -> {
            intentSeeNutritionalPlan.putExtra("clientID", clientID);
            startActivity(intentSeeNutritionalPlan);
        });

        buttonSeeWeekTraining.setOnClickListener(view -> {
            intentSeeWeekTrainings.putExtra("clientID", clientID);
            startActivity(intentSeeWeekTrainings);
        });


    }
}
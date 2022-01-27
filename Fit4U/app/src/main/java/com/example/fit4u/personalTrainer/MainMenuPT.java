package com.example.fit4u.personalTrainer;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.fit4u.R;
import com.example.fit4u.client.SeeNutritionalPlanActivity;
import com.example.fit4u.client.SeeWeekTrainingActivity;
import com.example.fit4u.client.StartNewTrainingActivity;

public class MainMenuPT extends AppCompatActivity {

    private String username;
    private TextView usernameView;
    private TextView buttonCreateTrainingPlan;
    private TextView buttonCreateNutritionalPlan;
    private TextView buttonAnexUserWithoutPt;
    private TextView buttonChangeTrainingPlan;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu_pt);

        usernameView = findViewById(R.id.usernamePT);
        buttonCreateTrainingPlan = findViewById(R.id.create_training_plan);
        buttonCreateNutritionalPlan = findViewById(R.id.create_nutritional_plan);
        buttonAnexUserWithoutPt = findViewById(R.id.anex_user);
        buttonChangeTrainingPlan = findViewById(R.id.change_training_plan);

        setup();
    }


    protected void setup(){
        Bundle bundle = getIntent().getExtras();
        username = bundle.getString("username");
        usernameView.setText(username);
        Intent intentCreateTrainingPlan = new Intent(this, CreateTrainingPlanActivity.class);
        Intent intentCreateNutritionalPlan = new Intent(this, CreateNutritionalPlanActivity.class);
        Intent intentAnexUser = new Intent(this, AnexUserActivity.class);
        Intent intentChangeNutritionalPlan = new Intent(this, ChangeNutritionalPlanActivity.class);

        buttonCreateTrainingPlan.setOnClickListener(view -> {
            intentCreateTrainingPlan.putExtra("key", username);
            startActivity(intentCreateNutritionalPlan);
        });

        buttonCreateNutritionalPlan.setOnClickListener(view -> {
            intentCreateNutritionalPlan.putExtra("key", username);
            startActivity(intentCreateNutritionalPlan);
        });

        buttonAnexUserWithoutPt.setOnClickListener(view -> {
            intentAnexUser.putExtra("key", username);
            startActivity(intentAnexUser);
        });

        buttonChangeTrainingPlan.setOnClickListener(view -> {
            intentChangeNutritionalPlan.putExtra("key", username);
            startActivity(intentChangeNutritionalPlan);
        });


    }
}
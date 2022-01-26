package com.example.fit4u;

import android.content.Intent;
import android.os.Bundle;
import android.util.Pair;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import domain.User;

public class LoginActivity extends AppCompatActivity {
    private EditText usernameEditTest;
    private EditText passwordEditTest;
    private Button loginButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        usernameEditTest = findViewById(R.id.username);
        passwordEditTest = findViewById(R.id.password);
        loginButton = findViewById(R.id.button);

        setup();
    }

    protected void setup(){
        DBHandler dbHandler = new DBHandler(getBaseContext());
        Intent intentClient = new Intent(this,MainActivity.class);
        Intent intentTrainer = new Intent(this, MainActivity.class);
        loginButton.setOnClickListener(view -> {
            User result= dbHandler.checkLogin(usernameEditTest.getText().toString(), passwordEditTest.getText().toString());
            if (result==null){
                Toast.makeText(getApplicationContext(), "wrong username or password", Toast.LENGTH_LONG).show();
            }else{
                if (result.getType()==0){
                    intentClient.putExtra("clientID", result.getID());
                    intentClient.putExtra("username", result.getUsername());
                    startActivity(intentClient);
                }else{
                    intentTrainer.putExtra("trainerID", result.getID());
                    intentTrainer.putExtra("username", result.getUsername());
                    startActivity(intentTrainer);
                }
                finish();
            }
        });
    }

}
package com.example.fit4u;

import android.content.Intent;
import android.os.Bundle;
import android.util.Pair;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class LoginActivity extends AppCompatActivity {
    private DBHandler db;
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
        Intent intentClient = new Intent(this,MainActivity.class);
        Intent intentTrainer = new Intent(this, MainActivity.class);
        loginButton.setOnClickListener(view -> {
            Pair<Integer, Integer> result=db.checkLogin(usernameEditTest.getText().toString(), passwordEditTest.getText().toString());
            if (result==null){
                Toast.makeText(getApplicationContext(), "wrong username or password", Toast.LENGTH_LONG).show();
            }else{
                if (result.second==0){
                    intentClient.putExtra("key", result.first);
                    startActivity(intentClient);
                }else{
                    intentTrainer.putExtra("key", result.first);
                    startActivity(intentTrainer);
                }
                finish();
            }
        });
    }

}
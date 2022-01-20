package com.example.fit4u;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

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

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                db.checkLogin(usernameEditTest.getText().toString(), passwordEditTest.getText().toString());
            }
        });
    }

}
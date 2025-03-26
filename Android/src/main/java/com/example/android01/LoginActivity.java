package com.example.android01;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import androidx.appcompat.app.AppCompatActivity;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        EditText email = findViewById(R.id.user_name);
        EditText password = findViewById(R.id.user_password);
        Button signin = findViewById(R.id.sign_try);
        Button calcu = findViewById(R.id.change_calculator);

        signin.setOnClickListener(v -> {
            String getemail = email.getText().toString();
            String getpassword = password.getText().toString();

            Intent intent = new Intent(LoginActivity.this, ProfileActivity.class);
            intent.putExtra("email", getemail);
            intent.putExtra("password", getpassword);
            startActivity(intent);
        });

        calcu.setOnClickListener(v -> {
            Intent intent = new Intent(LoginActivity.this, CalculatorActivity.class);
            startActivity(intent);
        });
    }
}
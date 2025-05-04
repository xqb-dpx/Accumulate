package com.example.android;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        EditText email = findViewById(R.id.user_name);
        EditText password = findViewById(R.id.user_password);
        Button signin = findViewById(R.id.sign_try);

        signin.setOnClickListener(v -> {
            if (!email.getText().toString().isEmpty() && !password.getText().toString().isEmpty()) {
                if (email.getText().toString().equals("mail@user.com") && password.getText().toString().equals("123")) {
                    Intent intent = new Intent(LoginActivity.this, Contact.class);
                    startActivity(intent);
                } else {
                    Toast.makeText(LoginActivity.this, "Invalid email or password", Toast.LENGTH_SHORT).show();
                }
            } else {
                Toast.makeText(LoginActivity.this, "Please enter your username and password", Toast.LENGTH_LONG).show();
            }
//            String getemail = email.getText().toString();
//            String getpassword = password.getText().toString();
//            intent.putExtra("email", getemail);
//            intent.putExtra("password", getpassword);
        });
    }
}
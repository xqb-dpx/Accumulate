package com.example.android;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;

public class MenuActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.item_contact);

        Button calcu = findViewById(R.id.change_calculator);
        Button music = findViewById(R.id.change_music);
        Button db = findViewById(R.id.change_db);
        Button login = findViewById(R.id.change_login);

        calcu.setOnClickListener(v -> {
            Intent intent = new Intent(MenuActivity.this, CalculatorActivity.class);
            startActivity(intent);
        });

        music.setOnClickListener(v -> {
            Intent intent = new Intent(MenuActivity.this, MusicActivity.class);
            startActivity(intent);
        });

        db.setOnClickListener(v -> {
            Intent intent = new Intent(MenuActivity.this, DatabaseActivity.class);
            startActivity(intent);
        });

        login.setOnClickListener(v -> {
            Intent intent = new Intent(MenuActivity.this, LoginActivity.class);
            startActivity(intent);
        });
    }
}
package com.example.musicplayer;

import android.content.Intent;
import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.room.Room;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

public class MainActivity extends AppCompatActivity {

    EditText edtLink;
    Button btnSave, btnShow;
    AppDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        edtLink = findViewById(R.id.edtLink);
        btnSave = findViewById(R.id.btnSave);
        btnShow = findViewById(R.id.btnShow);

        db = Room.databaseBuilder(getApplicationContext(), AppDatabase.class, "music_db").allowMainThreadQueries().build();

        btnSave.setOnClickListener(v -> {
            String link = edtLink.getText().toString().trim();
            if (!link.isEmpty()) {
                db.musicDao().insert(new MusicLink(link));
                Toast.makeText(this, "Link Saved Successfully.", Toast.LENGTH_SHORT).show();
                edtLink.setText("");
            } else {
                Toast.makeText(this, "Please Enter A Link!", Toast.LENGTH_SHORT).show();
            }
        });

        btnShow.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, MusicListActivity.class);
            startActivity(intent);
        });
    }
}


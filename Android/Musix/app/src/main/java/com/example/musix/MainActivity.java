package com.example.musix;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.os.Build;
import android.os.Bundle;

import android.provider.MediaStore;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;
import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.io.File;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    TextView noMusicTextView;
    ArrayList<AudioModel> songsList = new ArrayList<>();
    String permission;

    @SuppressLint("ObsoleteSdkInt")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        recyclerView = findViewById(R.id.recycler_view);
        noMusicTextView = findViewById(R.id.no_song_text);

        // Get SharedPreferences
        SharedPreferences sp = getSharedPreferences("Permission", MODE_PRIVATE);
        boolean granted = sp.getBoolean("granted", false);

        if (granted) {
            Toast.makeText(MainActivity.this, "Welcome Back!", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(MainActivity.this, "Hello Master", Toast.LENGTH_SHORT).show();
        }
        MusicListAdapter musicListAdapter = new MusicListAdapter(songsList, getApplicationContext());

        String[] projection = {
                MediaStore.Audio.Media.TITLE,
                MediaStore.Audio.Media.DATA,
                MediaStore.Audio.Media.DURATION
        };
        String selection = MediaStore.Audio.Media.IS_MUSIC + " != 0";

        // Receive Data from Adapter
        @SuppressLint("Recycle") Cursor cursor = getContentResolver().query(MediaStore.Audio.Media.EXTERNAL_CONTENT_URI, null, null, null, null);
        while (cursor.moveToNext()) {
            AudioModel songData = new AudioModel(cursor.getString(1), cursor.getString(0), cursor.getString(2));
            if (new File(songData.getPath()).exists()) {
                songsList.add(songData);
            }
            if (songsList.isEmpty()) {
                noMusicTextView.setVisibility(View.VISIBLE);
            } else {
                // recyclerView
                recyclerView.setLayoutManager(new LinearLayoutManager(this));
                recyclerView.setAdapter(musicListAdapter);
            }
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (recyclerView != null) {
            recyclerView.setAdapter(new MusicListAdapter(songsList, getApplicationContext()));
        }
    }
}
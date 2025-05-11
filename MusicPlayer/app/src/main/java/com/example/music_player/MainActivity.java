package com.example.music_player;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.ContentResolver;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;

import android.os.Handler;
import android.provider.MediaStore;
import android.util.Log;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    List<Song> songs = new ArrayList<>();
    SongAdapter songAdapter;

    private MediaPlayer mediaPlayer;
    SeekBar seekBar;
    Button btnPlayPause, btnNext, btnPrev;
    private int currentIndex = -1;
    Handler handler = new Handler();

    Runnable updateSeekBar;

    @SuppressLint({"SetTextI18n", "ObsoleteSdkInt"})
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        songs = new ArrayList<>();
        setContentView(R.layout.activity_main);
        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        String permission;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            permission = Manifest.permission.READ_MEDIA_AUDIO;
        } else {
            permission = Manifest.permission.READ_EXTERNAL_STORAGE;
        }

        if (ContextCompat.checkSelfPermission(this, permission) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this,
                    new String[]{permission}, 1);
        } else {
            loadSongs();
        }


        songAdapter = new SongAdapter(songs, this, this::playSong);


        recyclerView.setAdapter(songAdapter);
        seekBar = findViewById(R.id.seekBar);
        btnPlayPause = findViewById(R.id.btnPlayPause);
        btnNext = findViewById(R.id.btnNext);
        btnPrev = findViewById(R.id.btnPrev);



        btnPlayPause.setOnClickListener(v -> {
            if (mediaPlayer != null) {
                if (mediaPlayer.isPlaying()) {
                    mediaPlayer.pause();
                    btnPlayPause.setText("Play");
                } else {
                    mediaPlayer.start();
                    btnPlayPause.setText("Pause");
                }
            }
        });

        btnNext.setOnClickListener(v -> playNext());

        btnPrev.setOnClickListener(v -> playPrevious());

        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                if (mediaPlayer != null && fromUser) {
                    mediaPlayer.seekTo(progress);
                }
            }

            public void onStartTrackingTouch(SeekBar seekBar) {
            }

            public void onStopTrackingTouch(SeekBar seekBar) {
            }
        });
    }

    @SuppressLint("SetTextI18n")
    private void playSong(Song song) {
        String path = song.getPath();
        try {
            if (mediaPlayer != null) {
                mediaPlayer.stop();
                mediaPlayer.release();
            }

            mediaPlayer = new MediaPlayer();
            mediaPlayer.setDataSource(song.getPath());
            mediaPlayer.prepare();
            mediaPlayer.start();

            currentIndex = songs.indexOf(song);
        } catch (Exception e) {
            Log.e("MainActivity", "Error playing song: " + e.getMessage(), e);
            Toast.makeText(this, "Error playing song: " + e.getMessage(), Toast.LENGTH_SHORT).show();
        }
        try {
            mediaPlayer = new MediaPlayer();
            mediaPlayer.setDataSource(path);
            mediaPlayer.prepare();
            mediaPlayer.start();
        } catch (IOException e) {
            Log.e("MediaPlayer", e.getMessage());
        }

        mediaPlayer = MediaPlayer.create(this, Uri.parse(songs.get(currentIndex).getPath()));
        mediaPlayer.start();
        btnPlayPause.setText("Pause");

        seekBar.setMax(mediaPlayer.getDuration());

        updateSeekBar = new Runnable() {
            public void run() {
                if (mediaPlayer != null) {
                    seekBar.setProgress(mediaPlayer.getCurrentPosition());
                    handler.postDelayed(this, 500);
                }
            }
        };
        handler.post(updateSeekBar);

        mediaPlayer.setOnCompletionListener(mp -> playNext());
    }

    private void playNext() {
        if (currentIndex + 1 < songs.size()) {
            playSong(songs.get(currentIndex + 1));
        }
    }

    private void playPrevious() {
        if (currentIndex - 1 >= 0) {
            playSong(songs.get(currentIndex - 1));
        }
    }

    @SuppressLint("NotifyDataSetChanged")
    private void loadSongs() {
        Log.d("MainActivity", "loadSongs() called");
        songs.clear();
        ContentResolver contentResolver = getContentResolver();
        Uri uri = MediaStore.Audio.Media.EXTERNAL_CONTENT_URI;

        String selection = MediaStore.Audio.Media.IS_MUSIC + " != 0";
        Cursor cursor = contentResolver.query(uri, null, selection, null, null);

        if (cursor != null && cursor.moveToFirst()) {
            int titleColumn = cursor.getColumnIndex(MediaStore.Audio.Media.TITLE);
            int pathColumn = cursor.getColumnIndex(MediaStore.Audio.Media._ID);
            if (titleColumn == -1 || pathColumn == -1) {
                Log.e("MainActivity", "Column not found!");
                return;
            }
            do {
                String title = cursor.getString(titleColumn);
                String path = cursor.getString(pathColumn);
                Log.d("MainActivity", "Found song: " + title + " @ " + path);
                songs.add(new Song(title, path));
            } while (cursor.moveToNext());
            cursor.close();
            songAdapter.notifyDataSetChanged();
        } else {
            Log.d("MainActivity", "No songs found or permission denied.");
        }

        songAdapter = new SongAdapter(songs, this, this::playSong);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(songAdapter);
    }

    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if (requestCode == 1 && grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            loadSongs();
        } else {
            Toast.makeText(this, "Permission denied to read audio files", Toast.LENGTH_SHORT).show();
        }
    }
}
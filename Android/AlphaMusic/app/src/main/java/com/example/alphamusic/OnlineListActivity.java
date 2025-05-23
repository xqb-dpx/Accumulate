package com.example.alphamusic;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import java.io.IOException;
import java.util.List;

public class OnlineListActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    Button btnPrev, btnStop, btnNext;
    MediaPlayer mediaPlayer;
    MusicDatabase db;
    List<MusicLink> musicLinks;
    int currentIndex = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.item_online);

        recyclerView = findViewById(R.id.recyclerView);
        btnPrev = findViewById(R.id.btnPrev);
        btnStop = findViewById(R.id.btnStop);
        btnNext = findViewById(R.id.btnNext);

        db = Room.databaseBuilder(getApplicationContext(), MusicDatabase.class, "music_db").allowMainThreadQueries().build();

        musicLinks = db.musicDao().getAllLinks();

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(new OnlineAdapter(musicLinks, (link, position) -> {
            playMusic(link.url);
            currentIndex = position;
        }));

        btnNext.setOnClickListener(v -> {
            if (currentIndex < musicLinks.size() - 1) {
                currentIndex++;
                playMusic(musicLinks.get(currentIndex).url);
            }
        });

        btnPrev.setOnClickListener(v -> {
            if (currentIndex > 0) {
                currentIndex--;
                playMusic(musicLinks.get(currentIndex).url);
            }
        });

        btnStop.setOnClickListener(v -> {
            if (mediaPlayer != null && mediaPlayer.isPlaying()) {
                mediaPlayer.stop();
                mediaPlayer.reset();
            }
        });
    }

    private void playMusic(String url) {
        if (mediaPlayer == null) {
            mediaPlayer = new MediaPlayer();
        } else {
            mediaPlayer.reset();
        }

        try {
            mediaPlayer.setDataSource(url);
            mediaPlayer.prepareAsync(); // Async preparation for streaming
            mediaPlayer.setOnPreparedListener(mp -> {
                mediaPlayer.start(); // Start playing once prepared
                Toast.makeText(getApplicationContext(), "Playing", Toast.LENGTH_SHORT).show();
            });
        } catch (IOException e) {
            e.printStackTrace();
            Toast.makeText(OnlineListActivity.this, "An error!", Toast.LENGTH_SHORT).show();
        }
    }
}
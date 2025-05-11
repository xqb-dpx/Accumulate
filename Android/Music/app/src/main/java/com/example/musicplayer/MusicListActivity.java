package com.example.musicplayer;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import com.example.musicplayer.MusicLink;

import java.io.IOException;
import java.util.List;

public class MusicListActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    Button btnPrev, btnStop, btnNext;
    MediaPlayer mediaPlayer;
    AppDatabase db;
    List<MusicLink> musicLinks;
    int currentIndex = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_music_list);

        recyclerView = findViewById(R.id.recyclerView);
        btnPrev = findViewById(R.id.btnPrev);
        btnStop = findViewById(R.id.btnStop);
        btnNext = findViewById(R.id.btnNext);

        db = Room.databaseBuilder(getApplicationContext(), AppDatabase.class, "music_db")
                .allowMainThreadQueries()
                .build();

        musicLinks = db.musicDao().getAllLinks();

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(new MusicAdapter(musicLinks, (link, position) -> {
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
                Toast.makeText(MusicListActivity.this, "در حال پخش ", Toast.LENGTH_SHORT).show();
            });
        } catch (IOException e) {
            e.printStackTrace();
            Toast.makeText(MusicListActivity.this, "مشکلی در پخش .", Toast.LENGTH_SHORT).show();
        }
    }
}

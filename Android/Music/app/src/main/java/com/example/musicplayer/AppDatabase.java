package com.example.musicplayer;

import androidx.room.Database;
import androidx.room.RoomDatabase;

@Database(entities = {MusicLink.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {
    public abstract MusicDao musicDao();
}
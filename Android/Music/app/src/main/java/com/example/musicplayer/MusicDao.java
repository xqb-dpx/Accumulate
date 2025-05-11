package com.example.musicplayer;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface MusicDao {
    @Insert
    void insert(MusicLink musicLink);

    @Query("SELECT * FROM MusicLink")
    List<MusicLink> getAllLinks();
}

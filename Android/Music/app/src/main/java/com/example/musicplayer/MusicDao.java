package com.example.musicplayer;

import androidx.room.*;

import java.util.List;

@Dao
public interface MusicDao {
    @Query("SELECT * FROM MusicLink")
    List<MusicLink> getAllLinks();

    @Insert
    void insert(MusicLink musicLink);

    @Update
    void update(MusicLink musicLink);

    @Delete
    void delete(MusicLink musicLink);
}

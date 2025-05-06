package com.example.music_player;

import android.content.Context;
import android.media.MediaPlayer;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public class SongAdapter extends RecyclerView.Adapter<SongAdapter.ViewHolder> {
    List<Song> songs;
    Context context;
    OnItemClickListener listener;
    MediaPlayer mediaPlayer;

    public SongAdapter(List<Song> songs, Context context, OnItemClickListener listener) {
        this.songs = songs;
        this.context = context;
        this.listener = listener;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView songTitle;
        public ViewHolder(View itemView, OnItemClickListener listener) {
            super(itemView);
            songTitle = itemView.findViewById(R.id.songTitle);
            itemView.setOnClickListener(v ->{
                int pos = getAdapterPosition();
                if (pos != RecyclerView.NO_POSITION) {
                    listener.onItemClick(songs.get(pos));
                }
            });
        }
    }

    @Override
    public SongAdapter.@NotNull ViewHolder onCreateViewHolder(@NotNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_song, parent, false);
        return new ViewHolder(view, listener);
    }

    @Override
    public void onBindViewHolder(SongAdapter.ViewHolder holder, int position) {
        Song song = songs.get(position);
        holder.songTitle.setText(song.getTitle());

        holder.itemView.setOnClickListener(v -> {
            if (mediaPlayer != null && mediaPlayer.isPlaying()) {
                mediaPlayer.stop();
                mediaPlayer.release();
            }
            mediaPlayer = MediaPlayer.create(context, Uri.parse(song.getPath()));
            mediaPlayer.start();
        });
    }

    @Override
    public int getItemCount() {
        return songs.size();
    }

    public interface OnItemClickListener {
        void onItemClick(Song song);
    }
}
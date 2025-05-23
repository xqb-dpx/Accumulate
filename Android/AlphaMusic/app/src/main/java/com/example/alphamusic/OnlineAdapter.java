package com.example.alphamusic;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public class OnlineAdapter extends RecyclerView.Adapter<OnlineAdapter.ViewHolder> {
    private List<MusicLink> list;
    private OnItemClickListener listener;

    public interface OnItemClickListener {
        void onItemClick(MusicLink link, int position);
    }

    public OnlineAdapter(List<MusicLink> list, OnItemClickListener listener) {
        this.list = list;
        this.listener = listener;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView textView;

        public ViewHolder(View itemView) {
            super(itemView);
            textView = itemView.findViewById(android.R.id.text1);
        }

        public void bind(final MusicLink link, final int position, final OnItemClickListener listener) {
            textView.setText(link.url);
            itemView.setOnClickListener(v -> listener.onItemClick(link, position));
        }
    }

    @Override
    public @NotNull ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(android.R.layout.simple_list_item_1, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.bind(list.get(position), position, listener);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }
}
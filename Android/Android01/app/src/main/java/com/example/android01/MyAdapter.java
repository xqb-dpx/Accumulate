package com.example.android01;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {

    private final List<String[]> data;

    public MyAdapter(List<String[]> data) {
        this.data = data;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_profile, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        String[] item = data.get(position);
        holder.got_email.setText(item[0]);
        holder.got_password.setText(item[1]);
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    static class MyViewHolder extends RecyclerView.ViewHolder {
        TextView got_email, got_password;

        MyViewHolder(View itemView) {
            super(itemView);
            got_email = itemView.findViewById(R.id.user_name);
            got_password = itemView.findViewById(R.id.user_password);
        }
    }
}
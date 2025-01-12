package com.example.android01;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class ProfileActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler_view);

        RecyclerView recyclerView = findViewById(R.id.recycler);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        String email = getIntent().getStringExtra("email");
        String password = getIntent().getStringExtra("password");

        List<String[]> data = new ArrayList<>();
        data.add(new String[]{email, password});

        MyAdapter adapter = new MyAdapter(data);
        recyclerView.setAdapter(adapter);
    }
}
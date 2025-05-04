package com.example.android;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class Contact extends AppCompatActivity {

    private String name;
    private String phone;
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

        ContactAdapter adapter = new ContactAdapter(data);
        recyclerView.setAdapter(adapter);
    }

    public Contact(String name, String phone) {
        this.name = name;
        this.phone = phone;
    }
    public String getName(){return name;}
    public String getPhone() {return phone;}
}
package com.example.android;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.Toast;
import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

@SuppressLint("CustomSplashScreen")
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        Handler main_handler = new Handler();
        Runnable main_runnable = () -> {
            Intent main_intent = new Intent(MainActivity.this.peekAvailableContext(), LoginActivity.class);
            MainActivity.this.startActivity(main_intent);
            Toast.makeText(MainActivity.this.getApplicationContext(), "Starting...", Toast.LENGTH_SHORT).show();
            finish();
        };
        main_handler.postDelayed(main_runnable, 3000);
    }
}

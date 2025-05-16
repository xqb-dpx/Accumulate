package com.example.musix;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.*;
import android.content.pm.PackageManager;
import android.net.ConnectivityManager;
import android.net.Network;
import android.net.NetworkInfo;
import android.os.Bundle;

import android.os.Handler;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

@SuppressLint("CustomSplashScreen")
public class SplashActivity extends AppCompatActivity {

    private BroadcastReceiver receiver;
    TextView netstat;
    ImageView netstatimg ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_splash);
//        checkOldPermission();
//        checkNewPermission();
        netstatimg = findViewById(R.id.connection_state_img);
        if (!checkNewPermission()) {
            requestPermission();
        } else {
            // Set SharedPreferences
            boolean permissions = checkOldPermission() || checkNewPermission();
            SharedPreferences sp = getSharedPreferences("Permission", MODE_PRIVATE);
            SharedPreferences.Editor editor = sp.edit();
            editor.putBoolean("granted", permissions);
            editor.apply();
            // Intent & Habdler
            new Handler().postDelayed(() -> {
                Intent intent = new Intent(SplashActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            }, 1500);
        }
    }

    boolean checkOldPermission() {
        int result = ContextCompat.checkSelfPermission(SplashActivity.this, Manifest.permission.READ_EXTERNAL_STORAGE);
        return result == PackageManager.PERMISSION_GRANTED;
    }

    boolean checkNewPermission() {
        int result = ContextCompat.checkSelfPermission(SplashActivity.this, Manifest.permission.READ_MEDIA_AUDIO);
        return result == PackageManager.PERMISSION_GRANTED;
    }

    void requestPermission() {
        if (ActivityCompat.shouldShowRequestPermissionRationale(SplashActivity.this, Manifest.permission.READ_EXTERNAL_STORAGE)) {
            Toast.makeText(SplashActivity.this, "Permission is required!", Toast.LENGTH_SHORT).show();
        } else if (ActivityCompat.shouldShowRequestPermissionRationale(SplashActivity.this, Manifest.permission.READ_MEDIA_AUDIO)) {
            Toast.makeText(SplashActivity.this, "Permission is required!", Toast.LENGTH_SHORT).show();
        } else {
            ActivityCompat.requestPermissions(SplashActivity.this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.READ_MEDIA_AUDIO}, 1);
        }
    }

    @SuppressLint("SetTextI18n")
    public void onReceive(Context context) {
        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = cm.getActiveNetworkInfo();
        Toast.makeText(SplashActivity.this, "Checking Network Info...", Toast.LENGTH_SHORT).show();
        if (netInfo != null && netInfo.isConnectedOrConnecting()) {
            netstat.setText("Online!");
            netstatimg.setImageResource(R.drawable.conn);
        } else {
            netstat.setText("Trying for find a network connection!\nPlease Check Internet Connection...");
            netstatimg.setImageResource(R.drawable.dis);
        }
    }

    @Override
    protected void onResume() {
        // Run BroadcastReceiver
        super.onResume();
        registerReceiver(receiver, new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION));
    }

    @Override
    protected void onPause() {
        // Close BroadcastReceiver
        super.onPause();
        unregisterReceiver(receiver);
    }
}


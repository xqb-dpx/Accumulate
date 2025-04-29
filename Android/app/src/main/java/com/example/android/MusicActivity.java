package com.example.android;

import android.annotation.SuppressLint;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.os.Handler;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

@SuppressLint("CustomSplashScreen")
public class MusicActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_music);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        GetNetStat();
        // registerReceiver(broadcastReceiver, new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION));
    }

//    BroadcastReceiver broadcastReceiver = new BroadcastReceiver() {
//        TextView txtstat = findViewById(R.id.netstat);
//        ImageView imgstat = findViewById(R.id.netstatimg);
//        public void onReceive(Context context, Intent intent) {
//            if (intent.getAction().equals("ConnectivityManager.CONNECTIVITY_ACTION")) {
//                if (GetNetStat() == 1) {
//                    imgstat.setImageResource(R.drawable.wificon);
//                    txtstat.setText("Wifi");
//                } else if (GetNetStat() == 2) {
//                    imgstat.setImageResource(R.drawable.datacon);
//                    txtstat.setText("Mobile");
//                } else if (GetNetStat() == 3) {
//                    imgstat.setImageResource(R.drawable.datacon);
//                    txtstat.setText("Ethernet");
//                } else if (GetNetStat() == 0) {
//                    imgstat.setImageResource(R.drawable.datacon);
//                    txtstat.setText("Ethernet");
//                }
//            }
//        }
//    };

    @SuppressLint("SetTextI18n")
    public void GetNetStat() {
        // int netstat = 0;
        TextView txtstat = findViewById(R.id.netstat);
        ImageView imgstat = findViewById(R.id.netstatimg);
        ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
        if (networkInfo != null && networkInfo.isConnected()) {
            if (networkInfo.getType() == ConnectivityManager.TYPE_WIFI) {
                // netstat = 1;
                imgstat.setImageResource(R.drawable.wificon);
                txtstat.setText("Wifi");
            } else if (networkInfo.getType() == ConnectivityManager.TYPE_MOBILE) {
                // netstat = 2;
                imgstat.setImageResource(R.drawable.datacon);
                txtstat.setText("Mobile");
            } else if (networkInfo.getType() == ConnectivityManager.TYPE_ETHERNET) {
                // netstat = 3;
                imgstat.setImageResource(R.drawable.ethcon);
                txtstat.setText("Ethernet");
            }
        } else {
            // netstat = 0;
            imgstat.setImageResource(R.drawable.wifidis);
            txtstat.setText("Offline");
        }
        //return netstat;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //unregisterReceiver(broadcastReceiver);
    }
}

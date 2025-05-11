package com.example.musik

import android.Manifest
import android.content.pm.PackageManager
import android.database.Cursor
import android.os.Bundle
import android.provider.MediaStore
import android.view.View
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.musik.MusicListAdapter
import java.io.File

class MainActivity : AppCompatActivity() {
    private lateinit var recyclerView: RecyclerView
    private lateinit var noMusicTextView: TextView
    private val songsList = ArrayList<AudioModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recyclerView = findViewById(R.id.recycler_view)
        noMusicTextView = findViewById(R.id.no_song_text)

        if (!checkPermission()) {
            requestPermission()
            return
        }

        val cursor: Cursor? = contentResolver.query(
            MediaStore.Audio.Media.EXTERNAL_CONTENT_URI,
            null,
            null,
            null,
            null
        )

        cursor?.use {
            while (it.moveToNext()) {
                val path = it.getString(1)
                val title = it.getString(0)
                val duration = it.getString(2)
                val songData = AudioModel(path, title, duration)

                if (File(songData.path).exists()) {
                    songsList.add(songData)
                }
            }
        }

        if (songsList.isEmpty()) {
            noMusicTextView.visibility = View.VISIBLE
        } else {
            recyclerView.layoutManager = LinearLayoutManager(this)
            recyclerView.adapter = MusicListAdapter(songsList, applicationContext)
        }
    }

    private fun checkPermission(): Boolean {
        val result1 = ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE)
        val result2 = ContextCompat.checkSelfPermission(this, Manifest.permission.READ_MEDIA_AUDIO)
        return result1 == PackageManager.PERMISSION_GRANTED && result2 == PackageManager.PERMISSION_GRANTED
    }

    private fun requestPermission() {
        if (ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.READ_EXTERNAL_STORAGE) ||
            ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.READ_MEDIA_AUDIO)
        ) {
            Toast.makeText(this, "Permission is required!", Toast.LENGTH_SHORT).show()
        } else {
            ActivityCompat.requestPermissions(
                this,
                arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.READ_MEDIA_AUDIO),
                1
            )
        }
    }

    override fun onResume() {
        super.onResume()
        recyclerView.adapter = MusicListAdapter(songsList, applicationContext)
    }
}
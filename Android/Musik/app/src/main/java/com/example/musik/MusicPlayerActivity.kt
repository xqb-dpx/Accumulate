package com.example.musik

import android.media.MediaPlayer
import android.os.Bundle
import android.os.Handler
import android.widget.ImageView
import android.widget.SeekBar
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import java.io.IOException
import java.util.concurrent.TimeUnit

class MusicPlayerActivity : AppCompatActivity() {
    private lateinit var titleTv: TextView
    private lateinit var currentTimeTv: TextView
    private lateinit var totalTimeTv: TextView
    private lateinit var seekBar: SeekBar
    private lateinit var pausePlay: ImageView
    private lateinit var nextBtn: ImageView
    private lateinit var previousBtn: ImageView
    private lateinit var musicIcon: ImageView

    private lateinit var songsList: ArrayList<AudioModel>
    private lateinit var currentSong: AudioModel
    private val mediaPlayer: MediaPlayer = MyMediaPlayer.getInstance()
    private var x = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_music_player)

        titleTv = findViewById(R.id.song_title)
        currentTimeTv = findViewById(R.id.current_time)
        totalTimeTv = findViewById(R.id.total_time)
        seekBar = findViewById(R.id.seek_bar)
        pausePlay = findViewById(R.id.pause_play)
        nextBtn = findViewById(R.id.next)
        previousBtn = findViewById(R.id.previous)
        musicIcon = findViewById(R.id.music_icon_big)

        titleTv.isSelected = true
        songsList = intent.getSerializableExtra("LIST") as ArrayList<AudioModel>

        setResourcesWithMusic()

        runOnUiThread(object : Runnable {
            override fun run() {
                mediaPlayer.let {
                    seekBar.max = it.duration
                    currentTimeTv.text = convertToMMSS(it.currentPosition.toString())

                    if (it.isPlaying) {
                        pausePlay.setImageResource(R.drawable.pause)
                        musicIcon.rotation = x++.toFloat()
                    } else {
                        pausePlay.setImageResource(R.drawable.play)
                        musicIcon.rotation = 0f
                    }
                }
                Handler().postDelayed(this, 100)
            }
        })

        seekBar.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(sb: SeekBar?, progress: Int, fromUser: Boolean) {
                if (mediaPlayer != null && fromUser) {
                    mediaPlayer.seekTo(progress)
                }
            }

            override fun onStartTrackingTouch(sb: SeekBar?) {}
            override fun onStopTrackingTouch(sb: SeekBar?) {}
        })
    }

    private fun setResourcesWithMusic() {
        currentSong = songsList[MyMediaPlayer.currentIndex]
        titleTv.text = currentSong.title
        totalTimeTv.text = convertToMMSS(currentSong.duration)

        pausePlay.setOnClickListener { pausePlay() }
        nextBtn.setOnClickListener { playNextMusic() }
        previousBtn.setOnClickListener { playPreviousMusic() }

        playMusic()
    }

    private fun playMusic() {
        mediaPlayer.reset()
        try {
            mediaPlayer.setDataSource(currentSong.path)
            mediaPlayer.prepare()
            mediaPlayer.start()
            seekBar.progress = 0
            seekBar.max = mediaPlayer.duration
        } catch (e: IOException) {
            e.printStackTrace()
        }
    }

    private fun pausePlay() {
        if (mediaPlayer.isPlaying) {
            mediaPlayer.pause()
        } else {
            mediaPlayer.start()
        }
    }

    private fun playNextMusic() {
        if (MyMediaPlayer.currentIndex < songsList.size - 1) {
            MyMediaPlayer.currentIndex++
            setResourcesWithMusic()
        }
    }

    private fun playPreviousMusic() {
        if (MyMediaPlayer.currentIndex > 0) {
            MyMediaPlayer.currentIndex--
            setResourcesWithMusic()
        }
    }

    companion object {
        fun convertToMMSS(duration: String): String {
            val millis = duration.toLong()
            return String.format(
                "%02d:%02d",
                TimeUnit.MILLISECONDS.toMinutes(millis) % TimeUnit.HOURS.toMinutes(1),
                TimeUnit.MILLISECONDS.toSeconds(millis) % TimeUnit.MINUTES.toSeconds(1)
            )
        }
    }
}
package com.example.musik

import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class MusicListAdapter(
    private val songsList: ArrayList<AudioModel>,
    private val context: Context
) : RecyclerView.Adapter<MusicListAdapter.ViewHolder>() {

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val titleTextView: TextView = itemView.findViewById(R.id.music_title_text)
        val iconImageView: ImageView = itemView.findViewById(R.id.icon_view)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.recycler_item, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int = songsList.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val songData = songsList[position]
        holder.titleTextView.text = songData.title

        holder.titleTextView.setTextColor(
            if (MyMediaPlayer.currentIndex == position) Color.RED
            else Color.BLACK
        )

        holder.itemView.setOnClickListener {
            MyMediaPlayer.getInstance().reset()
            MyMediaPlayer.currentIndex = position
            val intent = Intent(context, MusicPlayerActivity::class.java).apply {
                putExtra("LIST", songsList)
                flags = Intent.FLAG_ACTIVITY_NEW_TASK
            }
            context.startActivity(intent)
        }
    }
}
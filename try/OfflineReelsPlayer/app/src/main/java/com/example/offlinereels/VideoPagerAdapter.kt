
package com.example.offlinereels

import android.app.Activity
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.VideoView
import androidx.recyclerview.widget.RecyclerView
import java.io.File

class VideoPagerAdapter(
    private val activity: Activity,
    private val videos: List<File>
) : RecyclerView.Adapter<VideoPagerAdapter.VideoViewHolder>() {

    inner class VideoViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val videoView: VideoView = view.findViewById(R.id.videoView)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VideoViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_video, parent, false)
        return VideoViewHolder(view)
    }

    override fun onBindViewHolder(holder: VideoViewHolder, position: Int) {
        val file = videos[position]
        holder.videoView.setVideoURI(Uri.fromFile(file))
        holder.videoView.setOnPreparedListener { mp ->
            mp.isLooping = true
        }
        holder.videoView.start()
    }

    override fun getItemCount(): Int = videos.size
}

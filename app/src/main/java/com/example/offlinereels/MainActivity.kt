
package com.example.offlinereels

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager2.widget.ViewPager2
import java.io.File

class MainActivity : AppCompatActivity() {

    private lateinit var viewPager: ViewPager2
    private val PICK_FOLDER = 1001

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        viewPager = findViewById(R.id.viewPager)
        pickFolder()
    }

    private fun pickFolder() {
        val intent = Intent(Intent.ACTION_OPEN_DOCUMENT_TREE)
        startActivityForResult(intent, PICK_FOLDER)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == PICK_FOLDER && resultCode == Activity.RESULT_OK) {
            val uri: Uri? = data?.data
            uri?.let {
                val docId = it.path?.split(":")?.get(1)
                val fullPath = "/storage/emulated/0/$docId"
                val folder = File(fullPath)
                val videos = folder.listFiles { f ->
                    f.extension.lowercase() in listOf("mp4","mkv","webm")
                }?.toList() ?: emptyList()

                viewPager.adapter = VideoPagerAdapter(this, videos)
                viewPager.orientation = ViewPager2.ORIENTATION_VERTICAL
            }
        }
    }
}

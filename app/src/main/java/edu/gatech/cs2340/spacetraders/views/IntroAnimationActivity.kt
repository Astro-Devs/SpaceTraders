package edu.gatech.cs2340.spacetraders.views

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.Button
import android.widget.VideoView
import edu.gatech.cs2340.spacetraders.R


class IntroAnimationActivity : AppCompatActivity() {

    private lateinit var videoView: VideoView
    private lateinit var skipVideoButton: Button
    private lateinit var videoPath: String
    private lateinit var uri: Uri
    private var endVideo = 31000      //In order to be able to skip the video (in milliseconds)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.intro_animation)
        videoView = findViewById(R.id.videoView)
        skipVideoButton = findViewById(R.id.skipVideoButton)
        videoPath = "android.resource://" + packageName + "/" + R.raw.spacetradersanimation
        uri = Uri.parse(videoPath)
        videoView.setVideoURI(uri)
        videoView.start()

        skipVideoButton.setOnClickListener {
            videoView.seekTo(endVideo)
        }

        videoView.setOnCompletionListener {
            val intent = Intent(this@IntroAnimationActivity, ConfigurationActivity::class.java)
            startActivity(intent)
        }

    }
}
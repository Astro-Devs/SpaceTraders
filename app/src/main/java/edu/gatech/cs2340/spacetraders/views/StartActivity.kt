package edu.gatech.cs2340.spacetraders.views

import android.content.Intent
import android.media.MediaPlayer
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import edu.gatech.cs2340.spacetraders.R

class StartActivity : AppCompatActivity() {

    companion object MediaPlayerObj{
        var keepPlaying: Boolean = false;
    }

    private lateinit var mediaPlayer: MediaPlayer

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.login_screen)

        mediaPlayer = MediaPlayer.create(applicationContext, R.raw.alienloadingscreen)

        mediaPlayer.start()
        mediaPlayer.isLooping = true
    }

    override fun onPause() {
        super.onPause()
        mediaPlayer.pause()
    }

    override fun onResume() {
        super.onResume()
        mediaPlayer.start()
    }

    override fun onStop() {
        super.onStop()
        if (!keepPlaying) {
            mediaPlayer.pause()
        }
    }

    fun onNewGamePressed(view: View) {
        keepPlaying = true
        val configintent = Intent(applicationContext, ConfigurationActivity::class.java)
        startActivityForResult(configintent, 0)
    }
}
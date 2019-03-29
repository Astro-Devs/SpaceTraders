package edu.gatech.cs2340.spacetraders.views

import android.content.Intent
import android.media.MediaPlayer
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import edu.gatech.cs2340.spacetraders.R
import edu.gatech.cs2340.spacetraders.model.ModelFacade
import edu.gatech.cs2340.spacetraders.model.ModelFacade.Companion.getInstance
import java.io.File


/**
 * The Start Activity View class,
 * which shows the first screen allowing the user to start or load a game
 */
class StartActivity : AppCompatActivity() {

    companion object MediaPlayerObj {
        var keepPlaying: Boolean = false
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

    /**
     * Handle the functionality of the "new game" button
     * @param view the view to prevent the app from crashing, part of the method's default signature
     */
    fun onNewGamePressed(@Suppress("UNUSED_PARAMETER")view: View) {
        keepPlaying = true
        val configIntent = Intent(applicationContext, IntroAnimationActivity::class.java)
        //startActivityForResult(configIntent, 0)
        startActivity(configIntent)

    }

    /**
     * Loads saved data when "load game" button pressed
     */
    fun onLoadGamePressed(view: View) {
        val facade: ModelFacade = getInstance()
        val file = File(this@StartActivity.filesDir, "data.bin")
        facade.load(file)
        val intent = Intent(applicationContext, ShipActivity::class.java)
        startActivity(intent)
    }
}
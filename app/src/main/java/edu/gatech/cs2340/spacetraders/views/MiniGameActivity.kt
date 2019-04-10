package edu.gatech.cs2340.spacetraders.views

import android.arch.lifecycle.ViewModelProviders
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.DisplayMetrics
import android.widget.ImageView
import edu.gatech.cs2340.spacetraders.R
import android.os.CountDownTimer
import android.view.View
import android.widget.TextView
import android.widget.Toast
import edu.gatech.cs2340.spacetraders.viewmodel.UniverseViewModel


class MiniGameActivity : AppCompatActivity() {
    private lateinit var image: ImageView
    private lateinit var textView: TextView
    private lateinit var viewModel: UniverseViewModel
    private var credits: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.mini_game)
        image = findViewById(R.id.imageView2)
        textView = findViewById(R.id.textView)


        viewModel = ViewModelProviders.of(this).get(UniverseViewModel::class.java)

        val dm = DisplayMetrics()
        windowManager.defaultDisplay.getMetrics(dm)
        val width = dm.widthPixels - 100
        val height = dm.heightPixels - 814


        image.setOnClickListener {
            credits++
        }

        object : CountDownTimer(10000, 800) {      //10 second timer

            override fun onTick(millisUntilFinished: Long) {
                textView.setText("seconds remaining: " + millisUntilFinished / 1000)
                val newWidth = (0..width).random()
                val newHeight = (0..height).random()
                image.x = newWidth.toFloat()
                image.y = newHeight.toFloat()
            }

            override fun onFinish() {
                textView.setText("done!")
                val configIntent = Intent(applicationContext, ShipActivity::class.java)
                startActivity(configIntent)
                Toast.makeText(this@MiniGameActivity, "You Earned: $credits credits!", Toast.LENGTH_SHORT).show()
                viewModel.addCreditsToPlayer(credits)
            }
        }.start()


    }
}
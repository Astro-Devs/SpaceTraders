package edu.gatech.cs2340.spacetraders.views

import android.arch.lifecycle.ViewModelProviders
import android.content.Intent
import android.media.MediaPlayer
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.Button
import android.widget.TextView
import edu.gatech.cs2340.spacetraders.R
import edu.gatech.cs2340.spacetraders.entity.SolarSystem
import edu.gatech.cs2340.spacetraders.viewmodel.UniverseViewModel

/**
 * The Travel Activity View class that displays the universe and its planets.
 */
class TravelActivity : AppCompatActivity() {

    private lateinit var planetArray: ArrayList<SolarSystem>
    private lateinit var buyMarketButton: Button
    private lateinit var sellMarketButton: Button
    private lateinit var universeButton: Button
    private lateinit var viewModel: UniverseViewModel
    private lateinit var recycler: RecyclerView
    private lateinit var mediaPlayer: MediaPlayer

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.ship_recycler)

        viewModel = ViewModelProviders.of(this).get(UniverseViewModel::class.java)
        planetArray = viewModel.populateTravelView()

        recycler = findViewById(R.id.universe_recycler) as RecyclerView
        var llmVar = LinearLayoutManager(this)
        recycler.layoutManager = llmVar

        //This needs to be set to false if we have a larger solar system size
        recycler.setHasFixedSize(true)

        this.populateUniverseData()

        var creditsDisplay: TextView = findViewById(R.id.creditsText)
        creditsDisplay.setText(viewModel.getPlayerCreds().toString())

        buyMarketButton = findViewById(R.id.buyMarketButton)

        buyMarketButton.setOnClickListener(object : View.OnClickListener {
            override fun onClick(view: View): Unit {
                val inventoryIntent = Intent(applicationContext, BuyMarketActivity::class.java)
                startActivityForResult(inventoryIntent, 0)
            }
        })

        sellMarketButton = findViewById(R.id.sellMarketButton)

        sellMarketButton.setOnClickListener(object : View.OnClickListener {
            override fun onClick(view: View): Unit {
                val inventoryIntent = Intent(applicationContext, SellMarketActivity::class.java)
                startActivityForResult(inventoryIntent, 0)
            }
        })

        universeButton = findViewById(R.id.universeButton)

        universeButton.setOnClickListener(object : View.OnClickListener {
            override fun onClick(view: View): Unit {
                val uniIntent = Intent(applicationContext, UniverseActivity::class.java)
                startActivityForResult(uniIntent, 0)
            }
        })

        mediaPlayer = MediaPlayer.create(applicationContext, R.raw.alienplanetscreen)

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
        var creditsDisplay: TextView = findViewById(R.id.creditsText)
        creditsDisplay.setText(viewModel.getPlayerCreds().toString())
    }

    override fun onStop() {
        super.onStop()
        if (!StartActivity.keepPlaying) {
            mediaPlayer.pause()
        }
    }

    /**
     * Populate the view with data about each planet in the universe.
     */
    fun populateUniverseData() {
        var adapter = TravelAdapter(planetArray, viewModel)
        recycler.adapter = adapter
    }
}
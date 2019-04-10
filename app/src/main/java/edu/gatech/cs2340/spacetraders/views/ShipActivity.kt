package edu.gatech.cs2340.spacetraders.views

import android.arch.lifecycle.ViewModelProviders
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import edu.gatech.cs2340.spacetraders.R
import edu.gatech.cs2340.spacetraders.entity.SolarSystem
import edu.gatech.cs2340.spacetraders.model.ModelFacade
import edu.gatech.cs2340.spacetraders.viewmodel.ImageList
import edu.gatech.cs2340.spacetraders.viewmodel.UniverseViewModel
import java.io.File


class ShipActivity : AppCompatActivity() {

    private lateinit var viewModel: UniverseViewModel
    private lateinit var currentPlanet: SolarSystem
    private lateinit var modelFac: ModelFacade

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.ship_screen)

        ImageList.populatePlanetImages()

        viewModel = ViewModelProviders.of(this).get(UniverseViewModel::class.java)

        modelFac = ModelFacade.getInstance()

        currentPlanet = viewModel.getCurrentPlanet()

        Log.d("onCreate()", "current planet is " + currentPlanet.planetName)

        updatePlanetInfo(ImageList.currImage, currentPlanet)
        updateCreditsFuel()

        val travelListButton: Button = findViewById(R.id.travelPlanetButton)
        travelListButton.setOnClickListener {
            val travelIntent = Intent(applicationContext, TravelActivity::class.java)
            startActivityForResult(travelIntent, 0)
        }

        val buyButton: Button = findViewById(R.id.buyMarketButton)
        buyButton.setOnClickListener {
            val buyIntent = Intent(applicationContext, BuyMarketActivity::class.java)
            startActivityForResult(buyIntent, 0)
        }

        val sellButton: Button = findViewById(R.id.sellMarketButton)
        sellButton.setOnClickListener {
            val sellIntent = Intent(applicationContext, SellMarketActivity::class.java)
            startActivityForResult(sellIntent, 0)
        }

        val uniButton: Button = findViewById(R.id.universeButton)
        uniButton.setOnClickListener {
            val uniIntent = Intent(applicationContext, UniverseActivity::class.java)
            startActivityForResult(uniIntent, 0)
        }

        val saveButton: Button = findViewById(R.id.saveButton)
        saveButton.setOnClickListener {
            val facade: ModelFacade = ModelFacade.getInstance()
            val file = File(this@ShipActivity.filesDir,"data.bin")
            Log.d("onSave", "made save file")
            facade.save(file)
            Toast.makeText(this@ShipActivity, "Saved Game!", Toast.LENGTH_SHORT).show()
        }

        val miniGameButton: Button = findViewById(R.id.miniGameButton)
        if(modelFac.getFlag()) {
            miniGameButton.visibility = View.VISIBLE
        }
        modelFac.setFlag(false)
        miniGameButton.setOnClickListener{
            val intent = Intent(applicationContext, MiniGameActivity::class.java)
            startActivity(intent)
            if(!modelFac.getFlag()) {
                miniGameButton.visibility = View.GONE
            }
        }
    }

    override fun onResume() {
        super.onResume()
        currentPlanet = viewModel.getCurrentPlanet()
        updateCreditsFuel()
        updatePlanetInfo(ImageList.currImage, currentPlanet)
        Log.d("onResume()", "current planet is " + currentPlanet.planetName)
    }

    /**
     * Updates all the information to be placed on the CardView for the planet
     *
     * @param planetImg the id for the image
     * @param planet the current planet to update info on
     */
    private fun updatePlanetInfo(planetImg: Int, planet: SolarSystem) {
        val currentPlanetImage: ImageView = findViewById(R.id.currPlanetIMG)
        currentPlanetImage.setImageResource(planetImg)

        val currentPlanetName: TextView = findViewById(R.id.currPlanetName)
        currentPlanetName.text = planet.planetName

        val currentPlanetLocation: TextView = findViewById(R.id.currPlanetLocation)
        currentPlanetLocation.text = String.format("(${planet.location.xPositionLocal}," +
                "${planet.location.yPositionLocal})")

        val currentPlanetTech: TextView = findViewById(R.id.currPlanetTech)
        currentPlanetTech.text = planet.techLevel.toString()

        val currentPlanetRes: TextView = findViewById(R.id.currPlanetResource)
        currentPlanetRes.text = planet.resources.toString()
    }

    /**
     * Updates misc values on the screen with their corresponding values from the model.
     * Currently updates the fuel, credits and randomevent
     */
    private fun updateCreditsFuel() {
        val fuelDisplay: TextView = findViewById(R.id.fuelText)
        fuelDisplay.text = viewModel.getShipFuel().toString()

        val creditsDisplay: TextView = findViewById(R.id.creditsText)
        creditsDisplay.text = viewModel.getPlayerCreds().toString()

        val randomDisplay: TextView = findViewById(R.id.randomEvent)
        randomDisplay.text = String.format("Random event: " + viewModel.getRandomEvent().name)
    }

}
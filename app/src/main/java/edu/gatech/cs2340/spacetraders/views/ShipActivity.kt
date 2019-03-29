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
import edu.gatech.cs2340.spacetraders.viewmodel.ImageList
import edu.gatech.cs2340.spacetraders.viewmodel.UniverseViewModel
import edu.gatech.cs2340.spacetraders.model.ModelFacade
import java.io.File


class ShipActivity : AppCompatActivity() {

    private lateinit var viewModel: UniverseViewModel
    private lateinit var currentPlanet: SolarSystem

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.ship_screen)

        ImageList.populatePlanetImages()

        viewModel = ViewModelProviders.of(this).get(UniverseViewModel::class.java)

        currentPlanet = viewModel.getCurrentPlanet()

        Log.d("onCreate()", "current planet is " + currentPlanet.planetName)

        updatePlanetInfo(ImageList.currImage, currentPlanet)
        updateCreditsFuel()

        val travelListButton: Button = findViewById(R.id.travelPlanetButton)
        travelListButton.setOnClickListener(object : View.OnClickListener {
            override fun onClick(view: View) {
                val travelIntent = Intent(applicationContext, TravelActivity::class.java)
                startActivityForResult(travelIntent, 0)
            }
        })

        val buyButton: Button = findViewById(R.id.buyMarketButton)
        buyButton.setOnClickListener(object : View.OnClickListener {
            override fun onClick(view: View) {
                val buyIntent = Intent(applicationContext, BuyMarketActivity::class.java)
                startActivityForResult(buyIntent, 0)
            }
        })

        val sellButton: Button = findViewById(R.id.sellMarketButton)
        sellButton.setOnClickListener(object : View.OnClickListener {
            override fun onClick(view: View) {
                val sellIntent = Intent(applicationContext, SellMarketActivity::class.java)
                startActivityForResult(sellIntent, 0)
            }
        })

        val uniButton: Button = findViewById(R.id.universeButton)
        uniButton.setOnClickListener(object : View.OnClickListener {
            override fun onClick(view: View) {
                val uniIntent = Intent(applicationContext, UniverseActivity::class.java)
                startActivityForResult(uniIntent, 0)
            }
        })

        val saveButton: Button = findViewById(R.id.saveButton)
        saveButton.setOnClickListener(object : View.OnClickListener {
            override fun onClick(v: View?) {
                val facade: ModelFacade = ModelFacade.getInstance()
                val file = File(this@ShipActivity.filesDir,"data.bin")
                Log.d("onSave", "made save file")
                facade.save(file)
                Toast.makeText(this@ShipActivity, "Saved Game!", Toast.LENGTH_SHORT).show()
            }
        })
    }

    override fun onResume() {
        super.onResume()
        currentPlanet = viewModel.getCurrentPlanet()
        updateCreditsFuel()
        updatePlanetInfo(ImageList.currImage, currentPlanet)
        Log.d("onResume()", "current planet is " + currentPlanet.planetName)
    }

    fun updatePlanetInfo(planetImg: Int, planet: SolarSystem) {
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

    fun updateCreditsFuel() {
        val fuelDisplay: TextView = findViewById(R.id.fuelText)
        fuelDisplay.text = viewModel.getShipFuel().toString()

        val creditsDisplay: TextView = findViewById(R.id.creditsText)
        creditsDisplay.text = viewModel.getPlayerCreds().toString()
    }

}
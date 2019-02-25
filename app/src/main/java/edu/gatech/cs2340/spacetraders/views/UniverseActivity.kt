package edu.gatech.cs2340.spacetraders.views

import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.widget.TextView
import edu.gatech.cs2340.spacetraders.R
import edu.gatech.cs2340.spacetraders.entity.Coordinates
import edu.gatech.cs2340.spacetraders.entity.SolarSystem
import edu.gatech.cs2340.spacetraders.viewmodel.ConfigViewModel
import edu.gatech.cs2340.spacetraders.viewmodel.UniverseViewModel

class UniverseActivity : AppCompatActivity() {

    private lateinit var viewModel : UniverseViewModel
    private lateinit var planetMap : Map<Coordinates, SolarSystem>
    private lateinit var planetKeys : Set<Coordinates>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.universe_layout_scrolling)

        viewModel = ViewModelProviders.of(this).get(UniverseViewModel::class.java)

        planetMap = viewModel.populateUniverseView()
        planetKeys = planetMap.keys

        this.populateUniverseData()


    }

    fun populateUniverseData() {

        var planet1Name : TextView = findViewById(R.id.planet1ID)
        planet1Name.setText("PLANET 1: " + planetMap.get(planetKeys.elementAt(0))?.planetName)

        var planet2Name : TextView = findViewById(R.id.planet2ID)
        planet2Name.setText("PLANET 2: " + planetMap.get(planetKeys.elementAt(1))?.planetName)

        var planet3Name : TextView = findViewById(R.id.planet3ID)
        planet3Name.setText("PLANET 3: " + planetMap.get(planetKeys.elementAt(2))?.planetName)

        var planet4Name : TextView = findViewById(R.id.planet4ID)
        planet4Name.setText("PLANET 4: " + planetMap.get(planetKeys.elementAt(3))?.planetName)

        var planet5Name : TextView = findViewById(R.id.planet1ID)
        planet5Name.setText("PLANET 5: " + planetMap.get(planetKeys.elementAt(0))?.planetName)
    }
}
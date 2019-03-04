package edu.gatech.cs2340.spacetraders.views

import android.arch.lifecycle.ViewModelProviders
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.widget.TextView
import android.widget.Button
import android.widget.EditText
import android.widget.ListView
import edu.gatech.cs2340.spacetraders.R
import edu.gatech.cs2340.spacetraders.entity.Coordinates
import edu.gatech.cs2340.spacetraders.entity.SolarSystem
import edu.gatech.cs2340.spacetraders.viewmodel.ConfigViewModel
import edu.gatech.cs2340.spacetraders.viewmodel.UniverseViewModel

/**
 * The Universe Activity View class that displays the universe and its planets.
 */
class UniverseActivity : AppCompatActivity() {

    private lateinit var planetArray: ArrayList<SolarSystem>
    private lateinit var btnViewInventory : Button
    private lateinit var planetListview : ListView
    private lateinit var listAdapter: ListAdapter
    private lateinit var viewModel : UniverseViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.universe_layout_scrolling)

        viewModel = ViewModelProviders.of(this).get(UniverseViewModel::class.java)
        planetArray = viewModel.populateUniverseView()

//        planetListview = findViewById(R.id.planetListView)
//        listAdapter = ListAdapter(this@UniverseActivity, planetArray)
//        planetListview.adapter = listAdapter

        this.populateUniverseData()

        btnViewInventory = findViewById(R.id.btnViewInventory)
        btnViewInventory.setOnClickListener(object: View.OnClickListener {
            override fun onClick(view: View): Unit {
            val inventoryIntent = Intent(applicationContext, InventoryActivity::class.java)
            startActivityForResult(inventoryIntent, 0)
            }
        })

    }

    /**
     * Populate the view with data about each planet in the universe.
     */
    fun populateUniverseData() {

    }
}
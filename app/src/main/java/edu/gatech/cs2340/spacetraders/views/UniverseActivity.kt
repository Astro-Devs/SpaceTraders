package edu.gatech.cs2340.spacetraders.views

import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.widget.Button
import edu.gatech.cs2340.spacetraders.R
import edu.gatech.cs2340.spacetraders.entity.SolarSystem
import edu.gatech.cs2340.spacetraders.viewmodel.UniverseViewModel

/**
 * The Universe Activity View class that displays the universe and its planets.
 */
class UniverseActivity : AppCompatActivity() {

    private lateinit var planetArray: ArrayList<SolarSystem>
    private lateinit var btnViewInventory : Button
    private lateinit var viewModel : UniverseViewModel
    private lateinit var recycler: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.universe_recycler)

        viewModel = ViewModelProviders.of(this).get(UniverseViewModel::class.java)
        planetArray = viewModel.populateUniverseView()

        recycler = findViewById(R.id.universe_recycler) as RecyclerView
        var llm: LinearLayoutManager = LinearLayoutManager(this)
        recycler.layoutManager = llm

        this.populateUniverseData()

//        btnViewInventory = findViewById(R.id.btnViewInventory)
//        btnViewInventory.setOnClickListener(object: View.OnClickListener {
//            override fun onClick(view: View): Unit {
//            val inventoryIntent = Intent(applicationContext, InventoryActivity::class.java)
//            startActivityForResult(inventoryIntent, 0)
//            }
//        })

    }

    /**
     * Populate the view with data about each planet in the universe.
     */
    fun populateUniverseData() {
        var adapter = UniAdapter(planetArray)
        recycler.adapter = adapter
    }
}
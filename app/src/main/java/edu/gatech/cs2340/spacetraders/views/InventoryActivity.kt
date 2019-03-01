package edu.gatech.cs2340.spacetraders.views

import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.TextView
import edu.gatech.cs2340.spacetraders.R
import edu.gatech.cs2340.spacetraders.entity.Products
import edu.gatech.cs2340.spacetraders.viewmodel.InventoryViewModel

/**
 * The Universe Activity View class that displays the universe and its planets.
 */
class InventoryActivity : AppCompatActivity() {

    private lateinit var viewModel : InventoryViewModel
    private lateinit var inventoryMap : Map<Products, Int>
    private lateinit var inventoryKeys : Set<Products>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.inventory_layout_scrolling)

        viewModel = ViewModelProviders.of(this).get(InventoryViewModel::class.java)

        inventoryMap = viewModel.populateInventoryView()
        inventoryKeys = inventoryMap.keys

        this.populateInventoryData()


    }

    /**
     * Populate the view with data about each planet in the universe.
     */
    fun populateInventoryData() {

        var product1Name : TextView = findViewById(R.id.product1ID)
        product1Name.setText("PRODUCT 1: " + inventoryKeys.elementAt(0)?.name)

        var product1Amount : TextView = findViewById(R.id.product1amount)
        product1Amount.setText("AMOUNT: " + inventoryMap.get(inventoryKeys.elementAt(0)))

        var product2Name : TextView = findViewById(R.id.product2ID)
        product2Name.setText("PRODUCT 2: " + inventoryKeys.elementAt(1)?.name)

        var product2Amount : TextView = findViewById(R.id.product2amount)
        product2Amount.setText("AMOUNT: " + inventoryMap.get(inventoryKeys.elementAt(1)))

    }
}
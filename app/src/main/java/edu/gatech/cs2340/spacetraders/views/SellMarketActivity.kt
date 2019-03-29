package edu.gatech.cs2340.spacetraders.views

import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.widget.TextView
import edu.gatech.cs2340.spacetraders.R
import edu.gatech.cs2340.spacetraders.entity.Products
import edu.gatech.cs2340.spacetraders.viewmodel.InventoryViewModel

/**
 * The Sellable Activity View class that displays the universe and its planets.
 */
class SellMarketActivity : AppCompatActivity() {

    private lateinit var viewModel: InventoryViewModel
    private lateinit var productSellSet: Set<MutableMap.MutableEntry<Products, Int>>
    private lateinit var productPrice: HashMap<Products, Int>
    private lateinit var recycler: RecyclerView
    private lateinit var creditsDisplay: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.market_recycler)

        viewModel = ViewModelProviders.of(this).get(InventoryViewModel::class.java)

        productSellSet = viewModel.getSellableMarket()
        productPrice = viewModel.getPriceMap()

        recycler = findViewById(R.id.market_recycler)
        val llmVar = LinearLayoutManager(this)
        recycler.layoutManager = llmVar

        //This needs to be set to false if we have a larger solar system size
        recycler.setHasFixedSize(true)


        creditsDisplay = findViewById(R.id.creditsText)
        creditsDisplay.text = viewModel.getPlayerCreds().toString()

        this.populateMarketData()

    }

    /**
     * Populate the view with data about product and its price and quantity
     */
    private fun populateMarketData() {
        val adapter = MarketAdapter(productSellSet, productPrice, false, viewModel, creditsDisplay, this)
        recycler.adapter = adapter
    }
}
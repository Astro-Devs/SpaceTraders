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
 * The Universe Activity View class that displays the universe and its planets.
 */
class BuyMarketActivity : AppCompatActivity() {

    private lateinit var viewModel : InventoryViewModel
    private lateinit var productBuySet: Set<MutableMap.MutableEntry<Products, Int>>
    private lateinit var productPrice: HashMap<Products, Int>
    private lateinit var recycler: RecyclerView
    private lateinit var creditsDisplay : TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.market_recycler)

        viewModel = ViewModelProviders.of(this).get(InventoryViewModel::class.java)

        productBuySet = viewModel.getBuyableMarket()
        productPrice = viewModel.getPriceMap()

        recycler = findViewById(R.id.market_recycler) as RecyclerView
        var llm = LinearLayoutManager(this)
        recycler.layoutManager = llm

        //This needs to be set to false if we have a larger solar system size
        recycler.setHasFixedSize(true)


        creditsDisplay = findViewById(R.id.creditsText)
        creditsDisplay.setText(viewModel.getPlayerCreds().toString())

        this.populateMarketData()


    }

    /**
     * Populate the view with data about each planet in the universe.
     */
    fun populateMarketData() {
        var adapter = MarketAdapter(productBuySet, productPrice, true, viewModel, creditsDisplay)
        recycler.adapter = adapter
    }
}
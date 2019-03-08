package edu.gatech.cs2340.spacetraders.views

import android.support.v7.widget.CardView
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import edu.gatech.cs2340.spacetraders.R
import edu.gatech.cs2340.spacetraders.entity.Products
import edu.gatech.cs2340.spacetraders.viewmodel.InventoryViewModel
import java.lang.Exception

class MarketAdapter : RecyclerView.Adapter<MarketAdapter.MarketViewHolder> {

    class MarketViewHolder: RecyclerView.ViewHolder {

        var cv: CardView
        var name: TextView
        var price: TextView
        var number: TextView
        var transactionButton: Button

        constructor(itemView: View): super(itemView) {
            cv = itemView.findViewById(R.id.market_card)
            name = itemView.findViewById(R.id.productName)
            price = itemView.findViewById(R.id.productPrice)
            number = itemView.findViewById(R.id.productNumber)
            transactionButton = itemView.findViewById(R.id.transactionButton)
        }
    }

    private var productSet: Set<MutableMap.MutableEntry<Products, Int>>
    private var priceMap: HashMap<Products, Int>
    private var isBuyable: Boolean
    private var viewModel: InventoryViewModel
    private var creditsDisplay: TextView
//    private var quantity: Int = 0

    constructor(productMap: Set<MutableMap.MutableEntry<Products, Int>>,
                priceMap: HashMap<Products, Int>,
                isBuyable: Boolean,
                viewModel: InventoryViewModel,
                creditsDisplay : TextView): super() {
        this.productSet = productMap
        this.priceMap = priceMap
        this.isBuyable = isBuyable
        this.viewModel = viewModel
        this.creditsDisplay = creditsDisplay
    }

    override fun onAttachedToRecyclerView(recyclerView: RecyclerView) {
        super.onAttachedToRecyclerView(recyclerView)
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, p1: Int): MarketViewHolder {
        var v: View = LayoutInflater.from(viewGroup.context).inflate(R.layout.market_card, viewGroup, false)
        var vh = MarketViewHolder(v)
        return vh
    }

    override fun getItemCount(): Int {
        return productSet.size
    }


    override fun onBindViewHolder(marketViewHolder: MarketViewHolder, i: Int) {
        marketViewHolder.name.setText("Product: " + productSet.elementAt(i).key.name)
//        quantity = productSet.elementAt(i).value
        marketViewHolder.number.setText("Quantity: " + productSet.elementAt(i).value)
        marketViewHolder.price.setText("Price: " + priceMap.get(productSet.elementAt(i).key) + " credits")

        if (isBuyable) {
            marketViewHolder.transactionButton.setText("Buy")
            marketViewHolder.transactionButton.setOnClickListener(object: View.OnClickListener {
                override fun onClick(view: View): Unit {
                    try {
                        viewModel.buy(productSet.elementAt(i).key, 1)
                        marketViewHolder.name.setText("Product: " + productSet.elementAt(i).key.name)
                        marketViewHolder.number.setText("Quantity: " + productSet.elementAt(i).value)
                        marketViewHolder.price.setText("Price: " + priceMap.get(productSet.elementAt(i).key) + " credits")
                        Log.d("Quanity left in buy", " Quantity left:" + productSet.elementAt(i).value)
                        Log.d("set print", "Set entry: " + productSet.elementAt(i))

                        creditsDisplay.setText(viewModel.getPlayerCreds().toString())
                    } catch (e: Exception) {
                        Log.d("Buy", "Buy set is empty, cannot buy anymore")
                    }
                }
            })
        } else {
            marketViewHolder.transactionButton.setText("Sell")
            marketViewHolder.transactionButton.setOnClickListener(object: View.OnClickListener {
                override fun onClick(view: View): Unit {
                    try {
                        viewModel.sell(productSet.elementAt(i).key, 1)
                        marketViewHolder.number.setText("Quantity: " + productSet.elementAt(i).value)
                        marketViewHolder.price.setText("Price: " + priceMap.get(productSet.elementAt(i).key) + " credits")
                        marketViewHolder.name.setText("Product: " + productSet.elementAt(i).key.name)

                        Log.d("Sold", "Product Sold:" + productSet.elementAt(i).key.name + " Quantity left:" + productSet.elementAt(i).value)
                        creditsDisplay.setText(viewModel.getPlayerCreds().toString())
                    } catch (e: Exception) {
                        Log.d("Sell", "Sell set is empty, cannot sell anymore")
                    }
                }
            })
        }
    }
}
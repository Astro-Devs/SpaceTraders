package edu.gatech.cs2340.spacetraders.views

import android.content.Context
import android.support.v7.widget.CardView
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import edu.gatech.cs2340.spacetraders.R
import edu.gatech.cs2340.spacetraders.entity.Products
import edu.gatech.cs2340.spacetraders.viewmodel.InventoryViewModel

/**
 * Adapter for recyclerview to display Market elements via a cardview
 *
 */
class MarketAdapter : RecyclerView.Adapter<MarketAdapter.MarketViewHolder> {

    class MarketViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        var cv: CardView
        var name: TextView
        var price: TextView
        var number: TextView
        var transactionButton: Button

        init {
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
    private var contextSub: Context

    constructor(
        productMap: Set<MutableMap.MutableEntry<Products, Int>>,
        priceMap: HashMap<Products, Int>,
        isBuyable: Boolean,
        viewModel: InventoryViewModel,
        creditsDisplay: TextView,
        contextSub: Context
    ) : super() {
        this.productSet = productMap
        this.priceMap = priceMap
        this.isBuyable = isBuyable
        this.viewModel = viewModel
        this.creditsDisplay = creditsDisplay
        this.contextSub = contextSub
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, p1: Int): MarketViewHolder {
        val v: View = LayoutInflater.from(viewGroup.context).inflate(R.layout.market_card, viewGroup, false)
        return MarketViewHolder(v)
    }

    override fun getItemCount(): Int {
        return productSet.size
    }


    override fun onBindViewHolder(marketViewHolder: MarketViewHolder, i: Int) {

        marketViewHolder.name.text = productSet.elementAt(marketViewHolder.adapterPosition).key.name
        marketViewHolder.number.text = productSet.elementAt(marketViewHolder.adapterPosition).value.toString()
        marketViewHolder.price.text = String.format(priceMap.get(productSet.elementAt(marketViewHolder.adapterPosition).key).toString() + " credits")

        if (isBuyable) {
            marketViewHolder.transactionButton.text = String.format("Buy")
            marketViewHolder.transactionButton.setOnClickListener(object : View.OnClickListener {
                override fun onClick(view: View) {
                    try {
                        if (viewModel.getPlayerCreds() < priceMap.get(productSet.elementAt(marketViewHolder.adapterPosition).key) as Int) {
                            Toast.makeText(contextSub, "Not enough money to buy", Toast.LENGTH_LONG).show()
                        }
                        if (viewModel.isCargoFull() && productSet.elementAt(marketViewHolder.adapterPosition).key != Products.FUEL) {
                            Toast.makeText(contextSub, "Cargo is full!", Toast.LENGTH_LONG).show()
                        }
                        viewModel.buy(productSet.elementAt(marketViewHolder.adapterPosition).key, 1)
                        notifyDataSetChanged()
                        creditsDisplay.text = viewModel.getPlayerCreds().toString()

                    } catch (e: Exception) {
                        Log.d("Buy", "Buy set is empty, cannot buy anymore")
                    }
                }
            })
        } else {
            marketViewHolder.transactionButton.text = String.format("Sell")
            marketViewHolder.transactionButton.setOnClickListener(object : View.OnClickListener {
                override fun onClick(view: View) {
                    try {
                        // Fix selling 1 product with 1 quantity not deleting the card
                        Log.d("Sell", "Trying to sell 1 " + productSet.elementAt(i).key)
                        viewModel.sell(productSet.elementAt(marketViewHolder.adapterPosition).key, 1)
                        notifyDataSetChanged()
                        notifyItemRemoved(marketViewHolder.adapterPosition)
                        creditsDisplay.text = viewModel.getPlayerCreds().toString()

                    } catch (e: Exception) {
                        Log.d("Sell", "Sell set is empty, cannot sell anymore")
                    }
                }
            })
        }
    }
}
package edu.gatech.cs2340.spacetraders.views

import android.content.Context
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
class MarketAdapter(
    productMap: Set<MutableMap.MutableEntry<Products, Int>>,
    private var priceMap: HashMap<Products, Int>,
    private var isBuyable: Boolean,
    private var viewModel: InventoryViewModel,
    private var creditsDisplay: TextView,
    private var contextSub: Context
) : RecyclerView.Adapter<MarketAdapter.MarketViewHolder>() {

    class MarketViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        var name: TextView = itemView.findViewById(R.id.productName)
        var price: TextView = itemView.findViewById(R.id.productPrice)
        var number: TextView = itemView.findViewById(R.id.productNumber)
        var transactionButton: Button = itemView.findViewById(R.id.transactionButton)

    }

    private var productSet: Set<MutableMap.MutableEntry<Products, Int>> = productMap

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
        marketViewHolder.price.text = String.format(priceMap[productSet.elementAt(marketViewHolder.adapterPosition).key].toString() + " credits")

        if (isBuyable) {
            marketViewHolder.transactionButton.text = String.format("Buy")
            marketViewHolder.transactionButton.setOnClickListener {
                try {
                    if (viewModel.getPlayerCreds() < priceMap[productSet.elementAt(marketViewHolder.adapterPosition).key] as Int) {
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
        } else {
            marketViewHolder.transactionButton.text = String.format("Sell")
            marketViewHolder.transactionButton.setOnClickListener {
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
        }
    }
}
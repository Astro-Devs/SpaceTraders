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
    private var productArrayList = ArrayList<MutableMap.MutableEntry<Products, Int>>(productSet)

    override fun onCreateViewHolder(viewGroup: ViewGroup, p1: Int): MarketViewHolder {
        val v: View = LayoutInflater.from(viewGroup.context).inflate(R.layout.market_card, viewGroup, false)
        return MarketViewHolder(v)
    }

    override fun getItemCount(): Int {
        return productArrayList.size
    }


    override fun onBindViewHolder(marketViewHolder: MarketViewHolder, i: Int) {

        marketViewHolder.name.text = productArrayList.elementAt(marketViewHolder.adapterPosition).key.name
        marketViewHolder.number.text = productArrayList.elementAt(marketViewHolder.adapterPosition).value.toString()
        marketViewHolder.price.text = String.format(
            priceMap[productArrayList.elementAt(marketViewHolder.adapterPosition).key].toString() + " credits")

        if (isBuyable) {
            marketViewHolder.transactionButton.text = String.format("Buy")
            marketViewHolder.transactionButton.setOnClickListener {
                try {
                    if (viewModel.getPlayerCreds() <
                        priceMap[productArrayList.elementAt(marketViewHolder.adapterPosition).key] as Int) {
                        Toast.makeText(contextSub, "Not enough money to buy", Toast.LENGTH_LONG).show()
                    }
                    if (viewModel.isCargoFull()
                        && productArrayList.elementAt(marketViewHolder.adapterPosition).key != Products.FUEL) {
                        Toast.makeText(contextSub, "Cargo is full!", Toast.LENGTH_LONG).show()
                    }
                    viewModel.buy(productArrayList.elementAt(marketViewHolder.adapterPosition).key, 1)
                    Log.d("buy", "quantity remaining " +
                            productArrayList.elementAt(marketViewHolder.adapterPosition).value)
                    if (productArrayList.elementAt(marketViewHolder.adapterPosition).value == 0) {
                        productArrayList.removeAt(marketViewHolder.adapterPosition)
                        notifyItemRemoved(marketViewHolder.adapterPosition)
                    }
                    notifyDataSetChanged()
                    creditsDisplay.text = viewModel.getPlayerCreds().toString()

                } catch (e: Exception) {
                    productArrayList.removeAt(marketViewHolder.adapterPosition)
                    notifyItemRemoved(marketViewHolder.adapterPosition)
                    Log.d("Buy", "Buy set is empty, cannot buy anymore")
                }
            }
        } else {
            marketViewHolder.transactionButton.text = String.format("Sell")
            marketViewHolder.transactionButton.setOnClickListener {
                try {
                    viewModel.sell(productSet.elementAt(marketViewHolder.adapterPosition).key, 1)
                    creditsDisplay.text = viewModel.getPlayerCreds().toString()
                    if (productArrayList.elementAt(marketViewHolder.adapterPosition).value == 0) {
                        productArrayList.remove(productArrayList.elementAt(marketViewHolder.adapterPosition))
                        notifyItemRemoved(marketViewHolder.adapterPosition)
                    }
                    notifyDataSetChanged()
                    Log.d("sell list size", viewModel.getBuyableMarket().size.toString())
                } catch (e: Exception) {
                    productArrayList.remove(productArrayList.elementAt(marketViewHolder.adapterPosition))
                    notifyItemRemoved(marketViewHolder.adapterPosition)
                    Log.d("Sell", "Sell set is empty, cannot sell anymore")
                }
            }
        }
    }

}
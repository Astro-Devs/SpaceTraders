package edu.gatech.cs2340.spacetraders.views

import android.support.v7.widget.CardView
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import edu.gatech.cs2340.spacetraders.R
import edu.gatech.cs2340.spacetraders.entity.Products

class MarketAdapter : RecyclerView.Adapter<MarketAdapter.MarketViewHolder> {

    class MarketViewHolder: RecyclerView.ViewHolder {

        var cv: CardView
        var name: TextView
        var price: TextView
        var number: TextView

        constructor(itemView: View): super(itemView) {
            cv = itemView.findViewById(R.id.market_card)
            name = itemView.findViewById(R.id.productName)
            price = itemView.findViewById(R.id.productPrice)
            number = itemView.findViewById(R.id.productNumber)
        }
    }

    private var productMap: Set<MutableMap.MutableEntry<Products, Int>>

    constructor(productMap: Set<MutableMap.MutableEntry<Products, Int>>): super() {
        this.productMap = productMap
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
        return productMap.size
    }

    override fun onBindViewHolder(marketViewHolder: MarketViewHolder, i: Int) {
        marketViewHolder.name.setText("Product: " + productMap.elementAt(i).key.name)
        marketViewHolder.number.setText("Quantity: " + productMap.elementAt(i).value)
        marketViewHolder.price.setText("Price: " + productMap.elementAt(i))
    }
}
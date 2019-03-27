package edu.gatech.cs2340.spacetraders.views

import android.support.v7.widget.CardView
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import edu.gatech.cs2340.spacetraders.R
import edu.gatech.cs2340.spacetraders.entity.SolarSystem
import edu.gatech.cs2340.spacetraders.viewmodel.ImageList

/**
 * Adapter for recyclerview to display universe elements via a cardview
 *
 */
class UniAdapter : RecyclerView.Adapter<UniAdapter.UniViewHolder> {

    /**
     * Inner class which overrides the recycler view holder and sets the cardview elements
     *
     */
    class UniViewHolder : RecyclerView.ViewHolder {

        var cv: CardView
        var name: TextView
        var img: ImageView
        var res: TextView
        var techlev: TextView
        var location: TextView

        constructor(itemView: View) : super(itemView) {
            cv = itemView.findViewById(R.id.uni_card)
            name = itemView.findViewById(R.id.planetName)
            img = itemView.findViewById(R.id.planetIMG)
            res = itemView.findViewById(R.id.planetResource)
            techlev = itemView.findViewById(R.id.planetTech)
            location = itemView.findViewById(R.id.planetLocation)
        }
    }

    private var solarList: List<SolarSystem>

    constructor(solarList: List<SolarSystem>) : super() {
        this.solarList = solarList
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, p1: Int): UniViewHolder {
        val v: View = LayoutInflater.from(viewGroup.context).inflate(R.layout.universe_card, viewGroup, false)
        return UniViewHolder(v)
    }

    override fun getItemCount(): Int {
        return solarList.size
    }

    override fun onBindViewHolder(uniViewHolder: UniViewHolder, i: Int) {
        uniViewHolder.name.text = solarList.get(i).planetName
        uniViewHolder.location.text = "(${solarList.get(i).location.xPositionLocal}, ${solarList.get(i).location.yPositionLocal})"
        uniViewHolder.res.text = solarList.get(i).resources.toString()
        uniViewHolder.techlev.text = solarList.get(i).techLevel.toString()
        uniViewHolder.img.setImageResource(ImageList.imageList.get(i))
    }

}
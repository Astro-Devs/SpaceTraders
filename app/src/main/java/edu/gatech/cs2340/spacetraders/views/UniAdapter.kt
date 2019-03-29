package edu.gatech.cs2340.spacetraders.views

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
class UniAdapter(private var solarList: List<SolarSystem>) : RecyclerView.Adapter<UniAdapter.UniViewHolder>() {

    /**
     * Inner class which overrides the recycler view holder and sets the cardview elements
     *
     */
    class UniViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        var name: TextView = itemView.findViewById(R.id.planetName)
        var img: ImageView = itemView.findViewById(R.id.planetIMG)
        var res: TextView = itemView.findViewById(R.id.planetResource)
        var techlev: TextView = itemView.findViewById(R.id.planetTech)
        var location: TextView = itemView.findViewById(R.id.planetLocation)

    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, p1: Int): UniViewHolder {
        val v: View = LayoutInflater.from(viewGroup.context).inflate(R.layout.universe_card, viewGroup, false)
        return UniViewHolder(v)
    }

    override fun getItemCount(): Int {
        return solarList.size
    }

    override fun onBindViewHolder(uniViewHolder: UniViewHolder, i: Int) {
        uniViewHolder.name.text = solarList[i].planetName
        uniViewHolder.location.text = String.format("(${solarList[i].location.xPositionLocal}," +
                "${solarList[i].location.yPositionLocal})")
        uniViewHolder.res.text = solarList[i].resources.toString()
        uniViewHolder.techlev.text = solarList[i].techLevel.toString()
        uniViewHolder.img.setImageResource(ImageList.imageList[i])
    }

}
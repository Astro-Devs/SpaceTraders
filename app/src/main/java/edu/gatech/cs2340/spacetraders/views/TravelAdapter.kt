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

class TravelAdapter : RecyclerView.Adapter<TravelAdapter.TravelViewHolder> {

    class TravelViewHolder : RecyclerView.ViewHolder {

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
        ImageList.populatePlanetImages()
    }

    override fun onAttachedToRecyclerView(recyclerView: RecyclerView) {
        super.onAttachedToRecyclerView(recyclerView)
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, p1: Int): TravelViewHolder {
        var v: View = LayoutInflater.from(viewGroup.context).inflate(R.layout.ship_card, viewGroup, false)
        var vh = TravelViewHolder(v)
        return vh
    }

    override fun getItemCount(): Int {
        return solarList.size
    }

    override fun onBindViewHolder(uniViewHolder: TravelViewHolder, i: Int) {
        uniViewHolder.name.setText(solarList.get(i).planetName)
        uniViewHolder.location.setText("(${solarList.get(i).location.xPositionLocal}, ${solarList.get(i).location.yPositionLocal})")
        uniViewHolder.res.setText(solarList.get(i).resources.toString())
        uniViewHolder.techlev.setText(solarList.get(i).techLevel.toString())
        uniViewHolder.img.setImageResource(ImageList.imageList.get(i))
    }


}
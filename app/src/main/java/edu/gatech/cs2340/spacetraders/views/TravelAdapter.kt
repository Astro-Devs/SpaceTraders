package edu.gatech.cs2340.spacetraders.views

import android.support.v7.widget.CardView
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import edu.gatech.cs2340.spacetraders.R
import edu.gatech.cs2340.spacetraders.entity.SolarSystem
import edu.gatech.cs2340.spacetraders.viewmodel.ImageList
import edu.gatech.cs2340.spacetraders.viewmodel.UniverseViewModel

/**
 * Adapter for recyclerview to display universe elements via a cardview
 *
 */
class TravelAdapter : RecyclerView.Adapter<TravelAdapter.TravelViewHolder> {

    /**
     * Inner class which overrides the recycler view holder and sets the cardview elements
     *
     */
    class TravelViewHolder : RecyclerView.ViewHolder {

        var cv: CardView
        var name: TextView
        var img: ImageView
        var res: TextView
        var techlev: TextView
        var location: TextView
        var travelButton: Button

        constructor(itemView: View) : super(itemView) {
            cv = itemView.findViewById(R.id.travel_card)
            name = itemView.findViewById(R.id.planetName)
            img = itemView.findViewById(R.id.planetIMG)
            res = itemView.findViewById(R.id.planetResource)
            techlev = itemView.findViewById(R.id.planetTech)
            location = itemView.findViewById(R.id.planetLocation)
            travelButton = itemView.findViewById(R.id.travelPlanetButton)
        }
    }

    private var solarList: List<SolarSystem>
    private var viewModel: UniverseViewModel

    constructor(solarList: List<SolarSystem>, viewModel: UniverseViewModel) : super() {
        this.solarList = solarList
        this.viewModel = viewModel
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, p1: Int): TravelViewHolder {
        var v: View = LayoutInflater.from(viewGroup.context).inflate(R.layout.travel_card, viewGroup, false)
        return TravelViewHolder(v)
    }

    override fun getItemCount(): Int {
        return solarList.size
    }

    override fun onBindViewHolder(travelViewHolder: TravelViewHolder, i: Int) {
        travelViewHolder.name.setText(solarList.get(i).planetName)
        travelViewHolder.location.setText("(${solarList.get(i).location.xPositionLocal}, ${solarList.get(i).location.yPositionLocal})")
        travelViewHolder.res.setText(solarList.get(i).resources.toString())
        travelViewHolder.techlev.setText(solarList.get(i).techLevel.toString())
        travelViewHolder.img.setImageResource(ImageList.imageList.get(i))
        travelViewHolder.travelButton.setOnClickListener(object : View.OnClickListener {
            override fun onClick(view: View) {
                if (viewModel.travel(solarList.get(i).location)) {
                    ImageList.currImage = ImageList.imageList.get(i)
                    Log.d("current planet", "curr planet is " + viewModel.getCurrentPlanet().planetName)
                }
            }
        })
    }


}
package edu.gatech.cs2340.spacetraders.views

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import edu.gatech.cs2340.spacetraders.R
import edu.gatech.cs2340.spacetraders.entity.SolarSystem
import edu.gatech.cs2340.spacetraders.viewmodel.ImageList
import edu.gatech.cs2340.spacetraders.viewmodel.UniverseViewModel

/**
 * Adapter for recyclerview to display universe elements via a cardview
 *
 */
class TravelAdapter(
    private var solarList: List<SolarSystem>,
    private var viewModel: UniverseViewModel,
    private var contextSub: Context
) : RecyclerView.Adapter<TravelAdapter.TravelViewHolder>() {

    /**
     * Inner class which overrides the recycler view holder and sets the cardview elements
     *
     */
    class TravelViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        var name: TextView = itemView.findViewById(R.id.planetName)
        var img: ImageView = itemView.findViewById(R.id.planetIMG)
        var res: TextView = itemView.findViewById(R.id.planetResource)
        var techlev: TextView = itemView.findViewById(R.id.planetTech)
        var location: TextView = itemView.findViewById(R.id.planetLocation)
        var travelButton: Button = itemView.findViewById(R.id.travelPlanetButton)

    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, p1: Int): TravelViewHolder {
        val v: View = LayoutInflater.from(viewGroup.context).inflate(R.layout.travel_card, viewGroup, false)
        return TravelViewHolder(v)
    }

    override fun getItemCount(): Int {
        return solarList.size
    }

    override fun onBindViewHolder(travelViewHolder: TravelViewHolder, i: Int) {
        travelViewHolder.name.text = solarList[i].planetName
        travelViewHolder.location.text = String.format("(${solarList[i].location.xPositionLocal}," +
                "${solarList[i].location.yPositionLocal})")
        travelViewHolder.res.text = solarList[i].resources.toString()
        travelViewHolder.techlev.text = solarList[i].techLevel.toString()
        travelViewHolder.img.setImageResource(ImageList.imageList[i])
        travelViewHolder.travelButton.setOnClickListener {
            if (viewModel.travel(solarList[i].location)) {
                Toast.makeText(
                    contextSub, "Successfully traveled to " + solarList[i].planetName + "!",
                    Toast.LENGTH_LONG
                ).show()
                ImageList.currImage = ImageList.imageList[i]
                Log.d("current planet", "curr planet is " + viewModel.getCurrentPlanet().planetName)
            } else {
                Toast.makeText(
                    contextSub, "You are already on " + solarList[i].planetName + "!",
                    Toast.LENGTH_LONG
                ).show()
            }
        }
    }


}
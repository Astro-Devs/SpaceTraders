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
import java.util.*

class UniAdapter : RecyclerView.Adapter<UniAdapter.UniViewHolder> {

    class UniViewHolder: RecyclerView.ViewHolder {

        var cv: CardView
        var name: TextView
        var img: ImageView
        var res: TextView
        var techlev: TextView
        var location: TextView

        constructor(itemView: View): super(itemView) {
            cv = itemView.findViewById(R.id.uni_card)
            name = itemView.findViewById(R.id.planetName)
            img = itemView.findViewById(R.id.planetIMG)
            res = itemView.findViewById(R.id.planetResource)
            techlev = itemView.findViewById(R.id.planetTech)
            location = itemView.findViewById(R.id.planetLocation)
        }
    }

    private var solarList: List<SolarSystem>
    private var imgList = ArrayList<Int>()

    constructor(solarList: List<SolarSystem>): super() {
        this.solarList = solarList
        populatePlanetImages()
    }

    override fun onAttachedToRecyclerView(recyclerView: RecyclerView) {
        super.onAttachedToRecyclerView(recyclerView)
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, p1: Int): UniViewHolder {
        var v: View = LayoutInflater.from(viewGroup.context).inflate(R.layout.universe_card, viewGroup, false)
        var vh = UniViewHolder(v)
        return vh
    }

    override fun getItemCount(): Int {
        return solarList.size
    }

    override fun onBindViewHolder(uniViewHolder: UniViewHolder, i: Int) {
        uniViewHolder.name.setText(solarList.get(i).planetName)
        uniViewHolder.location.setText("(${solarList.get(i).location.xPositionLocal}, ${solarList.get(i).location.yPositionLocal})")
        uniViewHolder.res.setText(solarList.get(i).resources.toString())
        uniViewHolder.techlev.setText(solarList.get(i).techLevel.toString())
        uniViewHolder.img.setImageResource(imgList.get(i))
    }

    fun populatePlanetImages() {
        imgList.add(R.drawable.planet1)
        imgList.add(R.drawable.planet2)
        imgList.add(R.drawable.planet3)
        imgList.add(R.drawable.planet4)
        imgList.add(R.drawable.planet5)
        imgList.add(R.drawable.planet6)
        imgList.add(R.drawable.planet7)
        imgList.add(R.drawable.planet8)
        imgList.add(R.drawable.planet9)
        imgList.add(R.drawable.planet10)
        imgList.add(R.drawable.planet11)
        imgList.add(R.drawable.planet12)
        imgList.add(R.drawable.planet13)
        imgList.add(R.drawable.planet14)
        imgList.add(R.drawable.planet15)
        imgList.add(R.drawable.planet16)
        imgList.add(R.drawable.planet17)
        imgList.add(R.drawable.planet18)
        imgList.add(R.drawable.planet19)
        imgList.add(R.drawable.planet20)
        Collections.shuffle(imgList)
    }
}
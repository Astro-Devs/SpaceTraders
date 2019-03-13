package edu.gatech.cs2340.spacetraders.viewmodel

import edu.gatech.cs2340.spacetraders.R
import java.util.*

class ImageList {
    companion object {
        var imageList = ArrayList<Int>()
        fun populatePlanetImages() {
            imageList.add(R.drawable.planet1)
            imageList.add(R.drawable.planet2)
            imageList.add(R.drawable.planet3)
            imageList.add(R.drawable.planet4)
            imageList.add(R.drawable.planet5)
            imageList.add(R.drawable.planet6)
            imageList.add(R.drawable.planet7)
            imageList.add(R.drawable.planet8)
            imageList.add(R.drawable.planet9)
            imageList.add(R.drawable.planet10)
            imageList.add(R.drawable.planet11)
            imageList.add(R.drawable.planet12)
            imageList.add(R.drawable.planet13)
            imageList.add(R.drawable.planet14)
            imageList.add(R.drawable.planet15)
            imageList.add(R.drawable.planet16)
            imageList.add(R.drawable.planet17)
            imageList.add(R.drawable.planet18)
            imageList.add(R.drawable.planet19)
            imageList.add(R.drawable.planet20)
            Collections.shuffle(imageList)
        }
    }
}
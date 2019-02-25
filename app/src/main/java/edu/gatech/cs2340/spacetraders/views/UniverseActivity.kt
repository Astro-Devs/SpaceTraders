package edu.gatech.cs2340.spacetraders.views

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import edu.gatech.cs2340.spacetraders.R

class UniverseActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.universe_layout_scrolling)
    }
}
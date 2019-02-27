package edu.gatech.cs2340.spacetraders.viewmodel

import android.arch.lifecycle.ViewModel
import edu.gatech.cs2340.spacetraders.entity.Coordinates
import edu.gatech.cs2340.spacetraders.entity.SolarSystem
import edu.gatech.cs2340.spacetraders.model.ModelFacade

/**
 * The ViewModel class for the Universe that works with Universe Activity
 */
class UniverseViewModel : ViewModel() {

    var modelFacade : ModelFacade = ModelFacade.getInstance()

    /**
     * Populate the universe view using the universe map
     * @return the map of the universe
     */
    fun populateUniverseView() : Map<Coordinates, SolarSystem> {
        return modelFacade.getUniverseMap()
    }
}
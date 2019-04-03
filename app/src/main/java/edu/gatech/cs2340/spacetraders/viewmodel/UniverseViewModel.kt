package edu.gatech.cs2340.spacetraders.viewmodel

import android.arch.lifecycle.ViewModel
import edu.gatech.cs2340.spacetraders.entity.Coordinates
import edu.gatech.cs2340.spacetraders.entity.RandomEvent
import edu.gatech.cs2340.spacetraders.entity.SolarSystem
import edu.gatech.cs2340.spacetraders.model.ModelFacade

/**
 * The ViewModel class for the Universe that works with Universe Activity
 */
class UniverseViewModel : ViewModel() {

    private var modelFacade: ModelFacade = ModelFacade.getInstance()


    /**
     * Provide the universe view with the universe array list
     * @return the array list of the universe
     */
    fun populateUniverseView(): ArrayList<SolarSystem> {
        return modelFacade.getUniverseArray()
    }

    /**
     * Provide the travel view with the travelable planets array list
     * @return the array list of the planets that can be travelled to
     */
    fun populateTravelView(): ArrayList<SolarSystem> {
        return modelFacade.getTravelList()
    }

    /**
     * Provide the view with the player's credit balance
     * @return number of credits that the player has
     */
    fun getPlayerCreds(): Int {
        return modelFacade.getPlayerCredits()
    }

    fun getShipFuel(): Int {
        return modelFacade.getShipFuel()
    }

    fun travel(destination: Coordinates): Boolean {
        return modelFacade.travel(destination)
    }

    fun getCurrentPlanet(): SolarSystem {
        return modelFacade.getCurrentPlanet()
    }

    fun getRandomEvent(): RandomEvent {
        return modelFacade.getCurrentRandomEvent()
    }

}
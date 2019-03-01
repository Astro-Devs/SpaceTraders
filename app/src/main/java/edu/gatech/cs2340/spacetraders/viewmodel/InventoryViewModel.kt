package edu.gatech.cs2340.spacetraders.viewmodel

import android.arch.lifecycle.ViewModel
import edu.gatech.cs2340.spacetraders.entity.Coordinates
import edu.gatech.cs2340.spacetraders.entity.Products
import edu.gatech.cs2340.spacetraders.entity.SolarSystem
import edu.gatech.cs2340.spacetraders.model.ModelFacade

/**
 * The ViewModel class for the Inventory that works with Inventory Activity
 */
class InventoryViewModel : ViewModel() {

    var modelFacade : ModelFacade = ModelFacade.getInstance()

    /**
     * Populate the inventory view using the inventory map
     * @return the inventory of the player
     */
    fun populateInventoryView() : Map<Products, Int>  {
        return modelFacade.getInventoryMap()
    }
}
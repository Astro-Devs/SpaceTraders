package edu.gatech.cs2340.spacetraders.viewmodel

import android.arch.lifecycle.ViewModel
import edu.gatech.cs2340.spacetraders.entity.Products
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
    fun getBuyableMarket() : Set<MutableMap.MutableEntry<Products, Int>>  {
        return modelFacade.getBuyableMarket()
    }

    fun getSellableMarket() : Set<MutableMap.MutableEntry<Products, Int>>  {
        return modelFacade.getSellableMarket()
    }

    fun getPriceMap() : HashMap<Products, Int> {
        return modelFacade.getPriceMap()
    }

    fun sell(products: Products, quantity: Int): Int {
        return modelFacade.sell(products, quantity)
    }

    fun buy(products: Products, quantity: Int): Int {
        return modelFacade.buy(products, quantity)
    }

    fun getPlayerCreds(): Int {
        return modelFacade.getPlayerCredits()
    }
}
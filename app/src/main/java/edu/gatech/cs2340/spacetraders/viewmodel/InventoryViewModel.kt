package edu.gatech.cs2340.spacetraders.viewmodel

import android.arch.lifecycle.ViewModel
import edu.gatech.cs2340.spacetraders.entity.Products
import edu.gatech.cs2340.spacetraders.model.ModelFacade

/**
 * The ViewModel class for the Inventory that works with Inventory Activity
 */
class InventoryViewModel : ViewModel() {

    var modelFacade: ModelFacade = ModelFacade.getInstance()

    /**
     * Provide the buyable market view with the planet's inventory
     * @return the inventory of the planet
     */
    fun getBuyableMarket(): Set<MutableMap.MutableEntry<Products, Int>> {
        return modelFacade.getBuyableMarket()
    }

    /**
     * Provide the sellable market view using the player's inventory
     * @return the inventory of the player
     */
    fun getSellableMarket(): Set<MutableMap.MutableEntry<Products, Int>> {
        return modelFacade.getSellableMarket()
    }

    /**
     * Provide the view with the price map for the products
     * @return the map of products to prices
     */
    fun getPriceMap(): HashMap<Products, Int> {
        return modelFacade.getPriceMap()
    }

    /**
     * Pass through the request to sell a product
     * @param products: the product to sell
     * @param quantity: the quantity of the product to sell
     */
    fun sell(products: Products, quantity: Int): Int {
        return modelFacade.sell(products, quantity)
    }

    /**
     * Pass through the request to buy a product
     * @param products: the product to buy
     * @param quantity: the quantity of the product to buy
     */
    fun buy(products: Products, quantity: Int): Int {
        return modelFacade.buy(products, quantity)
    }

    /**
     * Provide the view with the player's credit balance
     * @return number of credits that the player has
     */
    fun getPlayerCreds(): Int {
        return modelFacade.getPlayerCredits()
    }

    /**
     * Provide the view with information about whether the player's cargo space is full
     * @return whether the player's cargo space is full
     */
    fun isCargoFull(): Boolean {
        return modelFacade.isCargoFull()
    }
}
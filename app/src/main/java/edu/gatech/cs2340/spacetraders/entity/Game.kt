package edu.gatech.cs2340.spacetraders.entity

import android.util.Log

/**
 * Game class that represents an instance of the class
 * @param difficulty the game difficulty to play on
 * @param player the instance of the player that represents the user
 */
class Game(difficulty: GameDifficulty, player: Player){
    val player = player
    val difficulty = difficulty
    var universe : Universe = Universe()
    lateinit var marketPlace : MarketPlace

    fun createPlanets() {
        universe.createPlanets()
    }

    fun getUniverseMap() : Map<Coordinates, SolarSystem> {
        return universe.map
    }


    fun initializeMarketPlace() {
        val playerLocation : Coordinates = player.getLocation()
        val currentPlanetInventory : Inventory = universe.getPlanetInventory(playerLocation)
        val currentPlanetTechLevel : TechLevels = universe.getPlanetTechLevel(playerLocation)
        val currentPlanetResources : Resources = universe.getPlanetResources(playerLocation)
        marketPlace = MarketPlace(currentPlanetInventory, currentPlanetTechLevel, currentPlanetResources)

        marketPlace.stockInventory()
        marketPlace.initializePrices(player)
        Log.d("Solar System", "Current Planet " + currentPlanetInventory.toString())
        //Tests
        val furPrice : Int = this.getPriceMap()[Products.FUR]!!
        this.buy(player, Products.FUR, 3)
        this.buy(player, Products.WATER, 1)
        var credits : Int = player.credits
        var inventorySize : Int = player.getTotalAmountInInventory()
        Log.d("Solar System", "Fur Price: $furPrice, After buy credits: $credits, After buy Inventory size: $inventorySize")
        Log.d("Solar System", "Current Planet " + currentPlanetInventory.toString())
        this.sell(player, Products.FUR, 1)
        credits = player.credits
        inventorySize = player.getTotalAmountInInventory()
        Log.d("Solar System", "Fur Price: $furPrice, After sell credits: $credits, After sell Inventory size: $inventorySize")
        Log.d("Solar System", "Current Planet " + currentPlanetInventory.toString())
        //
    }

    fun getBuyableProducts() : MutableSet<MutableMap.MutableEntry<Products, Int>> {
        return marketPlace.getBuyableProducts()
    }

    fun getSellableProducts() : MutableSet<MutableMap.MutableEntry<Products, Int>> {
        return marketPlace.getSellableProducts(player.playerInventory)
    }

    fun getPriceMap() : HashMap<Products, Int> {
        return marketPlace.priceMap
    }

    fun buy(player : Player, product : Products, quantity : Int) {
        return marketPlace.buy(player, product, quantity)
    }

    fun sell(player : Player, product : Products, quantity : Int) {
        return marketPlace.sell(player, product, quantity)
    }

    override fun toString(): String {
        return "Game with difficulty: $difficulty, " + player.toString()
    }




}
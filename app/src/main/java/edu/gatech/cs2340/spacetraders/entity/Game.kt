package edu.gatech.cs2340.spacetraders.entity

import android.util.Log

/**
 * Game class that represents an instance of the class
 * @param difficulty the game difficulty to play on
 * @param player the instance of the player that represents the user
 */
class Game(difficulty: GameDifficulty, player: Player){
    private val player = player
    private val difficulty = difficulty
    private var universe : Universe = Universe()
    private lateinit var marketPlace : MarketPlace

    /**
     * Randomly select names, tech levels, resource levels, and coordinates,
     * create 20 planets with these attributes, and add them to the map
     */
    fun createPlanets() {
        universe.createPlanets()
    }

    /**
     * Getter for retrieving the universe map that when given a Coordinate, it return the Solar System at that
     * coordinate if it exists
     *
     * @return the map of the universe
     */
    fun getUniverseArray() : ArrayList<SolarSystem> {
        return universe.getPlanetArray()
    }

    /**
     * Creates the marketPlace based on the player's current location
     *
     * Initialize the marketPlace, stock the marketPlace with appropriate products, and initialize priceMap given the
     * Solar System's parameters
     */
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
//        val furPrice : Int = this.getPriceMap()[Products.FUR]!!
//        this.buy(player, Products.FUR, 3)
//        this.buy(player, Products.WATER, 1)
//        var credits : Int = player.credits
//        var inventorySize : Int = player.getTotalAmountInInventory()
//        Log.d("Solar System", "Fur Price: $furPrice, After buy credits: $credits, After buy Inventory size: $inventorySize")
//        Log.d("Solar System", "Current Planet " + currentPlanetInventory.toString())
//        this.sell(player, Products.FUR, 1)
//        credits = player.credits
//        inventorySize = player.getTotalAmountInInventory()
//        Log.d("Solar System", "Fur Price: $furPrice, After sell credits: $credits, After sell Inventory size: $inventorySize")
//        Log.d("Solar System", "Current Planet " + currentPlanetInventory.toString())
        //
    }

    /**
     * Getter for a set of entries, where each entry is a (product, quantity) pair that corresponds to a
     * buyable product and its inventory quantity
     *
     * @return a set of entries, where each entry is a (product, quantity) pair that corresponds to a buyable product
     * and its inventory quantity
     */
    fun getBuyableProducts() : MutableSet<MutableMap.MutableEntry<Products, Int>> {
        return marketPlace.getBuyableProducts()
    }

    /**
     * Getter for a set of entries, where each entry is a (product, quantity) pair that corresponds to a
     * sellable product and its inventory quantity
     *
     * @return a set of entries, where each entry is a (product, quantity) pair that corresponds to a sellable product
     * and its inventory quantity
     */
    fun getSellableProducts() : MutableSet<MutableMap.MutableEntry<Products, Int>> {
        return marketPlace.getSellableProducts(player.playerInventory)
    }


    /**
     * Getter for the priceMap corresponding to the current marketPlace. When given a product, the priceMap returns
     * that products price
     *
     * @return the priceMap corresponding to the current marketPlace
     */
    fun getPriceMap() : HashMap<Products, Int> {
        return marketPlace.priceMap
    }

    /**
     * Process a "buy" transaction between player and the current Solar System
     *
     * @param player the player buying the products
     * @param product the product the player is buying
     * @param quantity the amount of the product the player is buying
     * @return an Int indicating the success of the operation: 0 = success, 1 = "Not enough cargo capacity", 2 = "Not enough credits"
     */
    fun buy(player : Player, product : Products, quantity : Int) : Int {
        return marketPlace.buy(player, product, quantity)
    }

    /**
     * Process a "sell" transaction between player and the current Solar System
     *
     * @param player the player selling the products
     * @param product the product the player is selling
     * @param quantity the amount of the product the player is selling
     * @return an Int indicating the success of the operation: 0 = success, 1 = "Not enough of the product owned"
     */
    fun sell(player : Player, product : Products, quantity : Int) : Int {
        return marketPlace.sell(player, product, quantity)
    }

    fun getPlayerInventory() : Map<Products, Int> {
        return player.getInventoryMap()
    }

    override fun toString(): String {
        return "Game with difficulty: $difficulty, " + player.toString()
    }

    fun getPlayerCredits(): Int {
        return player.credits
    }

    fun getPlayer(): Player {
        return player
    }

    fun isCargoFull(quantity: Int): Boolean {
        return player.getTotalAmountInInventory() + quantity > player.getShipCargoCapacity()
    }

}
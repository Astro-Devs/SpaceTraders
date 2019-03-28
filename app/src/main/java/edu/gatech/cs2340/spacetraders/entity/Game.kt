package edu.gatech.cs2340.spacetraders.entity

import android.util.Log
import kotlin.random.Random

/**
 * Game class that represents an instance of the class
 * @param difficulty the game difficulty to play on
 * @param player the instance of the player that represents the user
 */
class Game(private val difficulty: GameDifficulty, private val player: Player) {
    private var universe: Universe = Universe()
    private lateinit var marketPlace: MarketPlace

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
    fun getUniverseArray(): ArrayList<SolarSystem> {
        return universe.getPlanetArray()
    }

    /**
     * Creates the marketPlace based on the player's current location
     *
     * Initialize the marketPlace, stock the marketPlace with appropriate products, and initialize priceMap given the
     * Solar System's parameters
     */
    fun initializeMarketPlace(randomEvent : RandomEvent = RandomEvent.NOEVENT) {
        val playerLocation: Coordinates = player.getLocation()
        val currentPlanetInventory: Inventory = universe.getPlanetInventory(playerLocation)
        val currentPlanetTechLevel: TechLevels = universe.getPlanetTechLevel(playerLocation)
        val currentPlanetResources: Resources = universe.getPlanetResources(playerLocation)
        marketPlace = MarketPlace(currentPlanetInventory, currentPlanetTechLevel, currentPlanetResources, randomEvent)

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
    fun getBuyableProducts(): MutableSet<MutableMap.MutableEntry<Products, Int>> {
        return marketPlace.getBuyableProducts()
    }

    /**
     * Getter for a set of entries, where each entry is a (product, quantity) pair that corresponds to a
     * sellable product and its inventory quantity
     *
     * @return a set of entries, where each entry is a (product, quantity) pair that corresponds to a sellable product
     * and its inventory quantity
     */
    fun getSellableProducts(): MutableSet<MutableMap.MutableEntry<Products, Int>> {
        return marketPlace.getSellableProducts(player.playerInventory)
    }


    /**
     * Getter for the priceMap corresponding to the current marketPlace. When given a product, the priceMap returns
     * that products price
     *
     * @return the priceMap corresponding to the current marketPlace
     */
    fun getPriceMap(): HashMap<Products, Int> {
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
    fun buy(player: Player, product: Products, quantity: Int): Int {
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
    fun sell(player: Player, product: Products, quantity: Int): Int {
        return marketPlace.sell(player, product, quantity)
    }

    override fun toString(): String {
        return "Game with difficulty: $difficulty, " + player.toString()
    }

    /**
     * Getter for credits of player
     *
     * @return credits of player
     */
    fun getPlayerCredits(): Int {
        return player.credits
    }

    fun getShipFuel(): Int {
        return player.getShipFuel()
    }

    /**
     * Getter for player object
     *
     * @return
     */
    fun getPlayer(): Player {
        return player
    }

    /**
     * checks to see if ship cargo is full - used to see if player can buy more objects
     *
     * @return boolean value for if cargo is full
     */
    fun isCargoFull(quantity: Int): Boolean {
        return player.getTotalAmountInInventory() + quantity > player.getShipCargoCapacity()
    }

    /**
     * Takes in a list of all planets, sorted by distance from current location
     * and creates a new ArrayList which has only the planets the player can
     * travel to based on current fuel
     *
     * @return sorted list of travelable planets
     */
    fun listTravelPlanets(): ArrayList<SolarSystem> {
        val fullList: ArrayList<SolarSystem> = universe.getPlanetArray()
        val travelList: ArrayList<SolarSystem> = ArrayList()
        for (i in fullList) {
            if (universe.distance(player.getLocation(), i.location) <= player.getShipFuel()) {
                travelList.add(i)
            }
        }
        return travelList
    }

    fun travel(destination: Coordinates): Boolean {
        val fuelToUse = Math.floor((universe.distance(player.getLocation(), destination))).toInt()
        when {
            fuelToUse == 0 -> {
                Log.d("travel", "already on the planet, cannot travel!")
                return false
            }
            fuelToUse > player.getShipFuel() -> {
                Log.d("fuel", "not enough fuel to travel")
                return false
            }
            else -> {
                player.setLocation(destination)
                player.subtractFuel(fuelToUse)
                Log.d("travel", "traveled to: " + destination + " Fuel left: " + player.getShipFuel())
                val randomEventList : Array<RandomEvent> = RandomEvent.values()
                initializeMarketPlace(randomEventList[Random.nextInt(0, randomEventList.size)])
                return true
            }

        }
    }

    fun getCurrentPlanet(): SolarSystem {
        return universe.getPlanet(player.getLocation())
    }

    fun getCurrentRandomEvent() : RandomEvent {
        return marketPlace.randomEvent
    }
}
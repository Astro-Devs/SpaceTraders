package edu.gatech.cs2340.spacetraders.model

import android.util.Log
import edu.gatech.cs2340.spacetraders.entity.*
import java.io.*


/**
 * Facade for the Model entities that allows to ViewModel
 * to interact with its attributes
 */
class ModelFacade private constructor() {
    /**
     * Companion object for the model facade
     */
    private var futureVisiblilityflag: Boolean = true   //variable for making mini game disappear
    companion object {
        private var modelFac: ModelFacade = ModelFacade()
        lateinit var newGame: Game

        fun getInstance(): ModelFacade {
            return modelFac
        }

    }

    /**
     * Getter for future visibility flag
     * @return Boolean if the flag is going to visible or not
     */
    fun getFlag(): Boolean {
        return futureVisiblilityflag
    }

    /**
     * Setter for future visibility flag
     * @param bool set future visibility
     */
    fun setFlag(bool: Boolean) {
        futureVisiblilityflag = bool
    }


    /**
     * Creates an instance of the game with the player's given attributes
     * @param difficulty the game difficulty to play the game on
     * @param player the player instance which represents the user
     * @return the new instance of the game with the given attributes
     */
    fun createGame(difficulty: GameDifficulty, player: Player): Game {
        newGame = Game(difficulty, player)
        Log.d("Test", newGame.toString())
        newGame.createPlanets()
        newGame.initializeMarketPlace()
        return newGame
    }

    /**
     * Getter for the universe map
     * @return the map of the universe
     */
    fun getUniverseArray(): ArrayList<SolarSystem> {
        return newGame.getUniverseArray()
    }

    /**
     * Getter for list of travelable planets
     *
     * @return ArrayList of travelable planets
     */
    fun getTravelList(): ArrayList<SolarSystem> {
        return newGame.listTravelPlanets()
    }

    /**
     * Getter for "buying" market
     *
     * @return Set holding elements of the buyable map
     * (map takes in products as key and has number of that product as value)
     */
    fun getBuyableMarket(): Set<MutableMap.MutableEntry<Products, Int>> {
        return newGame.getBuyableProducts()
    }

    /**
     * Getter for "selling" market
     *
     * @return Set holding elements of sellable map
     */
    fun getSellableMarket(): Set<MutableMap.MutableEntry<Products, Int>> {
        return newGame.getSellableProducts()
    }

    /**
     * Getter for map of prices and the product
     *
     * @return map of prices and product
     */
    fun getPriceMap(): HashMap<Products, Int> {
        return newGame.getPriceMap()
    }

    /**
     * Gets the number of player credits
     *
     * @return number of player credits
     */
    fun getPlayerCredits(): Int {
        return newGame.getPlayerCredits()
    }

    /**
     * Checks if cargo is full for when buying 1 element
     *
     * @return boolean value if full or not
     */
    fun isCargoFull(): Boolean {
        return newGame.isCargoFull(1)
    }

    /**
     * Sell function to be used in the view - from the marketplace
     *
     * @param products the product to sell
     * @param quantity how many of the product to sell
     * @return int if product sold or not
     */
    fun sell(products: Products, quantity: Int): Int {
        return newGame.sell(newGame.getPlayer(), products, quantity)
    }

    /**
     * Buy function to be used in view - from marketplace
     *
     * @param products product to buy
     * @param quantity how many of the product to buy
     * @return int if product bought or not
     */
    fun buy(products: Products, quantity: Int): Int {
        return newGame.buy(newGame.getPlayer(), products, quantity)
    }

    /**
     * travel() function from the Game
     *
     * @param destination the target to travel to
     * @return boolean value if travel succeeded or not
     */
    fun travel(destination: Coordinates): Boolean {
        return newGame.travel(destination)
    }

    /**
     * Getter for ship fuel
     *
     * @return the amount of ship fuel
     */
    fun getShipFuel(): Int {
        return newGame.getShipFuel()
    }

    /**
     * Getter for current planet
     *
     * @return current planet
     */
    fun getCurrentPlanet(): SolarSystem {
        return newGame.getCurrentPlanet()
    }

    /**
     * Getter for random event
     *
     * @return the RandomEvent on the current planet
     */
    fun getCurrentRandomEvent(): RandomEvent {
        return newGame.getCurrentRandomEvent()
    }

    /**
     * Add credits to player
     * @param addCredits amount of credits to be added
     */
    fun addCreditsToPlayer(addCredits: Int) {
        newGame.addCreditsToPlayer(addCredits)
    }

    /**
     * Method to allow the game load data from save file
     */
    fun load(file: File) {
        try {
            val fileIn = FileInputStream(file)
            val inn = ObjectInputStream(fileIn)
            newGame = inn.readObject() as Game
            Log.d("load", newGame.toString())
            inn.close()
            fileIn.close()
        } catch (e: ClassNotFoundException) {
            e.printStackTrace()
        } catch (e: FileNotFoundException) {
            e.printStackTrace()
        } catch (e: IOException) {
            e.printStackTrace()
        }
    }

    /**
     * Method that saves data from game
     */
    fun save(file: File) {
        try {
            val fileOut = FileOutputStream(file)
            val out = ObjectOutputStream(fileOut)
            out.writeObject(newGame)
            out.close()
            fileOut.close()
        } catch (e: FileNotFoundException) {
            e.printStackTrace()
        } catch (e: IOException) {
            e.printStackTrace()
        }
    }
}
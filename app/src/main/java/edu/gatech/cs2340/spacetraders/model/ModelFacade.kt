package edu.gatech.cs2340.spacetraders.model

import android.util.Log
import edu.gatech.cs2340.spacetraders.entity.*
import java.io.File
import java.io.FileInputStream
import java.io.FileNotFoundException
import java.io.ObjectInputStream
import java.io.IOException
import java.io.FileOutputStream
import java.io.ObjectOutputStream


/**
 * Facade for the Model entities that allows to ViewModel
 * to interact with its attributes
 */
class ModelFacade private constructor() {
    /**
     * Companion object for the model facade
     */
    companion object {
        var modelFac: ModelFacade = ModelFacade()
        lateinit var newGame: Game

        fun getInstance(): ModelFacade {
            return modelFac
        }

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
     * @return int if product sold or not
     */
    fun sell(products: Products, quantity: Int): Int {
        return newGame.sell(newGame.getPlayer(), products, quantity)
    }

    /**
     * Buy function to be used in view - from marketplace
     *
     * @return int if product bought or not
     */
    fun buy(products: Products, quantity: Int): Int {
        return newGame.buy(newGame.getPlayer(), products, quantity)
    }

    fun travel(destination: Coordinates): Boolean {
        return newGame.travel(destination)
    }

    fun getShipFuel(): Int {
        return newGame.getShipFuel()
    }

    fun getCurrentPlanet(): SolarSystem {
        return newGame.getCurrentPlanet()
    }


    fun getCurrentRandomEvent(): RandomEvent {
        return newGame.getCurrentRandomEvent()
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
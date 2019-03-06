package edu.gatech.cs2340.spacetraders.model

import android.util.Log
import edu.gatech.cs2340.spacetraders.entity.*

/**
 * Facade for the Model entities that allows to ViewModel
 * to interact with its attributes
 */
class ModelFacade private constructor() {
    /**
     * Companion object for the model facade
     */
    companion object {
        var modelFac : ModelFacade = ModelFacade()
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
    fun getUniverseArray() : ArrayList<SolarSystem> {
        return newGame.getUniverseArray()
    }

    fun getInventoryMap() : Map<Products, Int> {
        return newGame.getPlayerInventory()
    }

    fun getPlayerCredits() : Int {
        return getPlayerCredits()
    }
}
package edu.gatech.cs2340.spacetraders.model

import android.util.Log
import edu.gatech.cs2340.spacetraders.entity.Game
import edu.gatech.cs2340.spacetraders.entity.GameDifficulty
import edu.gatech.cs2340.spacetraders.entity.Player

class ModelFacade private constructor() {
    companion object {
        var modelFac : ModelFacade = ModelFacade()
        lateinit var newGame: Game

        fun getInstance(): ModelFacade {
            return modelFac
        }

    }

    fun createGame(difficulty: GameDifficulty, player: Player): Game {
        newGame = Game(difficulty, player)
        Log.d("Test", newGame.toString())
        return newGame
    }
}
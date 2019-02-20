package edu.gatech.cs2340.spacetraders.entity

/**
 * Game class that represents an instance of the class
 * @param difficulty the game difficulty to play on
 * @param player the instance of the player that represents the user
 */
class Game(difficulty: GameDifficulty, player: Player){
    val player = player
    val difficulty = difficulty

    override fun toString(): String {
        return "Game with difficulty: $difficulty, " + player.toString()
    }
}
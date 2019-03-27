package edu.gatech.cs2340.spacetraders.entity

import java.io.Serializable

/**
 * Game difficulty enum that shows the different
 * difficulty levels that players can choose
 * to play the game in
 */
enum class GameDifficulty : Serializable{
    BEGINNER,
    EASY,
    NORMAL,
    HARD,
    IMPOSSIBLE
}
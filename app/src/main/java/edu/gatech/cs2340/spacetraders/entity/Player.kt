package edu.gatech.cs2340.spacetraders.entity

/**
 * General Player class that simulates the user's attributes
 * @param name the name of the player
 * @param skillArr the integer array of the skill points
 * @param credits the money the player has
 * @param ship the player's spaceship to traverse the galaxy
 */
class Player constructor(val name : String, var skillArr : IntArray, var credits : Int = 1000,
                         var ship : Ship = Ship.GNAT ) {
    override fun toString(): String {
        return "Player with name: $name, Pilot Pts: ${skillArr[0]}, Engineer Pts: ${skillArr[1]}, " +
                "Trader Pts: ${skillArr[2]}, Fighter Pts: ${skillArr[3]}, Credits: $credits, Ship: $ship"
    }

}
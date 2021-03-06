package edu.gatech.cs2340.spacetraders.entity

import java.io.Serializable

/**
 * General Player class that simulates the user's attributes
 * @param name the name of the player
 * @param skillArr the integer array of the skill points
 * @param credits the money the player has
 * @param ship the player's spaceship to traverse the galaxy
 */
class Player constructor(
    val name: String, private var skillArr: IntArray, var credits: Int = 10000,
    private var ship: Ship = Ship()
) : Serializable{

    var playerInventory: Inventory = Inventory()

    init {
        this.addToInventory(Products.FUEL, ship.getShipFuel())
    }


    /**
     * Getter for the current location of the Player
     *
     * @return the Player's current location
     */
    fun getLocation(): Coordinates {
        return ship.location
    }

    /**
     * Setter for setting the new location of the ship
     *
     * @param newCoords the new coordinates to set the location to
     */
    fun setLocation(newCoords: Coordinates) {
        ship.location = newCoords
    }

    /**
     * Getter for the total amount of all products in Inventory
     *
     * @return the total amount of all products in Inventory
     */
    fun getTotalAmountInInventory(): Int {
        return playerInventory.getTotalAmountofProducts()
    }

    /**
     * Getter for the amount of "product" in Inventory
     *
     * @param product the product to get the amount of from the Inventory
     * @return the amount of the product in the Inventory
     */
    fun getAmountOf(product: Products): Int {
        return playerInventory.getAmountOf(product)
    }

    /**
     * Getter for the ships cargo capacity
     *
     * @return the ships cargo capacity
     */
    fun getShipCargoCapacity(): Int {
        return ship.getCargoCapacity()
    }

    /**
     * Getter for current fuel of ship
     *
     * @return fuel left in ship
     */
    fun getShipFuel(): Int {
        return ship.getShipFuel()
    }

    /**
     * Removes fuel from the ship
     *
     * @param fuelTraveled the fuel used in a travel()
     */
    fun subtractFuel(fuelTraveled: Int) {
        ship.fuel -= fuelTraveled
        this.removeFromInventory(Products.FUEL, fuelTraveled)
    }

    /**
     * Sets the ship's fuel to a static amount
     *
     * @param fuelAmt the amount to set the fuel to
     */
    fun setShipFuel(fuelAmt: Int) {
        ship.fuel = fuelAmt
    }

    /**
     * Adds "quantity" products to player's Inventory
     *
     * @param product the product to add into Inventory
     * @param quantity the amount of the product to add into Inventory
     */
    fun addToInventory(product: Products, quantity: Int) {
        playerInventory.add(product, quantity)
    }

    /**
     * Removes "quantity" products from player's Inventory
     *
     * @param product the product to remove from Inventory
     * @param quantity the amount of the product to remove from Inventory
     */
    fun removeFromInventory(product: Products, quantity: Int) {
        playerInventory.remove(product, quantity)
    }

    override fun toString(): String {
        return "Player with name: $name, Pilot Pts: ${skillArr[0]}, Engineer Pts: ${skillArr[1]}, " +
                "Trader Pts: ${skillArr[2]}, Fighter Pts: ${skillArr[3]}, Credits: $credits, Ship: $ship, " +
                playerInventory.toString()
    }

}
package edu.gatech.cs2340.spacetraders.entity

/**
 * Ship class that represents the ship the player is traveling on
 *
 * @param location the initial location of the ship
 * @param shipType represents the type of the ship
 */
class Ship(var location: Coordinates = Coordinates(0,0), var shipType: ShipType = ShipType.GNAT) {

    /**
     * Getter for the ship's Cargo Capacity
     *
     * @return the cargo capacity of the ship
     */
    fun getCargoCapacity(): Int {
        return shipType.cargoCapacity
    }
}
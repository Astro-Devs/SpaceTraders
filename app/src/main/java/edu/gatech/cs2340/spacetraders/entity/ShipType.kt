package edu.gatech.cs2340.spacetraders.entity

import java.io.Serializable

/**
 * Enum that represents the player's shipType
 *
 * @param cargoCapacity the cargo capacity of the shipType
 */
enum class ShipType(val cargoCapacity: Int) : Serializable{
    GNAT(10)
}
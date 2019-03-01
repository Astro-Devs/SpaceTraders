package edu.gatech.cs2340.spacetraders.entity


/**
 * Solar System class that represents each instance of a planet
 * @param planetName the planet's name
 * @param location the Coordinate location of each planet
 * @param techLevel the corresponding tech level of the planet
 * @param resources the corresponding resource level of the planet
 */
class SolarSystem(val planetName : String, val location : Coordinates,
                       val techLevel : TechLevels, val resources : Resources) {

    var planetInventory : Inventory = Inventory()
    var restockThreshold : Int = 0

    override fun toString(): String {
        return "$planetName\n$location\n$techLevel\n$resources\n" + planetInventory.toString()
    }

}

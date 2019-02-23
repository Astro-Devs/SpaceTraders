package edu.gatech.cs2340.spacetraders.entity

import edu.gatech.cs2340.spacetraders.entity.Resources.ResourceLevel.resourceLevel
import edu.gatech.cs2340.spacetraders.entity.TechLevels.Levels.levels
import kotlin.random.Random

/**
 * Solar System class that represents each instance of a planet
 * @param planetName the planet's name
 * @param location the Coordinate location of each planet
 * @param techLevel the corresponding tech level of the planet
 * @param resources the corresponding resource level of the planet
 */
class SolarSystem(val planetName : String, val location : Coordinates,
                       val techLevel : TechLevels, val resources : Resources) {

    override fun toString(): String {
        return "$planetName\n$location\n$techLevel\n$resources"
    }

}

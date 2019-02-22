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
//    val hamlet : SolarSystem = SolarSystem("Hamlet", Coordinates(0, 0), levels[Random.nextInt(levels.size)], resourceLevel[Random.nextInt(resourceLevel.size)]),
//    HELENA("Helena", Coordinates(0, 0), levels[Random.nextInt(levels.size)], resourceLevel[Random.nextInt(resourceLevel.size)]),
//    KLAATU("Klaatu", Coordinates(0, 0), levels[Random.nextInt(levels.size)], resourceLevel[Random.nextInt(resourceLevel.size)]),
//    POLLUX("Pollux", Coordinates(0, 0), levels[Random.nextInt(levels.size)], resourceLevel[Random.nextInt(resourceLevel.size)]),
//    TANUGA("Tanuga", Coordinates(0, 0), levels[Random.nextInt(levels.size)], resourceLevel[Random.nextInt(resourceLevel.size)]),
//    BRAX("Brax", Coordinates(0, 0), levels[Random.nextInt(levels.size)], resourceLevel[Random.nextInt(resourceLevel.size)]),
//    FROLIX("Frolix", Coordinates(0, 0), levels[Random.nextInt(levels.size)], resourceLevel[Random.nextInt(resourceLevel.size)]),
//    DENEVA("Deneva", Coordinates(0, 0), levels[Random.nextInt(levels.size)], resourceLevel[Random.nextInt(resourceLevel.size)]),
//    ZUUL("Zuul", Coordinates(0, 0), levels[Random.nextInt(levels.size)], resourceLevel[Random.nextInt(resourceLevel.size)]),
//    UTOPIA("Utopia", Coordinates(0, 0), levels[Random.nextInt(levels.size)], resourceLevel[Random.nextInt(resourceLevel.size)])
}

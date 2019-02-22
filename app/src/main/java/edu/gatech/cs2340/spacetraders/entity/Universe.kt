package edu.gatech.cs2340.spacetraders.entity

import kotlin.math.abs
import kotlin.math.hypot
import kotlin.random.Random

/**
 * Universe class that represents the structurer for managing solar systems
 */
class Universe {

    /**
     * Calculate the distance between two planets
     *
     * @param p1 : coordinates for planet 1
     * @param p2 : coordinates for planet 2
     * @return distance between the coordinates for planets 1 and 2
     */
    fun distance(p1 : Coordinates, p2 : Coordinates) : Double {
        return hypot(abs(p2.xPositionLocal - p1.xPositionLocal) as Double, abs(p2.yPositionLocal - p1.yPositionLocal) as Double)
    }

    /**
     * Randomly generate coordinates
     *
     * @return the randomly generated coordinates
     */
    fun randomCoordinatesGenerator() : Coordinates {
        return Coordinates(Random.nextInt(100), Random.nextInt(100))
    }

    val map : HashMap<Coordinates, SolarSystem> = HashMap()

    fun coordinatesToPlanets() : Void {
        while (map.size < 10) {
            
        }
    }

    val hamlet : SolarSystem = SolarSystem("Hamlet", Coordinates(0, 0), TechLevels.levels[Random.nextInt(TechLevels.levels.size)], Resources.resourceLevel[Random.nextInt(
        Resources.resourceLevel.size)])

}
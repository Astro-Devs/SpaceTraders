package edu.gatech.cs2340.spacetraders.entity

import kotlin.math.abs
import kotlin.math.hypot
import kotlin.random.Random

/**
 * Everything happens here
 */
class Universe {
    fun distance(p1 : Coordinates, p2 : Coordinates) : Double {
        return hypot(abs(p2.xPositionLocal - p1.xPositionLocal) as Double, abs(p2.yPositionLocal - p1.yPositionLocal) as Double)
    }

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
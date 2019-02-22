package edu.gatech.cs2340.spacetraders.entity

import edu.gatech.cs2340.spacetraders.entity.Resources.ResourceLevel.resourceLevel
import edu.gatech.cs2340.spacetraders.entity.TechLevels.Levels.levels
import kotlin.random.Random

enum class SolarSystem(val planetName : String, val location : Coordinates,
                       val techLevel : TechLevels, val resources : Resources) {

    HAMLET("Hamlet", Coordinates(0, 0), levels[Random.nextInt(levels.size)], resourceLevel[Random.nextInt(resourceLevel.size)]),
    HELENA("Helena", Coordinates(0, 0), levels[Random.nextInt(levels.size)], resourceLevel[Random.nextInt(resourceLevel.size)]),
    KLAATU("Klaatu", Coordinates(0, 0), levels[Random.nextInt(levels.size)], resourceLevel[Random.nextInt(resourceLevel.size)]),
    POLLUX("Pollux", Coordinates(0, 0), levels[Random.nextInt(levels.size)], resourceLevel[Random.nextInt(resourceLevel.size)]),
    TANUGA("Tanuga", Coordinates(0, 0), levels[Random.nextInt(levels.size)], resourceLevel[Random.nextInt(resourceLevel.size)]),
    BRAX("Brax", Coordinates(0, 0), levels[Random.nextInt(levels.size)], resourceLevel[Random.nextInt(resourceLevel.size)]),
    FROLIX("Frolix", Coordinates(0, 0), levels[Random.nextInt(levels.size)], resourceLevel[Random.nextInt(resourceLevel.size)]),
    DENEVA("Deneva", Coordinates(0, 0), levels[Random.nextInt(levels.size)], resourceLevel[Random.nextInt(resourceLevel.size)]),
    ZUUL("Zuul", Coordinates(0, 0), levels[Random.nextInt(levels.size)], resourceLevel[Random.nextInt(resourceLevel.size)]),
    UTOPIA("Utopia", Coordinates(0, 0), levels[Random.nextInt(levels.size)], resourceLevel[Random.nextInt(resourceLevel.size)])
}

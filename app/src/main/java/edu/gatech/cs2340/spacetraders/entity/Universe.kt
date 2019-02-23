package edu.gatech.cs2340.spacetraders.entity

import android.arch.lifecycle.Transformations.map
import android.util.Log
import edu.gatech.cs2340.spacetraders.entity.Resources.ResourceLevel.resourceLevel
import edu.gatech.cs2340.spacetraders.entity.TechLevels.Levels.levels
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
        return hypot(abs(p2.xPositionLocal - p1.xPositionLocal).toDouble(), abs(p2.yPositionLocal - p1.yPositionLocal) as Double)
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
    val names = arrayListOf<String>("Festen",		// A great Scandinavian movie
        "Fourmi",		// An ant, in French
        "Frolix",		// A solar system in one of Philip K. Dick's novels
        "Gemulon",
        "Guinifer",		// One way of writing the name of king Arthur's wife
        "Hades",		// The underworld
        "Hamlet",		// From Shakespeare
        "Helena",		// Of Troy
        "Hulst",		// A Dutch plant
        "Iodine",		// An element
        "Iralius",
        "Janus",		// A seldom encountered Dutch boy's name
        "Japori",
        "Jarada",
        "Jason",		// A Greek hero
        "Kaylon",
        "Khefka",
        "Kira",			// My dog's name
        "Klaatu",		// From a classic SF movie
        "Klaestron",
        "Korma",		// An Indian sauce
        "Kravat",		// Interesting spelling of the French word for "tie"
        "Krios",
        "Laertes",		// A king in a Greek tragedy
        "Largo",
        "Lave",			// The starting system in Elite
        "Ligon",
        "Lowry",		// The name of the "hero" in Terry Gilliam's "Brazil"
        "Magrat",		// The second of the witches in Pratchett's Discworld
        "Malcoria",
        "Melina",
        "Mentar",		// The Psilon home system in Master of Orion
        "Merik")

    /**
     * Randomly select names, tech levels, resource levels, and coordinates,
     * create 10 planet with these attributes, and add them to the map
     */
    fun createPlanets() {
        while (map.size < 10) {
            val coords : Coordinates = randomCoordinatesGenerator()
            if (!map.containsKey(coords)) {
                map[coords] = SolarSystem(names.removeAt(Random.nextInt(names.size)),
                    coords, levels[Random.nextInt(levels.size)],
                    resourceLevel[Random.nextInt(resourceLevel.size)])
            }
        }
        for ((key, value) in map) {
            Log.d("Solar System: ", key.toString() + " \n" + value.toString())
        }
    }

}
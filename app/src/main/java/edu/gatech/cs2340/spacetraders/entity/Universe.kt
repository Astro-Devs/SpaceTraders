package edu.gatech.cs2340.spacetraders.entity

import android.util.Log
import edu.gatech.cs2340.spacetraders.entity.Resources.ResourceLevel.resourceLevel
import edu.gatech.cs2340.spacetraders.entity.TechLevels.Levels.levels
import java.io.Serializable
import kotlin.math.abs
import kotlin.math.hypot
import kotlin.random.Random

/**
 * Universe class that represents the structurer for managing solar systems
 */
class Universe : Serializable{

    /**
     * Calculate the distance between two planets
     *
     * @param p1 : coordinates for planet 1
     * @param p2 : coordinates for planet 2
     * @return distance between the coordinates for planets 1 and 2
     */
    fun distance(p1: Coordinates, p2: Coordinates): Double {
        return hypot(
            abs(p2.xPositionLocal - p1.xPositionLocal).toDouble(),
            abs(p2.yPositionLocal - p1.yPositionLocal).toDouble()
        )
    }

    /**
     * Randomly generate coordinates
     *
     * @return the randomly generated coordinates
     */
    private fun randomCoordinatesGenerator(): Coordinates {
        return Coordinates(Random.nextInt(100), Random.nextInt(100))
    }

    private val map: HashMap<Coordinates, SolarSystem> = HashMap()
    private val names = arrayListOf(
        "Acamar",
        "Adahn",        // The alternate personality for The Nameless One in "Planescape: Torment"
        "Aldea",
        "Andevian",
        "Antedi",
        "Balosnee",
        "Baratas",
        "Brax",            // One of the heroes in Master of Magic
        "Bretel",        // This is a Dutch device for keeping your pants up.
        "Calondia",
        "Campor",
        "Capelle",        // The city I lived in while programming this game
        "Carzon",
        "Castor",        // A Greek demi-god
        "Cestus",
        "Cheron",
        "Courteney",    // After Courteney Cox…
        "Daled",
        "Damast",
        "Davlos",
        "Deneb",
        "Deneva",
        "Devidia",
        "Draylon",
        "Drema",
        "Endor",
        "Esmee",        // One of the witches in Pratchett's Discworld
        "Exo",
        "Ferris",        // Iron
        "Festen",        // A great Scandinavian movie
        "Fourmi",        // An ant, in French
        "Frolix",        // A solar system in one of Philip K. Dick's novels
        "Gemulon",
        "Guinifer",        // One way of writing the name of king Arthur's wife
        "Hades",        // The underworld
        "Hamlet",        // From Shakespeare
        "Helena",        // Of Troy
        "Hulst",        // A Dutch plant
        "Iodine",        // An element
        "Iralius",
        "Janus",        // A seldom encountered Dutch boy's name
        "Japori",
        "Jarada",
        "Jason",        // A Greek hero
        "Kaylon",
        "Khefka",
        "Kira",            // My dog's name
        "Klaatu",        // From a classic SF movie
        "Klaestron",
        "Korma",        // An Indian sauce
        "Kravat",        // Interesting spelling of the French word for "tie"
        "Krios",
        "Laertes",        // A king in a Greek tragedy
        "Largo",
        "Lave",            // The starting system in Elite
        "Ligon",
        "Lowry",        // The name of the "hero" in Terry Gilliam's "Brazil"
        "Magrat",        // The second of the witches in Pratchett's Discworld
        "Malcoria",
        "Melina",
        "Mentar",        // The Psilon home system in Master of Orion
        "Merik",
        "Mintaka",
        "Montor",        // A city in Ultima III and Ultima VII part 2
        "Mordan",
        "Myrthe",        // The name of my daughter
        "Nelvana",
        "Nix",            // An interesting spelling of a word meaning "nothing" in Dutch
        "Nyle",            // An interesting spelling of the great river
        "Odet",
        "Og",            // The last of the witches in Pratchett's Discworld
        "Omega",        // The end of it all
        "Omphalos",        // Greek for navel
        "Orias",
        "Othello",        // From Shakespeare
        "Parade",        // This word means the same in Dutch and in English
        "Penthara",
        "Picard",        // The enigmatic captain from ST:TNG
        "Pollux",        // Brother of Castor
        "Quator",
        "Rakhar",
        "Ran",            // A film by Akira Kurosawa
        "Regulas",
        "Relva",
        "Rhymus",
        "Rochani",
        "Rubicum",        // The river Ceasar crossed to get into Rome
        "Rutia",
        "Sarpeidon",
        "Sefalla",
        "Seltrice",
        "Sigma",
        "Sol",            // That's our own solar system
        "Somari",
        "Stakoron",
        "Styris",
        "Talani",
        "Tamus",
        "Tantalos",        // A king from a Greek tragedy
        "Tanuga",
        "Tarchannen",
        "Terosa",
        "Thera",        // A seldom encountered Dutch girl's name
        "Titan",        // The largest moon of Jupiter
        "Torin",        // A hero from Master of Magic
        "Triacus",
        "Turkana",
        "Tyrus",
        "Umberlee",        // A god from AD&D, which has a prominent role in Baldur's Gate
        "Utopia",        // The ultimate goal
        "Vadera",
        "Vagra",
        "Vandor",
        "Ventax",
        "Xenon",
        "Xerxes",        // A Greek hero
        "Yew",            // A city which is in almost all of the Ultima games
        "Yojimbo",        // A film by Akira Kurosawa
        "Zalkon",
        "Zuul"
    )            // From the first Ghostbusters movie)

    /**
     * Randomly select names, tech levels, resource levels, and coordinates,
     * create 20 planets with these attributes, and add them to the map
     */
    fun createPlanets() {
        map[Coordinates(50, 50)] = SolarSystem(
            "Home Park",
            Coordinates(50, 50), levels[0], resourceLevel[0]
        )
        while (map.size < 20) {
            val coords: Coordinates = randomCoordinatesGenerator()
            if (!map.containsKey(coords)) {
                map[coords] = SolarSystem(
                    names.removeAt(Random.nextInt(names.size)),
                    coords, levels[Random.nextInt(levels.size)],
                    resourceLevel[Random.nextInt(resourceLevel.size)]
                )
            }
        }
        for ((key, value) in map) {
            largeLog("Solar System: ", "$key \n$value")
        }
    }

    /**
     * Getter for the Solar System at "coordinates" tech level
     *
     * @param coordinates the location of the Solar System you want to get from
     * @return the tech level of the Solar System
     */
    fun getPlanetTechLevel(coordinates: Coordinates): TechLevels {
        return map[coordinates]!!.techLevel
    }

    /**
     * Getter for the Solar System at "coordinates" resource level
     *
     * @param coordinates the location of the Solar System you want to get from
     * @return the resource level of the Solar System
     */
    fun getPlanetResources(coordinates: Coordinates): Resources {
        return map[coordinates]!!.resources
    }

    /**
     * Getter for the Solar System at "coordinates" Inventory
     *
     * @param coordinates the location of the Solar System you want to get from
     * @return the Inventory of the Solar System
     */
    fun getPlanetInventory(coordinates: Coordinates): Inventory {
        return map[coordinates]!!.planetInventory
    }

    /**
     * Getter for current planet
     *
     * @param coordinates the coordinates the ship/player is on
     * @return the SolarSystem corresponding to the location
     */
    fun getPlanet(coordinates: Coordinates): SolarSystem {
        return map[coordinates]!!
    }

    /**
     * Takes in the existing planet map and puts all the planets in order, based on their distance from
     * home (50, 50) and then places them in an ArrayList
     *
     * @return ArrayList of sorted planets
     */
    fun getPlanetArray(): ArrayList<SolarSystem> {
        val values: Collection<SolarSystem> = map.values
        val unsorted: ArrayList<SolarSystem> = ArrayList(values)
        val sorted: ArrayList<SolarSystem> = ArrayList()
        for (j in 0..19) {
            var smallest = 0
            for (i in unsorted.indices) {
                smallest = if (distance(Coordinates(50, 50), unsorted[smallest].location) <
                    distance(Coordinates(50, 50), unsorted[i].location)
                ) smallest else i
            }
            sorted.add(unsorted.removeAt(smallest))
        }
        return sorted
    }


    /**
     * Workaround to 4000 byte limit to a log entry, printing to the logcat
     * @param tag the tag to search in the logcat
     * @param content the message to print to logcat
     */
    private fun largeLog(tag: String, content: String) {
        if (content.length > 4000) {
            Log.d(tag, content.substring(0, 4000))
            largeLog(tag, content.substring(4000))
        } else {
            Log.d(tag, content)
        }
    }

}
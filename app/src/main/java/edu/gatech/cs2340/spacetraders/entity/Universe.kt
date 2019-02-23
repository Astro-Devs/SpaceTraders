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
    val names = arrayListOf<String>("Acamar",
        "Adahn",		// The alternate personality for The Nameless One in "Planescape: Torment"
        "Aldea",
        "Andevian",
        "Antedi",
        "Balosnee",
        "Baratas",
        "Brax",			// One of the heroes in Master of Magic
        "Bretel",		// This is a Dutch device for keeping your pants up.
        "Calondia",
        "Campor",
        "Capelle",		// The city I lived in while programming this game
        "Carzon",
        "Castor",		// A Greek demi-god
        "Cestus",
        "Cheron",
        "Courteney",	// After Courteney Cox…
        "Daled",
        "Damast",
        "Davlos",
        "Deneb",
        "Deneva",
        "Devidia",
        "Draylon",
        "Drema",
        "Endor",
        "Esmee",		// One of the witches in Pratchett's Discworld
        "Exo",
        "Ferris",		// Iron
        "Festen",		// A great Scandinavian movie
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
        "Merik",
        "Mintaka",
        "Montor",		// A city in Ultima III and Ultima VII part 2
        "Mordan",
        "Myrthe",		// The name of my daughter
        "Nelvana",
        "Nix",			// An interesting spelling of a word meaning "nothing" in Dutch
        "Nyle",			// An interesting spelling of the great river
        "Odet",
        "Og",			// The last of the witches in Pratchett's Discworld
        "Omega",		// The end of it all
        "Omphalos",		// Greek for navel
        "Orias",
        "Othello",		// From Shakespeare
        "Parade",		// This word means the same in Dutch and in English
        "Penthara",
        "Picard",		// The enigmatic captain from ST:TNG
        "Pollux",		// Brother of Castor
        "Quator",
        "Rakhar",
        "Ran",			// A film by Akira Kurosawa
        "Regulas",
        "Relva",
        "Rhymus",
        "Rochani",
        "Rubicum",		// The river Ceasar crossed to get into Rome
        "Rutia",
        "Sarpeidon",
        "Sefalla",
        "Seltrice",
        "Sigma",
        "Sol",			// That's our own solar system
        "Somari",
        "Stakoron",
        "Styris",
        "Talani",
        "Tamus",
        "Tantalos",		// A king from a Greek tragedy
        "Tanuga",
        "Tarchannen",
        "Terosa",
        "Thera",		// A seldom encountered Dutch girl's name
        "Titan",		// The largest moon of Jupiter
        "Torin",		// A hero from Master of Magic
        "Triacus",
        "Turkana",
        "Tyrus",
        "Umberlee",		// A god from AD&D, which has a prominent role in Baldur's Gate
        "Utopia",		// The ultimate goal
        "Vadera",
        "Vagra",
        "Vandor",
        "Ventax",
        "Xenon",
        "Xerxes",		// A Greek hero
        "Yew",			// A city which is in almost all of the Ultima games
        "Yojimbo",		// A film by Akira Kurosawa
        "Zalkon",
        "Zuul")			// From the first Ghostbusters movie)

    /**
     * Randomly select names, tech levels, resource levels, and coordinates,
     * create 10 planet with these attributes, and add them to the map
     */
    fun createPlanets() {
        while (map.size < 20) {
            val coords : Coordinates = randomCoordinatesGenerator()
            if (!map.containsKey(coords)) {
                map[coords] = SolarSystem(names.removeAt(Random.nextInt(names.size)),
                    coords, levels[Random.nextInt(levels.size)],
                    resourceLevel[Random.nextInt(resourceLevel.size)])
            }
        }
        for ((key, value) in map) {
            largeLog("Solar System: ", key.toString() + " \n" + value.toString())
        }
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
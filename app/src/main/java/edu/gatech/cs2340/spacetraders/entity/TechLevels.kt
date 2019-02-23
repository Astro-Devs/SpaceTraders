package edu.gatech.cs2340.spacetraders.entity

/**
 * Enum that lists the different tech levels
 * for the solar systems
 * @param level the number corresponding to the tech level
 */
enum class TechLevels(val level: Int) {
    PREAGRICULTURE(0),
    AGRICULTURE(1),
    MEDIEVAL(2),
    RENAISSANCE(3),
    EARLYINDUSTRIAL(4),
    INDUSTRIAL(5),
    POST_INDUSTRIAL(6),
    HITECH(7);

    /**
     * Companion object Levels for the TechLevels
     * in order to access the ordinal values as an array
     */
    companion object Levels {
        val levels : Array<TechLevels> = TechLevels.values()
    }

}
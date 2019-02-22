package edu.gatech.cs2340.spacetraders.entity

/**
 * Enum that lists the different resources
 * for the solar systems
 */
enum class Resources(val level: Int) {
    NOSPECIALRESOURCES(0),
    MINERALRICH(1),
    MINERALPOOR(2),
    DESERT(3),
    LOTSOFWATER(4),
    RICHSOIL(5),
    POORSOIL(6),
    RICHFAUNA(7),
    LIFELESS(8),
    WEIRDMUSHROOMS(9),
    LOTSOFHERBS(10),
    ARTISTIC(11),
    WARLIKE(12);

    companion object ResourceLevel {
        val resourceLevel : Array<Resources> = Resources.values()
    }
}
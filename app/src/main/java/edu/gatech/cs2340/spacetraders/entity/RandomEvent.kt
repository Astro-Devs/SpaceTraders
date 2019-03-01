package edu.gatech.cs2340.spacetraders.entity

/**
 * RandomEvent enum that represents a random condition of a Solar System, affects product pricing
 */
enum class RandomEvent {
    NOEVENT,
    DROUGHT,
    COLD,
    CROPFAIL,
    WAR,
    BOREDOM,
    PLAGUE,
    LACKOFWORKERS;
}
package edu.gatech.cs2340.spacetraders.entity

import java.io.Serializable

/**
 * RandomEvent enum that represents a random condition of a Solar System, affects product pricing
 */
enum class RandomEvent : Serializable{
    NOEVENT,
    DROUGHT,
    COLD,
    CROPFAIL,
    WAR,
    BOREDOM,
    PLAGUE,
    LACKOFWORKERS;
}
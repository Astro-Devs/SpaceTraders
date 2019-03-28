package edu.gatech.cs2340.spacetraders.entity

import java.io.Serializable

/**
 * Coordinates class that represents the grid map of the universe
 * @param xPosition the x position of the given planet
 * @param yPosition the y position of the given planet
 */
class Coordinates(xPosition: Int, yPosition: Int) : Serializable{

    val xPositionLocal: Int = xPosition
    val yPositionLocal: Int = yPosition

    override fun hashCode(): Int {
        return 3697 * xPositionLocal + 1229 * yPositionLocal
    }

    override fun equals(other: Any?): Boolean {
        if (other == null || other !is Coordinates) {
            return false
        }
        return this.xPositionLocal == other.xPositionLocal
                && this.yPositionLocal == other.yPositionLocal
    }

    override fun toString(): String {
        return "($xPositionLocal, $yPositionLocal)"
    }
}
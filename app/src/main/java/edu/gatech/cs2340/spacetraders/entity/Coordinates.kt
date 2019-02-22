package edu.gatech.cs2340.spacetraders.entity

/**
 * Coordinates class that represents the grid map of the universe
 * @param xPosition the x position of the given planet
 * @param yPosition the y position of the given planet
 */
class Coordinates(xPosition : Int, yPosition : Int) {
    /**
     * Point inner class that represents the actual point of a planet
     * @param pointA the first point of the planet
     * @param pointB the second point of the planet
     */
//    inner class Point(pointA : Int, pointB : Int) {
//        val pointA = pointA
//        val pointB = pointB
//    }

    val xPositionLocal : Int = xPosition
    val yPositionLocal : Int = yPosition

}
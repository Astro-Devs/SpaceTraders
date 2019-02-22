package edu.gatech.cs2340.spacetraders.entity

import android.graphics.Point
import kotlin.math.abs
import kotlin.math.hypot
import kotlin.random.Random

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

    fun distance(p1 : Coordinates, p2 : Coordinates) : Double {
        return hypot(abs(p2.xPositionLocal - p1.xPositionLocal) as Double, abs(p2.yPositionLocal - p1.yPositionLocal) as Double)
    }

    fun randomPointGenerator() : Point {
        return Point(Random.nextInt(100), Random.nextInt(100))
    }
}
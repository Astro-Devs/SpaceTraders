package edu.gatech.cs2340.spacetraders.entity

import kotlin.math.abs
import kotlin.math.hypot

class Coordinates {
    inner class Point(pointA : Int, pointB : Int) {
        val pointA = pointA
        val pointB = pointB
    }

    fun distance(p1 : Point, p2 : Point) : Double {
        return hypot(abs(p2.pointA - p1.pointA) as Double, abs(p2.pointB - p1.pointB) as Double)
    }
}
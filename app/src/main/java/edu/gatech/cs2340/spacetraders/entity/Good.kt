package edu.gatech.cs2340.spacetraders.entity

/**
 * Good class that represents a trade good (product) that can be traded
 *
 * @param name Name of the good
 * @param MTLP Minimum Tech Level to Produce this resource (You can't buy on planets below this level)
 * @param MTLU Minimum Tech Level to Use this resource (You can't sell on planets below this level)
 * @param TTP Tech Level which produces the most of this item
 * @param IPL Price increase per tech level
 * @param Var variance is the maximum percentage that the price can vary above or below the base
 * @param IE Radical price increase event, when this even happens on a planet, the price may increase astronomically
 * @param CR When this condition is present, the price of this resource is unusually low
 * @param ER When this condition is present, the resource is expensive
 * @param MTL Min price offered in space trade with random trader (not on a planet)
 * @param MTH Max price offered in space trade with random trader (not on a planet)
 *
 */
class Good(val name : String, val MTLP : TechLevels, val MTLU : TechLevels, val TTP: TechLevels, val IPL : Float,
           val Var : Float, val IE: RadicalPriceIncreaseEvent, val CR: PlanetCondition, val ER: PlanetCondition,
           val MTL: Float, val MTH: Float) {

    override fun toString(): String {
        return "Name: $name, MTLP: $MTLP, MTLU: $MTLU, TTP: $TTP, IPL: $IPL, \n" +
                "Var: $Var, IE: $IE, CR: $CR, ER: $ER, MTL: $MTL, MTH: $MTH"
    }

}
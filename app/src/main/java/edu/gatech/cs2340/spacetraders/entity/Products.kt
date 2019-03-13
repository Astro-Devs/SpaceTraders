package edu.gatech.cs2340.spacetraders.entity

/**
 * Products enum that represents trade goods (products) that can be traded
 *
 * @param MTLP Minimum Tech Level to Produce this resource (You can't buy on planets below this level)
 * @param MTLU Minimum Tech Level to Use this resource (You can't sell on planets below this level)
 * @param TTP Tech Level which produces the most of this item
 * @param IPL Price increase per tech level
 * @param Var variance is the maximum percentage that the price can vary above or below the base
 * @param RE Radical price increase event, when this even happens on a planet, the price may increase astronomically
 * @param CR When this condition is present, the price of this resource is unusually low
 * @param ER When this condition is present, the resource is expensive
 * @param MTL Min price offered in space trade with random trader (not on a planet)
 * @param MTH Max price offered in space trade with random trader (not on a planet)
 *
 */
enum class Products(
    val MTLP: TechLevels, val MTLU: TechLevels, val TTP: TechLevels, val BASEPRICE: Int,
    val IPL: Int, val Var: Int, val RE: RandomEvent, val CR: Resources, val ER: Resources,
    val MTL: Int, val MTH: Int
) {
    FUEL(
        TechLevels.PREAGRICULTURE, TechLevels.PREAGRICULTURE, TechLevels.INDUSTRIAL, 100,
        20, 10, RandomEvent.WAR, Resources.MINERALRICH, Resources.MINERALPOOR,
        150, 200
    ),
    WATER(
        TechLevels.PREAGRICULTURE, TechLevels.PREAGRICULTURE, TechLevels.MEDIEVAL, 30,
        3, 4, RandomEvent.NOEVENT, Resources.LOTSOFWATER, Resources.DESERT,
        30, 50
    ),
    FUR(
        TechLevels.PREAGRICULTURE, TechLevels.PREAGRICULTURE, TechLevels.PREAGRICULTURE, 250,
        10, 10, RandomEvent.COLD, Resources.RICHFAUNA, Resources.LIFELESS,
        230, 280
    ),
    FOOD(
        TechLevels.AGRICULTURE, TechLevels.PREAGRICULTURE, TechLevels.AGRICULTURE, 100,
        5, 5, RandomEvent.CROPFAIL, Resources.RICHSOIL, Resources.POORSOIL,
        90, 160
    ),
    ORE(
        TechLevels.MEDIEVAL, TechLevels.MEDIEVAL, TechLevels.RENAISSANCE, 350, 20, 10,
        RandomEvent.WAR, Resources.MINERALRICH, Resources.MINERALPOOR, 350, 420
    ),
    GAMES(
        TechLevels.RENAISSANCE, TechLevels.AGRICULTURE, TechLevels.POST_INDUSTRIAL, 250, -10, 5,
        RandomEvent.BOREDOM, Resources.ARTISTIC, Resources.NOSPECIALRESOURCES, 160, 270
    ),
    FIREARMS(
        TechLevels.RENAISSANCE, TechLevels.AGRICULTURE, TechLevels.INDUSTRIAL, 1250, -75, 100,
        RandomEvent.WAR, Resources.WARLIKE, Resources.NOSPECIALRESOURCES, 600, 1100
    ),
    MEDICINE(
        TechLevels.EARLYINDUSTRIAL, TechLevels.AGRICULTURE, TechLevels.POST_INDUSTRIAL, 650, -20,
        10, RandomEvent.PLAGUE, Resources.LOTSOFHERBS, Resources.NOSPECIALRESOURCES, 400, 700
    ),
    MACHINES(
        TechLevels.EARLYINDUSTRIAL, TechLevels.RENAISSANCE, TechLevels.INDUSTRIAL, 900, -30, 5,
        RandomEvent.LACKOFWORKERS, Resources.NOSPECIALRESOURCES, Resources.NOSPECIALRESOURCES, 600, 800
    ),
    NARCOTICS(
        TechLevels.INDUSTRIAL, TechLevels.PREAGRICULTURE, TechLevels.INDUSTRIAL, 3500, -125,
        150, RandomEvent.BOREDOM, Resources.WEIRDMUSHROOMS, Resources.NOSPECIALRESOURCES, 2000, 3000
    ),
    ROBOTS(
        TechLevels.POST_INDUSTRIAL, TechLevels.EARLYINDUSTRIAL, TechLevels.HITECH, 5000, -150,
        100, RandomEvent.LACKOFWORKERS, Resources.NOSPECIALRESOURCES, Resources.NOSPECIALRESOURCES, 3500,
        5000
    );

    override fun toString(): String {
        return "Name: $name, MTLP: $MTLP, MTLU: $MTLU, TTP: $TTP, IPL: $IPL, \n" +
                "Var: $Var, IE: $RE, CR: $CR, ER: $ER, MTL: $MTL, MTH: $MTH"
    }

}
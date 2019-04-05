package edu.gatech.cs2340.spacetraders;

import edu.gatech.cs2340.spacetraders.entity.*;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class TravelUnitTest {

    private int[] skillArray;
    private Ship playerShip;
    private Player testPlayer;
    private Game myGame;

    @Before
    public void init() {
        skillArray = new int[4];
        for (int i = 0; i < skillArray.length; i++) {
            skillArray[i] = 4;
        }
        playerShip = new Ship();
        testPlayer = new Player("Test", skillArray, 10000, playerShip);
        myGame = new Game(GameDifficulty.BEGINNER, testPlayer);
    }

    @Test
    public void samePlanetCoordinates() {
        Coordinates currLocation = testPlayer.getLocation();
        Boolean travel = myGame.travel(currLocation);
        int currentFuel = testPlayer.getShipFuel();
        Assert.assertFalse("Shouldn't travel b/c same destination",travel);
        Assert.assertEquals("current fuel = final fuel", currentFuel, testPlayer.getShipFuel());
    }
}

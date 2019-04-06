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
        myGame.createPlanets();
        myGame.initializeMarketPlace(RandomEvent.NOEVENT);
    }

    @Test
    public void samePlanetCoordinates() {
        Coordinates currLocation = testPlayer.getLocation();
        int currentFuel = testPlayer.getShipFuel();
        Boolean travel = myGame.travel(currLocation);
        Assert.assertFalse("Shouldn't travel b/c same destination", travel);
        Assert.assertEquals("current fuel = final fuel", currentFuel, testPlayer.getShipFuel());
        Assert.assertEquals("locations are same", currLocation, testPlayer.getLocation());
    }

    @Test
    public void notEnoughFuel() {
        playerShip.setFuel(10);
        playerShip.setLocation(new Coordinates(50, 50));
        Coordinates destination = new Coordinates(20, 20);
        Coordinates currLocation = testPlayer.getLocation();
        System.out.println("Location: " + currLocation);
        int currentFuel = playerShip.getFuel();
        System.out.println("Fuel: " + currentFuel);
        System.out.println("Fuel to travel: " + myGame.calcFuelToUse(destination, currLocation));

        Assert.assertTrue("fuel to travel should be greater than fuel in ship",
                myGame.calcFuelToUse(destination, testPlayer.getLocation()) > testPlayer.getShipFuel());
        Assert.assertFalse("shouldnt travel b/c not enough fuel", myGame.travel(destination));
        Assert.assertEquals("fuel should be the same", testPlayer.getShipFuel(), currentFuel);
        Assert.assertEquals("location should be the same", currLocation, testPlayer.getLocation());
    }

    @Test
    public void normalTravel() {
        playerShip.setFuel(500);
        playerShip.setLocation(new Coordinates(50, 50));
        SolarSystem destinationPlanet = myGame.listTravelPlanets().get(myGame.listTravelPlanets().size() - 1);
        Coordinates destination = destinationPlanet.getLocation();
        int fuelToTravel = myGame.calcFuelToUse(destination, testPlayer.getLocation());
        int expectedFuel = playerShip.getFuel() - fuelToTravel;

        Assert.assertFalse("fuel to travel should be greater than fuel in ship",
                 fuelToTravel > testPlayer.getShipFuel());
        Assert.assertTrue("should normally travel", myGame.travel(destination));
        Assert.assertEquals("Fuel should change", testPlayer.getShipFuel(), expectedFuel);
        Assert.assertEquals("location should change", destination, testPlayer.getLocation());
    }

    @Test(expected = IllegalArgumentException.class)
    public void nullInputs() {
        Coordinates currLocation = testPlayer.getLocation();
        int currentFuel = testPlayer.getShipFuel();

        myGame.travel(null);
        Assert.assertEquals("same location", currLocation, testPlayer.getLocation());
        Assert.assertEquals("same fuel", currentFuel, testPlayer.getShipFuel());
    }
}

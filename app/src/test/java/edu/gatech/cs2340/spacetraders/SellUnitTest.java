package edu.gatech.cs2340.spacetraders;

import edu.gatech.cs2340.spacetraders.entity.*;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class SellUnitTest {

    private int[] testSkillArray;
    private Player testPlayer;
    private Ship testShip;
    private SolarSystem currentSolarSystem;
    private Inventory currentPlanetInventory;
    private Game testGame;


    @Before
    public void setUp() {
        testSkillArray = new int[]{4,4,4,4};
        testShip = new Ship();
        testPlayer = new Player("testName", testSkillArray, 10000, testShip);
        testGame = new Game(GameDifficulty.BEGINNER, testPlayer);
        testGame.createPlanets();
        testGame.initializeMarketPlace(RandomEvent.NOEVENT);
        currentSolarSystem = testGame.getCurrentPlanet();
        currentPlanetInventory = currentSolarSystem.getPlanetInventory();
    }

    @Test
    public void sellSingleProduct() {
        int initialPlanetProductAmount = currentPlanetInventory.getAmountOf(Products.FUEL);
        int initialPlayerProductAmount = testPlayer.getAmountOf(Products.FUEL);
        testGame.sell(testPlayer, Products.FUEL, 1);
        Assert.assertTrue("Player credits not incremented properly", testPlayer.getCredits() > 10000);
        Assert.assertEquals("Failed to add product to planet inventory", initialPlanetProductAmount + 1,
                currentPlanetInventory.getAmountOf(Products.FUEL));
        Assert.assertEquals("Failed to remove product from player inventory",
                initialPlayerProductAmount - 1, testPlayer.getAmountOf(Products.FUEL));
    }

    @Test
    public void sellMultipleProducts() {
        testPlayer.addToInventory(Products.WATER, 2);
        int initialPlanetProductAmount = currentPlanetInventory.getAmountOf(Products.WATER);
        testGame.sell(testPlayer, Products.WATER, 2);
        Assert.assertTrue("Player credits not incremented properly", testPlayer.getCredits() > 10000);
        Assert.assertEquals("Failed to remove products to player inventory", 0,
                testPlayer.getAmountOf(Products.WATER));
        Assert.assertEquals("Failed to add products to planet inventory",
                initialPlanetProductAmount + 2, currentPlanetInventory.getAmountOf(Products.WATER));
    }

    @Test
    public void sellWithNoProduct() {
        int initialPlanetProductAmount = currentPlanetInventory.getAmountOf(Products.WATER);
        testGame.sell(testPlayer, Products.WATER, 1);
        Assert.assertEquals("Player credits should not be incremented", 10000,
                testPlayer.getCredits());
        Assert.assertNotEquals("New product should not be added to planet inventory",
                initialPlanetProductAmount + 1,
                currentPlanetInventory.getAmountOf(Products.WATER));
        Assert.assertEquals("Product should not be removed from player inventory",
                0, testPlayer.getAmountOf(Products.WATER));
    }

    @Test
    public void sellZeroProduct() {
        int initialPlanetProductAmount = currentPlanetInventory.getAmountOf(Products.FUEL);
        int initialPlayerProductAmount = testPlayer.getAmountOf(Products.FUEL);
        testGame.sell(testPlayer, Products.FUEL, 0);
        Assert.assertEquals("Player credits should not be changed", 10000,
                testPlayer.getCredits());
        Assert.assertFalse("New product should not be added to planet inventory",
                currentPlanetInventory.getAmountOf(Products.FUEL) > initialPlanetProductAmount);
        Assert.assertEquals("Product should not be removed from player inventory",
                initialPlayerProductAmount, testPlayer.getAmountOf(Products.FUEL));
    }

    @Test
    public void sellNegativeProduct() {
        int initialPlanetProductAmount = currentPlanetInventory.getAmountOf(Products.FUEL);
        int initialPlayerProductAmount = testPlayer.getAmountOf(Products.FUEL);
        testGame.sell(testPlayer, Products.FUEL, -1);
        Assert.assertEquals("Player credits should not be changed", 10000,
                testPlayer.getCredits());
        Assert.assertFalse("New product should not be added to planet inventory",
                currentPlanetInventory.getAmountOf(Products.FUEL) > initialPlanetProductAmount);
        Assert.assertEquals("Product should not be removed from player inventory",
                initialPlayerProductAmount, testPlayer.getAmountOf(Products.FUEL));
    }


}

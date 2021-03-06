package edu.gatech.cs2340.spacetraders;

import edu.gatech.cs2340.spacetraders.entity.*;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class BuyUnitTest {

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
    public void buySingleProduct() {
        int initialPlanetProductAmount = currentPlanetInventory.getAmountOf(Products.WATER);
        testGame.buy(testPlayer, Products.WATER, 1);
        Assert.assertTrue("Player credits not decremented properly", testPlayer.getCredits() < 10000);
        Assert.assertEquals("Failed to add product to player inventory", 1,
                testPlayer.getAmountOf(Products.WATER));
        Assert.assertEquals("Failed to remove product from planet inventory",
                initialPlanetProductAmount - 1, currentPlanetInventory.getAmountOf(Products.WATER));
    }

    @Test
    public void buyMultipleProducts() {
        currentPlanetInventory.add(Products.WATER, 2);
        int initialPlanetProductAmount = currentPlanetInventory.getAmountOf(Products.WATER);
        testGame.buy(testPlayer, Products.WATER, 2);
        Assert.assertTrue("Player credits not decremented properly", testPlayer.getCredits() < 10000);
        Assert.assertEquals("Failed to add products to player inventory", 2,
                testPlayer.getAmountOf(Products.WATER));
        Assert.assertEquals("Failed to remove products from planet inventory",
                initialPlanetProductAmount - 2, currentPlanetInventory.getAmountOf(Products.WATER));
    }

    @Test
    public void buyWithFullCargo() {
        testPlayer.addToInventory(Products.WATER, 10);
        int initialPlanetProductAmount = currentPlanetInventory.getAmountOf(Products.WATER);
        testGame.buy(testPlayer, Products.WATER, 1);
        Assert.assertEquals("Player credits should not be decremented", 10000,
                testPlayer.getCredits());
        Assert.assertNotEquals("New product should not be added to player inventory", 11,
                testPlayer.getAmountOf(Products.WATER));
        Assert.assertEquals("Product should not be removed from planet inventory",
                initialPlanetProductAmount, currentPlanetInventory.getAmountOf(Products.WATER));
    }

    @Test
    public void buyWithZeroCredits() {
        testPlayer.setCredits(0);
        int initialPlanetProductAmount = currentPlanetInventory.getAmountOf(Products.WATER);
        testGame.buy(testPlayer, Products.WATER, 1);
        Assert.assertEquals("Player credits should not be decremented", 0, testPlayer.getCredits());
        Assert.assertNotEquals("New product should not be added to player inventory", 1,
                testPlayer.getAmountOf(Products.WATER));
        Assert.assertEquals("Product should not be removed from planet inventory",
                initialPlanetProductAmount, currentPlanetInventory.getAmountOf(Products.WATER));

    }

    @Test
    public void buyZeroProduct() {
        int initialPlanetProductAmount = currentPlanetInventory.getAmountOf(Products.WATER);
        testGame.buy(testPlayer, Products.WATER, 0);
        Assert.assertEquals("Player credits should not be decremented", 10000,
                testPlayer.getCredits());
        Assert.assertFalse("New product should not be added to player inventory",
                testPlayer.getAmountOf(Products.WATER) > 0);
        Assert.assertEquals("Product should not be removed from planet inventory",
                initialPlanetProductAmount, currentPlanetInventory.getAmountOf(Products.WATER));
    }

    @Test
    public void buyNegativeProduct() {
        int initialPlanetProductAmount = currentPlanetInventory.getAmountOf(Products.WATER);
        testGame.buy(testPlayer, Products.WATER, -1);
        Assert.assertEquals("Player credits should not change", 10000,
                testPlayer.getCredits());
        Assert.assertFalse("New product should not be added to player inventory",
                testPlayer.getAmountOf(Products.WATER) > 0);
        Assert.assertEquals("Product should not be removed from planet inventory",
                initialPlanetProductAmount, currentPlanetInventory.getAmountOf(Products.WATER));
    }


}

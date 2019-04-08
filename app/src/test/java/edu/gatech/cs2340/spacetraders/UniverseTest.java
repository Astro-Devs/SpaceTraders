package edu.gatech.cs2340.spacetraders;

import edu.gatech.cs2340.spacetraders.entity.Coordinates;
import edu.gatech.cs2340.spacetraders.entity.SolarSystem;
import edu.gatech.cs2340.spacetraders.entity.Universe;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

public class UniverseTest {
    private Universe testUniverse;

    @Before
    public void setup() {
        testUniverse = new Universe();
    }

    @Test
    public void testGetPlanetArray() {
        testUniverse.createPlanets();
        ArrayList<SolarSystem> planetArray = testUniverse.getPlanetArray();
        Assert.assertNotNull("Map should not be null", testUniverse.getMap());
        for (Coordinates coordinates : testUniverse.getMap().keySet()) {
            Assert.assertNotNull("Map entries should not be null", coordinates);
        }
        Assert.assertTrue("Array's size must be greater than 0",planetArray.size() > 0);
        Assert.assertTrue("Array's size must be less than 21", planetArray.size() < 21);
        Assert.assertEquals("Array's size should be equal to 20", 20, planetArray.size());
        double distanceFromOrigin = 0;
        for (int i = 1; i < planetArray.size(); i++) {
            SolarSystem systemOne = planetArray.get(i);
            Coordinates coords = systemOne.getLocation();
            double currDistance = testUniverse.distance(new Coordinates(50, 50), coords);
            Assert.assertTrue("Planets are not in coordinate ascending order",
                    currDistance >= distanceFromOrigin);
            distanceFromOrigin = currDistance;
        }
    }

    @Test (timeout = 1000)
    public void fuzzyTestGetPlanetArray() {
        for (int i = 0; i < 1000; i++) {
            testGetPlanetArray();
        }
        Assert.assertTrue(true);
    }
}

package edu.gatech.cs2340.spacetraders;

import edu.gatech.cs2340.spacetraders.entity.*;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;

public class ListTravelPlanetsUnitTest {

    private int[] skillArray;
    private Ship ship;
    private Player player;
    private Game game;
    private Universe universe;
    private HashMap<Coordinates, SolarSystem> newMap;
    private SolarSystem initialSolarSystem;

    @Before
    public void setUp() {
        ship = new Ship();
        universe = new Universe();
        skillArray = new int[]{4, 4, 4, 4};
        player = new Player("John", skillArray, 10000, ship);
        game = new Game(GameDifficulty.BEGINNER, player);
        player.setLocation(new Coordinates(0,0));
        newMap = new HashMap<>();

        char planetName = 'a';
        Coordinates coordinates = new Coordinates(5,5);
        int add = 5;
        Coordinates initialCoords = new Coordinates(0,0);

        Resources resource = Resources.NOSPECIALRESOURCES;
        TechLevels techLevel = TechLevels.AGRICULTURE;

        initialSolarSystem = new SolarSystem("Home", initialCoords, techLevel, resource);
        newMap.put(initialCoords, initialSolarSystem);

        for(int i = 0; i <= 18; i++) {
            SolarSystem planet = new SolarSystem(planetName++ + "", coordinates, techLevel, resource);
            newMap.put(coordinates, planet);
            add += 5;
            coordinates = new Coordinates(add, add);
        }

    }

    @Test
    public void testCorrectTravelList() {
        player.setShipFuel(100);
        game.setUniverse(universe);
        universe.setMap(newMap);
        ArrayList<SolarSystem> output = game.listTravelPlanets();
        ArrayList<SolarSystem> correctList;
        correctList = universe.getPlanetArray();
        SolarSystem a = correctList.get(9);
        SolarSystem b = correctList.get(12);
        SolarSystem c = correctList.get(13);
        SolarSystem d = correctList.get(16);
        SolarSystem e = correctList.get(18);
        correctList.remove(a);
        correctList.remove(b);
        correctList.remove(c);
        correctList.remove(d);
        correctList.remove(e);

        Assert.assertEquals("size of lists does not match", correctList.size(), output.size());
        Assert.assertEquals("different elements in lists",correctList,output);
    }

    @Test
    public void testNoFuelList() {
        player.setShipFuel(0);
        game.setUniverse(universe);
        universe.setMap(newMap);
        ArrayList<SolarSystem> output = game.listTravelPlanets();
        ArrayList<SolarSystem> correctList = new ArrayList<>();
        correctList.add(initialSolarSystem);

        Assert.assertEquals("size of lists do not match", correctList.size(), output.size());
        Assert.assertEquals("different elements in lists", correctList, output);
    }

}
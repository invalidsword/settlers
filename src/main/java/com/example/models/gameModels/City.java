package com.example.models.gameModels;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Created by G on 17/02/28.
 */
public class City extends IntersectionUnit {
    private static final Queue<City> aCities = new LinkedList<City>();
    private boolean hasMetropolis;
    private Metropolis aMetropolis;
    private boolean hasWall;
    private Wall aWall;

    static {
        int maxCities = 16;
        for (int i = 0; i < maxCities; i++) {
            City newCity = new City();
            aCities.add(newCity);
        }
    }

    private City() {
        hasMetropolis = false;
        aMetropolis = null;
        hasWall = false;
        aWall = null;
    }

    public void addMetropolis(ImprovementType pType){
        assert hasMetropolis == false;
        assert aMetropolis == null;
        aMetropolis = Metropolis.getMetropolis(pType);
        hasMetropolis = true;
    }

    public void removeMetropolis(){
        assert hasMetropolis == true;
        assert aMetropolis != null;
        aMetropolis = null;
        hasMetropolis = false;
    }

    public void addWall(){
        assert hasWall == false;
        assert aWall == null;
        //aMetropolis;
        hasWall = true;
    }

    public static Queue<City> getUnits() {
        Queue<City> aCopy = new LinkedList<City>();
        aCopy = aCities;
        return aCopy;
    }
}

package com.example.models.gameModels;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Created by G on 17/03/02.
 */
public class Wall extends OwnableUnit{
    private static final Queue<Wall> aWalls = new LinkedList<Wall>();

    static {
        int maxWalls = 12;
        for (int i = 0; i < maxWalls; i++) {
            Wall newWall = new Wall();
            aWalls.add(newWall);
        }
    }

    private Wall(){
    }

    public static Queue<Wall> getUnits(){
        Queue<Wall> aCopy = new LinkedList<Wall>();
        aCopy = aWalls;
        return aCopy;
    }
}

package com.example.models.gameModels;
import java.util.Queue;
import java.util.LinkedList;
/**
 * Created by G on 17/02/27.
 */
public final class Road extends EdgeUnit {

    private static final Queue<Road> aRoads = new LinkedList<Road>();

    static{
        int maxRoads = 60;
        for(int i = 0; i < maxRoads; i++){
            Road newRoad = new Road();
            aRoads.add(newRoad);
        }
    }

    private Road(){
    }

    public static Queue<Road> getUnits(){
        Queue<Road> aCopy = new LinkedList<Road>();
        aCopy = aRoads;
        return aCopy;
    }
}

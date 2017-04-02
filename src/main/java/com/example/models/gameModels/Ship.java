package com.example.models.gameModels;
import java.util.Queue;
import java.util.LinkedList;
/**
 * Created by G on 17/02/27.
 */
public class Ship extends EdgeUnit{

    private static final Queue<Ship> aShips = new LinkedList<Ship>();

    static{
        int maxShips = 60;
        for(int i = 0; i < maxShips; i++){
            Ship newShip = new Ship();
            aShips.add(newShip);
        }
    }

    private Ship(){
    }

    public static Queue<Ship> getUnits(){
        Queue<Ship> aCopy = new LinkedList<Ship>();
        aCopy = aShips;
        return aCopy;
    }
}

package com.example.models;

/**
 * Created by G on 17/02/27.
 */
public class Intersection extends Geometry {

    private HarbourKind aHarbour;
    private IntersectionUnit aOccupant;
    private boolean isOccupied;


    public Intersection(int x, int y,HarbourKind pHarbour)
    {
        super(x,y);
        aHarbour = pHarbour;
        isOccupied = false;
    }

    public boolean getOccupancyFlag(){return isOccupied;}

    public void setOccupant(IntersectionUnit pOccupant)
    {
        aOccupant = pOccupant;
        isOccupied = true;
    }
    public IntersectionUnit removeOccupant()
    {
        IntersectionUnit tempUnit = aOccupant;
        aOccupant = null;
        isOccupied = false;
        return tempUnit;
    }

    @Override
    public Hex getHexNeighbours() {
        return null; // NOT DONE
    }
    @Override
    public Geometry getIntersectionNeighbours() {
        return null; // NOT DONE
    }
    @Override
    public Edge getEdgeNeighbours() {
        return null; // NOT DONE
    }
}

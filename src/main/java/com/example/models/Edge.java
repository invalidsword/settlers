package com.example.models;


/**
 * Created by G on 17/02/27.
 */
public class Edge extends Geometry{

    private boolean isOccupied;
    private EdgeUnit aOccupant;

    public Edge(int x, int y)
    {
        super(x,y);
        isOccupied = false;
    }

    public boolean getOccupancyFlag()
    {
       return isOccupied;
    }

    public void setOccupant(EdgeUnit pOccupant)
    {
        aOccupant = pOccupant;
        isOccupied = true;
    }
    public EdgeUnit removeOccupant()
    {
        EdgeUnit tempUnit = aOccupant;
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

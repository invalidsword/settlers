package com.example.models;

/**
 * Created by G on 17/02/27.
 */
public abstract class Hex extends Geometry{

    public Hex(int x,int y) {super(x,y);}
    public abstract TerrainKind getTerrainType();

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

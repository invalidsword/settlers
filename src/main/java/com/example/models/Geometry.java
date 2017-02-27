package com.example.models;

/**
 * Created by G on 17/02/27.
 */
public abstract class Geometry {
    int aX;
    int aY;
    public Geometry(int x,int y){aX = x; aY = y;}

    public Coordinate getCoordinates(){return new Coordinate(aX,aY);}

    protected class Coordinate
    {
        int x;
        int y;
        Coordinate(int a,int b){
            x = a;
            y = b;
        }
        public int getX(){return x;}
        public int getY(){return y;}
    }
    public abstract Edge getEdgeNeighbours();
    public abstract Geometry getIntersectionNeighbours();
    public abstract Hex getHexNeighbours();
}

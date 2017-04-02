package com.example.models.gameModels;


import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;

/**
 * Created by G on 17/02/27.
 */
public abstract class Geometry {
    private final String aId;
    private final String[] splitId;

    protected Geometry(String pId){
        aId = pId;
        splitId = pId.split("_");
    }

    protected Queue<Hex> HexNeighbours = new LinkedList<Hex>();
    protected Queue<Intersection> IntersectionNeighbours = new LinkedList<Intersection>();
    protected Queue<Edge> EdgeNeighbours = new LinkedList<Edge>();

    public String getId(){return aId;};

    public String getPrefix(){
        return  splitId[0];
    }

    public int getX(){
        return  Integer.parseInt(splitId[1]);
    }

    public int getY(){
        return  Integer.parseInt(splitId[2]);
    }

    public Queue<Edge> getEdgeNeighbours(){return EdgeNeighbours;};
    public Queue<Intersection> getIntersectionNeighbours(){return IntersectionNeighbours;};
    public Queue<Hex> getHexNeighbours(){return HexNeighbours;};

    public abstract void setEdgeNeighbours(Map<String,Edge> pEdges);
    public abstract void setHexNeighbours(Map<String,Hex> pHexes);
    public abstract void setIntersectionNeighbours(Map<String,Intersection> pIntersections);
}

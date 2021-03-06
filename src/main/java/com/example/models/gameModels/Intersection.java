package com.example.models.gameModels;

import java.util.Map;
import java.util.Queue;

/**
 * Created by G on 17/02/27.
 */
public class Intersection extends Geometry {

    private HarbourType aHarbour;
    private IntersectionUnit aOccupant;
    private boolean isOccupied;


    public Intersection(String pId,HarbourType pHarbour)
    {
        super(pId);
        aHarbour = pHarbour;
        isOccupied = false;
    }

    public boolean getOccupancyFlag(){
        return isOccupied;
    }

    public IntersectionUnit getOccupant(){
        return aOccupant;
    }

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
    public void setEdgeNeighbours(Map<String,Edge> pEdges){
        final int x = getX();
        final int xm = x-1;
        final int xp = x+1;

        final int y = getY();
        final int ym = y-1;
        final int yp = y+1;

        String id1;
        String id2;
        String id3;

        switch (getPrefix()){
            case "i3": id2 = "e1_"+x+"_"+y;
                id1 = "e2_"+x+"_"+ym;
                id3 = "e3_"+x+"_"+ym;
                break;

            case "i4":  id2 = "e1_"+x+"_"+y;
                id1 = "e2_"+x+"_"+y;
                id3 = "e3_"+xm+"_"+y;
                break;

            default: id2 = "blah";
                id1 = "blah";
                id3 = "blah";
                //will return null
        }

        if(pEdges.get(id1)!=null)
            EdgeNeighbours.add(pEdges.get(id1));
        if(pEdges.get(id2)!=null)
            EdgeNeighbours.add(pEdges.get(id2));
        if(pEdges.get(id3)!=null)
            EdgeNeighbours.add(pEdges.get(id3));
    }

    @Override
    public void setHexNeighbours(Map<String,Hex> pHexes){
        final int x = getX();
        final int xm = x-1;
        final int xp = x+1;

        final int y = getY();
        final int ym = y-1;
        final int yp = y+1;

        String id1;
        String id2;
        String id3;

        switch (getPrefix()){
            case "i3": id2 = "h_"+xm+"_"+yp;
                id1 = "h_"+x+"_"+y;
                id3 = "h_"+xm+"_"+y;
                break;

            case "i4":  id2 = "h_"+x+"_"+y;
                id1 = "h_"+xm+"_"+y;
                id3 = "h_"+x+"_"+ym;
                break;

            default: id2 = "blah";
                id1 = "blah";
                id3 = "blah";
                //will return null
        }

        if(pHexes.get(id1)!=null)
            HexNeighbours.add(pHexes.get(id1));
        if(pHexes.get(id2)!=null)
            HexNeighbours.add(pHexes.get(id2));
        if(pHexes.get(id3)!=null)
            HexNeighbours.add(pHexes.get(id3));
    }

    @Override
    public void setIntersectionNeighbours(Map<String,Intersection> pIntersections){
        final int x = getX();
        final int xm = x-1;
        final int xp = x+1;

        final int y = getY();
        final int ym = y-1;
        final int yp = y+1;

        String id1;
        String id2;
        String id3;

        switch (getPrefix()){
            case "i3": id2 = "i4_"+xm+"_"+yp;
                id1 = "i4_"+x+"_"+y;
                id3 = "i4_"+xm+"_"+y;
                break;

            case "i4":  id2 = "i3_"+x+"_"+y;
                id1 = "i3_"+xm+"_"+y;
                id3 = "i3_"+x+"_"+ym;
                break;

            default: id2 = "blah";
                id1 = "blah";
                id3 = "blah";
                //will return null
        }

        if(pIntersections.get(id1)!=null)
            IntersectionNeighbours.add(pIntersections.get(id1));
        if(pIntersections.get(id2)!=null)
            IntersectionNeighbours.add(pIntersections.get(id2));
        if(pIntersections.get(id3)!=null)
            IntersectionNeighbours.add(pIntersections.get(id3));
    }

}
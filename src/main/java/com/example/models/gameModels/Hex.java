package com.example.models.gameModels;

import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;

/**
 * Created by G on 17/02/27.
 */
public abstract class Hex extends Geometry{

    public abstract TerrainType getTerrainType();
    private boolean aVillain;

    public Hex(String pId) {
        super(pId);
        String[] Ay = pId.split("_");
        aId = Ay[0]+"_"+-Integer.parseInt(Ay[2])+"_"+-Integer.parseInt(splitId[1]);
        splitId = aId.split("_");
        aVillain = false;
    }

    public boolean hasVillain(){
        return aVillain;
    }

    public void updateRobberFlag()
    {
        if(aVillain==true)
            aVillain = false;
        else
            aVillain = true;
    }

    @Override
    public void setEdgeNeighbours(Map<String,Edge> pEdges){
        final int x = getX();
        final int xm = x-1;
        final int xp = x+1;

        final int y = getY();
        final int ym = y-1;
        final int yp = y+1;

        String id1 = "e1_"+x+"_"+y;
        String id2 = "e2_"+x+"_"+y;
        String id3 = "e3_"+x+"_"+y;
        String id4 = "e1_"+xp+"_"+y;
        String id5 = "e2_"+xp+"_"+ym;
        String id6 = "e3_"+x+"_"+ym;

        if(pEdges.get(id1)!=null)
            EdgeNeighbours.add(pEdges.get(id1));
        if(pEdges.get(id2)!=null)
            EdgeNeighbours.add(pEdges.get(id2));
        if(pEdges.get(id3)!=null)
            EdgeNeighbours.add(pEdges.get(id3));
        if(pEdges.get(id4)!=null)
            EdgeNeighbours.add(pEdges.get(id4));
        if(pEdges.get(id5)!=null)
            EdgeNeighbours.add(pEdges.get(id5));
        if(pEdges.get(id6)!=null)
            EdgeNeighbours.add(pEdges.get(id6));



    }

    @Override
    public void setHexNeighbours(Map<String,Hex> pHexes ){
        // every hex has 6 neighbours
        final int x = getX();
        final int xm = x-1;
        final int xp = x+1;

        final int y = getY();
        final int ym = y-1;
        final int yp = y+1;

        String id1 = "h_"+xp+"_"+y;
        String id2 = "h_"+xm+"_"+y;
        String id3 = "h_"+xm+"_"+yp;
        String id4 = "h_"+x+"_"+yp;
        String id5 = "h_"+xp+"_"+ym;
        String id6 = "h_"+x+"_"+ym;

        if(pHexes.get(id1)!=null)
            HexNeighbours.add(pHexes.get(id1));
        if(pHexes.get(id2)!=null)
            HexNeighbours.add(pHexes.get(id2));
        if(pHexes.get(id3)!=null)
            HexNeighbours.add(pHexes.get(id3));
        if(pHexes.get(id4)!=null)
            HexNeighbours.add(pHexes.get(id4));
        if(pHexes.get(id5)!=null)
            HexNeighbours.add(pHexes.get(id5));
        if(pHexes.get(id6)!=null)
            HexNeighbours.add(pHexes.get(id6));
    }

    @Override
    public void setIntersectionNeighbours(Map<String,Intersection> pIntersections){
        final int x = getX();
        final int xm = x-1;
        final int xp = x+1;

        final int y = getY();
        final int ym = y-1;
        final int yp = y+1;

        String id1 = "i3_"+x+"_"+y;
        String id2 = "i4_"+x+"_"+y;
        String id3 = "i4_"+x+"_"+yp;
        String id4 = "i3_"+xp+"_"+y;
        String id5 = "i4_"+xp+"_"+y;
        String id6 = "i3_"+xp+"_"+ym;

        if(pIntersections.get(id1)!=null)
            IntersectionNeighbours.add(pIntersections.get(id1));
        if(pIntersections.get(id2)!=null)
            IntersectionNeighbours.add(pIntersections.get(id2));
        if(pIntersections.get(id3)!=null)
            IntersectionNeighbours.add(pIntersections.get(id3));
        if(pIntersections.get(id4)!=null)
            IntersectionNeighbours.add(pIntersections.get(id4));
        if(pIntersections.get(id5)!=null)
            IntersectionNeighbours.add(pIntersections.get(id5));
        if(pIntersections.get(id6)!=null)
            IntersectionNeighbours.add(pIntersections.get(id6));

    }

}

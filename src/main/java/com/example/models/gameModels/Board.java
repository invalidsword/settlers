package com.example.models.gameModels;

import com.example.viewobjects.ViewEdge;
import com.example.viewobjects.ViewHex;
import com.example.viewobjects.ViewIntersection;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


@Entity
public class Board {
    @Override
    public String toString() {
        return "Board{" +
                "boardid=" + boardid +
                '}';
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long boardid;

    @OneToMany(cascade=CascadeType.ALL, fetch = FetchType.LAZY)
    private Map<String,Hex> aHexes = new HashMap<>();
    @OneToMany(cascade=CascadeType.ALL, fetch = FetchType.LAZY)
    private Map<String,Edge> aEdges  = new HashMap<>();
    @OneToMany(cascade=CascadeType.ALL, fetch = FetchType.LAZY)
    private Map<String,Intersection> aIntersections  = new HashMap<>();
    @ElementCollection
    private Map<Integer, ArrayList<LandHex>> aLandHexes = new HashMap<>();


    public Board(){
        aHexes = new HashMap<>();
        aEdges = new HashMap<>();
        aIntersections = new HashMap<>();
        aLandHexes = new HashMap<>();
        for(int i = 2; i<=12;i++)
            aLandHexes.put(i, new ArrayList<LandHex>());

    }


    public Map<String, Hex> getHexes(){
        return aHexes;
    }


    public Map<String, Edge> getEdges(){
        return aEdges;
    }


    public Map<String, Intersection> getIntersections(){
        return aIntersections;
    }


    public Map<Integer, ArrayList<LandHex>> getLandHexes(){
        return aLandHexes;
    }

    public void setHex(ViewHex pHex){
        //System.out.println("HEX");
        switch (pHex.getTerrainType()) {
            case "wood":
                LandHex aHex = new LandHex(pHex.getId(), pHex.getNumber(), TerrainType.Forest);
                aHexes.put(aHex.getId(), aHex);
                aLandHexes.get(aHex.getProductionNumber()).add(aHex);
                break;
            case "ore":
                LandHex bHex = new LandHex(pHex.getId(), pHex.getNumber(), TerrainType.Mountains);
                aHexes.put(bHex.getId(), bHex);
                aLandHexes.get(bHex.getProductionNumber()).add(bHex);
                break;
            case "brick":
                LandHex cHex = new LandHex(pHex.getId(), pHex.getNumber(), TerrainType.Hills);
                aHexes.put(cHex.getId(), cHex);
                aLandHexes.get(cHex.getProductionNumber()).add(cHex);
                break;
            case "sheep":
                LandHex dHex = new LandHex(pHex.getId(), pHex.getNumber(), TerrainType.Pasture);
                aHexes.put(dHex.getId(), dHex);
                aLandHexes.get(dHex.getProductionNumber()).add(dHex);
                break;
            case "gold":
                LandHex eHex = new LandHex(pHex.getId(), pHex.getNumber(), TerrainType.GoldMine);
                aHexes.put(eHex.getId(), eHex);
                aLandHexes.get(eHex.getProductionNumber()).add(eHex);
                break;
            case "wheat":
                LandHex fHex = new LandHex(pHex.getId(), pHex.getNumber(), TerrainType.Fields);
                aHexes.put(fHex.getId(), fHex);
                aLandHexes.get(fHex.getProductionNumber()).add(fHex);
                break;
            case "desert":
                LandHex gHex = new LandHex(pHex.getId(), pHex.getNumber(), TerrainType.Desert);
                aHexes.put(gHex.getId(), gHex);
                break;
            case "sea":
                SeaHex hHex = new SeaHex(pHex.getId());
                aHexes.put(hHex.getId(), hHex);
            default:
                hHex = new SeaHex(pHex.getId());
        }
    }

    public void setIntersection(ViewIntersection pIntersection){
        //System.out.println(pIntersection.getId());
        Intersection aIntersection = new Intersection(pIntersection.getId(), HarbourType.None);
        aIntersections.put(aIntersection.getId(), aIntersection);
    }

    public void setEdge(ViewEdge pEdge){
        //System.out.println(pEdge.getId());
        Edge aEdge = new Edge(pEdge.getId());
        aEdges.put(aEdge.getId(), aEdge);
    }

    public void makeEdges()
    {
        for(int x = -5;x<=5;x++)
        {
            for(int y = -5;y<=5;y++) {
                for(int id=1;id<=3;id++) {
                    String aId = "e"+id+"_"+x+"_"+y;
                    Edge aEdge = new Edge(aId);
                    aEdges.put(aEdge.getId(),aEdge);
                }
            }
        }
    }

    public void makeIntersections()
    {
        for(int x = -5;x<=5;x++)
        {
            for(int y = -5;y<=5;y++) {
                for(int id=3;id<=4;id++) {
                    String aId = "i"+id+"_"+x+"_"+y;
                    Intersection aIntersection = new Intersection(aId,HarbourType.None);
                    aIntersections.put(aIntersection.getId(),aIntersection);
                }
            }
        }
    }


    public void setAllNeighbours(){
        //System.out.println("Friendly Neighbourhood SPODERMAN");
        for (String aKey : aHexes.keySet()){
            //System.out.println(aHexes.get(aKey).getId());
            //System.out.println(aHexes.get(aKey).getId());
            aHexes.get(aKey).setIntersectionNeighbours(aIntersections);
            aHexes.get(aKey).setHexNeighbours(aHexes);
            aHexes.get(aKey).setEdgeNeighbours(aEdges);
        }

        for (String aKey : aEdges.keySet()){
            //System.out.println(aEdges.get(aKey).getId());
            //System.out.println(aEdges.get(aKey).getId());
            aEdges.get(aKey).setIntersectionNeighbours(aIntersections);
            aEdges.get(aKey).setHexNeighbours(aHexes);
            aEdges.get(aKey).setEdgeNeighbours(aEdges);
        }

        for (String aKey : aIntersections.keySet()){
            //System.out.println(aIntersections.get(aKey).getId());
            aIntersections.get(aKey).setIntersectionNeighbours(aIntersections);
            aIntersections.get(aKey).setHexNeighbours(aHexes);
            aIntersections.get(aKey).setEdgeNeighbours(aEdges);
        }
        System.out.println("Neighbours set");
    }


    @Override
    public int hashCode() {
        int result = boardid.hashCode();
        return result;
    }
}

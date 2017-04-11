package com.example.models.gameModels;


import javax.persistence.*;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;

/**
 * Created by G on 17/02/27.
 */

@Entity
public abstract class Geometry {
    public Geometry(){}

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long generatedId;

    protected String aId; // should be private final

    protected String Prefix;
    protected int X;
    protected int Y;

    protected Geometry(String pId){
        aId = pId;
        String[] splitId = pId.split("_");
        Prefix = splitId[0];
        X = Integer.parseInt(splitId[1]);
        Y = Integer.parseInt(splitId[2]);
    }

    @Override
    public String toString() {
        return "Geometry{" +
                ", X=" + X +
                ", Y=" + Y +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Geometry)) return false;

        Geometry geometry = (Geometry) o;

        if (X != geometry.X) return false;
        if (Y != geometry.Y) return false;
        if (generatedId != null ? !generatedId.equals(geometry.generatedId) : geometry.generatedId != null)
            return false;
        if (aId != null ? !aId.equals(geometry.aId) : geometry.aId != null) return false;
        if (Prefix != null ? !Prefix.equals(geometry.Prefix) : geometry.Prefix != null) return false;
        if (HexNeighbours != null ? !HexNeighbours.equals(geometry.HexNeighbours) : geometry.HexNeighbours != null)
            return false;
        if (IntersectionNeighbours != null ? !IntersectionNeighbours.equals(geometry.IntersectionNeighbours) : geometry.IntersectionNeighbours != null)
            return false;
        return EdgeNeighbours != null ? EdgeNeighbours.equals(geometry.EdgeNeighbours) : geometry.EdgeNeighbours == null;
    }

    @Override
    public int hashCode() {
        int result = generatedId.hashCode();
        return result;
    }

    @ManyToMany(cascade=CascadeType.ALL, fetch = FetchType.LAZY)
    protected List<Hex> HexNeighbours = new LinkedList<Hex>();
    @ManyToMany(cascade=CascadeType.ALL, fetch = FetchType.LAZY)
    protected List<Intersection> IntersectionNeighbours = new LinkedList<Intersection>();
    @ManyToMany(cascade=CascadeType.ALL, fetch = FetchType.LAZY)
    protected List<Edge> EdgeNeighbours = new LinkedList<Edge>();

    public String getId(){return aId;};

    public String getPrefix(){
        return Prefix;
    }

    public int getX(){
        return X;
    }

    public int getY(){
        return Y;
    }

    public List<Edge> getEdgeNeighbours(){return EdgeNeighbours;};
    public List<Intersection> getIntersectionNeighbours(){return IntersectionNeighbours;};
    public List<Hex> getHexNeighbours(){return HexNeighbours;};

    public abstract void setEdgeNeighbours(Map<String,Edge> pEdges);
    public abstract void setHexNeighbours(Map<String,Hex> pHexes);
    public abstract void setIntersectionNeighbours(Map<String,Intersection> pIntersections);

}

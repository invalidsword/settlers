package com.example.models.gameModels;


import javax.persistence.*;

@Entity
public class OwnedBuilding {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long aId;

    public OwnedBuilding(){}

    @ManyToOne(cascade=CascadeType.ALL, fetch = FetchType.LAZY)
    private Player aOwner;

    @Override
    public String toString() {
        return "OwnedBuilding{" +
                "aId=" + aId +

                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof OwnedBuilding)) return false;

        OwnedBuilding that = (OwnedBuilding) o;

        if (aId != null ? !aId.equals(that.aId) : that.aId != null) return false;
        if (aOwner != null ? !aOwner.equals(that.aOwner) : that.aOwner != null) return false;
        return aUnit == that.aUnit;
    }

    @Override
    public int hashCode() {
        int result = aId.hashCode();
        return result;
    }

    private Unit.Building aUnit;

    public OwnedBuilding(Player pOwner, Unit.Building pUnit){
        aOwner= pOwner;
        aUnit = pUnit;
    }

    public Player getOwner(){
        return aOwner;
    }

    public boolean isCity(){
        return (aUnit == Unit.Building.CITY);
    }
}

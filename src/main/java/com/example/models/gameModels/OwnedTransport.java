package com.example.models.gameModels;

import javax.persistence.*;

@Entity
public class OwnedTransport {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long aId;

    @ManyToOne(cascade=CascadeType.ALL, fetch = FetchType.LAZY)
    private Player aOwner;

    private Unit.Transport aUnit;

    public OwnedTransport(Player pOwner, Unit.Transport pUnit){
        aOwner= pOwner;
        aUnit = pUnit;
    }

    @Override
    public String toString() {
        return "OwnedTransport{" +
                "aId=" + aId +
                ", aOwner=" + aOwner +
                ", aUnit=" + aUnit +
                '}';
    }

    @Override
    public int hashCode() {
        int result = aId.hashCode();
        return result;
    }


    public OwnedTransport(){}

    public Player getOwner(){
        return aOwner;
    }
    public  Unit.Transport getUnit() { return  aUnit; }
}

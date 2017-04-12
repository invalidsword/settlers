package com.example.models.gameModels;

import javax.persistence.*;

@Entity
public class OwnedKnight {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long aId;

    public OwnedKnight(){}

    @ManyToOne(cascade=CascadeType.ALL, fetch = FetchType.LAZY)
    private Player aOwner;

    private Unit.Knight aUnit;
    private boolean aActiveState;

    public OwnedKnight(Player pOwner, Unit.Knight pUnit){
        aOwner= pOwner;
        aUnit = pUnit;
    }

    @Override
    public String toString() {
        return "OwnedKnight{" +
                "aId=" + aId +

                ", aActiveState=" + aActiveState +
                '}';
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof OwnedKnight)) return false;

        OwnedKnight that = (OwnedKnight) o;

        if (aActiveState != that.aActiveState) return false;
        if (aId != null ? !aId.equals(that.aId) : that.aId != null) return false;
        if (aOwner != null ? !aOwner.equals(that.aOwner) : that.aOwner != null) return false;
        return aUnit == that.aUnit;
    }

    @Override
    public int hashCode() {
        int result = aId.hashCode();
        return result;
    }

    public Player getOwner(){
        return aOwner;
    }

    public boolean getState(){
        return aActiveState;
    }

    public int getStrength(){
        return aUnit.strength();
    }

    public Unit.Knight getUpgrade(){
        return Unit.Knight.values()[aUnit.ordinal() + 1];
    }

    public boolean canUpgrade(){
        if (aUnit.ordinal() == Unit.Knight.values().length - 1){
            return false;
        }
        return true;
    }

    public void activate(){
        aActiveState = true;
    }

    public void deactivate(){
        aActiveState = false;
    }
}

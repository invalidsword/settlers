package com.example.models.gameModels;
import java.util.Queue;
import java.util.LinkedList;

/**
 * Created by G on 17/02/28.
 */
public class Settlement extends IntersectionUnit {
    private static final Queue<Settlement> aSettlements = new LinkedList<Settlement>();

    static {
        int maxSettlements = 16;
        for (int i = 0; i < maxSettlements; i++) {
            Settlement newSettlement = new Settlement();
            aSettlements.add(newSettlement);
        }
    }

    private Settlement() {
    }

    public static Queue<Settlement> getUnits() {
        Queue<Settlement> aCopy = new LinkedList<Settlement>();
        aCopy = aSettlements;
        return aCopy;
    }
}
//package com.example.models.gameModels;
//import java.util.Map;
//import java.util.HashMap;
//
//
///**
// * Created by G on 17/02/28.
// */
//public class Metropolis {
//    private static final Map<ImprovementType, Metropolis> aMetropolises = new HashMap<ImprovementType, Metropolis>();
//    private final ImprovementType aImprovementType;
//
//    static{
//        for (ImprovementType type : ImprovementType.values()){
//            Metropolis newMetropolis = new Metropolis(type);
//            aMetropolises.put(type, newMetropolis);
//        }
//    }
//
//    private Metropolis(ImprovementType pImprovementType){
//        aImprovementType = pImprovementType;
//
//    }
//
//    public static Metropolis getMetropolis(ImprovementType pType){
//        return aMetropolises.get(pType);
//    }
//}
//

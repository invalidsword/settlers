package com.example.models.gameModels;
import java.util.Random;

public interface ProgressCard {
    public enum Trade {
        COMMERCIAL_HARBOR(2), MASTER_MERCHANT(2), MERCHANT(6), MERCHANT_FLEET(2), RESOURCE_MONOPOLY(4), TRADE_MONOPOLY(2);
        private final int aWeight;
        private Trade(int weight){
            aWeight = weight;
        }
        public int getaWeight(){
            return aWeight;
        }
        public static Trade getRandomTradeCard(){
            double bestValue = Double.MAX_VALUE;
            Random random = new Random();
            Trade card = null;
            for (Trade tradeCard : Trade.values()){
                double value = -Math.log(random.nextDouble()) / tradeCard.getaWeight();
                if (value < bestValue){
                    bestValue = value;
                    card = tradeCard;
                }
            }
            return card;
        }
    }

    public enum Politics {
        BISHOP(2), CONSTITUTION(1), DESERTER(2), DIPLOMAT(2), INTRIGUE(2), SABOTEUR(2), SPY(3), WARLORD(2), WEDDING(2);
        private final int aWeight;
        private Politics(int weight){
            aWeight = weight;
        }
        public int getaWeight(){
            return aWeight;
        }
        public static Politics getRandomPoliticsCard(){
            double bestValue = Double.MAX_VALUE;
            Random random = new Random();
            Politics card = null;
            for (Politics politicsCard : Politics.values()){
                double value = -Math.log(random.nextDouble()) / politicsCard.getaWeight();
                if (value < bestValue){
                    bestValue = value;
                    card = politicsCard;
                }
            }
            return card;
        }
    }

    public enum Science {
        ALCHEMIST(2), CRANE(2), ENGINEER(2), INVENTOR(1), IRRIGATION(2), MEDICINE(2), MINING(2), PRINTER(1), ROAD_BUILDING(2), SMITH(2);
        private final int aWeight;
        private Science(int weight){
            aWeight = weight;
        }
        public int getaWeight(){
            return aWeight;
        }
        public static Science getRandomScienceCard(){
            double bestValue = Double.MAX_VALUE;
            Random random = new Random();
            Science card = null;
            for (Science scienceCard : Science.values()){
                double value = -Math.log(random.nextDouble()) / scienceCard.getaWeight();
                if (value < bestValue){
                    bestValue = value;
                    card = scienceCard;
                }
            }
            return card;
        }
    }
}



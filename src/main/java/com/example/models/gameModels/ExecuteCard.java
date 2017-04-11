package com.example.models.gameModels;

import com.sun.javaws.progress.Progress;

import javax.persistence.Embeddable;

@Embeddable
public class ExecuteCard {
    @Override
    public String toString() {
        return "ExecuteCard{}";
    }

    public void executeTradeCard(ProgressCard.Trade pCard, Player pPlayer){
        switch(pCard){
            case COMMERCIAL_HARBOR:
                commercial_harbor(pPlayer);
                break;
            case MASTER_MERCHANT:
                master_merchant(pPlayer);
                break;
            case MERCHANT:
                merchant(pPlayer);
                break;
            case MERCHANT_FLEET:
                merchant_fleet(pPlayer);
                break;
            case RESOURCE_MONOPOLY:
                resource_monopoly(pPlayer);
                break;
            case TRADE_MONOPOLY:
                trade_monopoly(pPlayer);
                break;
        }
    }

    public void executePoliticsCard(ProgressCard.Politics pCard, Player pPlayer){
        switch (pCard){
            case BISHOP:
                bishop(pPlayer);
                break;
            case CONSTITUTION:
                constitution(pPlayer);
                break;
            case DESERTER:
                deserter(pPlayer);
                break;
            case DIPLOMAT:
                diplomat(pPlayer);
                break;
            case INTRIGUE:
                intrigue(pPlayer);
                break;
            case SABOTEUR:
                saboteur(pPlayer);
                break;
            case SPY:
                spy(pPlayer);
                break;
            case WARLORD:
                warlord(pPlayer);
                break;
            case WEDDING:
                wedding(pPlayer);
                break;
        }
    }

    public void executeScienceCard(ProgressCard.Science pCard, Player pPlayer){
        switch (pCard){
            case ALCHEMIST:
                alchemist(pPlayer);
                break;
            case CRANE:
                crane(pPlayer);
                break;
            case ENGINEER:
                engineer(pPlayer);
                break;
            case INVENTOR:
                inventor(pPlayer);
                break;
            case IRRIGATION:
                irrigation(pPlayer);
                break;
            case MEDICINE:
                medicine(pPlayer);
                break;
            case MINING:
                mining(pPlayer);
                break;
            case PRINTER:
                printer(pPlayer);
                break;
            case ROAD_BUILDING:
                road_building(pPlayer);
                break;
            case SMITH:
                smith(pPlayer);
                break;
        }
    }

    private void commercial_harbor(Player pPlayer){}
    private void master_merchant(Player pPlayer){}
    private void merchant(Player pPlayer){}
    private void merchant_fleet(Player pPlayer){}
    private void resource_monopoly(Player pPlayer){}
    private void trade_monopoly(Player pPlayer){}
    private void bishop(Player pPlayer){}
    private void constitution(Player pPlayer){}
    private void deserter(Player pPlayer){}
    private void diplomat(Player pPlayer){}
    private void intrigue(Player pPlayer){}
    private void saboteur(Player pPlayer){}
    private void spy(Player pPlayer){}
    private void warlord(Player pPlayer){}
    private void wedding(Player pPlayer){}
    private void alchemist(Player pPlayer){}
    private void crane(Player pPlayer){}
    private void engineer(Player pPlayer){}
    private void inventor(Player pPlayer){}
    private void irrigation(Player pPlayer){}
    private void medicine(Player pPlayer){}
    private void mining(Player pPlayer){}
    private void printer(Player pPlayer){}
    private void road_building(Player pPlayer){}
    private void smith(Player pPlayer){}
}

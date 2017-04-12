package com.example.models.gameModels;

/**
 * Created by G on 17/02/27.
 */
public enum TerrainType {
        Sea,Desert,Pasture,Forest,Mountains,Hills,Fields,GoldMine;
        public StealableCard.Resource giveResource(){
                switch (this){
                        case Mountains:
                                return StealableCard.Resource.ORE;
                        case Forest:
                                return StealableCard.Resource.WOOD;
                        case Pasture:
                                return StealableCard.Resource.SHEEP;
                        case Fields:
                                return StealableCard.Resource.WHEAT;
                        case Hills:
                                return StealableCard.Resource.BRICK;

                }
                return null;
        }

        public StealableCard.Commodity giveCommodity(){
                switch(this){
                        case Mountains:
                                return StealableCard.Commodity.COIN;
                        case Forest:
                                return StealableCard.Commodity.PAPER;
                        case Pasture:
                                return StealableCard.Commodity.CLOTH;
                }
                return null;
        }
}

package com.example.models.gameModels;

/**
 * Created by G on 17/02/27.
 */
public enum TerrainType {
        Sea,Desert,Pasture,Forest,Mountains,Hills,Fields,GoldMine;
        public ResourceCard.ResourceType giveResource(){
                switch (this){
                        case Mountains:
                                return ResourceCard.ResourceType.Ore;
                        case Forest:
                                return ResourceCard.ResourceType.Wood;
                        case Pasture:
                                return ResourceCard.ResourceType.Sheep;
                        case Fields:
                                return ResourceCard.ResourceType.Wheat;
                        case Hills:
                                return ResourceCard.ResourceType.Brick;
                }
                return null;
        }

        public CommodityCard.CommodityType giveCommodity(){
                switch(this){
                        case Mountains:
                                return CommodityCard.CommodityType.Coin;
                        case Forest:
                                return CommodityCard.CommodityType.Paper;
                        case Pasture:
                                return CommodityCard.CommodityType.Cloth;
                }
                return null;
        }
}

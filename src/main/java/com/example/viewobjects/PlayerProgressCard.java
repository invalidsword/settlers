package com.example.viewobjects;

import java.util.ArrayList;

/**
 * Created by Ming-PC on 4/11/2017.
 */
public class PlayerProgressCard {
    private String p1Card1;
    private String p1Card2;
    private String p1Card3;
    private String p1Card4;
    private String p2Card1;
    private String p2Card2;
    private String p2Card3;
    private String p2Card4;
    private String p3Card1;
    private String p3Card2;
    private String p3Card3;
    private String p3Card4;
    private String p4Card1;
    private String p4Card2;
    private String p4Card3;
    private String p4Card4;
    
    public PlayerProgressCard(){
    }
    
    public void setp1(ArrayList<String> pCards){
        p1Card1 = pCards.get(0);
        p1Card2 = pCards.get(1);
        p1Card3 = pCards.get(2);
        p1Card4 = pCards.get(3);
    }
    public void setp2(ArrayList<String> pCards){
        p2Card1 = pCards.get(0);
        p2Card2 = pCards.get(1);
        p2Card3 = pCards.get(2);
        p2Card4 = pCards.get(3);
    }
    public void setp3(ArrayList<String> pCards){
        p3Card1 = pCards.get(0);
        p3Card2 = pCards.get(1);
        p3Card3 = pCards.get(2);
        p3Card4 = pCards.get(3);
    }
    public void setp4(ArrayList<String> pCards){
        p4Card1 = pCards.get(0);
        p4Card2 = pCards.get(1);
        p4Card3 = pCards.get(2);
        p4Card4 = pCards.get(3);
    }
}


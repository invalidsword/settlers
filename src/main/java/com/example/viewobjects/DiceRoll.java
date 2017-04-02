package com.example.viewobjects;

/**
 * Created by tooji on 3/9/2017.
 */
public class DiceRoll {
    private int red;
    private int yellow;
    private int event;

    public DiceRoll(int pRed, int pYellow, int pEvent){

        red = pRed;
        yellow = pYellow;
        event = pEvent;

    }

    public DiceRoll(){}

    public int getRed() {
        return red;
    }

    public void setRed(int red) {
        this.red = red;
    }

    public int getYellow() {
        return yellow;
    }

    public void setYellow(int yellow) {
        this.yellow = yellow;
    }

    public int getEvent() {
        return event;
    }

    public void setEvent(int event) {
        this.event = event;
    }
}

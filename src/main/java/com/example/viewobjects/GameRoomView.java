package com.example.viewobjects;

/**
 * Created by tooji on 3/9/2017.
 */
public class GameRoomView {

    private String name;
    private int numJoined;

    public GameRoomView(String pName, int pNum){
        name = pName;
        numJoined = pNum;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getNumJoined() {
        return numJoined;
    }

    public void setNumJoined(int numJoined) {
        this.numJoined = numJoined;
    }
}

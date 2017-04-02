package com.example.viewobjects;

/**
 * Created by tooji on 3/9/2017.
 */
public class ViewPiece {

    private String id;
    private String color;

    ViewPiece(String pId, String pColor){
        id = pId;
        color = pColor;
    }

    ViewPiece(){}

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }
}

package com.example.viewobjects;

/**
 * Created by tooji on 3/9/2017.
 */
public class ViewPiece {

    private String id;
    private String color;
    private boolean isValid;

    ViewPiece(String pId, String pColor,Boolean pIsValid){
        id = pId;
        color = pColor;
        isValid = pIsValid;
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

    public void setIsValid(boolean valid){this.isValid = valid;}

    public boolean getIsValid(){return this.isValid;}
}

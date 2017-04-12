package com.example.viewobjects;

/**
 * Created by Ming-PC on 4/12/2017.
 */
public class ViewRobber {
    private String hexId;
    private boolean isValid;
    private boolean hasStealable;

    public ViewRobber(){
    }

    public String getHexId() {
        return hexId;
    }

    public void setHexId(String hexId) {
        this.hexId = hexId;
    }

    public boolean getIsValid() {
        return isValid;
    }

    public void setIsValid(boolean valid) {
        isValid = valid;
    }

    public boolean isHasStealable() {
        return hasStealable;
    }

    public void setHasStealable(boolean hasStealable) {
        this.hasStealable = hasStealable;
    }
}
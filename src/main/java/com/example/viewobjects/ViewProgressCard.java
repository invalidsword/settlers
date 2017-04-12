package com.example.viewobjects;

/**
 * Created by Ming-PC on 4/10/2017.
 */
public class ViewProgressCard {
    private String aType;
    private String aName;
    private boolean valid;

    public ViewProgressCard(){
    }

    public String getaType() {
        return aType;
    }

    public void setaType(String aType) {
        this.aType = aType;
    }

    public String getaName() {
        return aName;
    }

    public void setaName(String aName) {
        this.aName = aName;
    }

    public boolean isValid() {
        return valid;
    }

    public void setValid(boolean valid) {
        this.valid = valid;
    }
}

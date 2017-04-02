package com.example.viewobjects;

/**
 * Created by tooji on 3/9/2017.
 */
public class PlayerAndPhase {

    private String username;
    private boolean setup1;
    private boolean setup2;

    public PlayerAndPhase(){}

    public PlayerAndPhase(String username, boolean setup1, boolean setup2) {
        this.username = username;
        this.setup1 = setup1;
        this.setup2 = setup2;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public boolean isSetup1() {
        return setup1;
    }

    public void setSetup1(boolean setup1) {
        this.setup1 = setup1;
    }

    public boolean isSetup2() {
        return setup2;
    }

    public void setSetup2(boolean setup2) {
        this.setup2 = setup2;
    }
}

package model;

import java.io.Serializable;

public class UpdatePoints implements Serializable {
    private String compName;
    private int points;
    private String challenge;

    public UpdatePoints(String compName, int points, String challenge) {
        this.compName = compName;
        this.points = points;
        this.challenge = challenge;
    }

    public String getCompName() {
        return compName;
    }

    public int getPoints() {
        return points;
    }

    public String getChallenge() {
        return challenge;
    }

    public void setChallenge(String challenge) {
        this.challenge = challenge;
    }

    @Override
    public String toString() {
        return "competitor " + compName + " has now " + points + " points at " + challenge;
    }
}

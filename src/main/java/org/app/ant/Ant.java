package org.app.ant;

import org.app.agent.Agent;

public class Ant extends Agent {

    private double antLifetime;
    private double antHunger;
    private double antDecisionFactor;

    public Ant(double antLifetime, double antHunger, double antDecisionFactor) {
        super();
        this.antLifetime = antLifetime;
        this.antHunger = antHunger;
        this.antDecisionFactor = antDecisionFactor;
    }

    private boolean moveTest() {
        boolean possibilityOfMovement = false;

        return possibilityOfMovement;
    }

    public void update() {

    };


    public String toString() {
        return "id: { " + getId() + " }";
    }
}

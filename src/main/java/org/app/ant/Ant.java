package org.app.ant;

import org.app.agent.Agent;

public class Ant extends Agent {

    private double antLifetime;
    private double antHunger;
    private double antDecisionFactor;

    public Ant() {
        super();
        this.antHunger = 100;
        this.antLifetime = 100;
        this.antDecisionFactor = 0.5;
    }

    public Ant(double antLifetime, double antHunger, double antDecisionFactor) {
        super();
        this.antLifetime = antLifetime;
        this.antHunger = antHunger;
        this.antDecisionFactor = antDecisionFactor;
    }


    private boolean moveTest() {
        return true;
    }

    public void update() {

    };


    public String toString() {
        return "id: { " + getId() + " }";
    }
}

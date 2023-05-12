package org.app.pheromone;

import org.app.agent.Agent;

import java.util.UUID;

public class Pheromone extends Agent {

    private int livedUpdatesPheromone = 0;

    public Pheromone() {
        super();
    }

    public Pheromone(int livedUpdatesPheromone) {
        super();
        this.livedUpdatesPheromone = livedUpdatesPheromone;
    }


    public double getLocXOfPheromone() {

        return (getLocx());
    }

    public double getLocYOfPheromone() {

        return (getLocy());
    }

    public UUID getIdOfpheromone() {

        return getId();
    }

    public int getLivedUpdatesPheromone() {
        return livedUpdatesPheromone;
    }

    public void updatePheromone() {

        livedUpdatesPheromone++;

    }
}

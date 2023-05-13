package org.app.agent.pheromone;

import java.util.UUID;

public class Pheromone {
    PheromoneType type;
    UUID creator;

    public Pheromone() {
        type = PheromoneType.NONE;
        creator = null;
    }

    public Pheromone(PheromoneType type, UUID creator) {
        this.type = type;
        this.creator = creator;
    }

    public PheromoneType getType() {
        return type;
    }

    public UUID getCreator() {
        return creator;
    }

    public enum PheromoneType {
        FOOD, HOME, SEARCH, NONE
    }
}

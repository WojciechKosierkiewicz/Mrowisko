package org.app.agent.pheromone;

import org.app.menager.config.Config;

import java.util.UUID;

public class Pheromone {
    PheromoneType type;
    UUID creator;

    int creationTick;
    private Config settings;

    public Pheromone() {
        type = PheromoneType.NONE;
        creator = null;
    }

    public int getCreationTick() {
        return creationTick;
    }

    public void setCreationTick(double creationTick) {
        this.creationTick = settings.getCurrentTick();
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

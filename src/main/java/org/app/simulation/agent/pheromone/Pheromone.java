package org.app.simulation.agent.pheromone;

import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import org.app.simulation.agent.Agent;
import org.app.simulation.agent.TypAgenta;
import org.app.simulation.menager.config.Config;

import java.util.UUID;

public class Pheromone extends Agent {
    PheromoneType type;
    UUID creator;

    int creationTick;

    public Pheromone(Config settings, double posx, double posy, UUID creator, int creationTick) {
        super(TypAgenta.PHEROMONE, settings);
        this.creator = creator;
        this.creationTick = creationTick;
        setLocx(posx);
        setLocy(posy);
    }


    public int getCreationTick() {
        return creationTick;
    }
    public void setCreationTick(double creationTick) {
        this.creationTick = getSettings().getCurrentTick();
    }
    public PheromoneType getType() {
        return type;
    }

    public UUID getCreator() {
        return creator;
    }

}

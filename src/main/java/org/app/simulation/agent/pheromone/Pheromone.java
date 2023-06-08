package org.app.simulation.agent.pheromone;

import javafx.scene.paint.Color;
import org.app.simulation.agent.Agent;
import org.app.simulation.agent.TypAgenta;
import org.app.simulation.agent.ant.Ant;
import org.app.simulation.menager.config.Config;

import java.util.UUID;

public class Pheromone extends Agent {
    PheromoneType type;
    Ant creator;

    int creationTick;

    public Pheromone(Config settings, double posx, double posy, Ant creator, int creationTick) {
        super(TypAgenta.PHEROMONE, settings);
        this.creator = creator;
        this.creationTick = creationTick;
        setLocx(posx);
        setLocy(posy);
    }

    public Pheromone(Ant ant, PheromoneType type) {
        super(TypAgenta.PHEROMONE, ant.getSettings());
        this.type = type;
        this.creator = ant;
        this.creationTick = ant.getSettings().getCurrentTick();
        setLocx(creator.getLocx());
        setLocy(creator.getLocy());
        updateJavaFxShapeSettings();

        // TODO: 08.06.2023
        //NIEBEZPIECZNEE

        if (type == PheromoneType.HOME) {
            RemoveFromJavaFxDisplay();
        }

    }


    public void updateJavaFxShapeSettings() {
        getShape().setRadius(getSettings().getPheromoneCircleRadius());
        switch (type) {
            case FOOD:
                getShape().setFill(Color.BLUE);
                break;
            case HOME:
                getShape().setFill(Color.RED);
                break;
        }
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

    public UUID getCreatorID() {
        return creator.getId();
    }

    public Ant getCreator() {
        return creator;
    }
}

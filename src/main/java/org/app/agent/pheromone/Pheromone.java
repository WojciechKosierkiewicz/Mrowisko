package org.app.agent.pheromone;

import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import org.app.agent.Agent;
import org.app.agent.TypAgenta;
import org.app.menager.config.Config;

import java.util.UUID;

public class Pheromone extends Agent {
    PheromoneType type;
    UUID creator;

    int creationTick;
    private Config settings;

    private Circle shape;

    public Pheromone() {
        type = PheromoneType.NONE;
        creator = null;
    }

    public Pheromone(Config settings, double posx, double posy, UUID creator, int creationTick) {
        this.settings = settings;
        this.creator = creator;
        this.creationTick = creationTick;
        this.setTypAgenta(TypAgenta.PHEROMONE);
        setLocx(posx);
        setLocy(posy);
        shape = new Circle(settings.getPheromoneCircleRadius(), this.getColor());
        shape.setRadius(settings.getPheromoneCircleRadius());
        shape.setTranslateX(this.getLocx());
        shape.setTranslateY(this.getLocy());
        settings.getWorld().getChildren().add(shape);
        shape.setStroke(Color.RED);
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

    public void removefromworld() {
        settings.getWorld().getChildren().remove(shape);
    }

}

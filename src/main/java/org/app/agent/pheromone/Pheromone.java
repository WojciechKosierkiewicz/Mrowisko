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
    private Pane world;

    public Pheromone() {
        type = PheromoneType.NONE;
        creator = null;
    }

    public Pheromone(Config settings, double posx, double posy, UUID creator, Pane world) {
        this.settings = settings;
        this.creator = creator;
        this.world = world;
        this.setTypAgenta(TypAgenta.PHEROMONE);
        setLocx(posx);
        setLocy(posy);
        shape = new Circle(settings.getPheromoneCircleRadius(), this.getColor());
        shape.setRadius(settings.getPheromoneCircleRadius());
        shape.setTranslateX(this.getLocx());
        shape.setTranslateY(this.getLocy());
        world.getChildren().add(shape);
        shape.setStroke(Color.RED);
        System.out.println("Pheromone created");
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
        world.getChildren().remove(shape);
    }

    public enum PheromoneType {
        FOOD, HOME, SEARCH, NONE
    }
}

package org.app.simulation.agent.pheromone;

import javafx.scene.paint.Color;
import org.app.simulation.agent.Agent;
import org.app.simulation.agent.TypAgenta;
import org.app.simulation.agent.ant.Ant;
import org.app.simulation.meneger.config.Config;

import java.util.UUID;

/**
 * Reprezentuje pojedynczy feromon w symulacji.
 * Jest podklasą klasy "Agent.
 */
public class Pheromone extends Agent {
    PheromoneType type;
    Ant creator;

    int creationTick;
    int gottotarget = 0;

    /**
     * Konstruktor klasy Pheromone,
     * inicjalizuje obiekt feromonu.
     */
    public Pheromone(double posx, double posy, Ant creator, PheromoneType type) {
        super(TypAgenta.PHEROMONE, creator.getSettings());
        this.type = type;
        this.creator = creator;
        this.creationTick = creator.getSettings().getMap().getTick();
        setLocx(posx);
        setLocy(posy);
        updateJavaFxShapeSettings();

    }

    /**
     * Konstruktor klasy Pheromone dla feromonu utworzonego przez mrówkę.
     */
    public Pheromone(Ant ant, PheromoneType type) {
        super(TypAgenta.PHEROMONE, ant.getSettings());
        this.type = type;
        this.creator = ant;
        this.creationTick = ant.getSettings().getMap().getTick();
        setLocx(creator.getLocx());
        setLocy(creator.getLocy());
        updateJavaFxShapeSettings();

    }

    /**
     * Inkrementuje wartość zmiennej gottotarget o jeden.
     */
    public void add_success() {
        gottotarget++;
    }

    /**
     * Aktualizuje ustawienia obiektu wizualizacji JavaFX dla feromonu.
     */
    public void updateJavaFxShapeSettings() {
        if (getSettings().isPheromoneVisible()) {
            AddToJavaFxDisplay();
        } else {
            RemoveFromJavaFxDisplay();
        }

        getShape().setRadius(getSettings().getDisplayPheromoneSize());
        switch (type) {
            case FOOD -> {
                if (getSettings().isPheromoneToFoodVisible()) {
                    AddToJavaFxDisplay();
                } else {
                    RemoveFromJavaFxDisplay();
                }
                getShape().setFill(Color.BLUE);
            }
            case HOME -> {
                if (getSettings().isPheromoneToHomeVisible()) {
                    AddToJavaFxDisplay();
                } else {
                    RemoveFromJavaFxDisplay();
                }
                getShape().setFill(Color.RED);
            }
            default -> getShape().setFill(Color.BLACK);
        }
    }

    public int getGottotarget() {
        return gottotarget;
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

package org.app.simulation.agent;

import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import org.app.simulation.meneger.config.Config;

import java.util.Random;
import java.util.UUID;

/**
 * Odpowiada za wspólne położenie,
 * kolor oraz ID obiektu,
 * z tej klasy dziedziczy klasa Ant,
 * Athill,
 * Food i Pheromone.
 */
public abstract class Agent {
    private final java.util.UUID id;
    private TypAgenta typ;
    private double locx;
    private double locy;

    private Config settings;
    private Circle shape;

    /**
     * Konstruktor abstrakcyjnej klasy Agent,
     * inicjalizuje agenta o określonym typie i ustawieniach.
     */
    public Agent(TypAgenta typ, Config settings) {
        this.typ = typ;
        id = java.util.UUID.randomUUID();
        this.settings = settings;
        this.shape = typ.getShape(settings);
        AddToJavaFxDisplay();
    }

    /**
     * Dodaje agenta do widoku JavaFX.
     */
    public void AddToJavaFxDisplay() {
        //dont add if arleady in world
        if (settings.getWorld().getChildren().contains(shape)) {
            return;
        }

        settings.getWorld().getChildren().add(shape);
    }

    /**
     * Usuwa agenta z widoku JavaFX.
     */
    public void RemoveFromJavaFxDisplay() {
        settings.getWorld().getChildren().remove(shape);
    }

    /**
     * Aktualizuje ustawienia kształtu obiektu wizualizacji JavaFX dla agenta.
     */
    public void updateJavaFxShapeSettings() {
        shape.setRadius(settings.getDisplayAntSize());
        shape.setFill(getColor());
    }

    public Circle getShape() {
        return shape;
    }

    /**
     * Ustawia losową pozycję agenta na mapie.
     */
    public void setRandomPosition() {
        Random rand = new Random();
        setLocx(Math.random() * settings.getMapSizeX());
        setLocy(Math.random() * settings.getMapSizeY());
    }

    public void setPosition(double x, double y) {
        setLocx(x);
        setLocy(y);
    }

    public Config getSettings() {
        return settings;
    }

    public TypAgenta getTypAgenta() {
        return typ;
    }

    public void setTypAgenta(TypAgenta typ) {
        this.typ = typ;
    }

    public Color getColor() {
        return typ.getColor();
    }

    public UUID getId() {
        return id;
    }

    public double getLocx() {
        return locx;
    }

    public void setLocx(double locx) {
        this.locx = locx;
        shape.setTranslateX(locx);
    }

    public double getLocy() {
        return locy;
    }

    public void setLocy(double locy) {
        this.locy = locy;
        shape.setTranslateY(locy);
    }

    public org.app.simulation.map.Map getMap() {
        return settings.getMap();
    }

}

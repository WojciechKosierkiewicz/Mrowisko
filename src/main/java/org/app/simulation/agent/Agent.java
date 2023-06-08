package org.app.simulation.agent;

import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import org.app.simulation.menager.config.Config;

import java.util.Map;
import java.util.Random;
import java.util.UUID;

public abstract class Agent {
    private final java.util.UUID id;
    private TypAgenta typ;
    private double locx;
    private double locy;

    private Config settings;
    private Circle shape;

    public Agent(TypAgenta typ, Config settings) {
        this.typ = typ;
        id = java.util.UUID.randomUUID();
        this.settings = settings;
        this.shape = typ.getShape(settings);
        AddToJavaFxDisplay();
    }

    public void AddToJavaFxDisplay() {
        settings.getWorld().getChildren().add(shape);
    }

    public void RemoveFromJavaFxDisplay() {
        settings.getWorld().getChildren().remove(shape);
    }

    public void updateJavaFxShapeSettings() {
        shape.setRadius(settings.getAntCircleRadius());
        shape.setFill(getColor());
    }

    public Circle getShape() {
        return shape;
    }

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

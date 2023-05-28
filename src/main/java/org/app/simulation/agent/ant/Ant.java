package org.app.simulation.agent.ant;

import javafx.scene.paint.Color;
import org.app.simulation.agent.Agent;
import org.app.simulation.agent.TypAgenta;
import org.app.simulation.menager.config.Config;

import java.util.Random;
import java.util.UUID;

import javafx.scene.shape.Circle;

public class Ant extends Agent {

    private int livedUpdates = 0;
    private double antHunger = 0;
    private int CarriedFood = 0;
    private Antdirection direction = Antdirection.FOOD;

    private final AntHeading heading;

    private final Config settings;
    private final Circle shape;
    UUID id_mrowiska;

    public Ant(UUID id_mrowiska, Config settings) {
        super();
        this.heading = new AntHeading(settings);
        this.id_mrowiska = id_mrowiska;
        this.setTypAgenta(TypAgenta.ANT);
        this.settings = settings;
        this.shape = new Circle(settings.getAntCircleRadius(), this.getColor());
        shape.setStroke(Color.BLACK);
        settings.getWorld().getChildren().add(shape);
        updateJavaFxLocation();
    }

    public int getLivedUpdates() {
        return livedUpdates;
    }

    public void updateJavaFxShape() {
        shape.setRadius(settings.getAntCircleRadius());
        shape.setFill(this.getColor());
    }

    public void updateJavaFxLocation() {
        shape.setTranslateX(this.getLocx());
        shape.setTranslateY(this.getLocy());
    }

    public void removefromworld() {
        settings.getWorld().getChildren().remove(shape);
    }


    public void setPosition(double x, double y) {
        this.setLocx(x);
        this.setLocy(y);
    }

    public void setRandomPosition() {
        Random rand = new Random();
        this.setLocx(Math.random() * settings.getWorld().getWidth());
        this.setLocy(Math.random() * settings.getWorld().getHeight());
    }

    public double getAntHunger() {
        return antHunger;
    }

    void feedself() {
        if (!settings.isAntGetHungry()) {
            return;
        }
        if (CarriedFood <= 0) {
            return;
        }
        if (antHunger == settings.getAntHungerLimit() / 2) {
            antHunger -= 5;
            CarriedFood--;
        }
    }

    void starve() {
        if (!settings.isAntGetHungry()) {
            return;
        }
        if (antHunger > 0) {
            antHunger -= settings.getAntConsumption();
        }
    }

    public void setAntHunger(double antHunger) {
        this.antHunger = antHunger;
    }


    public void moveAnt() {
        double movementx = settings.getAntStepLen() * Math.cos(heading.getHeadingAngle());
        double movementy = settings.getAntStepLen() * Math.sin(heading.getHeadingAngle());

        double newx = this.getLocx() + movementx;
        double newy = this.getLocy() + movementy;

        if (newx < 0 || newx > settings.getMapSizeX()) {
            heading.bouncexwall();
        }

        if (newy < 0 || newy > settings.getMapSizeY()) {
            heading.bounceywall();
        }

        this.setLocx(newx);
        this.setLocy(newy);
    }


    public void updateAngle() {
        heading.update(getLocx(), getLocy(), direction);
    }

    private double countAngleBeetwenPoints(double x1, double y1, double x2, double y2) {
        double dx = x2 - x1;
        double dy = y2 - y1;

        return Math.atan2(dy, dx);
    }

    public void leavePheromoneBehind() {
        if (livedUpdates % settings.getAntPheromoneInterval() == 0) {
            settings.getMap().createPheromoneAtPoint(this.getLocx(), this.getLocy(), this.id_mrowiska);
        }
    }

    public void update() {
        starve();
        feedself();
        moveAnt();
        updateAngle();
        updateJavaFxLocation();
        leavePheromoneBehind();
        livedUpdates++;
    }

    public String toString() {
        return "id: { " + getId() + " }, locx: { " + getLocx() + " }, locy: { " + getLocy() + " }";
    }
}

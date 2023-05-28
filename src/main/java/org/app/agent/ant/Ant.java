package org.app.agent.ant;

import javafx.scene.paint.Color;
import org.app.agent.Agent;
import org.app.agent.TypAgenta;
import org.app.agent.food.Food;
import org.app.menager.config.Config;
import org.app.agent.pheromone.Pheromone;
import org.app.menager.config.Config;

import java.util.Map;
import java.util.Random;
import java.util.UUID;
import java.util.Vector;

import javafx.scene.shape.Circle;
import javafx.scene.layout.Pane;

import org.app.agent.ant.AntHeading;

public class Ant extends Agent {

    private int livedUpdates = 0;
    private double antHunger;

    private AntHeading heading;

    private Config settings;
    private Circle shape;

    private Pane world;

    private org.app.map.Map map;

    UUID id_mrowiska;

    public Ant(UUID id_mrowiska, org.app.map.Map map, Pane world, Config settings) {
        super();
        this.heading = new AntHeading(settings);
        this.antHunger = settings.getInitialAntHunger();
        this.id_mrowiska = id_mrowiska;
        this.map = map;
        this.setTypAgenta(TypAgenta.ANT);
        this.world = world;
        this.settings = settings;
        this.shape = new Circle(settings.getAntCircleRadius(), this.getColor());
        shape.setStroke(Color.BLACK);
        world.getChildren().add(shape);
        draw();
    }

    public int getLivedUpdates() {
        return livedUpdates;
    }

    public void draw() {
        shape.setRadius(settings.getAntCircleRadius());
        shape.setTranslateX(this.getLocx());
        shape.setTranslateY(this.getLocy());
    }

    public void removefromworld() {
        world.getChildren().remove(shape);
    }


    public void setPosition(double x, double y) {
        this.setLocx(x);
        this.setLocy(y);
    }

    public void setRandomPosition() {
        Random rand = new Random();
        this.setLocx(Math.random() * world.getWidth());
        this.setLocy(Math.random() * world.getHeight());
    }

    public double getAntHunger() {
        return antHunger;
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
        heading.update();
    }

    private double countAngleBeetwenPoints(double x1, double y1, double x2, double y2) {
        double dx = x2 - x1;
        double dy = y2 - y1;

        double radianAngle = Math.atan2(dy, dx);

        return radianAngle;
    }

    public void update() {
        moveAnt();
        updateAngle();
        draw();
        livedUpdates++;
        if (livedUpdates % 10 == 0) {
            map.createPheromoneAtPoint(this.getLocx(), this.getLocy(), this.id_mrowiska);
        }
    }


    public String toString() {
        return "id: { " + getId() + " }, locx: { " + getLocx() + " }, locy: { " + getLocy() + " }";
    }
}

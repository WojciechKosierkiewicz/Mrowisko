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

public class Ant extends Agent {

    private int livedUpdates = 0;
    private double antHunger;

    private double movement_angle;
    private double ant_freedom_angle;

    private Config settings;
    private Circle shape;
    private Pane world;


    private org.app.map.Map map;

    UUID id_mrowiska;

    public Ant(UUID id_mrowiska, org.app.map.Map map, Pane world, Config settings) {
        super();
        this.antHunger = settings.getInitialAntHunger();
        this.id_mrowiska = id_mrowiska;
        this.map = map;
        this.setTypAgenta(TypAgenta.ANT);
        this.world = world;
        this.settings = settings;
        this.movement_angle = Math.toRadians(Math.random() * 360);
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


    public void setMovement_angle(double movement_angle) {
        this.movement_angle = movement_angle;
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
        //losowanie kąta w zakresie dopuszczonym przez ant freedom angle

        //przesunięcie o krok w kierunku wylosowanego kąta
        //aktualnie nie sprawdzam czy dany krok jest legalny :( fuck law
        this.setLocx(this.getLocx() + settings.getAntStepLen() * Math.cos(movement_angle));
        this.setLocy(this.getLocy() + settings.getAntStepLen() * Math.sin(movement_angle));
    }


    public void updateAngle() {

        //wysyłam zapytanie do mapy o feromony
        Vector<Pheromone> pheromones = map.getSurroundingPheromones(getLocx(), getLocy(), settings.getAntRange());

        Pheromone oldestPheromone = pheromones.get(1);

        for (Pheromone p : pheromones) {

            if (p.getCreationTick() < oldestPheromone.getCreationTick()) {
                oldestPheromone = p;
            }
        }

        movement_angle = countAngleBeetwenPoints(getLocx(), getLocy(), oldestPheromone.getLocx(), oldestPheromone.getLocy());
        movement_angle = ((Math.random() * (movement_angle + ant_freedom_angle - (movement_angle - ant_freedom_angle))) + (movement_angle - ant_freedom_angle));
    }

    private double countAngleBeetwenPoints(double x1, double y1, double x2, double y2) {
        double dx = x2 - x1;
        double dy = y2 - y1;

        double radianAngle = Math.atan2(dy, dx);

        return radianAngle;
    }

    public void update() {
        moveAnt();
        draw();
    }


    public String toString() {
        return "id: { " + getId() + " }, locx: { " + getLocx() + " }, locy: { " + getLocy() + " }";
    }
}

package org.app.simulation.agent.ant;

import javafx.scene.paint.Color;
import org.app.simulation.agent.Agent;
import org.app.simulation.agent.TypAgenta;
import org.app.simulation.agent.food.Food;
import org.app.simulation.menager.config.Config;

import java.util.Random;
import java.util.UUID;
import java.util.Vector;

import javafx.scene.shape.Circle;

public class Ant extends Agent {

    private int livedUpdates = 0;
    private double antHunger = 0;
    private int CarriedFood = 0;
    private Antdirection direction = Antdirection.FOOD;

    private final AntHeading heading;

    UUID id_mrowiska;

    public Ant(UUID id_mrowiska, Config settings) {
        super(TypAgenta.ANT, settings);
        this.heading = new AntHeading(settings, this);
        this.id_mrowiska = id_mrowiska;
    }

    public int getLivedUpdates() {
        return livedUpdates;
    }

    public double getAntHunger() {
        return antHunger;
    }

    void feedself() {
        if (!getSettings().isAntGetHungry()) {
            return;
        }
        if (CarriedFood <= 0) {
            return;
        }
        if (antHunger == getSettings().getAntHungerLimit() / 2) {
            antHunger -= 5;
            CarriedFood--;
        }
    }

    void starve() {
        if (!getSettings().isAntGetHungry()) {
            return;
        }
        if (antHunger > 0) {
            antHunger -= getSettings().getAntConsumption();
        }
    }

    public void setAntHunger(double antHunger) {
        this.antHunger = antHunger;
    }

    void handleFoodObtaining() {
        if (direction == Antdirection.HOME) {
            return;
        }
        Vector<Food> localfood = getSettings().getMap().getSurroundingFoods(this.getLocx(), this.getLocy(), getSettings().getAntRange());

        if (localfood.size() > 0) {

            Food closestfood = localfood.get(0);

            if (direction == Antdirection.FOOD) {
                heading.setHeadingAngle(countAngleBeetwenPoints(this.getLocx(), this.getLocy(), closestfood.getLocx(), closestfood.getLocy()));
                direction = Antdirection.FOODFOUND;
            }

            if (countDistanceBetweenAgents(closestfood) < getSettings().getAntFeedingRange()) {
                CarriedFood += closestfood.requestFood(getSettings().getAntFoodCapacity());
                direction = Antdirection.HOME;
            }
        }
    }

    public void moveAnt() {
        double movementx = getSettings().getAntStepLen() * Math.cos(heading.getHeadingAngle());
        double movementy = getSettings().getAntStepLen() * Math.sin(heading.getHeadingAngle());

        double newx = this.getLocx() + movementx;
        double newy = this.getLocy() + movementy;

        if (newx < 0 || newx > getSettings().getMapSizeX()) {
            heading.bouncexwall();
        }

        if (newy < 0 || newy > getSettings().getMapSizeY()) {
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

        return Math.atan2(dy, dx);
    }

    public double countDistanceBeetwenPoints(double x1, double y1, double x2, double y2) {
        return Math.sqrt((x2 - x1) * (x2 - x1) + (y2 - y1) * (y2 - y1));
    }

    public double countDistanceBetweenAgents(Agent agent) {
        return countDistanceBeetwenPoints(this.getLocx(), this.getLocy(), agent.getLocx(), agent.getLocy());
    }

    public void leavePheromoneBehind() {
        if (livedUpdates % getSettings().getAntPheromoneInterval() == 0) {
            getSettings().getMap().createPheromoneAtPoint(this.getLocx(), this.getLocy(), this.id_mrowiska);
        }
    }

    public void update() {

        //Handles Food
        handleFoodObtaining();
        starve();
        feedself();

        //Handles Movement
        updateAngle();
        moveAnt();

        //Handles Pheromones
        leavePheromoneBehind();

        livedUpdates++;
    }

    public String toString() {
        return "id: { " + getId() + " }, locx: { " + getLocx() + " }, locy: { " + getLocy() + " }";
    }

    public Antdirection getDirection() {
        return direction;
    }
}

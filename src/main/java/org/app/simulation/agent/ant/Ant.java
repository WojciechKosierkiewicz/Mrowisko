package org.app.simulation.agent.ant;

import org.app.simulation.agent.Agent;
import org.app.simulation.agent.TypAgenta;
import org.app.simulation.agent.anthill.Anthill;
import org.app.simulation.agent.food.Food;
import org.app.simulation.agent.pheromone.Pheromone;
import org.app.simulation.agent.pheromone.PheromoneType;
import org.app.simulation.menager.config.Config;

import java.util.Vector;

public class Ant extends Agent {

    private int livedUpdates = 0;
    private double antHunger = 0;
    private int CarriedFood = 0;
    private Antdirection direction = Antdirection.FOOD;

    private final AntHeading heading;

    Anthill mrowisko;

    public Ant(Anthill mrowisko, Config settings) {
        super(TypAgenta.ANT, settings);
        this.heading = new AntHeading(settings, this);
        this.mrowisko = mrowisko;
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

    void handlefoundfood(Vector<Food> foods) {
        direction = Antdirection.HOME;
        heading.clear_known_pheromones();
    }

    void handlefoundhome() {
        direction = Antdirection.FOOD;
        heading.clear_known_pheromones();
    }

    void checkisTargetinRange() {
        switch (direction) {
            case FOOD -> {
                Vector<Food> foods = new Vector<>(getMap().getFoods());

                foods.removeIf(f -> countDistanceBetweenAgents(f) > getSettings().getSenseRange());

                if (foods.size() > 0) {
                    handlefoundfood(foods);
                }
            }
            case HOME -> {
                if (countDistanceBetweenAgents(mrowisko) < getSettings().getSenseRange()) {
                    handlefoundhome();
                }
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

    public double countAngleBeetwenPoints(double x1, double y1, double x2, double y2) {
        double dx = x2 - x1;
        double dy = y2 - y1;

        return Math.atan2(dy, dx);
    }

    public double countDistanceBeetwenPoints(double x1, double y1, double x2, double y2) {
        double dx = x2 - x1;
        double dy = y2 - y1;

        return Math.sqrt(dx * dx + dy * dy);
    }

    public double countDistanceBetweenAgents(Agent agent) {
        return countDistanceBeetwenPoints(getLocx(), getLocy(), agent.getLocx(), agent.getLocy());
    }

    public void leavePheromoneBehind() {
        if (livedUpdates % getSettings().getAntPheromoneInterval() == 0) {
            switch (direction) {
                case FOOD -> getMap().addPheromone(new Pheromone(this, PheromoneType.HOME));
                case HOME, FOODFOUND -> getMap().addPheromone(new Pheromone(this, PheromoneType.FOOD));
                default -> {

                }
            }
        }
    }

    public void update() {
        checkisTargetinRange();

        //Handles Food
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

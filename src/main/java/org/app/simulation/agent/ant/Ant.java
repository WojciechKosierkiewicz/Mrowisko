package org.app.simulation.agent.ant;

import org.app.simulation.agent.Agent;
import org.app.simulation.agent.TypAgenta;
import org.app.simulation.agent.anthill.Anthill;
import org.app.simulation.agent.food.Food;
import org.app.simulation.agent.pheromone.Pheromone;
import org.app.simulation.agent.pheromone.PheromoneType;
import org.app.simulation.meneger.config.Config;

import java.util.Vector;

/**
 * Rozszerza funkcjonalność klasy Agent,
 * dostosowując ją do zachowań i cech charakterystycznych dla mrówek,
 * implementując ruch agenta oraz jego reakcje na środowisko.
 */
public class Ant extends Agent {

    private int livedUpdates = 0;
    private double antHunger = 0;
    private int CarriedFood = 0;
    private Antdirection direction = Antdirection.FOOD;

    private final AntHeading heading;

    Anthill mrowisko;

    /**
     * Konstruktor klasy Ant,
     * inicjalizuje mrówkę ustawiając odpowiednie wartości początkowe.
     */
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

    /**
     * Zajmuje się karmieniem mrówki.
     */
    void feedself() {
        if (!getSettings().isAntGetHungry()) {
            return;
        }
        if (CarriedFood <= 0) {
            return;
        }
        if (antHunger == getSettings().getAntMaximumHungerLevel() / 2) {
            antHunger -= 5;
            CarriedFood--;
        }
    }

    /**
     * Obsługuje głodzenie się mrówki.
     */
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

    /**
     * Obsługuje znalezienie jedzenia przez mrówkę.
     */
    void handlefoundfood(Vector<Food> foods) {
        leavePhermoneat(foods.get(0).getLocx(), foods.get(0).getLocy(), PheromoneType.FOOD);
        direction = Antdirection.HOME;
        getSettings().getMap().updateperomonesuccesrate(this, Antdirection.FOOD);
    }

    /**
     * Obsługuje znalezienie mrowiska przez mrówkę.
     */
    void handlefoundhome() {
        direction = Antdirection.FOOD;
        getSettings().getMap().updateperomonesuccesrate(this, Antdirection.HOME);
        leavePhermoneat(mrowisko.getLocx(), mrowisko.getLocy(), PheromoneType.FOOD);
    }

    /**
     * Sprawdza czy cel znajduje się w zasięgu wzroku mrówki.
     */
    void checkisTargetinRange() {
        switch (direction) {
            case FOOD -> {
                Vector<Food> foods = new Vector<>(getMap().getFoods());

                foods.removeIf(f -> countDistanceBetweenAgents(f) > getSettings().getAntViewRange());

                if (foods.size() > 0) {
                    handlefoundfood(foods);
                }
            }
            case HOME -> {
                if (countDistanceBetweenAgents(mrowisko) < getSettings().getAntViewRange()) {
                    handlefoundhome();
                }
            }
        }
    }

    /**
     * Porusza mrówką na podstawie jej aktualnej pozycji i kierunku.
     */
    public void moveAnt() {
        double movementx = getSettings().getAntStepLengthh() * Math.cos(heading.getHeadingAngle());
        double movementy = getSettings().getAntStepLengthh() * Math.sin(heading.getHeadingAngle());

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

    /**
     * Aktualizuje kąt kierunku mrówki.
     */
    public void updateAngle() {
        heading.update();
    }

    /**
     * Oblicza kąt między dwoma punktami.
     */
    public double countAngleBeetwenPoints(double x1, double y1, double x2, double y2) {
        double dx = x2 - x1;
        double dy = y2 - y1;

        return Math.atan2(dy, dx);
    }

    /**
     * Oblicza kąt między dwoma punktami.
     */
    public double countDistanceBeetwenPoints(double x1, double y1, double x2, double y2) {
        double dx = x2 - x1;
        double dy = y2 - y1;

        return Math.sqrt(dx * dx + dy * dy);
    }

    /**
     * Oblicza odległość między mrówką a innym agentem.
     */
    public double countDistanceBetweenAgents(Agent agent) {
        return countDistanceBeetwenPoints(getLocx(), getLocy(), agent.getLocx(), agent.getLocy());
    }

    /**
     * Pozostawia feromon informujący o jedzeniu w środowisku.
     */
    void leavePhermoneat(double locx, double locy, PheromoneType type) {
        getMap().addPheromone(new Pheromone(locx, locy, this, type));
    }

    /**
     * Pozostawia feromon w środowisku na podstawie aktualnego kierunku mrówki.
     */
    public void leavePheromoneBehind() {
        if (livedUpdates % getSettings().getAntLeavePheromoneInterval() == 0) {
            switch (direction) {
                case FOOD -> getMap().addPheromone(new Pheromone(this, PheromoneType.HOME));
                case HOME, FOODFOUND -> getMap().addPheromone(new Pheromone(this, PheromoneType.FOOD));
                default -> {

                }
            }
        }
    }

    /**
     * Aktualizuje stan mrówki na podstawie jej zachowania.
     */
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

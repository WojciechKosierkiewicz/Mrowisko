package org.app.agent.ant;

import org.app.agent.Agent;
import org.app.agent.food.Food;
import org.app.menager.config.Config;
import org.app.agent.pheromone.Pheromone;

import java.util.Map;
import java.util.Random;
import java.util.UUID;
import java.util.Vector;

public class Ant extends Agent {

    private int livedUpdates = 0;
    private double antHunger;

    private double movement_angle;
    private double ant_freedom_angle;

    private double step_len;
    private Config settings;


    private org.app.map.Map map;

    UUID id_mrowiska;

    public Ant(double antHunger, double antDecisionFactor, UUID id_mrowiska, org.app.map.Map map) {
        super();
        this.antHunger = antHunger;
        this.id_mrowiska = id_mrowiska;
        this.map = map;
    }

    public int getLivedUpdates() {
        return livedUpdates;
    }


    public void setMovement_angle(double movement_angle) {
        this.movement_angle = movement_angle;
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
        this.setLocx(this.getLocx() + step_len * Math.cos(movement_angle));
        this.setLocy(this.getLocy() + step_len * Math.sin(movement_angle));
    }

    public void updateAngle() {

        //wysyłam zapytanie do mapy o feromony
        Vector<Pheromone> pheromones = map.getSurroundingPheromones(getLocx(), getLocy(), settings.getAntRange());
        Vector<Food> foods = map.getFoodinRange(getLocx(), getLocy(), settings.getAntRange());

        if (foods.size() > 0) {
            movement_angle = countAngleBeetwenPoints(foods.get(0).getLocx(), foods.get(0).getLocy(), getLocx(), getLocy());
        }

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
        livedUpdates++;
    }


    public String toString() {
        return "id: { " + getId() + " }";
    }
}

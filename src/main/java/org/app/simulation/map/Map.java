package org.app.simulation.map;

import javafx.scene.layout.Pane;
import org.app.simulation.agent.Agent;
import org.app.simulation.agent.anthill.Anthill;
import org.app.simulation.agent.food.Food;
import org.app.simulation.agent.pheromone.Pheromone;
import org.app.simulation.menager.config.Config;

import java.util.UUID;
import java.util.Vector;

public class Map {

    private Vector<Pheromone> pheromones;
    private Vector<Food> foods;

    private Config settings;
    private Pane world;
    private int ticks;


    public Map(int x, int y, Config settings, Pane world) {
        this.world = world;
        this.settings = settings;

        foods = new Vector<>();
        pheromones = new Vector<>();

    }

    public Vector<Pheromone> getSurroundingPheromones(Agent queryowner, double range) {
        Vector<Pheromone> pheromonez = new Vector<>();
        for (Pheromone p : pheromones) {
            if (getDistanceBetweenAgents(queryowner, p) < range) {
                pheromonez.add(p);
            }
        }
        return pheromonez;
    }

    public void RemovePheromonesCreatedBy(UUID id) {
        for (int i = 0; i < pheromones.size(); i++) {
            if (pheromones.get(i).getCreatorID() == id) {
                pheromones.get(i).RemoveFromJavaFxDisplay();
                pheromones.remove(i);
                i--;
            }
        }
    }

    public void AddFood(Food food) {
        foods.add(food);
    }

    public Vector<Food> getFoods() {
        return foods;
    }

    public void Tick() {
        ticks++;
    }


    double getDistanceBetweenPoints(double x1, double y1, double x2, double y2) {
        return Math.sqrt(Math.pow((x2 - x1), 2) + Math.pow((y2 - y1), 2));
    }

    double getDistanceBetweenAgents(Agent a1, Agent a2) {
        return getDistanceBetweenPoints(a1.getLocx(), a1.getLocy(), a2.getLocx(), a2.getLocy());
    }

    public void addPheromone(Pheromone pheromone) {
        pheromones.add(pheromone);
    }

    public void removePheromonesolderthan(int x) {
        for (int i = 0; i < pheromones.size(); i++) {
            if (pheromones.get(i).getCreationTick() - ticks > x) {
                pheromones.get(i).RemoveFromJavaFxDisplay();
                pheromones.remove(i);
                i--;
            }
        }
    }

    public int getamountofpheromones() {
        return pheromones.size();
    }

    public void clearPhermonoes() {
        for (Pheromone p : pheromones) {
            p.RemoveFromJavaFxDisplay();
        }
        pheromones.clear();
    }

    public Vector<Pheromone> getPheromones() {
        return pheromones;
    }

    public void clearFood() {
        for (Food f : foods) {
            f.RemoveFromJavaFxDisplay();
        }
        foods.clear();
    }
}

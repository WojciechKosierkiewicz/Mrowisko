package org.app.simulation.map;

import javafx.scene.layout.Pane;
import org.app.simulation.agent.Agent;
import org.app.simulation.agent.ant.Ant;
import org.app.simulation.agent.food.Food;
import org.app.simulation.agent.pheromone.Pheromone;
import org.app.simulation.map.supportClasses.PheromoneSectorMap;
import org.app.simulation.map.supportClasses.PheromoneSectorMapBruteForceFAKEONE;
import org.app.simulation.menager.config.Config;

import java.util.UUID;
import java.util.Vector;

public class Map {

    private PheromoneSectorMapBruteForceFAKEONE pheromonessectormap;
    private Vector<Food> foods;

    private Config settings;
    private Pane world;
    private int ticks;


    public Map(int x, int y, Config settings, Pane world) {
        this.world = world;
        this.settings = settings;

        foods = new Vector<>();
        pheromonessectormap = new PheromoneSectorMapBruteForceFAKEONE(settings);
    }

    public void hide_pheromones() {
        pheromonessectormap.HideAllPheromones();
    }

    public void show_pheromones() {
        pheromonessectormap.ShowAllPheromones();
    }

    public Vector<Pheromone> getSurroundingPheromones(Ant querywoner, double range) {
        return pheromonessectormap.request_pheromones_surrounding_point(querywoner.getLocx(), querywoner.getLocy(), range);
    }

    public void RemovePheromonesCreatedBy(UUID id) {
        pheromonessectormap.remove_pheromones_by_id(id);
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
        pheromonessectormap.add_pheromone(pheromone);
    }

    public void removePheromonesolderthan(int x) {
        pheromonessectormap.removePheromonesOlderThan(x, ticks);
    }

    public int getamountofpheromones() {
        return pheromonessectormap.get_pheromone_count();
    }

    public void clearPhermonoes() {
        pheromonessectormap.HideAllPheromones();
        pheromonessectormap.clear();
    }

    public Vector<Pheromone> getPheromones() {
        return pheromonessectormap.get_all_pheromones();
    }

    public void clearFood() {
        for (Food f : foods) {
            f.RemoveFromJavaFxDisplay();
        }
        foods.clear();
    }
}

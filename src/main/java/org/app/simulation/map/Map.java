package org.app.simulation.map;

import javafx.scene.layout.Pane;
import org.app.simulation.agent.Agent;
import org.app.simulation.agent.ant.Ant;
import org.app.simulation.agent.ant.Antdirection;
import org.app.simulation.agent.food.Food;
import org.app.simulation.agent.pheromone.Pheromone;
import org.app.simulation.map.supportClasses.PheromoneSectorMap;
import org.app.simulation.meneger.config.Config;

import java.util.UUID;
import java.util.Vector;

/**
 * Reprezentuje mapę w symulacji.
 * Odpowiada za zarządzanie elementami mapy,
 * takimi jak pożywienie,
 * feromony i operacje na nich.
 */
public class Map {

    private PheromoneSectorMap pheromonessectormap;
    private Vector<Food> foods;

    private Config settings;
    private Pane world;
    private int ticks;

    /**
     * Konstruktor klasy Map, inicjalizuje mapę o określonym rozmiarze i ustawieniach.
     */
    public Map(int x, int y, Config settings, Pane world) {
        this.world = world;
        this.settings = settings;

        foods = new Vector<>();
        pheromonessectormap = new PheromoneSectorMap(settings);
    }

    /**
     * Ukrywa wszystkie feromony na mapie.
     */
    public void hide_pheromones() {
        pheromonessectormap.HideAllPheromones();
    }

    /**
     * Wyświetla wszystkie feromony na mapie.
     */
    public void show_pheromones() {
        pheromonessectormap.ShowAllPheromones();
    }

    /**
     * Zwraca wektor feromonów znajdujących się w określonym obszarze wokół określonego mrówki.
     */
    public Vector<Pheromone> getSurroundingPheromones(Ant querywoner, double range) {
        return pheromonessectormap.request_pheromones_surrounding_point(querywoner.getLocx(), querywoner.getLocy(), range);
    }

    /**
     * Usuwa feromony utworzone przez agenta o określonym identyfikatorze.
     */
    public void RemovePheromonesCreatedBy(UUID id) {
        pheromonessectormap.remove_pheromones_by_id(id);
    }

    /**
     * Dodaje jedzenie do mapy.
     */
    public void AddFood(Food food) {
        foods.add(food);
    }

    public Vector<Food> getFoods() {
        return foods;
    }

    /**
     * Zwiększa wartość tików o jeden.
     */
    public void Tick() {
        ticks++;
    }

    /**
     * Oblicza odległość między dwoma punktami na podstawie współrzędnych.
     */
    double getDistanceBetweenPoints(double x1, double y1, double x2, double y2) {
        return Math.sqrt(Math.pow((x2 - x1), 2) + Math.pow((y2 - y1), 2));
    }

    /**
     * Oblicza odległość między dwoma agentami na podstawie ich współrzędnych.
     */
    double getDistanceBetweenAgents(Agent a1, Agent a2) {
        return getDistanceBetweenPoints(a1.getLocx(), a1.getLocy(), a2.getLocx(), a2.getLocy());
    }

    /**
     * Dodaje feromon do mapy.
     */
    public void addPheromone(Pheromone pheromone) {
        pheromonessectormap.add_pheromone(pheromone);
    }

    /**
     * Usuwa feromony starsze niż określona liczba tików.
     */
    public void removePheromonesolderthan(int x) {
        pheromonessectormap.removePheromonesOlderThan(x, ticks);
    }

    /**
     * Zwraca liczbę feromonów na mapie.
     */
    public int getamountofpheromones() {
        return pheromonessectormap.get_pheromone_count();
    }

    /**
     * Czyści mapę feromonów,
     * usuwając wszystkie feromony.
     */
    public void clearPhermonoes() {
        pheromonessectormap.HideAllPheromones();
        pheromonessectormap.clear();
    }

    public Vector<Pheromone> getPheromones() {
        return pheromonessectormap.get_all_pheromones();
    }

    /**
     * Czyści jedzenie z mapy, usuwając wszystkie obiekty jedzenia.
     */
    public void clearFood() {
        for (Food f : foods) {
            f.RemoveFromJavaFxDisplay();
        }
        foods.clear();
    }

    public int getTick() {
        return ticks;
    }

    /**
     * Aktualizuje wskaźnik sukcesu dla feromonów tworzonych przez daną mrówkę w określonym kierunku.
     */
    public void updateperomonesuccesrate(Ant ant, Antdirection direction) {
        pheromonessectormap.update_pheromone_succes_rate(ant, direction);
    }
}

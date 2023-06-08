package org.app.simulation.map;

import javafx.scene.layout.Pane;
import org.app.simulation.agent.food.Food;
import org.app.simulation.agent.pheromone.Pheromone;
import org.app.simulation.menager.config.Config;

import java.util.UUID;
import java.util.Vector;

public class Map {

    private final int sector_size;
    private Vector<Vector<Vector<Pheromone>>> Pheromone_Sector_map;
    private Vector<Food> foods;

    private Config settings;
    private Pane world;
    private int ticks;


    public Map(int x, int y, int sector_size, Config settings, Pane world) {
        this.world = world;
        this.settings = settings;

        this.sector_size = sector_size;

        Pheromone_Sector_map = new Vector<>();
        for (int i = 0; i < x / sector_size; i++) {
            Pheromone_Sector_map.add(new Vector<>());
            for (int j = 0; j < y / sector_size; j++) {
                Pheromone_Sector_map.get(i).add(new Vector<>());
            }
        }

        foods = new Vector<>();

    }

    public Vector<Pheromone> getSurroundingPheromones(double x, double y, double range) {
        Vector<Pheromone> result = new Vector<>();
        int sector_x = (int) (x / sector_size);
        int sector_y = (int) (y / sector_size);
        int sectors_in_range = (int) (range / sector_size);

        for (int i = sector_x - sectors_in_range; i <= sector_x + sectors_in_range; i++)
            for (int j = sector_y - sectors_in_range; j <= sector_y + sectors_in_range; j++)
                if (i >= 0 && i < Pheromone_Sector_map.size() && j >= 0 && j < Pheromone_Sector_map.get(i).size())
                    result.addAll(Pheromone_Sector_map.get(i).get(j));

        return result;
    }

    public void RemovePheromonesCreatedBy(UUID id) {
        for (Vector<Vector<Pheromone>> sector : Pheromone_Sector_map) {
            for (Vector<Pheromone> pheromones : sector) {
                for (int i = 0; i < pheromones.size(); i++) {
                    if (pheromones.get(i).getCreatorID() == id) {
                        pheromones.get(i).RemoveFromJavaFxDisplay();
                        pheromones.remove(i);
                        i--;
                    }
                }
            }
        }
    }

    public void AddFood(Food food) {
        foods.add(food);
    }

    public Vector<Food> getFoods() {
        return foods;
    }

    public Vector<Food> getSurroundingFoods(double x, double y, double range) {
        if (foods == null)
            return null;

        Vector<Food> result = new Vector<>();

        for (Food food : foods)
            if (getDistanceBetweenPoints(x, y, food.getLocx(), food.getLocy()) < range)
                result.add(food);

        return result;
    }

    public void Tick() {
        ticks++;
    }


    double getDistanceBetweenPoints(double x1, double y1, double x2, double y2) {
        return Math.sqrt(Math.pow((x2 - x1), 2) + Math.pow((y2 - y1), 2));
    }

    public void addPheromone(Pheromone pheromone) {
        int sector_x = (int) (pheromone.getLocx() / sector_size);
        int sector_y = (int) (pheromone.getLocy() / sector_size);

        if (sector_x > Pheromone_Sector_map.size() - 1)
            sector_x = Pheromone_Sector_map.size() - 1;

        if (sector_y > Pheromone_Sector_map.get(sector_x).size() - 1)
            sector_y = Pheromone_Sector_map.get(sector_x).size() - 1;

        Pheromone_Sector_map.get(sector_x).get(sector_y).add(pheromone);
    }

    public void removePheromonesolderthan(int x) {
        for (Vector<Vector<Pheromone>> vectors : Pheromone_Sector_map) {
            for (Vector<Pheromone> vector : vectors) {
                for (int k = 0; k < vector.size(); k++) {
                    if (ticks - vector.get(k).getCreationTick() > x) {
                        vector.get(k).RemoveFromJavaFxDisplay();
                        vector.remove(k);
                        k--;
                    }
                }
            }
        }
    }

    public int getamountofpheromones() {
        int result = 0;
        for (Vector<Vector<Pheromone>> vectors : Pheromone_Sector_map) {
            for (Vector<Pheromone> vector : vectors) {
                result += vector.size();
            }
        }
        return result;
    }

    public void clearPhermonoes() {
        for (Vector<Vector<Pheromone>> sector : Pheromone_Sector_map) {
            for (Vector<Pheromone> pheromonez : sector) {
                for (Pheromone p : pheromonez) {
                    p.RemoveFromJavaFxDisplay();
                }
                pheromonez.clear();
            }
            sector.clear();
        }
    }

    public Pheromone[] getPheromones() {
        Pheromone[] result = new Pheromone[getamountofpheromones()];
        int index = 0;
        for (Vector<Vector<Pheromone>> sector : Pheromone_Sector_map) {
            for (Vector<Pheromone> pheromonez : sector) {
                for (Pheromone p : pheromonez) {
                    result[index] = p;
                    index++;
                }
            }
        }
        return result;
    }

    public void clearFood() {
        for (Food f : foods) {
            f.RemoveFromJavaFxDisplay();
        }
        foods.clear();
    }
}

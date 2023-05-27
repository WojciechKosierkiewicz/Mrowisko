package org.app.map;

import org.app.agent.anthill.Anthill;
import org.app.agent.food.Food;
import org.app.agent.pheromone.*;
import org.app.menager.config.Config;

import java.util.List;
import java.util.UUID;
import java.util.Vector;

import org.app.agent.ant.Ant;

public class Map {

    private final int sector_size;
    private final Vector<Vector<Integer>> HeightMap;
    private final Vector<Vector<Vector<Pheromone>>> Pheromone_Sector_map;
    private Config settings;



    public Map(int x, int y, int sector_size, Config settings) {
        this.settings = settings;
        HeightMap = new Vector<>();

        for (int i = 0; i < x; i++) {
            HeightMap.add(new Vector<>());
            for (int j = 0; j < y; j++) {
                HeightMap.get(i).add(0);
            }
        }

        this.sector_size = sector_size;

        Pheromone_Sector_map = new Vector<>();
        for (int i = 0; i < x / sector_size; i++) {
            Pheromone_Sector_map.add(new Vector<>());
            for (int j = 0; j < y / sector_size; j++) {
                Pheromone_Sector_map.get(i).add(new Vector<>());
            }
        }

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

    public Vector<Pheromone> getSurroundingPheromones(double x, double y, double range, Pheromone.PheromoneType type) {
        Vector<Pheromone> result = new Vector<>();
        int sector_x = (int) (x / sector_size);
        int sector_y = (int) (y / sector_size);
        int sectors_in_range = (int) (range / sector_size);

        for (int i = sector_x - sectors_in_range; i <= sector_x + sectors_in_range; i++)
            for (int j = sector_y - sectors_in_range; j <= sector_y + sectors_in_range; j++)
                if (i >= 0 && i < Pheromone_Sector_map.size() && j >= 0 && j < Pheromone_Sector_map.get(i).size())
                    for (Pheromone p : Pheromone_Sector_map.get(i).get(j))
                        if (p.getType() == type)
                            result.add(p);

        return result;
    }


    public Vector<Pheromone> getSurroundingPheromones(double x, double y, double range, Pheromone.PheromoneType type, UUID creator) {
        Vector<Pheromone> result = new Vector<>();
        int sector_x = (int) (x / sector_size);
        int sector_y = (int) (y / sector_size);
        int sectors_in_range = (int) (range / sector_size);

        for (int i = sector_x - sectors_in_range; i <= sector_x + sectors_in_range; i++)
            for (int j = sector_y - sectors_in_range; j <= sector_y + sectors_in_range; j++)
                if (i >= 0 && i < Pheromone_Sector_map.size() && j >= 0 && j < Pheromone_Sector_map.get(i).size())
                    for (Pheromone p : Pheromone_Sector_map.get(i).get(j))
                        if (p.getType() == type && p.getCreator() == creator)
                            result.add(p);

        return result;
    }

    public Vector<Vector<Integer>> getSurroundingTiles(double x, double y) {
        int posx = (int) x;
        int posy = (int) y;

        Vector results = new Vector();
        Vector subres = new Vector();

        for (int i = posx - 1; i <= posx + 1; i++) {
            subres = new Vector();
            for (int j = posy - 1; j <= posy + 1; j++) {
                if (i >= 0 && i < HeightMap.size() && j >= 0 && j < HeightMap.get(i).size()) {
                    subres.add(HeightMap.get(i).get(j));
                }
            }
            results.add(subres);
        }

        return results;
    }

    double getDistanceBetweenPoints(double x1, double y1, double x2, double y2) {
        return Math.sqrt(Math.pow((x2 - x1), 2) + Math.pow((y2 - y1), 2));
    }
}

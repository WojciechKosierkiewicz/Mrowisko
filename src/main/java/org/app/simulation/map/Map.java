package org.app.simulation.map;

import javafx.scene.layout.Pane;
import org.app.simulation.agent.pheromone.PheromoneType;
import org.app.simulation.agent.pheromone.Pheromone;
import org.app.simulation.menager.config.Config;

import java.util.UUID;
import java.util.Vector;

public class Map {

    private final int sector_size;
    private final Vector<Vector<Integer>> HeightMap;
    private final Vector<Vector<Vector<Pheromone>>> Pheromone_Sector_map;
    private Config settings;
    private Pane world;
    private int ticks;


    public Map(int x, int y, int sector_size, Config settings, Pane world) {
        this.world = world;
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

    public void Tick() {
        ticks++;
    }


    double getDistanceBetweenPoints(double x1, double y1, double x2, double y2) {
        return Math.sqrt(Math.pow((x2 - x1), 2) + Math.pow((y2 - y1), 2));
    }

    public void createPheromoneAtPoint(double posx, double posy, UUID creator) {
        int sector_x = 2;
        int sector_y = 2;
        Pheromone_Sector_map.get(sector_x).get(sector_y).add(new Pheromone(settings, posx, posy, creator, ticks));
    }

    public void removePheromonesolderthan(int x) {

        for (Vector<Vector<Pheromone>> vectors : Pheromone_Sector_map) {
            for (Vector<Pheromone> vector : vectors) {
                for (int k = 0; k < vector.size(); k++) {
                    if (ticks - vector.get(k).getCreationTick() > x) {
                        vector.get(k).removefromworld();
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
                    p.removefromworld();
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
}

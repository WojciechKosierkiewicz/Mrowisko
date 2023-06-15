package org.app.simulation.map.supportClasses;

import org.app.simulation.agent.pheromone.Pheromone;
import org.app.simulation.agent.pheromone.PheromoneType;
import org.app.simulation.menager.config.Config;

import java.util.UUID;
import java.util.Vector;

public class PheromoneSectorMapBruteForceFAKEONE {
    Config settings;
    Vector<Pheromone> data;

    public PheromoneSectorMapBruteForceFAKEONE(Config settings) {
        this.settings = settings;
        data = new Vector<>();
    }

    public void HideAllPheromones() {
        for (Pheromone p : data) {
            p.RemoveFromJavaFxDisplay();
        }
    }

    public void ShowAllPheromones() {
        for (Pheromone p : data) {
            p.AddToJavaFxDisplay();
        }
    }

    public void remove_pheromones_by_id(UUID id) {
        for (int k = 0; k < data.size(); k++) {
            if (data.get(k).getCreatorID() == id) {
                data.remove(k);
                k--;
            }
        }
    }

    public void add_pheromone(Pheromone pheromone) {
        data.add(pheromone);
    }

    public int get_pheromone_count() {
        return data.size();
    }

    public void clear() {
        data.clear();
    }

    public Vector<Pheromone> get_all_pheromones() {
        return new Vector<>(data);
    }

    public void removePheromonesOlderThan(int ticks, int currentTicks) {
        for (int k = 0; k < data.size(); k++) {
            if (data.get(k).getCreationTick() + ticks < currentTicks) {
                data.get(k).RemoveFromJavaFxDisplay();
                data.remove(k);
                k--;
            }
        }
    }

    double count_distance(double x1, double y1, double x2, double y2) {
        return Math.sqrt(Math.pow(x1 - x2, 2) + Math.pow(y1 - y2, 2));
    }

    public Vector<Pheromone> request_pheromones_surrounding_point(double locx, double locy, double range) {
        Vector<Pheromone> pheromones = new Vector<>(data);
        pheromones.removeIf(p -> count_distance(p.getLocx(), p.getLocy(), locx, locy) > range);
        return pheromones;
    }

    public void remove_overly_used_pheromones() {
        if (settings.isPheromoneToFoodUsedUp() && settings.isPheromoneToHomeUsedUp()) {
            return;
        }

        for (Pheromone p : data) {
            if (p.getType() == PheromoneType.FOOD && settings.isPheromoneToFoodUsedUp()) {
                if (p.getAmountofuse() > settings.getMaxPheromoneToFoodUsage()) {
                    p.RemoveFromJavaFxDisplay();
                    data.remove(p);
                }
            } else {
                if (settings.isPheromoneToHomeUsedUp()) {
                    if (p.getAmountofuse() > settings.getMaxPheromoneToHomeUsage()) {
                        p.RemoveFromJavaFxDisplay();
                        data.remove(p);
                    }
                }
            }
        }
    }
}

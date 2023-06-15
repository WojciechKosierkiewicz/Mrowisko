package org.app.simulation.map.supportClasses;

import org.app.simulation.agent.ant.Ant;
import org.app.simulation.agent.ant.Antdirection;
import org.app.simulation.agent.pheromone.Pheromone;
import org.app.simulation.agent.pheromone.PheromoneType;
import org.app.simulation.meneger.config.Config;

import java.util.UUID;
import java.util.Vector;

public class PheromoneSectorMap {
    Config settings;
    Vector<Pheromone>[][] data;

    public PheromoneSectorMap(Config settings) {
        this.settings = settings;
        data = new Vector[(settings.getMapSizeX() / settings.getMapSectorSize()) + 1][(settings.getMapSizeY() / settings.getMapSectorSize()) + 1];
        for (int i = 0; i < data.length; i++) {
            for (int j = 0; j < data[i].length; j++) {
                data[i][j] = new Vector<>();
            }
        }
    }

    public void HideAllPheromones() {
        Vector<Pheromone> pheromones = get_all_pheromones();
        for (Pheromone p : pheromones) {
            p.RemoveFromJavaFxDisplay();
        }
    }

    public void ShowAllPheromones() {
        Vector<Pheromone> pheromones = get_all_pheromones();
        for (Pheromone p : pheromones) {
            p.AddToJavaFxDisplay();
        }
    }

    public void remove_pheromones_by_id(UUID id) {
        for (Vector<Pheromone>[] datax : data) {
            for (Vector<Pheromone> pheromones : datax) {
                for (int k = 0; k < pheromones.size(); k++) {
                    if (pheromones.get(k).getCreatorID() == id) {
                        pheromones.get(k).RemoveFromJavaFxDisplay();
                        pheromones.remove(k);
                        k--;
                    }
                }
            }
        }
    }

    public void add_pheromone(Pheromone pheromone) {
        int x = (int) (pheromone.getLocx() / settings.getMapSectorSize());
        int y = (int) (pheromone.getLocy() / settings.getMapSectorSize());
        data[x][y].add(pheromone);
    }

    public int get_pheromone_count() {
        int count = 0;
        for (Vector<Pheromone>[] datax : data) {
            for (Vector<Pheromone> pheromones : datax) {
                count += pheromones.size();
            }
        }
        return count;
    }

    public void clear() {
        for (Vector<Pheromone>[] datax : data) {
            for (Vector<Pheromone> pheromones : datax) {
                for (Pheromone p : pheromones) {
                    p.RemoveFromJavaFxDisplay();
                }
                pheromones.clear();
            }
        }
    }

    public Vector<Pheromone> get_all_pheromones() {
        Vector<Pheromone> pheromones = new Vector<>();
        for (Vector<Pheromone>[] datax : data) {
            for (Vector<Pheromone> pheromone : datax) {
                pheromones.addAll(pheromone);
            }
        }
        return pheromones;
    }

    public void removePheromonesOlderThan(int ticks, int currentTicks) {
        for (Vector<Pheromone>[] datax : data) {
            for (Vector<Pheromone> pheromones : datax) {
                for (int k = 0; k < pheromones.size(); k++) {
                    if (currentTicks - pheromones.get(k).getCreationTick() > ticks) {
                        pheromones.get(k).RemoveFromJavaFxDisplay();
                        pheromones.remove(k);
                        k--;
                    }
                }
            }
        }
    }

    public Vector<Pheromone> request_pheromones_surrounding_point(double locx, double locy, double range) {
        Vector<Pheromone> pheromones = new Vector<>();
        int x = (int) (locx / settings.getMapSectorSize());
        int y = (int) (locy / settings.getMapSectorSize());
        int rangeSectors = (int) (range / settings.getMapSectorSize());
        for (int i = x - rangeSectors; i <= x + rangeSectors; i++) {
            for (int j = y - rangeSectors; j <= y + rangeSectors; j++) {
                if (i >= 0 && i < data.length && j >= 0 && j < data[i].length) {
                    pheromones.addAll(data[i][j]);
                }
            }
        }
        return pheromones;
    }

    public void update_pheromone_succes_rate(Ant ant, Antdirection direction) {
        for (Vector<Pheromone>[] datax : data) {
            for (Vector<Pheromone> pheromones : datax) {
                for (Pheromone p : pheromones) {
                    if (p.getCreator() == ant) {
                        if (direction == Antdirection.FOOD) {
                            if (p.getType() == PheromoneType.FOOD) {
                                p.add_success();
                            }
                        } else if (direction == Antdirection.HOME) {
                            if (p.getType() == PheromoneType.HOME) {
                                p.add_success();
                            }
                        }
                    }
                }
            }
        }
    }
}

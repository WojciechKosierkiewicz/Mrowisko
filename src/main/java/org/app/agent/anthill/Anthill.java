package org.app.agent.anthill;

import java.util.Vector;

import org.app.agent.Agent;
import org.app.agent.ant.Ant;
import org.app.menager.config.Config;

public class Anthill extends Agent {
    Vector<Ant> Ants;
    private org.app.map.Map map;
    private Config settings;

    public Anthill() {
        Ants = new Vector<Ant>();
    }

    public Anthill(int AntLifeTime) {
        Ants = new Vector<Ant>();
    }

    public Anthill(Vector<Ant> Ants, int AntLifeTime) {
        this.Ants = Ants;
    }

    public void addAnt(Ant ant) {
        Ants.add(ant);
    }

    public void printAnts() {
        for (Ant ant : Ants) {
            System.out.println(ant);
        }
    }

    public void Update() {
        for (Ant ant : Ants) {
            ant.update();
        }
        removeOldAnts();
    }

    private void removeOldAnts() {

        for (int i = 0; i < Ants.size(); i++)
            if (Ants.get(i).getLivedUpdates() > settings.getAntLifetime())
                Ants.remove(i);

    }
}
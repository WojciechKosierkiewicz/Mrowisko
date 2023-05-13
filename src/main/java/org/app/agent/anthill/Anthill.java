package org.app.agent.anthill;

import java.util.Vector;

import org.app.agent.ant.Ant;

public class Anthill {
    Vector<Ant> Ants;
    int AntLimit;
    int AntLifeTime;

    public Anthill() {
        Ants = new Vector<Ant>();
        AntLifeTime = 100;
    }

    public Anthill(int AntLifeTime) {
        Ants = new Vector<Ant>();
        this.AntLifeTime = AntLifeTime;
    }

    public Anthill(Vector<Ant> Ants, int AntLifeTime) {
        this.Ants = Ants;
        this.AntLifeTime = AntLifeTime;
    }

    public void addAnt(Ant ant) {
        Ants.add(ant);
    }

    public void addAnt() {
        Ants.add(new Ant());
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
            if (Ants.get(i).getLivedUpdates() > AntLifeTime)
                Ants.remove(i);

    }
}
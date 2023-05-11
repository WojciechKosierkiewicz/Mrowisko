package org.app.anthill;

import java.util.Vector;

import org.app.ant.Ant;

public class Anthill {
    Vector<Ant> Ants;

    public Anthill() {
        Ants = new Vector<Ant>();
    }

    public Anthill(Vector<Ant> Ants) {
        this.Ants = Ants;
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
}
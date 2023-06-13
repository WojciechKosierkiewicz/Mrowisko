package org.app.simulation.agent.anthill;

import java.util.Vector;

import org.app.simulation.agent.Agent;
import org.app.simulation.agent.TypAgenta;
import org.app.simulation.agent.ant.Ant;
import org.app.simulation.menager.config.Config;

public class Anthill extends Agent {
    Vector<Ant> Ants;

    public Anthill(Config settings) {
        super(TypAgenta.ANTHILL, settings);
        Ants = new Vector<>();
    }

    public void removeAntsOutsideMap(double threshold) {
        for (int i = 0; i < Ants.size(); i++) {
            if (Ants.get(i).getLocx() < -threshold || Ants.get(i).getLocx() > getSettings().getMapSizeX() + threshold || Ants.get(i).getLocy() < 0 - threshold || Ants.get(i).getLocy() > getSettings().getMapSizeY() + threshold) {
                getAnts().get(i).RemoveFromJavaFxDisplay();
                getMap().RemovePheromonesCreatedBy(getAnts().get(i).getId());
                Ants.remove(i);
                //zmniejszam i by nie pominąc żadnego elementu w nowej zmniejszonej tablicy
                i--;
            }
        }
    }


    public void addAnt(Ant ant) {

        Ants.add(ant);
        Ants.get(Ants.size() - 1).setPosition(getLocx(), getLocy());
    }

    public void addAnt() {
        Ants.add(new Ant(this, getSettings()));
        Ants.get(Ants.size() - 1).setPosition(getLocx(), getLocy());
    }

    public void printAnts() {
        for (Ant ant : Ants) {
            System.out.println(ant);
        }
    }

    public void removeAntsfromWorld() {
        for (Ant ant : Ants) {
            ant.RemoveFromJavaFxDisplay();
        }
    }

    public void removeEverythingFromWorld() {
        removeAntsfromWorld();
        RemoveFromJavaFxDisplay();
    }

    public void update() {
        for (Ant ant : Ants) {
            ant.update();
        }
        removeOldAnts();
        if (Ants.size() < getSettings().getAnthillAntsLimit()) {
            if (Math.random() < getSettings().getAnthillChanceOfAntSpawning())
                addAnt();
        }

        removeAntsOutsideMap(getSettings().getOutsideMapTreshold());
    }

    private void removeOldAnts() {

        for (int i = 0; i < Ants.size(); i++) {
            if (Ants.get(i).getLivedUpdates() > getSettings().getAntLifetime()) {
                Ants.get(i).RemoveFromJavaFxDisplay();
                getMap().RemovePheromonesCreatedBy(Ants.get(i).getId());
                Ants.remove(i);
                //zmniejszam i by nie pominąc żadnego elementu w nowej zmniejszonej tablicy
                i--;
            }
        }

    }

    public Vector<Ant> getAnts() {
        return Ants;
    }
}
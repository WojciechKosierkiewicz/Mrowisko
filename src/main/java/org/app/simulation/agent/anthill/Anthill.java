package org.app.simulation.agent.anthill;

import java.util.Vector;

import org.app.simulation.agent.Agent;
import org.app.simulation.agent.TypAgenta;
import org.app.simulation.agent.ant.Ant;
import org.app.simulation.meneger.config.Config;

/**
 * Reprezentuje mrowisko w symulacji.
 * Odpowiada za zarządzanie mrówkami.
 */
public class Anthill extends Agent {
    Vector<Ant> Ants;

    public void updateJavaFxShapeSettings() {
        getShape().setRadius(getSettings().getDisplayAnthillSize());
        getShape().setFill(TypAgenta.ANTHILL.getColor());
    }

    public Anthill(Config settings) {
        super(TypAgenta.ANTHILL, settings);
        Ants = new Vector<>();
    }

    /**
     * Usuwa mrówki spoza granic mapy.
     */
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

    /**
     * Dodaje konkretną mrówkę do gniazda.
     */
    public void addAnt(Ant ant) {

        Ants.add(ant);
        Ants.get(Ants.size() - 1).setPosition(getLocx(), getLocy());
    }

    /**
     * Dodaje nową mrówkę do gniazda.
     */
    public void addAnt() {
        Ants.add(new Ant(this, getSettings()));
        Ants.get(Ants.size() - 1).setPosition(getLocx(), getLocy());
    }

    /**
     * Wyświetla informacje o wszystkich mrówkach w gnieździe.
     */
    public void printAnts() {
        for (Ant ant : Ants) {
            System.out.println(ant);
        }
    }

    /**
     * Usuwa mrówki z wyświetlania w interfejsie graficznym.
     */
    public void removeAntsfromWorld() {
        for (Ant ant : Ants) {
            ant.RemoveFromJavaFxDisplay();
        }
    }

    /**
     * Usuwa zarówno mrówki,
     * jak i gniazdo z wyświetlania w interfejsie graficznym.
     */
    public void removeEverythingFromWorld() {
        removeAntsfromWorld();
        RemoveFromJavaFxDisplay();
    }

    /**
     * Aktualizuje stan gniazda i wszystkich mrówek w nim.
     */
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

    /**
     * Usuwa "stare" mrówki z gniazda na podstawie maksymalnej liczby cykli życia.
     */
    private void removeOldAnts() {

        for (int i = 0; i < Ants.size(); i++) {
            if (Ants.get(i).getLivedUpdates() > getSettings().getAntLifetime()) {
                Ants.get(i).RemoveFromJavaFxDisplay();
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
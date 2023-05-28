package org.app.agent.anthill;

import java.util.Vector;

import javafx.scene.layout.Pane;
import org.app.agent.Agent;
import org.app.agent.ant.Ant;
import org.app.menager.config.Config;

public class Anthill extends Agent {
    private Pane world;
    Vector<Ant> Ants;
    private org.app.map.Map map;
    private Config settings;

    public Anthill() {
        Ants = new Vector<Ant>();
    }

    public Anthill(Config settings, Pane world, org.app.map.Map map) {
        this.settings = settings;
        this.world = world;
        this.map = map;
        Ants = new Vector<Ant>();
    }

    public void removeAntsOutsideMap(double threshold) {
        for (int i = 0; i < Ants.size(); i++) {
            if (Ants.get(i).getLocx() < -threshold || Ants.get(i).getLocx() > settings.getMapSizeX() + threshold || Ants.get(i).getLocy() < 0 - threshold || Ants.get(i).getLocy() > settings.getMapSizeY() + threshold) {
                getAnts().get(i).removefromworld();
                Ants.remove(i);
            }
        }
    }


    public void addAnt(Ant ant) {

        Ants.add(ant);
        Ants.get(Ants.size() - 1).setPosition(getLocx(), getLocy());
    }

    public void addAnt() {
        Ants.add(new Ant(getId(), map, world, settings));
        Ants.get(Ants.size() - 1).setPosition(getLocx(), getLocy());
    }

    public void printAnts() {
        for (Ant ant : Ants) {
            System.out.println(ant);
        }
    }

    public void setPos(double posx, double posy) {
        setLocx(posx);
        setLocy(posy);
    }

    public void setrandompos() {
        setLocx(Math.random() * settings.getMapSizeY());
        setLocy(Math.random() * settings.getMapSizeX());
    }


    public void update() {
        for (Ant ant : Ants) {
            ant.update();
        }
        removeOldAnts();
    }

    public void draw() {
        for (Ant ant : Ants) {
            ant.draw();
        }
    }

    private void removeOldAnts() {

        for (int i = 0; i < Ants.size(); i++)
            if (Ants.get(i).getLivedUpdates() > settings.getAntLifetime())
                Ants.remove(i);

    }

    public Vector<Ant> getAnts() {
        return Ants;
    }
}
package org.app.agent.anthill;

import java.util.Vector;

import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import org.app.agent.Agent;
import org.app.agent.TypAgenta;
import org.app.agent.ant.Ant;
import org.app.menager.config.Config;

public class Anthill extends Agent {
    Vector<Ant> Ants;
    private Config settings;
    private Circle shape;

    public Anthill() {
        Ants = new Vector<Ant>();
    }

    public Anthill(Config settings) {
        setTypAgenta(TypAgenta.ANTHILL);
        shape = new Circle(settings.getAntHillCircleRadius(), getColor());
        shape.setStroke(Color.BLUE);
        settings.getWorld().getChildren().add(shape);
        this.settings = settings;
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
        Ants.add(new Ant(getId(), settings));
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
        shape.setTranslateX(posx);
        shape.setTranslateY(posy);
    }

    public void setrandompos() {
        setPos(Math.random() * settings.getMapSizeX(), Math.random() * settings.getMapSizeY());
    }


    public void update() {
        for (Ant ant : Ants) {
            ant.update();
        }
        removeOldAnts();
        if (Ants.size() < settings.getAnthillAntLimit()) {
            if (Math.random() < settings.getAnthillAntSpawnChance())
                addAnt();
        }
    }

    public void draw() {
        for (Ant ant : Ants) {
            ant.draw();
        }
    }

    private void removeOldAnts() {

        for (int i = 0; i < Ants.size(); i++) {
            if (Ants.get(i).getLivedUpdates() > settings.getAntLifetime()) {
                Ants.get(i).removefromworld();
                Ants.remove(i);
            }
        }

    }

    public Vector<Ant> getAnts() {
        return Ants;
    }
}
package org.app.simulation.agent.anthill;

import java.util.Vector;

import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import org.app.simulation.agent.Agent;
import org.app.simulation.agent.TypAgenta;
import org.app.simulation.agent.ant.Ant;
import org.app.simulation.menager.config.Config;

public class Anthill extends Agent {
    Vector<Ant> Ants;
    private Config settings;
    private Circle shape;

    public Anthill() {
        Ants = new Vector<>();
    }

    public Anthill(Config settings) {
        setTypAgenta(TypAgenta.ANTHILL);
        shape = new Circle(settings.getAntHillCircleRadius(), getColor());
        shape.setStroke(Color.BLUE);
        settings.getWorld().getChildren().add(shape);
        this.settings = settings;
        Ants = new Vector<>();
    }

    public void updateJavaFxShape() {
        shape.setRadius(settings.getAntHillCircleRadius());
        shape.setFill(this.getColor());
    }

    public void removeAntsOutsideMap(double threshold) {
        for (int i = 0; i < Ants.size(); i++) {
            if (Ants.get(i).getLocx() < -threshold || Ants.get(i).getLocx() > settings.getMapSizeX() + threshold || Ants.get(i).getLocy() < 0 - threshold || Ants.get(i).getLocy() > settings.getMapSizeY() + threshold) {
                getAnts().get(i).removefromworld();
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

    public void removeFromWorld() {
        settings.getWorld().getChildren().remove(shape);
    }

    public void removeAntsfromWorld() {
        for (Ant ant : Ants) {
            ant.removefromworld();
        }
    }

    public void removeEverythingFromWorld() {
        removeFromWorld();
        removeAntsfromWorld();
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

        removeAntsOutsideMap(settings.getOutsideMapTreshold());
    }

    public void draw() {
        for (Ant ant : Ants) {
            ant.updateJavaFxLocation();
        }
    }

    private void removeOldAnts() {

        for (int i = 0; i < Ants.size(); i++) {
            if (Ants.get(i).getLivedUpdates() > settings.getAntLifetime()) {
                Ants.get(i).removefromworld();
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
package org.app.simulation.agent;

import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import org.app.simulation.menager.config.Config;


public enum TypAgenta {
    PHEROMONE {
        public Color getColor() {
            return Color.BLUE;
        }

        public Circle getShape(Config Settings) {
            return new Circle(Settings.getPheromoneCircleRadius(), this.getColor());
        }
    }, FOOD {
        public Color getColor() {
            return Color.GREEN;
        }

        public Circle getShape(Config Settings) {
            return new Circle(Settings.getFoodCircleRadius(), this.getColor());
        }
    }, ANTHILL {
        public Color getColor() {
            return Color.RED;
        }

        public Circle getShape(Config Settings) {
            return new Circle(Settings.getAntHillCircleRadius(), this.getColor());
        }
    }, ANT {
        public Color getColor() {
            return Color.ORANGE;
        }

        public Circle getShape(Config Settings) {
            return new Circle(Settings.getAntCircleRadius(), this.getColor());
        }
    };

    public abstract Color getColor();

    public abstract Circle getShape(Config Settings);
}

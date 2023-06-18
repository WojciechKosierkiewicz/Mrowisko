package org.app.simulation.agent;

import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import org.app.simulation.meneger.config.Config;

/**
 * Reprezentuje różne typy agentów w symulacji.
 * Każdy typ agenta ma przypisaną pewną barwę (Color) oraz kształt graficzny (Circle) zależny od konfiguracji (Settings).
 */

public enum TypAgenta {
    PHEROMONE {
        public Color getColor() {
            return Color.BLUE;
        }

        public Circle getShape(Config Settings) {
            return new Circle(Settings.getDisplayPheromoneSize(), this.getColor());
        }
    }, FOOD {
        public Color getColor() {
            return Color.GREEN;
        }

        public Circle getShape(Config Settings) {
            return new Circle(Settings.getDisplayFoodSize(), this.getColor());
        }
    }, ANTHILL {
        public Color getColor() {
            return Color.RED;
        }

        public Circle getShape(Config Settings) {
            return new Circle(Settings.getDisplayAnthillSize(), this.getColor());
        }
    }, ANT {
        public Color getColor() {
            return Color.ORANGE;
        }

        public Circle getShape(Config Settings) {
            return new Circle(Settings.getDisplayAntSize(), this.getColor());
        }
    };

    public abstract Color getColor();

    public abstract Circle getShape(Config Settings);
}

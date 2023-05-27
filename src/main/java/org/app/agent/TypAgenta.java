package org.app.agent;

import javafx.scene.paint.Color;


public enum TypAgenta {
    PHEROMONE {
        public Color getColor() {
            return Color.BLUE;
        }
    }, FOOD {
        public Color getColor() {
            return Color.GREEN;
        }
    }, ANTHILL {
        public Color getColor() {
            return Color.RED;
        }
    }, ANT {
        public Color getColor() {
            return Color.ORANGE;
        }
    };

    public abstract Color getColor();

}

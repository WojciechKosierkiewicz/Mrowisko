package org.app.map;

import java.util.Vector;

public class Map {
    Vector<Vector<Integer>> Map;

    Map(int x, int y) {
        Map = new Vector<Vector<Integer>>();
        for (int i = 0; i < x; i++) {
            Map.add(new Vector<Integer>());
            for (int j = 0; j < y; j++) {
                Map.get(i).add(0);
            }
        }
    }
}

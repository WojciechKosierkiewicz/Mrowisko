package org.app.map;

import org.app.anthill.Anthill;

import java.util.List;
import java.util.Vector;

public class Map {
    Vector<Vector<Integer>> HeightMap;
    List<Anthill> anthills;

    Map(int x, int y) {
        HeightMap = new Vector<>();
        for (int i = 0; i < x; i++) {
            HeightMap.add(new Vector<>());
            for (int j = 0; j < y; j++) {
                HeightMap.get(i).add(0);
            }
        }
    }

    public void addAnthill(Anthill anthill) {
        anthills.add(anthill);
    }
}

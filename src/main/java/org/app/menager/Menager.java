package org.app.menager;


import org.app.menager.config.Config;
import org.app.menager.simresults.SimResults;

import java.util.Map;
import java.util.Vector;

import org.app.agent.ant.Ant;

public class Menager {

    private Config konfiguracja;
    private SimResults results;
    private org.app.map.Map map;
    private int CurrentTick = 0;


    public Menager(Config konfiguracja) {
        // TODO: 15.05.2023  
    }

    void PrzeprowadzTickSymulacji() {
        // TODO: 15.05.2023  
    }

    void ZwrocAktulneWyniki() {
        // TODO: 15.05.2023  
    }

    void ZapiszWyniki() {
        // TODO: 15.05.2023   
    }


    public Vector<Ant> getAnts() {
        return map.getAnts();
    }

}
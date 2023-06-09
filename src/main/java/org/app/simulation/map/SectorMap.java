package org.app.simulation.map;

import org.app.simulation.agent.Agent;
import org.app.simulation.menager.config.Config;

import java.util.Vector;

public class SectorMap {
    Config settings;
    java.util.Map<Integer, Vector<Agent>> data;

    SectorMap(Config settings) {
        this.settings = settings;

        data = new java.util.HashMap<>();
    }

    int calculatesectornumber(double x, double y) {
        int sectorx = (int) (x / settings.getMapSectorSize());
        int sectory = (int) (y / settings.getMapSectorSize());
        return sectorx + sectory * settings.getMapSizeX() / settings.getMapSizeY();
    }

    void addAgent(Agent agent) {
        int sector = calculatesectornumber(agent.getLocx(), agent.getLocy());

        if (data.containsKey(sector)) {
            data.get(sector).add(agent);
        } else {
            Vector<Agent> agents = new Vector<>();
            agents.add(agent);
            data.put(sector, agents);
        }
    }

    public Vector<Agent> getSurroundingAgents(Agent queryowner, double range) {
        Vector<Agent> agents = new Vector<>();
        int sector = calculatesectornumber(queryowner.getLocx(), queryowner.getLocy());
        for (int i = -1; i < 2; i++) {
            if (data.containsKey(sector + i)) {
                for (Agent agent : data.get(sector + i)) {
                    if (getDistanceBetweenAgents(queryowner, agent) < range) {
                        agents.add(agent);
                    }
                }
            }
        }
        return agents;
    }

    private double getDistanceBetweenAgents(Agent queryowner, Agent agent) {
        return Math.sqrt(Math.pow(queryowner.getLocx() - agent.getLocx(), 2) + Math.pow(queryowner.getLocy() - agent.getLocy(), 2));
    }
}

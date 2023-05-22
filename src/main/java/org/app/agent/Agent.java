package org.app.agent;


import java.util.UUID;

public abstract class Agent {
    private final java.util.UUID id;
    private double locx;
    private double locy;

    public Agent() {
        id = java.util.UUID.randomUUID();
    }

    public UUID getId() {
        return id;
    }

    public double getLocx() {
        return locx;
    }

    public void setLocx(double locx) {
        this.locx = locx;
    }

    public double getLocy() {
        return locy;
    }

    public void setLocy(double locy) {
        this.locy = locy;
    }
}

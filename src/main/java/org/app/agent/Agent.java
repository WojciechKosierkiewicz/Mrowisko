package org.app.agent;


import java.util.UUID;

public abstract class Agent {
    private java.util.UUID id;
    private int locx;
    private int locy;

    public Agent() {
        id = java.util.UUID.randomUUID();
    }

    public UUID getId() {
        return id;
    }

    public int getLocx() {
        return locx;
    }

    public void setLocx(int locx) {
        this.locx = locx;
    }

    public int getLocy() {
        return locy;
    }

    public void setLocy(int locy) {
        this.locy = locy;
    }
}

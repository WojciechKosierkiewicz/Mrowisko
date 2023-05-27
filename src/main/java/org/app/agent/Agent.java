package org.app.agent;

import javafx.scene.paint.Color;

import java.util.UUID;

public abstract class Agent {
    private final java.util.UUID id;
    private TypAgenta typ;
    private double locx;
    private double locy;

    public Agent() {
        id = java.util.UUID.randomUUID();
    }

    public Agent(TypAgenta typ) {
        this.typ = typ;
        id = java.util.UUID.randomUUID();
    }

    public TypAgenta getTypAgenta() {
        return typ;
    }

    public void setTypAgenta(TypAgenta typ) {
        this.typ = typ;
    }

    public Color getColor() {
        return typ.getColor();
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

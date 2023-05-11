package org.app.agent;


import java.util.UUID;

public abstract class Agent {
    private java.util.UUID id;

    public Agent() {
        id = java.util.UUID.randomUUID();
    }

    public UUID getId() {
        return id;
    }
}

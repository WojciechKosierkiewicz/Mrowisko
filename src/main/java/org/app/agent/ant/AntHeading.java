package org.app.agent.ant;

import org.app.menager.config.Config;

public class AntHeading {

    Config settings;
    double turnangle = 0.001 * Math.PI;
    double currentangle;

    AntHeading(Config settings) {
        this.settings = settings;
        currentangle = Math.random() * (Math.PI * 2);
    }

    public double getHeadingAngle() {
        return currentangle;
    }

    public void setHeadingAngle(double angle) {
        currentangle = angle;
    }

    public void update() {
        limitpi();
        currentangle = currentangle + turnangle;
        turnangle += (Math.random() - 0.5) * settings.getAntTurnAngle();
        if (turnangle > settings.getAntTurnAngleMax()) {
            turnangle = settings.getAntTurnAngleMax();
        }
        if (turnangle < -settings.getAntTurnAngleMax()) {
            turnangle = -settings.getAntTurnAngleMax();
        }
    }

    public void limitpi() {
        if (currentangle > 2 * Math.PI) {
            currentangle = currentangle - 2 * Math.PI;
        }
        if (currentangle < 0) {
            currentangle = currentangle + 2 * Math.PI;
        }
    }

    public void bouncexwall() {
        currentangle = Math.PI - currentangle;
    }

    public void bounceywall() {
        currentangle = 2 * Math.PI - currentangle;
    }

}

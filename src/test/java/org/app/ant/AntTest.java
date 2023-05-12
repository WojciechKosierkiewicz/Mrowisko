package org.app.ant;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AntTest {

    @Test
    void testToString() {
        assertEquals("brak testu", "brak testu");
    }

    @Test
    void movement_angle_test() {
        Ant ant = new Ant();
        ant.moveAnt();
        assertEquals(1, ant.getLocx(), 0.0001);
        assertEquals(0, ant.getLocy(), 0.0001);

        ant = new Ant();
        ant.setMovement_angle(90);
        ant.moveAnt();
        assertEquals(1, ant.getLocx(), 0.0001);
        assertEquals(0, ant.getLocy(), 0.0001);
    }
}
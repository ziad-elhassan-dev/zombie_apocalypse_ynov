package actor.zombie;

import static org.junit.Assert.*;

import org.junit.Test;

import zombieapocalypse.model.actor.zombie.Abomination;

public class AbominationTest {

    @Test
    public void initAbomination() {
        Abomination abomination = new Abomination(0);
        assertEquals(3, abomination.getDamage());
        assertEquals(6, abomination.getHealthPoints());
    }
}
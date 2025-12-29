package actor.survivor;

import static org.junit.Assert.*;

import org.junit.Test;

import zombieapocalypse.model.actor.survivor.Fighter;

public class FighterTest {

    @Test
    public void initFighter() {
        Fighter fighter = new Fighter("", null);
        assertEquals(5, fighter.getHealthPoints());
        assertEquals(1, fighter.getLevel());
    }
}
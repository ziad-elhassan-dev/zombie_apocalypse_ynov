package actor.survivor;

import static org.junit.Assert.*;

import org.junit.Test;

import zombieapocalypse.model.actor.survivor.Healer;

public class HealerTest {

    @Test
    public void initHealer() {
        Healer healer = new Healer("", null);
        assertEquals(5, healer.getHealthPoints());
        assertEquals(1, healer.getLevel());
    }
}
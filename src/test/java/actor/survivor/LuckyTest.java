package actor.survivor;

import static org.junit.Assert.*;

import org.junit.Test;

import zombieapocalypse.model.actor.survivor.Lucky;

public class LuckyTest {

    @Test
    public void initLucky() {
        Lucky lucky = new Lucky("", null);
        assertEquals(5, lucky.getHealthPoints());
        assertEquals(1, lucky.getLevel());
    }
}
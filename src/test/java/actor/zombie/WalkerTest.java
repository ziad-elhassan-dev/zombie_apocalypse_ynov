package actor.zombie;

import static org.junit.Assert.*;

import org.junit.Test;

import zombieapocalypse.model.actor.zombie.Walker;

public class WalkerTest {

    @Test
    public void initWalker() {
        Walker walker = new Walker(0);
        assertEquals(1, walker.getDamage());
        assertEquals(1, walker.getHealthPoints());
    }
}
package actor.zombie;

import static org.junit.Assert.*;

import org.junit.Test;

import zombieapocalypse.model.actor.zombie.Bigboy;

public class BigboyTest {

    @Test
    public void initBigboy() {
        Bigboy bigboy = new Bigboy(0);
        assertEquals(2, bigboy.getDamage());
        assertEquals(4, bigboy.getHealthPoints());
    }
}
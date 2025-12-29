package actor.zombie;

import static org.junit.Assert.*;

import org.junit.Test;

import zombieapocalypse.model.actor.zombie.Zombie;

public class ZombieTest {

    @Test
    public void initZombie() {
        Zombie zombie = new Zombie(3, 5, "");
        assertEquals(5, zombie.getHealthPoints());
        assertEquals(3, zombie.getDamage());
    }
}

package actor.zombie;

import static org.junit.Assert.*;

import org.junit.Test;

import zombieapocalypse.model.actor.zombie.Runner;

public class RunnerTest {

    @Test
    public void initRunner() {
        Runner runner = new Runner(0);
        assertEquals(1, runner.getDamage());
        assertEquals(2, runner.getHealthPoints());
    }
}
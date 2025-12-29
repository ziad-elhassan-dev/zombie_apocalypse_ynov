package cell;

import static org.junit.Assert.*;
import zombieapocalypse.model.cell.*;
import zombieapocalypse.model.actor.survivor.Survivor;
import zombieapocalypse.model.actor.zombie.Zombie;

import org.junit.Test;

import java.util.List;


public class CellTest {

    @Test
    public void testGetName() {
        Cell cell = new CellImpl("Test Cell");
        assertEquals("Test Cell", cell.getName());
    }

    @Test
    public void testGetSurvivors() {
        Cell cell = new CellImpl("Test Cell");
        assertTrue(cell.getSurvivors().isEmpty());

        Survivor survivor = new Survivor("", null);
        cell.addSurvivor(survivor);
        List<Survivor> survivors = cell.getSurvivors();
        assertEquals(1, survivors.size());
        assertTrue(survivors.contains(survivor));
    }

    @Test
    public void testGetZombies() {
        Cell cell = new CellImpl("Test Cell");
        assertTrue(cell.getZombies().isEmpty());

        Zombie zombie = new Zombie(1, 1, "");
        cell.addZombie(zombie);
        List<Zombie> zombies = cell.getZombies();
        assertEquals(1, zombies.size());
        assertTrue(zombies.contains(zombie));
    }

    @Test
    public void testGetNoiseLevel() {
        Cell cell = new CellImpl("Test Cell");
        assertEquals(0, cell.getNoiseLevel());

        cell.increaseNoiseLevel();
        assertEquals(1, cell.getNoiseLevel());

        cell.resetNoiseLevel();
        assertEquals(0, cell.getNoiseLevel());
    }

    @Test
    public void testAddSurvivor() {
        Cell cell = new CellImpl("Test Cell");
        Survivor survivor = new Survivor("", null);
        cell.addSurvivor(survivor);
        assertTrue(cell.getSurvivors().contains(survivor));
    }

    @Test
    public void testAddZombie() {
        Cell cell = new CellImpl("Test Cell");
        Zombie zombie = new Zombie(1, 1, "");
        cell.addZombie(zombie);
        assertTrue(cell.getZombies().contains(zombie));
    }

    @Test
    public void testRemoveSurvivor() {
        Cell cell = new CellImpl("Test Cell");
        Survivor survivor = new Survivor("", null);
        cell.addSurvivor(survivor);
        assertTrue(cell.getSurvivors().contains(survivor));
        cell.removeSurvivor(survivor);
        assertFalse(cell.getSurvivors().contains(survivor));
    }

    @Test
    public void testRemoveZombie() {
        Cell cell = new CellImpl("Test Cell");
        Zombie zombie = new Zombie(1, 1, "");
        cell.addZombie(zombie);
        assertTrue(cell.getZombies().contains(zombie));
        cell.removeZombie(zombie);
        assertFalse(cell.getZombies().contains(zombie));
    }

    @Test
    public void testIncreaseNoiseLevel() {
        Cell cell = new CellImpl("Test Cell");
        assertEquals(0, cell.getNoiseLevel());
        cell.increaseNoiseLevel();
        assertEquals(1, cell.getNoiseLevel());
    }

    @Test
    public void testResetNoiseLevel() {
        Cell cell = new CellImpl("Test Cell");
        cell.increaseNoiseLevel();
        assertEquals(1, cell.getNoiseLevel());
        cell.resetNoiseLevel();
        assertEquals(0, cell.getNoiseLevel());
    }

    private static class CellImpl extends Cell {
        public CellImpl(String name) {
            super(name);
        }

        @Override
        public String toString() {
            return "Cell: " + this.name;
        }
    }
}

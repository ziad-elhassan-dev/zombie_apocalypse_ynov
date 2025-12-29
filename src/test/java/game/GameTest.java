/**package test.game;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import zombieapocalypse.actor.survivor.Survivor;
import zombieapocalypse.actor.zombie.Zombie;
import zombieapocalypse.cell.Cell;
import zombieapocalypse.cell.RoomCell;
import zombieapocalypse.cell.EmptyCell;
import zombieapocalypse.item.tool.SkeletonKey;
import zombieapocalypse.mapcreation.Map;
import zombieapocalypse.structure.Door;

public class GameTest {
    private Game game;

    @Before
    public void setUp() {
        Map map = new Map(5, 5);
        game = new Game(map, 4);
    }

    @Test
    public void testGameInitialization() {
        assertNotNull(game.getMap());
        assertNotNull(game.getSurvivors());
        assertNotNull(game.getZombies());
    }

    @Test
    public void testSpawnSurvivor() {
        game.spawnSurvivor(game.createSurvivor(1), 2, 2);
        assertEquals(1, game.getSurvivors().size());
    }

    @Test
    public void testSpawnZombie() {
        game.spawnZombie(game.createZombie(1), 2, 2);
        assertEquals(1, game.getZombies().size());
    }

    @Test
    public void testMoveZombie() {
        Zombie zombie = game.createZombie(1);
        game.spawnZombie(zombie, 2, 2);
        int[] coordinates = zombie.getCoordinates();
        int direction = 0;
        game.moveZombie(zombie, coordinates, direction);
        assertEquals(1, zombie.getCoordinates()[0]);
        assertEquals(2, zombie.getCoordinates()[1]);
    }

    @Test
    public void testMoveSurvivor() {
        Survivor survivor = game.createSurvivor(1);
        game.spawnSurvivor(survivor, 2, 2);
        int[] coordinates = survivor.getCoordinates();
        int direction = 1;
        game.moveSurvivor(survivor, coordinates, direction);
        assertEquals(2, survivor.getCoordinates()[0]);
        assertEquals(3, survivor.getCoordinates()[1]);
    }

    @Test
    public void testCanMove() {
        assertTrue(game.canMove(2, 2, 0, false));
        Cell cell = game.getMap().getCell(2, 2);
        RoomCell roomCell = new RoomCell("TestRoom");
        roomCell.setDoor(new Door(false, false), 0);
        game.getMap().setCell(2, 1, roomCell);
        assertFalse(game.canMove(2, 2, 0, false));
    }

    @Test
    public void testZombieTurn() {
        Zombie zombie = game.createZombie(1);
        game.spawnZombie(zombie, 2, 2);
        game.zombieTurn(zombie);
        int[] coordinates = zombie.getCoordinates();
        assertTrue(coordinates[0] == 1 || coordinates[0] == 3);
        assertTrue(coordinates[1] == 2 || coordinates[1] == 3);
        game.spawnSurvivor(game.createSurvivor(1), 1, 2);
        game.zombieTurn(zombie);
        assertFalse(game.getSurvivors().isEmpty());
    }

    @Test
    public void testSurvivorTurn() {
        Survivor survivor = game.createSurvivor(1);
        game.spawnSurvivor(survivor, 2, 2);
        game.survivorTurn(survivor);
        assertEquals(0, survivor.getHealthPoints());
        Zombie zombie = game.createZombie(1);
        game.spawnZombie(zombie, 2, 3);
        game.survivorTurn(survivor);
        assertTrue(zombie.getHealthPoints() < zombie.getMaxHealthPoints()); 
    }

    @Test
    public void testUseToolInHand() {
        Survivor survivor = game.createSurvivor(1);
        game.spawnSurvivor(survivor, 2, 2);
        Cell cell = new RoomCell("TestRoom");
        cell.setDoor(new Door(false, true), 0); 
        game.getMap().setCell(1, 2, cell);
        survivor.putItemInHand(new SkeletonKey());
        game.useToolInHand(survivor, cell);
        assertTrue(cell.getDoors().get(0).isOpen()); 
    }

    @Test
    public void testLookAround() {
        Survivor survivor = game.createSurvivor(1);
        game.spawnSurvivor(survivor, 2, 2);
        game.lookAround(game.getMap().getCell(2, 2), 2, 2);
    }

    @Test
    public void testInfraredGlasses() {
        Survivor survivor = game.createSurvivor(1);
        game.spawnSurvivor(survivor, 2, 2);
        game.infraredGlasses(2, 2); 
    }

    @Test
    public void testGetGlobalExperiencePoints() {
        assertEquals(0, game.getGlobalExperiencePoints());
    }

    @Test
    public void testGetInitialZombieSpawnRate() {
        assertEquals(10, game.getInitialZombieSpawnRate());
    }

    @Test
    public void testGetZombieSpawnRate() {
        assertEquals(0, game.getZombieSpawnRate());
    }

    @Test
    public void testGetDirectionString() {
        assertEquals("on top of you", game.getDirectionString(0));
        assertEquals("to your right", game.getDirectionString(1));
        assertEquals("under you", game.getDirectionString(2));
        assertEquals("to your left", game.getDirectionString(3));
    }

    @Test
    public void testGetLowestHpZombie() {
        Zombie zombie1 = new Walker(1);
        zombie1.setHealthPoints(10);
        Zombie zombie2 = new Walker(2);
        zombie2.setHealthPoints(5);
        Zombie zombie3 = new Walker(3);
        zombie3.setHealthPoints(8);
        game.spawnZombie(zombie1, 1, 1);
        game.spawnZombie(zombie2, 1, 2);
        game.spawnZombie(zombie3, 1, 3);
        Zombie lowestHpZombie = game.getLowestHpZombie(game.getZombies());
        assertEquals(zombie2, lowestHpZombie);
    }

    @Test
    public void testCreateZombie() {
        Zombie zombie = game.createZombie(1);
        assertNotNull(zombie);
    }

    @Test
    public void testCreateSurvivor() {
        Survivor survivor = game.createSurvivor(1);
        assertNotNull(survivor);
    }

    @Test
    public void testSpawnInitialZombies() {
        game.spawnInitialZombies();
        assertEquals(10, game.getZombies().size());
    }

    @Test
    public void testCheckZombieDeath() {
        
        Survivor survivor = game.createSurvivor(1);
        Zombie zombie = new Walker(1);
        zombie.setHealthPoints(0);
        game.spawnSurvivor(survivor, 2, 2);
        game.spawnZombie(zombie, 2, 3);
        game.checkZombieDeath(survivor);
        assertTrue(game.getZombies().isEmpty()); 
    }

    @Test
    public void testCheckSurvivorDeath() {
        
        Survivor survivor = new Fighter("Test Survivor", new Gun());
        Zombie zombie = game.createZombie(1);
        zombie.setHealthPoints(10);
        game.spawnSurvivor(survivor, 2, 2);
        game.spawnZombie(zombie, 2, 3);
        game.checkSurvivorDeath(zombie);
        assertTrue(game.getSurvivors().isEmpty()); 
    }

    @Test
    public void testGameLoop() {
        game.gameLoop(1); 
        game.gameLoop(5);
    }

    @Test
    public void testGetMap() {
        
        Map map = game.getMap();
        assertNotNull(map);
        assertEquals(5, map.getWidth());
        assertEquals(5, map.getHeight());
    }
}
*/
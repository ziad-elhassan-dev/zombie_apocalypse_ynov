package actor.survivor;

import static org.junit.Assert.*;

import org.junit.Test;

import zombieapocalypse.model.actor.survivor.Survivor;
import zombieapocalypse.model.item.tool.HealthPotion;

public class SurvivorTest {

    @Test
    public void initSurvivor() {
        Survivor survivor = new Survivor("", null);
        assertEquals(5, survivor.getHealthPoints());
        assertEquals(1, survivor.getLevel());
        assertEquals(0, survivor.getBackpack().size());
        assertEquals(null, survivor.getItemInHand());
    }

    @Test
    public void canPutItemInBackpack() {
        Survivor survivor = new Survivor("", null);
        HealthPotion item = new HealthPotion();
        assertEquals(0, survivor.getBackpack().size());
        survivor.addItemToBackpack(item);
        assertEquals(1, survivor.getBackpack().size());
        assertEquals("Health potion", survivor.getBackpack().get(0).getName());
    }

    @Test
    public void canPutItemInHand() {
        Survivor survivor = new Survivor("", null);
        HealthPotion item = new HealthPotion();
        assertEquals(null, survivor.getItemInHand());
        survivor.putItemInHand(item);
        assertEquals(item, survivor.getItemInHand());
        assertEquals("Health potion", survivor.getItemInHand().getName());
    }
}

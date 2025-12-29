package actor;

import static org.junit.Assert.*;

import org.junit.Test;

import zombieapocalypse.model.actor.Actor;

public class ActorTest {

    @Test
    public void initActor() {
        Actor actor = new Actor(5, "");
        assertEquals(5, actor.getHealthPoints());
    }

    @Test
    public void canAlterHealthPoints() {
        Actor actor = new Actor(5, "");
        actor.increaseHealthPoints(2);
        assertEquals(7, actor.getHealthPoints());
        actor.decreaseHealthPoints(2);
        assertEquals(5, actor.getHealthPoints());
    }
}

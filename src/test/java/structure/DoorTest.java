package structure;

import static org.junit.Assert.*;
import org.junit.Test;

import zombieapocalypse.model.structure.Door;

public class DoorTest {

    @Test
    public void DoorIsOpenTest() {
        Door door = new Door(true, false);
        assertTrue(door.getIsOpen());
    }

    @Test
    public void DoorIsClosedTest() {
        Door door = new Door(false, false);
        assertFalse(door.getIsOpen());
    }

    @Test
    public void DoorIsAtBorderTest() {
        Door door = new Door(true, true);
        assertTrue(door.getIsBorder());
    }

    @Test
    public void DoorIsNotAtBorderTest() {
        Door door = new Door(true, false);
        assertFalse(door.getIsBorder());
    }
}

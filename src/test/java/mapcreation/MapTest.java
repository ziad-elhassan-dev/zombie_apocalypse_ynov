package mapcreation;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.Test;

import zombieapocalypse.model.cell.*;
import zombieapocalypse.model.mapcreation.*;;

public class MapTest {

    @Test
    public void getWidthTest() {
        Map map = new Map(10, 8);
        assertEquals(10, map.getWidth());
    }

    @Test
    public void GetHeightTest() {
        Map map = new Map(10, 8);
        assertEquals(8, map.getHeight());
    }

    @Test
    public void CellExistanceTest() {
        Map map = new Map(5, 5);
        assertTrue(map.cellExists(3, 3));
        assertFalse(map.cellExists(6, 6));
    }

    @Test
    public void GetCoordinatesFromDirectionTest() {
        Map map = new Map(10, 10);
        int[] coords = map.getCoordinatesFromDirection(5, 5, 0); // Move up
        assertEquals(4, coords[0]);
        assertEquals(5, coords[1]);
    }

    @Test
    public void SetAndGetCellTest() {
        Map map = new Map(5, 5);
        Cell cell = new EmptyCell();
        map.setCell(2, 2, cell);
        assertEquals(cell, map.getCell(2, 2));
    }

    @Test
    public void SetAndGetMainroadsTest() {
        Map map = new Map(5, 5);
        map.setMainroads(2, 3);
        assertEquals(2, map.getMainroads()[0]);
        assertEquals(3, map.getMainroads()[1]);
    }

    @Test
    public void ResetMapNoiseLevelTest() {
        Map map = new Map(5, 5);
        Cell cell = new EmptyCell();
        map.setCell(2, 2, cell);
        cell.increaseNoiseLevel();
        map.resetMapNoiseLevel();
        assertEquals(0, cell.getNoiseLevel());
    }


}

package cell;

import static org.junit.Assert.*;
import org.junit.Test;
import zombieapocalypse.model.cell.StreetCell;

public class StreetCellTest {

    @Test
    public void StreetCellHasSewerTest() {
        StreetCell streetCell = new StreetCell("Main Street", null);
        assertFalse(streetCell.getHasSewer());
    }

    @Test
    public void SetHasSewerTest() {
        StreetCell streetCell = new StreetCell("Main Street", null);
        streetCell.setHasSewer(true);
        assertTrue(streetCell.getHasSewer());
    }
}

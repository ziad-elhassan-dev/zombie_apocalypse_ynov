package cell;

import static org.junit.Assert.assertEquals;
import org.junit.Test;

import zombieapocalypse.model.cell.EmptyCell;

public class EmptyCellTest {

    @Test
    public void testEmptyCellDescription() {
        EmptyCell emptyCell = new EmptyCell();
        assertEquals("This is an EmptyCell", emptyCell.toString());
    }

}


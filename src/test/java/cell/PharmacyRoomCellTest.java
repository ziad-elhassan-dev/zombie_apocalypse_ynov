package cell;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import zombieapocalypse.model.cell.PharmacyRoomCell;

public class PharmacyRoomCellTest {

    @Test
    public void testPharmacyRoomCellName() {
        PharmacyRoomCell pharmacyRoomCell = new PharmacyRoomCell();
        assertEquals("Pharmacy", pharmacyRoomCell.getName());
    }

}

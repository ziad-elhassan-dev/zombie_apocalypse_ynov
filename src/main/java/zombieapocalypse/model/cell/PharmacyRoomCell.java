package zombieapocalypse.model.cell;

import zombieapocalypse.model.actor.survivor.Survivor;
import zombieapocalypse.model.item.tool.HealthPotion;

/**
 * Represents a pharmacy room cell in the game, extending from RoomCell.
 */
public class PharmacyRoomCell extends RoomCell {
    /**
     * Constructs a PharmacyRoomCell with the specified name.
     */
    public PharmacyRoomCell() {
        super("Pharmacy");
    }

    @Override
    public void addSurvivor(Survivor survivor) {
        this.survivors.add(survivor);
        this.items.add(new HealthPotion());
    }
}
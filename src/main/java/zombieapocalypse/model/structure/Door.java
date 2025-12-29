package zombieapocalypse.model.structure;

/* Class representing a door */
public class Door {
    /** whether this Door is open */
    private boolean isOpen;
    /** whether this Door is at the border */
    private boolean isBorder;

    /**
     * Builds a Door
     * 
     * @param isOpen   if the door is open
     * @param isBorder if the door is at the border
     */
    public Door(boolean isOpen, boolean isBorder) {
        this.isOpen = isOpen;
        this.isBorder = isBorder;
    }

    /**
     * Returns whether the Door is at the border
     * 
     * @return true if door is at the border, false otherwise
     */
    public boolean getIsBorder() {
        return this.isBorder;
    }

    /**
     * Returns whether the Door is open or not
     * 
     * @return true if door is open, false otherwise
     */
    public boolean getIsOpen() {
        return this.isOpen;
    }

    /**
     * Opens the door.
     */
    public void openDoor() {
        this.isOpen = true;
    }
}

package zombieapocalypse.model.cell;

import zombieapocalypse.model.structure.Street;

/**
 * Represents a street cell in the game, extending from Cell.
 */
public class StreetCell extends Cell {
    /**
     * Street of this StreetCell.
     */
    private Street street;

    /**
     * Indicates if the StreetCell has a sewer.
     */
    private boolean hasSewer;

    /**
     * Constructs a StreetCell with the specified name and street, and initializes
     * hasSewer to false.
     * 
     * @param name   The name of the StreetCell
     * @param street The street of the StreetCell
     */
    public StreetCell(String name, Street street) {
        super(name);
        this.street = street;
        this.hasSewer = false;
    }

    /**
     * Returns the street of this StreetCell.
     * 
     * @return The StreetCell's street
     */
    public Street getStreet() {
        return this.street;
    }

    public void setStreet(Street street) {
        this.street = street;
    }

    /**
     * Returns whether this StreetCell has a sewer.
     * 
     * @return true if the StreetCell has a sewer, false otherwise
     */
    public boolean getHasSewer() {
        return this.hasSewer;
    }

    /**
     * Sets whether this StreetCell has a sewer.
     * 
     * @param hasSewer true to indicate the StreetCell has a sewer, false otherwise
     */
    public void setHasSewer(boolean hasSewer) {
        this.hasSewer = hasSewer;
    }

    /**
     * Returns a description of this StreetCell.
     * 
     * @return A description of this StreetCell
     */
    @Override
    public String toString() {
        return "This is a StreetCell";
    }
}
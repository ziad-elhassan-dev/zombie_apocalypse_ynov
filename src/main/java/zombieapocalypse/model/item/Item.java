package zombieapocalypse.model.item;

/**
 * Represents an abstract item in the game.
 */
public abstract class Item {
    /**
     * The name of the item.
     */
    protected String name;

    /**
     * The description of the item.
     */
    protected String description;

    /**
     * Constructs an Item with the specified name and description.
     * 
     * @param name        The name of the item
     * @param description The description of the item
     */
    public Item(String name, String description) {
        this.name = name;
        this.description = description;
    }

    /**
     * Returns the name of the item.
     * 
     * @return The name of the item
     */
    public String getName() {
        return name;
    }

    /**
     * Returns a string representation of the item's description.
     * 
     * @return The description of the item
     */
    public String toString() {
        return this.description;
    }
}
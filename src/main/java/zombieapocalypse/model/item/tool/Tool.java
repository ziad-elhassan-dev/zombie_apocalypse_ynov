package zombieapocalypse.model.item.tool;

import zombieapocalypse.model.item.Item;

/**
 * Represents a tool item in the game, extending from Item.
 */
public class Tool extends Item {
    /**
     * Constructs a Tool with the specified name and description.
     * 
     * @param name        The name of the tool
     * @param description The description of the tool
     */
    public Tool(String name, String description) {
        super(name, description);
    }
}
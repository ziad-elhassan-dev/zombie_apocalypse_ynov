package zombieapocalypse.model.actor.survivor;

import zombieapocalypse.model.item.Item;

/**
 * Represents a lucky survivor, a specialized type of Survivor, in the game.
 */
public class Lucky extends Survivor {
    /**
     * Constructs a Lucky survivor with default health points, level, and an empty
     * backpack.
     */
    public Lucky(String name, Item inHand) {
        super(name, inHand);
    }
}
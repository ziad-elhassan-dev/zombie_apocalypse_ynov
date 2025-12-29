package zombieapocalypse.model.actor.survivor;

import zombieapocalypse.model.item.Item;

/**
 * Represents a searcher, a specialized type of Survivor, in the game.
 */
public class Searcher extends Survivor {
    /**
     * Constructs a Searcher with default health points, level, and an empty
     * backpack.
     */
    public Searcher(String name, Item inHand) {
        super(name, inHand);
    }
}

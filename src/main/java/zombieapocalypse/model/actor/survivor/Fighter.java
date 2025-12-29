package zombieapocalypse.model.actor.survivor;

import java.util.Random;

import zombieapocalypse.model.item.Item;

/**
 * Represents a fighter survivor, a specialized type of Survivor, in the game.
 */
public class Fighter extends Survivor {
    /**
     * Constructs a Fighter survivor with default health points, level, and an empty
     * backpack.
     */
    public Fighter(String name, Item inHand) {
        super(name, inHand);
    }

    @Override
    public boolean throwDice(int threshold) {
        Random rand = new Random();
        int result = rand.nextInt(6) + 2;
        return (result >= threshold);
    }
}

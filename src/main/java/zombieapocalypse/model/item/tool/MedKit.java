package zombieapocalypse.model.item.tool;

import zombieapocalypse.model.actor.survivor.Survivor;

/**
 * Represents a medkit item in the game, extending from Tool.
 */
public class MedKit extends Tool {
    /**
     * Constructs a MedKit with the specified name and an empty description.
     */
    public MedKit() {
        super("MedKit", "The medkit allows a survivor to heal a survivor in the same cell as him 1 health point.");
    }

    public void heal(Survivor healer, Survivor healee) {
        healee.increaseHealthPoints(1);
        healer.putItemInHand(null);
        System.out.println(
                healer.getName() + " heals " + healee.getName() + " with medkit.");
    }
}
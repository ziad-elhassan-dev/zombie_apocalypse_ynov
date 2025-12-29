package zombieapocalypse.model.item.tool;

import zombieapocalypse.model.actor.survivor.Survivor;

/**
 * Represents a health potion item in the game, extending from Tool.
 */
public class HealthPotion extends Tool {
    /**
     * Constructs a HealthPotion with the specified name and an empty description.
     */
    public HealthPotion() {
        super("Health potion", "The heal potion allows a survivor to heal himself 1 health point.");
    }

    /**
     * Heals the specified survivor by increasing their health points by 1 and removing
     * the health potion from their hand. Prints a message indicating the healing action.
     * 
     * @param survivor The survivor to be healed.
     */
    public void heal(Survivor survivor) {
        survivor.increaseHealthPoints(1);
        survivor.putItemInHand(null);
        System.out.println(survivor.getName() + " heals himself with health potion.");
    }
}
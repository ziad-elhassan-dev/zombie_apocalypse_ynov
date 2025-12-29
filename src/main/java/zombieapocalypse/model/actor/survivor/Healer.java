package zombieapocalypse.model.actor.survivor;

import zombieapocalypse.model.item.Item;

/**
 * Represents a healer survivor, a specialized type of Survivor, in the game.
 */
public class Healer extends Survivor {
    /**
     * Constructs a Healer survivor with default health points, level, and an empty
     * backpack.
     */
    public Healer(String name, Item inHand) {
        super(name, inHand);
    }

    /**
     * Heals the specified survivor by 1 health point.
     * 
     * @param survivor The survivor to be healed
     */
    public void heal(Survivor survivor) {
        survivor.increaseHealthPoints(1);
        System.out.println(this.name + " heals " + survivor.getName() + ".");
    }

    @Override
    public int healSelf(int actionPoints) {
        heal(this);
        actionPoints--;
        return actionPoints;
    }

    @Override
    public int healSurvivor(Survivor survivor, int actionPoints) {
        heal(survivor);
        actionPoints--;
        return actionPoints;
    }

}
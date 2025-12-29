package zombieapocalypse.model.item.weapon;

import zombieapocalypse.model.item.Item;

/**
 * Represents a weapon item in the game, extending from Item.
 */
public class Weapon extends Item {
    /**
     * The number of dice rolls for the weapon.
     */
    protected int nbDiceRoll;

    /**
     * The threshold for the weapon.
     */
    protected int threshold;

    /**
     * The damage inflicted by the weapon.
     */
    protected int damage;

    /**
     * The range of the weapon.
     */
    protected int range;

    /**
     * is the item noisy or not
     */
    protected boolean noisy;

    /**
     * Constructs a Weapon with the specified attributes.
     * 
     * @param name        The name of the weapon
     * @param description The description of the weapon
     * @param nbDiceRoll  The number of dice rolls for the weapon
     * @param threshold   The threshold for the weapon
     * @param damage      The damage inflicted by the weapon
     * @param range       The range of the weapon
     */
    public Weapon(String name, String description, int nbDiceRoll, int threshold, int damage, int range,
            boolean noisy) {
        super(name, description);
        this.nbDiceRoll = nbDiceRoll;
        this.threshold = threshold;
        this.damage = damage;
        this.range = range;
        this.noisy = noisy;
    }

    /**
     * Gets the number of dice rolls for an attack.
     * 
     * @return The number of dice rolls.
     */
    public int getNbDiceRoll() {
        return this.nbDiceRoll;
    }

    /**
     * Gets the threshold value for an attack.
     * 
     * @return The threshold value.
     */
    public int getThreshold() {
        return this.threshold;
    }

    /**
     * Gets the damage value for an attack.
     * 
     * @return The damage value.
     */
    public int getDamage() {
        return this.damage;
    }

    /**
     * Checks if the attack produces noise.
     * 
     * @return True if the attack is noisy, false otherwise.
     */
    public boolean getNoisy() {
        return this.noisy;
    }
}
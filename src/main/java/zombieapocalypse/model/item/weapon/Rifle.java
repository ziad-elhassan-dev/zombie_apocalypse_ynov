package zombieapocalypse.model.item.weapon;

/**
 * Represents a rifle weapon item in the game, extending from Weapon.
 */
public class Rifle extends Weapon {
    /**
     * Constructs a Rifle with the specified attributes.
     */
    public Rifle() {
        super("Rifle", "", 2, 4, 1, 3, true);
    }
}
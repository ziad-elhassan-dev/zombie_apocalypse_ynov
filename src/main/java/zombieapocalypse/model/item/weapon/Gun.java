package zombieapocalypse.model.item.weapon;

/**
 * Represents a gun weapon item in the game, extending from Weapon.
 */
public class Gun extends Weapon {
    /**
     * Constructs a Gun with the specified attributes.
     */
    public Gun() {
        super("Gun", "", 1, 2, 1, 1, true);
    }
}
package zombieapocalypse.model.actor.zombie;

/**
 * Represents a walker zombie, a specialized type of Zombie, in the game.
 */
public class Walker extends Zombie {
    /**
     * Constructs a Walker with default damage and health points.
     */
    public Walker(int zombieID) {
        super(1, 1, "Walker#" + zombieID);
    }
}

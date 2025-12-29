package zombieapocalypse.model.actor.zombie;

/**
 * Represents a runner zombie, a specialized type of Zombie, in the game.
 */
public class Runner extends Zombie {
    /**
     * Constructs a Runner with default damage and health points.
     */
    public Runner(int zombieID) {
        super(1, 2, "Runner#" + zombieID);
    }
}
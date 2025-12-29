package zombieapocalypse.model.actor.zombie;

/**
 * Represents an abomination, a specialized type of Zombie, in the game.
 */
public class Abomination extends Zombie {
    /**
     * Constructs an Abomination with default damage and health points.
     */
    public Abomination(int zombieID) {
        super(3, 6, "Abomination#" + zombieID);
    }

    @Override
    public void decreaseHealthPoints(int nbHP) {
        if (nbHP > 1) {
            this.healthPoints = healthPoints - nbHP;
        }
    }
}
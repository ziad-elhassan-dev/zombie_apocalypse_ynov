package zombieapocalypse.model.actor.zombie;

/**
 * Represents a big boy zombie, a specialized type of Zombie, in the game.
 */
public class Bigboy extends Zombie {
    /**
     * Constructs a Bigboy with default damage and health points.
     */
    public Bigboy(int zombieID) {
        super(2, 4, "Bigboy#" + zombieID);
    }

    @Override
    public void decreaseHealthPoints(int nbHP) {
        if (nbHP > 1) {
            this.healthPoints = healthPoints - nbHP;
        }
    }
}
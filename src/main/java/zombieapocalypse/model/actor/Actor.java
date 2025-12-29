package zombieapocalypse.model.actor;

/**
 * Represents an actor in the game.
 */
public class Actor {
    /**
     * Actor's health points.
     */
    protected int healthPoints;

    protected String name;

    protected int[] coordinates;

    /**
     * Constructs an Actor with the specified health points.
     * 
     * @param healthPoints The health points of the Actor
     */
    public Actor(int healthPoints, String name) {
        this.healthPoints = healthPoints;
        this.name = name;
        this.coordinates = new int[2];
    }

    public String getName() {
        return this.name;
    }

    public int[] getCoordinates() {
        return this.coordinates;
    }

    public void setCoordinates(int i, int j) {
        this.coordinates[0] = i;
        this.coordinates[1] = j;
    }

    /**
     * Returns this Actor's health points.
     * 
     * @return The Actor's health points
     */
    public int getHealthPoints() {
        return this.healthPoints;
    }

    /**
     * 
     * Decreases Actor's health points by the given ammoutn
     * 
     * @param nbHP
     */
    public void decreaseHealthPoints(int nbHP) {
        this.healthPoints = healthPoints - nbHP;
    }

    /**
     * Increases the Actor's health points by the given amount.
     * 
     * @param nbHP The amount to alter the health points by
     */
    public void increaseHealthPoints(int nbHP) {
        this.healthPoints = healthPoints + nbHP;
    }
}
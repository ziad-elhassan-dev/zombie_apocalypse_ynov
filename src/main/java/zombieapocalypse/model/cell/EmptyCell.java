package zombieapocalypse.model.cell;

/**
 * Represents an empty cell in the game.
 */
public class EmptyCell extends Cell {
    /**
     * Constructs an EmptyCell.
     */
    public EmptyCell() {
        super("Empty");
    }

    /**
     * Returns a description of this EmptyCell.
     * 
     * @return A description of this EmptyCell
     */
    @Override
    public String toString() {
        return "This is an EmptyCell";
    }
}
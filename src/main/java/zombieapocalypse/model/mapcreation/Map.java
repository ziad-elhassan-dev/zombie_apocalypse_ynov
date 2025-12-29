package zombieapocalypse.model.mapcreation;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import zombieapocalypse.model.cell.*;
import zombieapocalypse.model.structure.Door;
import zombieapocalypse.view.style.PimpStyle;

/**
 * Represents a map in the game.
 */
public class Map {
    /**
     * The height of this Map.
     */
    private int height;

    /**
     * The width of this Map.
     */
    private int width;

    /**
     * The cells of this Map.
     */
    private Cell[][] cells;

    /**
     * The main roads of this Map.
     */
    private int[] mainroads;

    private List<Cell> sewerCells;

    /**
     * Constructs a Map with the specified width and height.
     * 
     * @param width  The width of this Map
     * @param height The height of this Map
     */
    public Map(int width, int height) {
        this.height = height;
        this.width = width;
        this.mainroads = new int[2];
        this.sewerCells = new ArrayList<>();
        this.cells = new Cell[width][height];
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                this.cells[i][j] = new EmptyCell();
            }
        }
    }

    /**
     * Returns this Map's height
     * 
     * @return Map's height
     */
    public int getHeight() {
        return this.height;
    }

    /**
     * Returns this Map's width
     * 
     * @return Map's width
     */
    public int getWidth() {
        return this.width;
    }

    /**
     * Returns specified Cell of this Map
     * 
     * @param x x coordinate of the Cell
     * @param y y coordinate of the Cell
     */
    public Cell getCell(int x, int y) {
        return this.cells[x][y];
    }

    /**
     * Sets specified Cell of this Map
     * 
     * @param x    x coordinate of the Cell
     * @param y    y coordinate of the Cell
     * @param cell cell to replace with
     */
    public void setCell(int x, int y, Cell cell) {
        this.cells[x][y] = cell;
    }

    /**
     * Returns the main roads of this Map.
     * 
     * @return The main roads of this Map
     */
    public int[] getMainroads() {
        return this.mainroads;
    }

    /**
     * Sets the main roads of this Map to the specified coordinates.
     * 
     * @param x The x-coordinate of the main road
     * @param y The y-coordinate of the main road
     */
    public void setMainroads(int x, int y) {
        this.mainroads[0] = x;
        this.mainroads[1] = y;
    }

    public void addSewerCell(Cell cell) {
        this.sewerCells.add(cell);
    }

    public Cell getSewerCell(int index) {
        return this.sewerCells.get(index);
    }

    /**
     * Returns symbol depending on type of Cell
     * 
     * @return symbol of the Cell
     */
    public String getCellSymbol(Cell cell) {
        if (cell instanceof EmptyCell) {
            return "·";
        } else if (cell instanceof PharmacyRoomCell) {
            return PimpStyle.BOLD + "+";
        } else if (cell instanceof HotelRoomCell) {
            return PimpStyle.BOLD + "C";
        } else if (cell instanceof StreetCell) {
            if (((StreetCell) cell).getStreet().getIsMainroads()) {
                return PimpStyle.BOLD + "S";
            }
            return "s";
        } else {
            return "R";
        }
    }

    /**
     * Returns color depending on type of Cell
     * 
     * @return color of the Cell
     */
    public String getCellColor(Cell cell) {
        if (cell instanceof StreetCell) {
            if (((StreetCell) cell).getHasSewer()) {
                return PimpStyle.YELLOW;
            }
            return PimpStyle.BLUE;
        } else if (cell instanceof PharmacyRoomCell) {
            return PimpStyle.GREEN;
        } else if (cell instanceof HotelRoomCell) {
            return PimpStyle.YELLOW;
        } else if (cell instanceof RoomCell) {
            return PimpStyle.RED;
        }
        return "";
    }

    /**
     * Returns the color of the door at the specified position.
     * 
     * @param i    The x-coordinate of the Cell
     * @param j    The y-coordinate of the Cell
     * @param side The side of the door
     * @return The color of the door
     */
    public String getDoorColor(int i, int j, int side) {
        Cell cell = this.cells[i][j];
        if (cell instanceof RoomCell) {
            RoomCell room = (RoomCell) cell;
            List<Door> doors = room.getDoors();
            if (!doors.get(side).getIsBorder()) {
                return PimpStyle.CYAN;
            }
        } else if (side == 2) {
            if (i + 1 != this.width) {
                Cell c = this.cells[i + 1][j];
                if (c instanceof RoomCell) {
                    return PimpStyle.CYAN;
                }
            }
        } else if (side == 1) {
            if (j + 1 != this.height) {
                Cell c = this.cells[i][j + 1];
                if (c instanceof RoomCell) {
                    return PimpStyle.CYAN;
                }
            }
        }
        return "";
    }

    /**
     * Returns the number of survivors in the Cell at the specified position.
     * 
     * @param i The x-coordinate of the Cell
     * @param j The y-coordinate of the Cell
     * @return The number of survivors
     */
    public String getCellNbSurvivor(int i, int j) {
        Cell cell = this.cells[i][j];
        int nb = cell.getSurvivors().size();
        return nb > 0 ? Integer.toString(nb) : " ";
    }

    /**
     * Returns the number of zombies in the Cell at the specified position.
     * 
     * @param i The x-coordinate of the Cell
     * @param j The y-coordinate of the Cell
     * @return The number of zombies
     */
    public String getCellNbZombie(int i, int j) {
        Cell cell = this.cells[i][j];
        int nb = cell.getZombies().size();
        return nb > 0 ? Integer.toString(nb) : " ";
    }

    /**
     * Prints out this Map
     */
    public void showMap() {
        for (int j = 0; j < this.height; j++) {
            System.out.print("______");
        }
        System.out.println();
        for (int i = 0; i < this.width; i++) {
            for (int j = 0; j < this.height; j++) {
                System.out.print("|" + getCellNbZombie(i, j) + "   " + getCellNbSurvivor(i, j));
            }
            System.out.print("|");
            System.out.println();
            System.out.print("|");
            for (int j = 0; j < this.height; j++) {
                System.out.print("  " + getCellColor(this.cells[i][j]) + getCellSymbol(this.cells[i][j])
                        + PimpStyle.RESET + getDoorColor(i, j, 1) + "  |" + PimpStyle.RESET);
            }
            System.out.println();
            for (int j = 0; j < this.height; j++) {
                System.out.print("|__" + getDoorColor(i, j, 2) + "_" + PimpStyle.RESET + "__");
            }
            System.out.print("|");
            System.out.println();
        }
    }

    /**
     * Checks if a cell exists at the specified coordinates.
     * 
     * @param i The x-coordinate of the cell.
     * @param j The y-coordinate of the cell.
     * @return True if the cell exists within the map boundaries, false otherwise.
     */
    public boolean cellExists(int i, int j) {
        if (i >= 0 && i < this.width && j >= 0 && j < this.height) {
            return true;
        }
        return false;
    }

    /**
     * Computes the new coordinates based on the given direction from the current coordinates.
     * 
     * @param i The x-coordinate of the starting point.
     * @param j The y-coordinate of the starting point.
     * @param direction The direction in which to move (0 for up, 1 for right, 2 for down, 3 for left).
     * @return An array containing the new coordinates after moving in the specified direction.
     */
    public int[] getCoordinatesFromDirection(int i, int j, int direction) {
        if (direction == 0) {
            i -= 1;
        } else if (direction == 1) {
            j += 1;
        } else if (direction == 2) {
            i += 1;
        } else if (direction == 3) {
            j -= 1;
        }
        return new int[] { i, j };
    }

    /**
     * Generates random coordinates within the map's dimensions.
     * 
     * @return An array containing random x and y coordinates within the map's boundaries.
     */
    public int[] getRandomCellCoordinates() {
        Random random = new Random();
        int i = random.nextInt(this.width);
        int j = random.nextInt(this.height);
        return new int[] { i, j };
    }

    /**
     * Calculates the distance between two points in a two-dimensional space.
     * 
     * @param x1 The x-coordinate of the first point.
     * @param y1 The y-coordinate of the first point.
     * @param x2 The x-coordinate of the second point.
     * @param y2 The y-coordinate of the second point.
     * @return The distance between the two points.
     */
    public static double distanceBetweenPoints(int x1, int y1, int x2, int y2) {
        int deltaX = x2 - x1;
        int deltaY = y2 - y1;
        return Math.sqrt(deltaX * deltaX + deltaY * deltaY);
    }

    /**
     * Checks if there is a closed door between two cells in a specified direction.
     * 
     * @param cell        The cell from which to check for a door.
     * @param targetCell  The target cell towards which to check for a door.
     * @param direction   The direction in which to check for a door (0: up, 1: right, 2: down, 3: left).
     * @return True if there is a closed door between the cells in the specified direction, false otherwise.
     */
    public boolean hasClosedDoorBetween(Cell cell, Cell targetCell, int direction) {
        if (cell instanceof RoomCell) {
            RoomCell room = (RoomCell) cell;
            List<Door> doors = room.getDoors();
            Door door = doors.get(direction);
            return !door.getIsOpen();
        } else if (targetCell instanceof RoomCell) {
            RoomCell room = (RoomCell) targetCell;
            List<Door> doors = room.getDoors();
            direction = (direction + 2) % 4;
            Door door = doors.get(direction);
            return !door.getIsOpen();
        }
        return false;
    }

    /**
     * resets noiseLevel for every Cell in the map
     */
    public void resetMapNoiseLevel() {
        for (int i = 0; i < this.width; i++) {
            for (int j = 0; j < this.height; j++) {
                getCell(i, j).resetNoiseLevel();
            }
        }
    }
}

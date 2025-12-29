package zombieapocalypse.controller.game;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

import zombieapocalypse.model.cell.Cell;
import zombieapocalypse.model.cell.EmptyCell;
import zombieapocalypse.model.cell.HotelRoomCell;
import zombieapocalypse.model.cell.RoomCell;
import zombieapocalypse.model.cell.StreetCell;
import zombieapocalypse.model.actor.survivor.Fighter;
import zombieapocalypse.model.actor.survivor.Healer;
import zombieapocalypse.model.actor.survivor.Lucky;
import zombieapocalypse.model.actor.survivor.Searcher;
import zombieapocalypse.model.actor.survivor.Survivor;
import zombieapocalypse.model.actor.zombie.Abomination;
import zombieapocalypse.model.actor.zombie.Bigboy;
import zombieapocalypse.model.actor.zombie.Runner;
import zombieapocalypse.model.actor.zombie.Walker;
import zombieapocalypse.model.actor.zombie.Zombie;
import zombieapocalypse.model.item.Item;
import zombieapocalypse.model.item.tool.HandheldMap;
import zombieapocalypse.model.item.tool.InfraredGlasses;
import zombieapocalypse.model.item.tool.SkeletonKey;
import zombieapocalypse.model.item.weapon.Gun;
import zombieapocalypse.model.mapcreation.Map;

/**
 * Represents the game engine.
 */
public class Game {
    /**
     * the map
     */
    protected Map map;

    /**
     * List of survivors in the Game.
     */
    protected List<Survivor> survivors;

    /**
     * List of zombies in the Game.
     */
    protected List<Zombie> zombies;

    protected int zombieID;

    /**
     * Constructs a Game with the specified map.
     * 
     * @param map The map of the game
     */
    public Game(Map map, int NbOfSurvivors) {
        this.map = map;
        this.survivors = new ArrayList<>();
        this.zombies = new ArrayList<>();
        spawnSurvivors(NbOfSurvivors);
        spawnInitialZombies();
    }

    /**
     * Spawns a survivor at the specified coordinates.
     * 
     * @param survivor The survivor to spawn
     * @param i        The x-coordinate
     * @param j        The y-coordinate
     */
    public void spawnSurvivor(Survivor survivor, int i, int j) {
        this.survivors.add(survivor);
        this.map.getCell(i, j).addSurvivor(survivor);
        survivor.setCoordinates(i, j);
    }

    /**
     * Spawns a zombie at the specified coordinates.
     * 
     * @param zombie The zombie to spawn
     * @param i      The x-coordinate
     * @param j      The y-coordinate
     */
    public void spawnZombie(Zombie zombie, int i, int j) {
        this.zombies.add(zombie);
        this.map.getCell(i, j).addZombie(zombie);
        zombie.setCoordinates(i, j);
    }

    /**
     * Moves the specified zombie to new coordinates based on the given direction.
     * 
     * @param zombie       The zombie to move
     * @param coordinates  The current coordinates of the zombie
     * @param direction    The direction to move the zombie (0 for up, 1 for right, 2 for down, 3 for left)
     */
    public void moveZombie(Zombie zombie, int[] coordinates, int direction) {
        int[] newCoordinates = this.map.getCoordinatesFromDirection(coordinates[0], coordinates[1], direction);
        Cell cell = this.map.getCell(coordinates[0], coordinates[1]);

        cell.removeZombie(zombie);
        cell = this.map.getCell(newCoordinates[0], newCoordinates[1]);
        cell.addZombie(zombie);
        zombie.setCoordinates(newCoordinates[0], newCoordinates[1]);
    }

    /**
     * Moves the specified survivor to new coordinates based on the given direction.
     * 
     * @param survivor     The survivor to move
     * @param coordinates  The current coordinates of the survivor
     * @param direction    The direction to move the survivor (0 for up, 1 for right, 2 for down, 3 for left)
     */
    public void moveSurvivor(Survivor survivor, int[] coordinates, int direction) {
        int[] newCoordinates = this.map.getCoordinatesFromDirection(coordinates[0], coordinates[1], direction);
        Cell cell = this.map.getCell(coordinates[0], coordinates[1]);

        cell.removeSurvivor(survivor);
        cell = this.map.getCell(newCoordinates[0], newCoordinates[1]);
        cell.addSurvivor(survivor);
        survivor.setCoordinates(newCoordinates[0], newCoordinates[1]);
    }

    /**
     * Checks if a move is possible from the specified coordinates in a certain direction.
     * 
     * @param i            The x-coordinate
     * @param j            The y-coordinate
     * @param direction    The direction of movement (0 for up, 1 for right, 2 for down, 3 for left)
     * @param lookAround   Indicates whether the survivor should look around to detect obstacles
     * @return             true if the move is possible, false otherwise
     */
    public boolean canMove(int i, int j, int direction, boolean lookAround) {
        Cell cell = this.map.getCell(i, j);
        Cell targetCell = new EmptyCell();
        String doorDirection = getDirectionString(direction);
        int[] coordinates = this.map.getCoordinatesFromDirection(i, j, direction);

        if (this.map.cellExists(coordinates[0], coordinates[1])) {
            targetCell = this.map.getCell(coordinates[0], coordinates[1]);
        }

        if (targetCell instanceof EmptyCell) {
            return false;
        } else if (this.map.hasClosedDoorBetween(cell, targetCell, direction)) {
            if (lookAround) {
                System.out.println("There is a closed door " + doorDirection + ".");
            }
            return false;
        } else {
            return true;
        }
    }

    /**
     * Handles a zombie's turn by deciding its action (move or attack).
     * 
     * @param zombie    The zombie whose turn it is
     */
    public void zombieTurn(Zombie zombie) {
        int[] coordinates = zombie.getCoordinates();
        Cell cell = this.map.getCell(coordinates[0], coordinates[1]);
        Random rand = new Random();
        if (cell.getSurvivors().isEmpty()) {
            int direction = rand.nextInt(4);
            while (true) {
                if (canMove(coordinates[0], coordinates[1], direction, false)) {
                    moveZombie(zombie, coordinates, direction);
                    System.out.println(zombie.getName() + " moves.");
                    break;
                }
                direction = rand.nextInt(4);
            }
        } else {
            List<Survivor> survivors = cell.getSurvivors();
            Survivor survivor = survivors.get(rand.nextInt(survivors.size()));
            zombie.attackSurvivor(survivor);
        }
    }

    /**
     * Returns the zombie with the lowest health points among the specified ones.
     * 
     * @param zombies    The list of zombies to search among
     * @return           The zombie with the lowest health points, or null if none
     */
    public Zombie getLowestHpZombie(List<Zombie> zombies) {
        Zombie zombie = null;
        int lowestHp = Integer.MAX_VALUE;

        for (Zombie currentZombie : zombies) {
            int hp = currentZombie.getHealthPoints();
            if (hp < lowestHp) {
                lowestHp = hp;
                zombie = currentZombie;
            }
        }
        return zombie;
    }

    /**
     * Handles a survivor's turn by performing various actions depending on the situation.
     * 
     * @param survivor    The survivor whose turn it is
     */
    public void survivorTurn(Survivor survivor) {
        int[] coordinates = survivor.getCoordinates();
        Cell cell = this.map.getCell(coordinates[0], coordinates[1]);
        int actionPoints = survivor.getActionPoints();

        System.out.println(survivor.getName() + " looks around himself.");
        lookAround(cell, coordinates[0], coordinates[1]);

        List<Survivor> survivorsInCell = cell.getSurvivors();
        List<Zombie> zombiesInCell = cell.getZombies();
        List<Survivor> survivorsInCellCopy = new ArrayList<>(survivorsInCell);
        survivorsInCellCopy.remove(survivor);

        if ((survivor instanceof Searcher) && (cell instanceof RoomCell)) {
            RoomCell room = (RoomCell) cell;
            System.out.println(survivor.getName() + " searches the room.");
            survivor.searchRoom(room);
        }

        if (!zombiesInCell.isEmpty()) {
            Zombie targetZombie = getLowestHpZombie(zombiesInCell);
            actionPoints = survivor.attackZombie(targetZombie, actionPoints);
        } else {
            survivor.makeNoise(cell);
            actionPoints--;
        }

        if (survivor.getHealthPoints() < 3) {
            actionPoints = survivor.healSelf(actionPoints);
        }

        if (!survivorsInCell.isEmpty()) {
            Iterator<Survivor> iterator = survivorsInCellCopy.iterator();

            while (iterator.hasNext()) {
                Survivor survivorInCell = iterator.next();
                if (survivorInCell.getHealthPoints() < 3) {
                    actionPoints = survivor.healSurvivor(survivorInCell, actionPoints);
                    break;
                }
            }
        }

        if (zombiesInCell.isEmpty() || actionPoints > 0) {
            Random rand = new Random();
            int decision = rand.nextInt(2);

            if ((cell instanceof RoomCell) && (decision == 0) && !(survivor instanceof Searcher)) {
                RoomCell room = (RoomCell) cell;
                System.out.println(survivor.getName() + " searches the room.");
                survivor.searchRoom(room);
                actionPoints--;
            } else {
                while (true) {
                    int direction = rand.nextInt(4);
                    int[] coordinatesTarget = this.map.getCoordinatesFromDirection(coordinates[0], coordinates[1],
                            direction);

                    if (this.map.cellExists(coordinatesTarget[0], coordinatesTarget[1])) {
                        Cell targetCell = this.map.getCell(coordinatesTarget[0], coordinatesTarget[1]);
                        if (this.map.hasClosedDoorBetween(cell, targetCell, direction)) {
                            if (survivor.hasDoorItemInHand()) {
                                Item inHand = survivor.getItemInHand();
                                if (inHand instanceof SkeletonKey) {
                                    survivor.putItemInHand(null);
                                } else {
                                    cell.increaseNoiseLevel();
                                }
                                System.out.println(survivor.getName() + " opens door with " + inHand.getName() + ".");
                                actionPoints--;
                                if (actionPoints > 0) {
                                    moveSurvivor(survivor, coordinates, direction);
                                    actionPoints--;
                                }
                            }
                            break;
                        } else {
                            moveSurvivor(survivor, coordinates, direction);
                            actionPoints--;
                            break;
                        }
                    }
                }
                System.out.println(survivor.getName() + " moves.");
            }
        }
    }

    /**
     * Provides information about the surrounding environment to the survivor.
     * 
     * @param cell  The current cell where the survivor is located
     * @param i     The x-coordinate of the survivor
     * @param j     The y-coordinate of the survivor
     */
    public void lookAround(Cell cell, int i, int j) {
        if (cell instanceof StreetCell) {
            System.out.println("You are in the streets.");
        } else if (cell instanceof RoomCell) {
            System.out.println("You are in a room.");
        } else if (cell instanceof HotelRoomCell) {
            System.out.println("You are at the Continental”.");
            System.out.println("You can't see who is in this cell”.");
        } else {
            System.out.println("You are at the Pharmacy.");
        }

        canMove(i, j, 0, true);
        canMove(i, j, 1, true);
        canMove(i, j, 2, true);
        canMove(i, j, 3, true);

        if (!(cell instanceof HotelRoomCell)) {
            System.out.println("There are " + cell.getSurvivors().size() + " survivors in this cell including you.");
            System.out.println("There are " + cell.getZombies().size() + " zombies in this cell.");
        }
    }

    /**
     * Uses the item currently held by the survivor.
     * 
     * @param survivor The survivor using the tool
     * @param cell     The current cell where the survivor is located
     */
    public void useToolInHand(Survivor survivor, Cell cell) {
        Item itemInHand = survivor.getItemInHand();

        if (itemInHand instanceof HandheldMap) {
            System.out.println(survivor.getName() + " uses handheld map.");
            this.map.showMap();
            cell.increaseNoiseLevel();
            survivor.putItemInHand(null);
        } else if (itemInHand instanceof InfraredGlasses) {
            int[] coordinates = survivor.getCoordinates();
            System.out.println(survivor.getName() + " uses infrared glasses.");
            infraredGlasses(coordinates[0], coordinates[1]);
        }
    }

    /**
     * Uses infrared glasses to inspect neighboring cells for survivors and zombies.
     * 
     * @param i The x-coordinate of the survivor using the infrared glasses
     * @param j The y-coordinate of the survivor using the infrared glasses
     */
    public void infraredGlasses(int i, int j) {
        for (int direction = 0; i < 4; i++) {
            int[] coordinates = this.map.getCoordinatesFromDirection(i, j, direction);
            if (this.map.cellExists(coordinates[0], coordinates[1])) {
                Cell cell = this.map.getCell(coordinates[0], coordinates[1]);
                System.out.println("The cell " + getDirectionString(direction) + " has " + cell.getSurvivors().size()
                        + " survivors and " + cell.getZombies().size() + " zombies.");
            } else {
                System.out.println("There is no cell " + getDirectionString(direction) + ".");
            }
        }
    }

    /**
     * Calculates the total experience points accumulated by all survivors.
     * 
     * @return The total experience points of all survivors combined
     */
    public int getGlobalExperiencePoints() {
        int globalExperiencePoints = 0;
        Iterator<Survivor> iterator = this.survivors.iterator();

        while (iterator.hasNext()) {
            Survivor survivor = iterator.next();
            globalExperiencePoints += survivor.getExperiencePoints();
        }
        return globalExperiencePoints;
    }

    /**
     * Calculates the initial zombie spawn rate based on the dimensions of the map.
     * 
     * @return The initial zombie spawn rate
     */
    public int getInitialZombieSpawnRate() {
        return this.map.getHeight() + this.map.getWidth();
    }

    /**
     * Calculates the current zombie spawn rate based on the global experience points and the number of survivors.
     * 
     * @return The current zombie spawn rate
     */
    public int getZombieSpawnRate() {
        return (int) Math.ceil((double) (getGlobalExperiencePoints() / this.survivors.size()) / 3);
    }

    /**
     * Returns a string representation of the direction based on the provided integer.
     *
     * @param direction The integer representing the direction (0 for up, 1 for right, 2 for down, 3 for left)
     * @return The string representation of the direction
     */
    public String getDirectionString(int direction) {
        if (direction == 0) {
            return "on top of you";
        } else if (direction == 1) {
            return "to your right";
        } else if (direction == 2) {
            return "under you";
        } else {
            return "to your left";
        }
    }

    /**
     * Creates and returns a new Zombie based on the provided zombie ID.
     *
     * @param zombieID The ID of the zombie
     * @return A new Zombie
     */
    public Zombie createZombie(int zombieID) {
        int id = zombieID % 4;

        if (id == 0) {
            return new Abomination(zombieID);
        } else if (id == 1) {
            return new Bigboy(zombieID);
        } else if (id == 2) {
            return new Runner(zombieID);
        } else {
            return new Walker(zombieID);
        }
    }

    /**
     * Creates and returns a new Survivor based on the provided survivor ID.
     *
     * @param survivorID The ID of the survivor
     * @return A new Survivor
     */
    public Survivor createSurvivor(int survivorID) {
        int id = zombieID % 4;
        String name = "Player#" + survivorID;
        Gun gun = new Gun();

        if (id == 0) {
            return new Fighter(name, gun);
        } else if (id == 1) {
            return new Healer(name, gun);
        } else if (id == 2) {
            return new Lucky(name, gun);
        } else {
            return new Searcher(name, gun);
        }
    }

    /**
     * Spawns the specified number of survivors on the map.
     *
     * @param numberOfSurvivors The number of survivors to spawn
     */
    public void spawnSurvivors(int numberOfSurvivors) {
        int[] coordinates = this.map.getMainroads();

        for (int i = 0; i < numberOfSurvivors; i++) {
            Survivor survivor = createSurvivor(i);
            spawnSurvivor(survivor, coordinates[0], coordinates[1]);
        }
    }

    /**
     * Spawns the initial zombies on the map based on the initial spawn rate.
     */
    public void spawnInitialZombies() {
        int spawnRate = getInitialZombieSpawnRate();

        while (spawnRate != 0) {
            int[] coordinates = this.map.getRandomCellCoordinates();
            Cell cell = this.map.getCell(coordinates[0], coordinates[1]);

            if (cell instanceof StreetCell) {
                Zombie zombie = createZombie(this.zombieID);
                spawnZombie(zombie, coordinates[0], coordinates[1]);
                this.zombieID += 1;
                spawnRate -= 1;
            }
        }
    }

    /**
     * Checks if any zombie is dead and removes it from the map if so.
     * Also increases the experience points of the survivor who killed the zombie.
     *
     * @param survivor The survivor who may have killed the zombie.
     */
    public void checkZombieDeath(Survivor survivor) {
        List<Zombie> zombiesToRemove = new ArrayList<>();

        Iterator<Zombie> iterator = this.zombies.iterator();

        while (iterator.hasNext()) {
            Zombie zombie = iterator.next();
            if (zombie.getHealthPoints() <= 0) {
                System.out.println(survivor.getName() + " kills " + zombie.getName() + ".");
                int[] coordinates = zombie.getCoordinates();
                Cell cell = this.map.getCell(coordinates[0], coordinates[1]);
                cell.removeZombie(zombie);
                zombiesToRemove.add(zombie);
                survivor.increaseExperiencePoints();
            }
        }
        this.zombies.removeAll(zombiesToRemove);
    }

    /**
     * Checks if any survivor is dead and removes it from the map if so.
     * Additionally, if a survivor dies in a room, their items are dropped into the room.
     *
     * @param zombie The zombie that may have killed the survivor.
     */
    public void checkSurvivorDeath(Zombie zombie) {
        List<Survivor> survivorsToRemove = new ArrayList<>();

        Iterator<Survivor> iterator = this.survivors.iterator();

        while (iterator.hasNext()) {
            Survivor survivor = iterator.next();
            if (survivor.getHealthPoints() <= 0) {
                System.out.println(zombie.getName() + " kills " + survivor.getName() + ".");
                int[] coordinates = survivor.getCoordinates();
                Cell cell = this.map.getCell(coordinates[0], coordinates[1]);
                cell.removeSurvivor(survivor);

                if (cell instanceof RoomCell) {
                    RoomCell room = (RoomCell) cell;
                    Item item = survivor.getItemInHand();
                    if (item != null) {
                        room.addItem(item);
                    }
                    room.addAllItems(survivor.getBackpack());
                }
                survivorsToRemove.add(survivor);
            }
        }
        this.survivors.removeAll(survivorsToRemove);
    }

    /**
     * The main game loop that orchestrates the turns of survivors and zombies until
     * one of the game-ending conditions is met: all survivors are dead, all zombies
     * are dead, the survivors have gained enough experience points, or a maximum
     * number of rounds has been reached.
     */
    public void gameLoop() {
        int round = 1;
        while (getGlobalExperiencePoints() < 30 && !this.zombies.isEmpty() && !this.survivors.isEmpty() && round < 50) {
            System.out.println("********** Round " + round + " **********");
            System.out.println("");
            Iterator<Survivor> iterator = this.survivors.iterator();

            while (iterator.hasNext()) {
                Survivor survivor = iterator.next();
                System.out.println("Turn of " + survivor.getName() + ":");
                survivorTurn(survivor);
                checkZombieDeath(survivor);
                System.out.println("");
            }
            System.out.println("");

            Iterator<Zombie> iterator2 = this.zombies.iterator();

            while (iterator2.hasNext()) {
                Zombie zombie = iterator2.next();
                zombieTurn(zombie);
                checkSurvivorDeath(zombie);
            }
            System.out.println("");

            this.map.showMap();
            this.map.resetMapNoiseLevel();
            round++;
        }
        System.out.println("");

        if (this.survivors.isEmpty()) {
            System.out.println("The survivors lost.");
        } else {
            System.out.println("The survivors won.");
        }
    }

    /**
     * Returns the map of the game.
     * 
     * @return The map of the game
     */
    public Map getMap() {
        return this.map;
    }
}
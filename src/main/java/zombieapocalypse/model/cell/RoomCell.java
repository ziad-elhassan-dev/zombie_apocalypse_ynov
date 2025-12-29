package zombieapocalypse.model.cell;

import java.util.ArrayList;
import java.util.List;

import zombieapocalypse.model.item.Item;
import zombieapocalypse.model.item.tool.HealthPotion;
import zombieapocalypse.model.item.tool.MedKit;
import zombieapocalypse.model.item.tool.SkeletonKey;
import zombieapocalypse.model.item.weapon.Axe;
import zombieapocalypse.model.item.weapon.Gun;
import zombieapocalypse.model.item.weapon.Rifle;
import zombieapocalypse.model.structure.Door;

import java.util.Random;

/**
 * Represents a room cell in the game, extending from Cell.
 */
public class RoomCell extends Cell {

    protected List<Item> items;

    /**
     * List of doors in this RoomCell.
     * 0 -> top Door
     * 1 -> right Door
     * 2 -> bottom Door
     * 3 -> left Door
     */
    protected List<Door> doors;

    /**
     * Constructs a RoomCell with the specified name and initializes doors with
     * default values.
     * 
     * @param name The name of the RoomCell
     */
    public RoomCell(String name) {
        super(name);
        this.items = generateItems();
        this.doors = new ArrayList<>();
        for (int i = 0; i < 4; i++) {
            this.doors.add(new Door(false, true));
        }
    }

    /**
     * Adds an item to the list of items in this room cell.
     * 
     * @param item the item to add.
     */
    public void addItem(Item item) {
        this.items.add(item);
    }

    /**
     * Adds all items from the provided list to the list of items in this room cell.
     * 
     * @param itemsToAdd the list of items to add.
     */
    public void addAllItems(List<Item> itemsToAdd) {
        this.items.addAll(itemsToAdd);
    }

    /**
     * Retrieves the list of items in this room cell.
     * 
     * @return the list of items in this room cell.
     */
    public List<Item> getItems() {
        return this.items;
    }

    /**
     * Removes the specified item from the list of items in this room cell.
     * 
     * @param item the item to remove.
     */
    public void removeItem(Item item) {
        this.items.remove(item);
    }

    /**
     * Sets a Door to this RoomCell at the specified index.
     * 
     * @param door  The Door to replace with
     * @param index The index to put in
     */
    public void setDoor(Door door, int index) {
        this.doors.set(index, door);
    }

    /**
     * Returns this RoomCell's doors.
     * 
     * @return The RoomCell's doors
     */
    public List<Door> getDoors() {
        return this.doors;
    }

    public void openDoor(int index) {
        Door door = this.doors.get(index);
        door.openDoor();
    }

    /**
     * Returns a description of this RoomCell.
     * 
     * @return A description of this RoomCell
     */
    @Override
    public String toString() {
        return "This is a RoomCell";
    }

    /**
     * Randomly generates items for this RoomCell
     */
    private List<Item> generateItems() {
        List<Item> items = new ArrayList<>();
        Random rand = new Random();
        int numberOfItems = rand.nextInt(3) + 1;

        for (int i = 0; i < numberOfItems; i++) {
            Item item = null;
            int itemType = rand.nextInt(6);

            switch (itemType) {
                case 0:
                    item = new HealthPotion();
                    break;
                case 1:
                    item = new MedKit();
                    break;
                case 2:
                    item = new SkeletonKey();
                    break;
                case 3:
                    item = new Axe();
                    break;
                case 4:
                    item = new Gun();
                    break;
                case 5:
                    item = new Rifle();
                    break;
            }

            items.add(item);
        }

        return items;
    }
}
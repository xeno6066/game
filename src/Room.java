import java.util.ArrayList;
import java.util.HashMap;

public class Room
{
    private String description;
    private HashMap<String, Room> exits;
    private ArrayList<Item> items = new ArrayList<>();
    RoomType type ;

    public Room(String description) 
    {
        exits = new HashMap<>();
        this.description = description;
    }

    public void addItem(Item item) {
        if (item!=null) items.add(item);
    }

    public boolean hasItem(String name) {
        for(Item i : items) {
            if (i.getName().equals(name)) return true;
        }
        return false;
    }

    public Item getItem(String name) {
        Item foundItem = null;
        for(Item i : items) {
            if (i.getName().equals(name)) {
                foundItem = i;
                break;
            }
        }
        if (foundItem!=null && foundItem.isMoveable()) items.remove(foundItem);
        return foundItem;
    }

    public void setExit(String direction, Room room) {
        if (room!=null) exits.put(direction, room);
    }
    public Room getExit(String direction) {
        return exits.get(direction);
    }

    public String getDescription()
    {
        return description;
    }

    public String getExitString() {
        String returnString = "Exits: ";
        for(String direction : exits.keySet()) {
            returnString += direction + " ";
        }
        return returnString;
    }

    public String getLongDescription() {
        String returnString = getDescription() + ". It contains:" + System.lineSeparator();
        for(Item i : items) {
            returnString += "  " + i.getLongDescription() + System.lineSeparator();
        }
        return returnString + getExitString();
    }

}

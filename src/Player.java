import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

public class Player {
    public static final int ITEM_GONE = 0;
    public static final int ITEM_NOTPRESENT = 1;
    public static final int ITEM_NOTMOVEABLE = 2;
    public static final int ITEM_DROPPED = 3;

    public static final int ITEM_DESCRIPTION= 4;
    private String name;
    private Room currentRoom;
    private ArrayList<Item> bag = new ArrayList<>();
    private Item key ;

    public Player(String name , Item key) {
        this.name = name;
        this.key = key ;
    }

    public int take(String name) {
        Item item = currentRoom.getItem(name);
        if (item!=null && item.isMoveable()) {
            bag.add(item);
            return ITEM_GONE;
        }
        if (item==null) return ITEM_NOTPRESENT;
        return ITEM_NOTMOVEABLE;
    }

    public String getName() {
        return name;
    }

    public Room getCurrentRoom() {
        return currentRoom;
    }

    public void setCurrentRoom(Room currentRoom) {
        int key1 = Collections.frequency(bag , key);
        this.currentRoom = currentRoom;

        if(this.currentRoom.type.equals(RoomType.KEYDOOR)&&currentRoom.equals(key1));
    }


    public String getBagDescription() {
        if (bag.isEmpty()) {
            return "The bag of " + name + " is empty.";
        }
        String returnString = "The bag of " + name + " contains:" + System.lineSeparator();
        for(Item i : bag) {
            returnString += "  " + i.getLongDescription() + System.lineSeparator();
        }
        return returnString;
    }
    public int drop(String name) {
        for (Item item : bag) {
            if (item.getName().equals(name)){
                currentRoom.addItem(item);
                bag.remove(item);
                return ITEM_DROPPED;
            }

        }

        return ITEM_NOTPRESENT;
    }
    public int lookitem(String name){

        for (Item item : bag){
            if (item.getName().equals(name)){
                item.getDescription();
                return ITEM_DESCRIPTION;
            }
        }
        return ITEM_NOTPRESENT;
    }
}

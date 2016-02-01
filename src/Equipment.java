import java.util.ArrayList;
import java.util.HashMap;

class Equipment {
// does it make sense to use a class here, vs a Map?
    public static void equipItem(int x, int y, String itemName,
                                 ArrayList<Item> inventory, HashMap<String, Item> equipment) {

        // Check if item is a valid inventory item
        boolean inInventory = false;
        boolean isEquipment = false;
        Item item = null;
        String unequipThisItem;
        //get item from master item list to prevent null pointer exception
        //this also would remove the need to set the item object when matching the inventory by string input

        for (Item i : inventory ) {
            if (i.getType().equals("equipment")) {
                isEquipment = true;
            }
            if (i.getName().equals(itemName)) {
                inInventory = true;
                item = i;
                break;
            }
        }

        // Check if item is already equipped
        boolean equipped = false;
        for(HashMap.Entry<String, Item> entry: equipment.entrySet()) {
            if (entry.getValue().getName().equals(itemName)) {
                equipped = true;
                item = entry.getValue();
                break;
            }
        }

        // checks if target slot of item to be equipped is already filled; unequip if so
        for(HashMap.Entry<String, Item> entry: equipment.entrySet()) {
            if (entry.getKey().equals(item.getSlot())) {
                unequipThisItem = entry.getValue().getName();
                Equipment.unequipItem(x, y, unequipThisItem, inventory, equipment);
            }
        }

        // Text output
        if (!equipped && inInventory && isEquipment) {
            System.out.println("You equip the " + itemName + ".");
            //check if slot exists in map
            equipment.put(item.getSlot(),item);
            inventory.remove(item);
        }
        else if (equipped) {
            System.out.println("You already have a " + itemName + " equipped.");
        }
        else if (!inInventory) {
            System.out.println("You don't have that in your inventory.");
        }
        else if (!isEquipment) {
            System.out.println("You can't equip that, even though it would probably be hilarious.");
        }
        else {
            System.out.println("I don't understand.");
        }
    }

    public static void unequipItem(int x, int y, String itemName,
                                 ArrayList<Item> inventory, HashMap<String, Item> equipment) {


        // Check if item is valid equipment
        boolean equipped = false;
        Item item = null;

        for(HashMap.Entry<String, Item> entry: equipment.entrySet()) {
            if (entry.getValue().getName().equals(itemName)) {
                equipped = true;
                item = entry.getValue();
                break;
            }
        }
        // Text output
        if (equipped) {
            System.out.println("You unequip the " + itemName + ".");
            equipment.remove(item);
            inventory.add(item);
        }
        else if (!equipped) {
            System.out.println("You don't have one of those equipped.");
        }

        else {
            System.out.println("I don't understand.");
        }
    }

    public static void print(HashMap<String, Item> equipment) {

        System.out.println("Equipment:");

        for(HashMap.Entry<String, Item> entry: equipment.entrySet()) {
            System.out.println(entry.getKey() + ": " + entry.getValue().getName());
        }
    }
}

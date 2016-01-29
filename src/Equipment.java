import java.util.ArrayList;

class Equipment {

    public static void equipItem(int x, int y, String itemName,
                                 ArrayList<Item> inventory, ArrayList<Item> equipment) {

        // Check if item is a valid inventory item
        boolean inInventory = false;
        Item item = null;
        for (Item i : inventory ) {
            if (i.getName().equals(itemName)) {
                inInventory = true;
                item = i;
                break;
            }
        }

        // Check if item is already equipped
        boolean equipped = false;
        for (Item e: equipment) {
            if (e.getName().equals(itemName)) {
                equipped = true;
                break;
            }
        }


        // Text output
        if (!equipped && inInventory) {
            System.out.println("You equip the " + itemName + ".");
            equipment.add(item);
            inventory.remove(item);
        }
        else if (equipped) {
            System.out.println("You already have a " + itemName + " equipped.");
        }
        else if (!inInventory) {
            System.out.println("You don't have that in your inventory.");
        }
        else {
            System.out.println("I don't understand.");
        }
    }

    public static void unequipItem(int x, int y, String itemName,
                                 ArrayList<Item> inventory, ArrayList<Item> equipment) {


        // Check if item is valid equipment
        boolean equipped = false;
        Item item = null;
        for (Item e: equipment) {
            if (e.getName().equals(itemName)) {
                equipped = true;
                item = e;
                break;
            }
        }


        // Text output
        if (equipped) {
            System.out.println("You unequip the " + itemName + ".");
            equipment.add(item);
            inventory.remove(item);
        }
        else if (equipped) {
            System.out.println("The " + itemName + " is already equipped.");
        }

        else {
            System.out.println("I don't understand.");
        }
    }

    public static void print(ArrayList<Item> equipment) {

        System.out.println("Equipment:");
        for (Item equip : equipment) {
            System.out.println(equip.getName());
        }
    }
}

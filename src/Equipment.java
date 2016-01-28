import java.util.ArrayList;

class Equipment {

    public static void equipItem(int x, int y, String item,
                                 ArrayList<String> inventory, ArrayList<String> equipment) {


        // Check if item is a valid inventory item
        boolean inInventory = false;
        for (String inventoryItem : inventory ) {
            if (inventoryItem.equals(item)) {
                inInventory = true;
                break;
            }
        }

        // Check if item is already equipped
        boolean equipped = false;
        for (String itemEquipped: equipment) {
            if (itemEquipped.equals(item)) {
                equipped = true;
                break;
            }
        }


        // Text output
        if (!equipped && inInventory) {
            System.out.println("You equip the " + item + ".");
            equipment.add(item);
            inventory.remove(item);
        }
        else if (equipped) {
            System.out.println("You already have a " + item + " equipped.");
        }
        else if (!inInventory) {
            System.out.println("You don't have that in your inventory.");
        }
        else {
            System.out.println("I don't understand.");
        }
    }

    public static void unequipItem(int x, int y, String item,
                                 ArrayList<String> inventory, ArrayList<String> equipment) {


        // Check if item is valid equipment
        boolean equipped = false;
        for (String itemEquipped: equipment) {
            if (itemEquipped.equals(item)) {
                equipped = true;
                break;
            }
        }

        boolean inInventory = false;
        for (String inventoryItem : inventory ) {
            if (inventoryItem.equals(item)) {
                inInventory = true;
                break;
            }
        }


        // Text output
        if (equipped) {
            System.out.println("You unequip the " + item + ".");
            equipment.add(item);
            inventory.remove(item);
        }
        else if (equipped) {
            System.out.println("The " + item + " is already equipped.");
        }
        else if (!inInventory) {
            System.out.println("You don't have one of those.");
        }
        else {
            System.out.println("I don't understand.");
        }
    }



    public static void print(ArrayList<String> equipment) {

        System.out.println("Equipment:");
        for (String equip : equipment) {
            System.out.println(equip);
        }
    }
}

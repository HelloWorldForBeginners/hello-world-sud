import java.util.ArrayList;

public class Main {

    public static void main(String args[]) {

        // Build rooms
        final int WIDTH = 2;
        final int HEIGHT = 2;
        Room[][] room = new Room[WIDTH][HEIGHT];
        Rooms.build(room, WIDTH, HEIGHT);
        int x = 0;
        int y = 0;
        Rooms.print(room, x, y);

        // Load inventory
        ArrayList<Item> inventory = new ArrayList<>();

        // Load equipment
        ArrayList<Item> equipment = new ArrayList<>();

        // Start game
        boolean playing = true;
        while (playing) {

            String input = Input.getInput();

            // Movement commands
            if (input.equals("n") || input.equals("north")) {
                if (y > 0) {
                    y--;
                    Rooms.print(room, x, y);
                } else {
                    System.out.println("You can't go that way.");
                }
            } else if (input.equals("s") || input.equals("south")) {
                if (y < HEIGHT - 1) {
                    y++;
                    Rooms.print(room, x, y);
                } else {
                    System.out.println("You can't go that way.");
                }
            } else if (input.equals("e") || input.equals("east")) {
                if (x < WIDTH -1) {
                    x++;
                    Rooms.print(room, x, y);
                } else {
                    System.out.println("You can't go that way.");
                }
            } else if (input.equals("w") || input.equals("west")) {
                if (x > 0) {
                    x--;
                    Rooms.print(room, x, y);
                } else {
                    System.out.println("You can't go that way.");
                }
            }

            // Look commands
            else if (input.equals("look")) {
                Rooms.print(room, x, y);
            }

            // Get commands
            else if (input.length() > 4  && input.substring(0, 4).equals("get ")) {
                if (input.substring(0, input.indexOf(' ')).equals("get")) {
                    if (input.substring(input.indexOf(' ')).length() > 1) {
                        String item = input.substring(input.indexOf(' ') + 1);
                        Inventory.getItem(x, y, item, inventory, room);
                    }
                }
            }

            else if (input.length() > 4 && input.substring(0, 4).equals("put ")) {
                if (input.substring(0, input.indexOf(' ')).equals("put")) {
                    if (input.substring(input.indexOf(' ')).length() > 1) {
                        String item = input.substring(input.indexOf(' ') + 1);
                        Inventory.putItem(x, y, item, inventory, room);
                    }
                }
            }

            else if (input.length() > 6 && input.substring(0, 6).equals("equip ")) {
                if (input.substring(0, input.indexOf(' ')).equals("equip")) {
                    if (input.substring(input.indexOf(' ')).length() > 1) {
                        String item = input.substring(input.indexOf(' ') + 1);
                        Equipment.equipItem(x, y, item, inventory, equipment);
                    }
                }
            }

            else if (input.length() > 8 && input.substring(0, 8).equals("unequip ")) {
                if (input.substring(0, input.indexOf(' ')).equals("unequip")) {
                    if (input.substring(input.indexOf(' ')).length() > 1) {
                        String item = input.substring(input.indexOf(' ') + 1);
                        Equipment.unequipItem(x, y, item, inventory, equipment);
                    }
                }
            }

            // Inventory commands
            else if (input.equals("i") || input.equals("inv")
                    || input.equals("inventory")) {
                Inventory.print(inventory);
            }

            else if (input.equals("equip") || input.equals("equipment")) {
                Equipment.print(equipment);
            }

            // Quit commands
            else if (input.equals("quit")) {
                System.out.println("Goodbye!");
                playing = false;

                // Catch-all for invalid input
            } else {
                System.out.println("You can't do that.");
            }
        }
        System.exit(0);
    }
}

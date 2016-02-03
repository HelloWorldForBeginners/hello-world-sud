import java.util.ArrayList;
import java.util.HashMap;

public class Main {

    public static void main(String args[]) {

        // Build rooms
        final int WIDTH = 2;
        final int HEIGHT = 2;
        Room[][] room = new Room[WIDTH][HEIGHT];
        World.build(room, WIDTH, HEIGHT);
        int x = 0;
        int y = 0;
        World.print(room, x, y);

        // Load inventory
        ArrayList<Item> inventory = new ArrayList<>();
        // Load equipment
        HashMap equipment = new HashMap<String, Item>();
        Player player = new Player("Matt", "Awesome", 1, 0, 0, 10, 10, 10, 1, 1, inventory, equipment);

        // Start game
        boolean playing = true;
        while (playing) {

            String input = Input.getInput();

            // Movement commands
            if (input.equals("n") || input.equals("north")) {
                if (y > 0) {
                    y--;
                    World.print(room, x, y);
                } else {
                    System.out.println("You can't go that way.");
                }
            } else if (input.equals("s") || input.equals("south")) {
                if (y < HEIGHT - 1) {
                    y++;
                    World.print(room, x, y);
                } else {
                    System.out.println("You can't go that way.");
                }
            } else if (input.equals("e") || input.equals("east")) {
                if (x < WIDTH -1) {
                    x++;
                    World.print(room, x, y);
                } else {
                    System.out.println("You can't go that way.");
                }
            } else if (input.equals("w") || input.equals("west")) {
                if (x > 0) {
                    x--;
                    World.print(room, x, y);
                } else {
                    System.out.println("You can't go that way.");
                }
            }

            // Look commands
            else if (input.equals("look")) {
                World.print(room, x, y);
            }

            // Get commands
            else if (input.length() > 6  && input.substring(0, 6).equals("check ")) {
                if (input.substring(0, input.indexOf(' ')).equals("check")) {
                    if (input.substring(input.indexOf(' ')).length() > 1) {
                        String target = input.substring(input.indexOf(' ') + 1);

                        // this could probably be improved; want to use the same command to
                        // check items and creatures in the room
                        if (NonPlayer.printNonPlayerInfo(target, room, x, y).equals("")) {
                            Item.printItemInfo(target, room, x, y);
                        }
                    }
                }

            }

            else if (input.length() > 7  && input.substring(0, 7).equals("attack ")) {
                if (input.substring(0, input.indexOf(' ')).equals("attack")) {
                    if (input.substring(input.indexOf(' ')).length() > 1) {
                        String target = input.substring(input.indexOf(' ') + 1);

                        // processAttackRound
                        NonPlayer.attackNonPlayer(target, room, x, y, player);

                    }
                }
            }

            // Get commands
            else if (input.length() > 4  && input.substring(0, 4).equals("get ")) {
                if (input.substring(0, input.indexOf(' ')).equals("get")) {
                    if (input.substring(input.indexOf(' ')).length() > 1) {
                        String item = input.substring(input.indexOf(' ') + 1);
                        Inventory.getItem(x, y, item, player, room);
                    }
                }
            }

            else if (input.length() > 4 && input.substring(0, 4).equals("put ")) {
                if (input.substring(0, input.indexOf(' ')).equals("put")) {
                    if (input.substring(input.indexOf(' ')).length() > 1) {
                        String item = input.substring(input.indexOf(' ') + 1);
                        Inventory.putItem(x, y, item, player, room);
                    }
                }
            }

            else if (input.length() > 6 && input.substring(0, 6).equals("equip ")) {
                if (input.substring(0, input.indexOf(' ')).equals("equip")) {
                    if (input.substring(input.indexOf(' ')).length() > 1) {
                        String item = input.substring(input.indexOf(' ') + 1);
                        Equipment.equipItem(x, y, item, player);
                    }
                }
            }

            else if (input.length() > 8 && input.substring(0, 8).equals("unequip ")) {
                if (input.substring(0, input.indexOf(' ')).equals("unequip")) {
                    if (input.substring(input.indexOf(' ')).length() > 1) {
                        String item = input.substring(input.indexOf(' ') + 1);
                        Equipment.unequipItem(x, y, item, player);
                    }
                }
            }

            // Inventory commands
            else if (input.equals("i") || input.equals("inv") || input.equals("inventory")) {
                Inventory.print(inventory);
            }

            else if (input.equals("equip") || input.equals("equipment") || input.equals("eq")) {
                Equipment.print(equipment);
            }

            else if (input.equals("player")) {
                Player.printPlayerInfo(player);
            }

            else if (input.equals("equip") || input.equals("equipment") || input.equals("eq")) {
                Equipment.print(equipment);
            }

            // Misc commands
            else if (input.equals("poop") || input.equals("pee") || input.equals("poo")) {
                if (World.getRoomName(room, x, y).equals("Bathroom")) {
                    System.out.println("Hurray! You made it!");
                }
                else {
                    System.out.println("Gross. What a mess.");
                }
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

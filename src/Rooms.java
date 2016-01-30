import java.util.ArrayList;
import java.util.Arrays;

class Rooms {

    public static void build(Room[][] room, final int WIDTH, final int HEIGHT) {

        // Initialize rooms (a 2D array)
        for (int i = 0; i < WIDTH; i++) {
            for (int j = 0; j < HEIGHT; j++) {
                room[i][j] = new Room(i, "", "", null, null);
            }
        }

        room[0][0].setNumber(1);
        room[0][0].setName("Living Room");
        room[0][0].setDescription("You are in your living room.");
        room[0][0].setExits("Exits: bedroom, south; kitchen, east.");
        room[0][0].setItems( new Item("shirt","smelly",1,"equipment","body"));
        room[0][0].setItems( new Item("jacket","cool",1,"equipment","body"));


        room[0][1].setNumber(2);
        room[0][1].setName("Bedroom");
        room[0][1].setDescription("You are in your bedroom. Your closet is slightly ajar.");
        room[0][1].setExits("Exits: bathroom, east; living room, north.");
        room[0][1].setItems( new Item("pants","wet",1,"equipment","legs"));


        room[1][1].setNumber(4);
        room[1][1].setName("Bathroom");
        room[1][1].setDescription("You are in your bathroom. Someone has drawn abstract art on the wall with " +
                "toothpaste. Gross.");
        room[1][1].setExits("Exits: kitchen, north; bedroom, west.");
        room[1][1].setItems( new Item("shoes","smelly",1,"equipment","feet"));
        room[1][1].setItems( new Item("toilet paper","cool",1,"item","none"));


        room[1][0].setNumber(3);
        room[1][0].setName("Kitchen");
        room[1][0].setDescription("You are in your kitchen. Looks like someone had a party.");
        room[1][0].setExits("Exits: bathroom, south; living room, west.");
        room[1][0].setItems( new Item("gloves","stained",1,"equipment","hands"));
    }

    public static void print(Room[][] room, int x, int y) {

        System.out.println(room[x][y].getDescription());
        System.out.println(room[x][y].getExits());
        System.out.println();
        if (room[x][y].getItems().size() > 0) {
            System.out.println("You see: " + room[x][y].getItems());
        }
        System.out.println();
        if (room[x][y].getCreatures().size() > 0) {
            System.out.println("There are creatures here: " + room[x][y].getCreatures());
        }
        System.out.println();
    }

    public static String getRoomName(Room[][] room, int x, int y) {

        return room[x][y].getName();
    }

    // Remove item from room when added to inventory
    public static void removeItem(Room[][] room, int x, int y, Item item) {

        room[x][y].deleteItem(item);
    }

    public static void addItem(Room[][] room, int x, int y, Item item) {

        room[x][y].addItem(item);
    }
}

class Room {

    private int number;
    private String name;
    private String description;
    private String exits;
    public ArrayList<Item> items = new ArrayList<>();
    public ArrayList<Item> creatures = new ArrayList<>();


    public Room(int number, String name, String description, ArrayList<Item> items, ArrayList<Item> creatures) {

    }

    public void setNumber(int number) {
        this.number = number;
    }

    public int getNumber() {
        return this.number;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDescription() {
        return this.description;
    }

    public void setExits(String exits) {
        this.exits = exits;
    }

    public String getExits() {
        return this.exits;
    }

    public void setItems(Item item) {
        this.items.add(item);
    }

    public void setCreatures(Item item) {
        this.creatures.add(item);
    }

    public void deleteItem(Item item) {
        this.items.remove(item);
    }

    public void addItem(Item item) {
        this.items.add(item);
    }

    public ArrayList<String> getItems() {
        ArrayList<String> itemArray = new ArrayList<>();
        for (Item i: this.items) {
            itemArray.add(i.getName());
        }
        return itemArray;
    }


    public ArrayList<Item> getCreatures() {
        return this.creatures;
    }
}

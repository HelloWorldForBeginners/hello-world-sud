import java.util.ArrayList;

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
        room[0][0].setDescription("You are in your living room. Exits: bedroom, south; kitchen, east.");
        room[0][0].setItems("wallet");
        room[0][0].setItems("remote");
        room[0][0].setItems("book");
        room[0][0].setItems("socks");
        room[0][0].setItems("pants");
        room[0][0].setCreatures("mouse");

        room[0][1].setNumber(2);
        room[0][1].setName("Bedroom");
        room[0][1].setDescription("You are in your bedroom. Your closet is slightly ajar. Exits: bathroom, east; " +
                "living room, north.");
        room[0][1].setItems("keys");
        room[0][1].setItems("flashlight");
        room[0][1].setCreatures("spider");
        room[0][0].setItems("shirt");

        room[1][1].setNumber(4);
        room[1][1].setName("Bathroom");
        room[1][1].setDescription("You are in your bathroom. Someone has drawn abstract art on the wall with " +
                "toothpaste. Gross. Exits: kitchen, north; bedroom, west.");
        room[1][1].setItems("toilet paper");
        room[1][1].setItems("magazine");
        room[0][0].setItems("shoes");
        room[1][1].setCreatures("fly");

        room[1][0].setNumber(3);
        room[1][0].setName("Kitchen");
        room[1][0].setDescription("You are in your kitchen. Looks like someone had a party. Exits: bathroom, south; " +
                "living room, west.");
        room[1][0].setItems("pop tarts");
        room[1][0].setItems("soda");
        room[1][0].setCreatures("bird");
    }

    public static void print(Room[][] room, int x, int y) {

        System.out.println(room[x][y].getDescription());
        System.out.println("You see: " + room[x][y].getItems());
        System.out.println("There are creatures here: " + room[x][y].getCreatures());
        System.out.println();
    }

    // Remove item from room when added to inventory
    public static void removeItem(Room[][] room, int x, int y, String item) {

        room[x][y].deleteItem(item);
    }

    public static void addItem(Room[][] room, int x, int y, String item) {

        room[x][y].addItem(item);
    }
}

class Room {

    private int number;
    private String name;
    private String description;
    public ArrayList<String> items = new ArrayList<>();
    public ArrayList<String> creatures = new ArrayList<>();

    public Room(int number, String name, String description,
                ArrayList<String> items, ArrayList<String> creatures) {
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

    public void setItems(String item) {
        this.items.add(item);
    }

    public void setCreatures(String item) {
        this.creatures.add(item);
    }

    public void deleteItem(String item) {
        this.items.remove(item);
    }

    public void addItem(String item) {
        this.items.add(item);
    }

    public ArrayList<String> getItems() {
        return this.items;
    }

    public ArrayList<String> getCreatures() {
        return this.creatures;
    }
}

import java.util.ArrayList;

class Room {

    private int number;
    private String name;
    private String description;
    private String exits;
    public ArrayList<Item> items = new ArrayList<>();
    public ArrayList<NonPlayer> creatures = new ArrayList<>();

    public Room(int number, String name, String description, ArrayList<Item> items, ArrayList<Character> creatures) {

    }

    public void setNumber(int number) {
        this.number = number;
    }

    public void setItems(ArrayList<Item> items) {
        this.items = items;
    }

    public void setCreatures(ArrayList<NonPlayer> creatures) {
        this.creatures = creatures;
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

    public ArrayList<String> getItems() {
        ArrayList<String> itemArray = new ArrayList<>();
        for (Item i: this.items) {
            itemArray.add(i.getName());
        }
        return itemArray;
    }

    public void setCreatures(NonPlayer creature) {
        this.creatures.add(creature);
    }

    public ArrayList<String> getCreatures() {
        ArrayList<String> itemArray = new ArrayList<>();
        for (Character c: this.creatures) {
            itemArray.add(c.getName());
        }
        return itemArray;
    }

    public void deleteItem(Item item) {
        this.items.remove(item);
    }

    public void addItem(Item item) {
        this.items.add(item);
    }
}

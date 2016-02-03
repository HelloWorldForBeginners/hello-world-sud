public class Item extends GameObject {
    private int amount;
    private String type;
    private String slot;
    private int defense;

    public Item(String name, String description, int amount, String type, String slot, int defense) {
        super(name, description);
        this.amount = amount;
        this.type = type;
        this.slot = slot;
        this.defense = defense;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public int getAmount() {
        return this.amount;
    }

    public String getType() {
        return this.type;
    }

    public String getSlot() {
        return this.slot;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setSlot(String slot) {
        this.slot = slot;
    }

    public int getDefense() {
        return defense;
    }

    public void setDefense(int defense) {
        this.defense = defense;
    }

    public static String printItemInfo(String target, Room[][] room, int x, int y) {

        Item item = null;
        String result = "";

        for (Item checkItem: room[x][y].items ) {
            if (checkItem.getName().equals(target)) {
                item = checkItem;
                break;
            }
        }

        if (item != null) {
            System.out.println("Name: " + item.getName() + "(" + item.getAmount() + ")");
            System.out.println(item.getDescription());
            System.out.println(item.getType());
            System.out.println(item.getSlot());
            System.out.println(item.getDefense());
            System.out.println();
            result = "item";
        }

        return result;
    }
}

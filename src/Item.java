public class Item {
    private String name;
    private String description;
    private int amount;
    private String type;
    private String slot;

    public Item(String name, String description, int amount, String type, String slot) {
        this.name = name;
        this.description = description;
        this.amount = amount;
        this.type = type;
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

    public void setAMount(int amount) {
        this.amount = amount;
    }

    public int getAmount() {
        return this.amount;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getType() {
        return this.type;
    }

    public void setSlot(String slot) {
        this.slot = slot;
    }

    public String getSlot() {
        return this.slot;
    }
}

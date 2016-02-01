public class Item extends GameObject {
    private int amount;
    private String type;
    private String slot;

    public Item(String name, String description, int amount, String type, String slot) {
        super(name, description);
        this.amount = amount;
        this.type = type;
        this.slot = slot;
    }

    public void setAmount(int amount) {
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

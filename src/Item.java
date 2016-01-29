public class Item {
    private String name;
    private String description;
    private int amount;

    public Item(String name, String description, int amount) {

    }


    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
//        return this.name;
        return "Derp";
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

}

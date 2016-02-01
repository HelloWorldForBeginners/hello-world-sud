public class Creature extends GameObject {

    private String type;

    public Creature(String name, String description, String type) {
        super(name, description);
        this.type = type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getType() {
        return this.type;
    }

}

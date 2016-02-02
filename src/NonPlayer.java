import java.util.ArrayList;
import java.util.HashMap;

public class NonPlayer extends Character {

    public NonPlayer(String name, String description, int level, int exp, int hitPoints, int maxHitPoints,
                     int attack, int defense, ArrayList<Item> inventory, HashMap<String, Item> equipment) {
        super(name, description, level, exp, hitPoints, maxHitPoints, attack, defense, inventory, equipment);
    }
}

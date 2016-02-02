import java.util.ArrayList;
import java.util.HashMap;

public class Player extends Character {

    private int expToNextLevel;

    public Player(String name, String description, int level, int exp, int expToNextLevel, int hitPoints, int maxHitPoints,
                  int attack, int defense, ArrayList<Item> inventory, HashMap<String, Item> equipment) {
        super(name, description, level, exp, hitPoints, maxHitPoints, attack, defense, inventory, equipment);
        this.expToNextLevel = expToNextLevel;
    }

    public static void printPlayerInfo(Player player) {
        System.out.println("Name: " + player.getName() + "(" + player.getLevel() + ")");
        System.out.println("HP: " + player.getHitPoints() + "/" + player.getMaxHitPoints());
        System.out.println("Attack: " + player.getAttack());
        System.out.println("Defense: " + player.getDefense());
        System.out.println("Exp: " + player.getExp() + "/" + player.expToNextLevel);
        System.out.println();
        player.printInventory(player.getInventory());
        player.printEquipment(player.getEquipment());
    }
}

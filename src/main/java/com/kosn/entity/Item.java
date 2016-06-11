package com.kosn.entity;

public class Item extends GameObject {
    private int amount;
    private String type;
    private String slot;
    private int defense;

    public Item(String _name, String _description, int _amount, String _type, String _slot, int _defense) {
        super(_name, _description);
    }

    // Constructor for importing item from a save file
    public Item(String[] params) {
        super(params[0], params[1]);
        this.amount = Integer.parseInt(params[2]);
        this.type = params[3];
        this.slot = params[4];
        this.defense = Integer.parseInt(params[5]);
    }
    
    public void setAmount(int _amount) {
        this.amount = _amount;
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

    public void setType(String _type) {
        this.type = _type;
    }

    public void setSlot(String _slot) {
        this.slot = _slot;
    }

    public int getDefense() {
        return defense;
    }

    public void setDefense(int _defense) {
        this.defense = _defense;
    }
    
    public static void printItemInfo(String _target, Room _room) {

        Item item = getitem(_target, _room);
        if (item != null) {
        	printInfo(item);
        }
    }

    private static void printInfo(Item _item) {

    	System.out.println("Name: " + _item.getName() + "(" + _item.getAmount() + ")");
        System.out.println(_item.getDescription());
        System.out.println("Type: " + _item.getType());
        System.out.println("Slot: " + _item.getSlot());
        System.out.println("Def: " + _item.getDefense());
        System.out.println();
	}

	private static Item getitem(String _target, Room _room) {
    	for (Item checkItem: _room.getItems() ) {
            if (checkItem.getName().equals(_target)) {
                return checkItem;
            }
            if (checkItem.getName().startsWith(_target)) {
                return checkItem;
            }
        }
		return null;
	}

	@Override //means this method exists somewhere; it comes from the Object type
	public String toString() {
		String toString = getName();
		return toString;
	}
    
}

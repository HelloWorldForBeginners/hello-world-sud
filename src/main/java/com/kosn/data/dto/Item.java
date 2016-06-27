package com.kosn.data.dto;

public class Item implements Comparable<Item> {

//	public Item() {}
	
	private String classType;
	private String name;
	private String description;
    private int amount = 1;
    private String type;
    private String slot = "none";
    private int defense = 0;
    private ItemEffectType effectType = ItemEffectType.other;
    private int effectValue;
    
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
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

    public void printInfo() {
    	System.out.println("Name: " + this.name + "(" + this.amount + ")");
        System.out.println(this.description);
        System.out.println("Type: " + this.type);
        System.out.println("Slot: " + this.slot);
        System.out.println("Def: " + this.defense);
        if (this.effectType != null) {
	        System.out.println("Effect: " + this.effectType.toString());
	        System.out.println("Value: " + this.effectValue);
        }
        System.out.println();
	}

	@Override
	public int compareTo(Item o) {
		return name.compareTo(o.getName());
	}

	public int getEffectValue() {
		return effectValue;
	}

	public void setEffectValue(int effectValue) {
		this.effectValue = effectValue;
	}

	public ItemEffectType getEffectType() {
		return effectType;
	}

	public void setEffectType(ItemEffectType effectType) {
		this.effectType = effectType;
	}



	@Override
	public String toString() {
		return "Item [classType=" + classType + ", name=" + name + ", description=" + description + ", amount=" + amount
				+ ", type=" + type + ", slot=" + slot + ", defense=" + defense + ", effectType=" + effectType
				+ ", effectValue=" + effectValue + "]";
	}

	public String getClassType() {
		return classType;
	}

	public void setClassType(String classType) {
		this.classType = classType;
	}

//	@Override
//	public String toString() {
//		String toString = getName();
//		return toString;
//	}
	
}

package com.kosn.entity;

import com.kosn.util.TextHandler;

public class Item implements Examinable, Comparable<Item> {
	
	private String classType;
	private String name;
	private String description;
    private int amount = 1;
    private ItemType type = ItemType.nonconsumable;
    private EquipSlot slot;
    private int attack = 0;
    private int defense = 0;
    private EffectType effectType = EffectType.other;
    private int effectValue;
    
	@Override
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

    public ItemType getType() {
        return this.type;
    }

    public EquipSlot getSlot() {
        return this.slot;
    }

    public void setType(ItemType _type) {
        this.type = _type;
    }

    public void setSlot(EquipSlot _slot) {
        this.slot = _slot;
    }

    public int getAttack() {
		return attack;
	}

	public void setAttack(int attack) {
		this.attack = attack;
	}

	public int getDefense() {
        return defense;
    }

    public void setDefense(int _defense) {
        this.defense = _defense;
    }
    
    @Override
	public void printInfo() {
    	System.out.format("You behold the %s. It looks %s\n\n", this.name, this.description);
        if (this.slot != null) {
            System.out.format("With your amazing observational powers, you notice a few things about the %s:\n", this.name);
            TextHandler.printAsTwoColumnsLeftAligned("Slot", this.slot.toString());
            TextHandler.printAsTwoColumnsLeftAligned("Attack", Integer.toString(this.attack));
            TextHandler.printAsTwoColumnsLeftAligned("Defense", Integer.toString(this.defense));
            System.out.println();
        }
        if (this.effectType != EffectType.other) {
        	System.out.format("This item is consumable!\n");
        	System.out.format("%s Effect: %s %s\n", this.type, this.effectValue, this.effectType.toString());
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

	public EffectType getEffectType() {
		return effectType;
	}

	public void setEffectType(EffectType effectType) {
		this.effectType = effectType;
	}

	public String getClassType() {
		return classType;
	}

	public void setClassType(String classType) {
		this.classType = classType;
	}

	@Override
	public String toString() {
		String toString = getName();
		return toString;
	}
}

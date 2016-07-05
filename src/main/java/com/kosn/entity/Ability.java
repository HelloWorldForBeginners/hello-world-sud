package com.kosn.entity;

public class Ability {

	private String classType;
	private String name;
	private String description;
    private String type;
    private EffectType effectType = EffectType.other;
    private int effectValue;
	
    public String getClassType() {
		return classType;
	}
	public void setClassType(String classType) {
		this.classType = classType;
	}
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
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public EffectType getEffectType() {
		return effectType;
	}
	public void setEffectType(EffectType effectType) {
		this.effectType = effectType;
	}
	public int getEffectValue() {
		return effectValue;
	}
	public void setEffectValue(int effectValue) {
		this.effectValue = effectValue;
	}
	@Override
	public String toString() {
		return "Ability [classType=" + classType + ", name=" + name + ", description=" + description + ", type=" + type
				+ ", effectType=" + effectType + ", effectValue=" + effectValue + "]";
	}
}
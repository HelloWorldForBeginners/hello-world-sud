package com.kosn.entity;

public class GameObject {

	protected String name;
	protected String description;
	
	public GameObject() {}
	
	public GameObject(String name, String description) {
	    this.name = name;
	    this.description = description;
	}
	
	public void setName(String name) { this.name = name; }
	
	public String getName() {
	    return this.name;
	}
	
	public void setDescription(String description) {
	    this.description = description;
	}
	
	public String getDescription() {
	    return this.description;
	}
	
	@Override
	public String toString() {
		return name;
	}
}

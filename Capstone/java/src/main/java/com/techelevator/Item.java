package com.techelevator;

public class Item {
	private String name;
	private double price;
	String itemType;
	
	public Item(String name, double price, String type) {
		this.name = name;
		this.price = price;
		itemType = type;
	}

	public String getName() {
		return name;
	}

	public double getPrice() {
		return price;
	}
	
	public String getDispenseMessage() {
													//  TODO : ADD MESSAGE AND LOGIC
		return "Temp message";
	}
	
	@Override
	public String toString() {
		return this.name + " " + this.price;
	}
}

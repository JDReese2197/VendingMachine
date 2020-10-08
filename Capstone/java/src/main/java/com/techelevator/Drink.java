package com.techelevator;

public class Drink extends Item{
	
	public Drink(String name, double price) {
		super(name, price);
	}
	
	public String getDispenseMessage() {
		return "Glug Glug, Yum!";
	}
}

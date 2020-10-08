package com.techelevator;

public class Gum extends Item{
	
	public Gum(String name, double price) {
		super(name, price);
	}
	
	public String getDispenseMessage() {
		return "Chew Chew, Yum!";
	}
}

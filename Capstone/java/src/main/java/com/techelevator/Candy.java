package com.techelevator;

public class Candy extends Item{
	
	public Candy(String name, double price) {
		super(name, price);
	}
	
	public String getDispenseMessage() {
		return "Munch Munch, Yum!";
	}
}
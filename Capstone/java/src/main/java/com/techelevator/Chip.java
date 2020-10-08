package com.techelevator;

public class Chip extends Item{
	
	public Chip(String name, double price) {
		super(name, price);
	}
	
	public String getDispenseMessage() {
		return "Crunch Crunch, Yum!";
	}
}
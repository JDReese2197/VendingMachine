package com.techelevator;

public class Money {
	private double currentMoney;
	
	public Money() {
		this.currentMoney = 0.00;
	}
	
	public double getCurrentMoney() {
		return currentMoney;
	}
	
	public double addMoney(int addedMoney) {
		if(isValidBill(addedMoney)) {
			currentMoney += (double)addedMoney;
		}
		return currentMoney;
	}
	
	public double subtractMoney(double itemCost) {
		currentMoney -= itemCost;
		return currentMoney;
	}
	
	public boolean hasEnoughMoney(double itemCost) {
		if(itemCost <= currentMoney) {
			return true;
		}
		return false;
	}
	
	private boolean isValidBill(int addedMoney) {
		switch(addedMoney) {
			case 1:
				return true;
			case 2:
				return true;
			case 5:
				return true;
			case 10:
				return true;
		}
		System.out.println("Bill submitted was not valid.\n"
				+ "Please try again with a valid bill type \n");
		return false;
	}
}

package com.techelevator;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;
import java.util.TreeMap;

/**************************************************************************************************************************
*  This is your Vending Machine Command Line Interface (CLI) class
*
*  It is the main process for the Vending Machine
*
*  THIS is where most, if not all, of your Vending Machine interactions should be coded
*  
*  It is instantiated and invoked from the VendingMachineApp (main() application)
*  
*  Your code vending machine related code should be placed in here
***************************************************************************************************************************/
import com.techelevator.view.Menu;         // Gain access to Menu class provided for the Capstone

public class VendingMachineCLI {
	
	Inventory itemInventory;
	Money machineMoney = new Money();
	//TreeMap<String, HashMap<Item, Integer>> inventory = new TreeMap<>();
	
	
    // Main menu options defined as constants

	private static final String MAIN_MENU_OPTION_DISPLAY_ITEMS = "Display Vending Machine Items";
	private static final String MAIN_MENU_OPTION_PURCHASE      = "Purchase";
	private static final String MAIN_MENU_OPTION_EXIT          = "Exit";
	private static final String[] MAIN_MENU_OPTIONS = { MAIN_MENU_OPTION_DISPLAY_ITEMS,
													    MAIN_MENU_OPTION_PURCHASE,
													    MAIN_MENU_OPTION_EXIT
													    };
	private static final String SUB_MENU_FEED_MONEY = "Feed Money";
	private static final String SUB_MENU_SELECT_PRODUCT = "Select Product";
	private static final String SUB_MENU_FINISH_TRANSACTION = "Finish Transaction";
	private static final String[] SUB_MENU_OPTIONS = { SUB_MENU_FEED_MONEY,
														SUB_MENU_SELECT_PRODUCT,
														SUB_MENU_FINISH_TRANSACTION 
														};
	
	private Menu vendingMenu;              // Menu object to be used by an instance of this class
	
	public VendingMachineCLI(Menu menu) {  // Constructor - user will pas a menu for this class to use
		this.vendingMenu = menu;           // Make the Menu the user object passed, our Menu
	}
	/**************************************************************************************************************************
	*  VendingMachineCLI main processing loop
	*  
	*  Display the main menu and process option chosen
	*
	*  It is invoked from the VendingMachineApp program
	*
	*  THIS is where most, if not all, of your Vending Machine objects and interactions 
	*  should be coded
	*
	*  Methods should be defined following run() method and invoked from it
	 * @throws IOException 
	*
	***************************************************************************************************************************/

	public void run() throws IOException {
		itemInventory = new Inventory();

		boolean shouldProcess = true;         // Loop control variable
		
		while(shouldProcess) {                // Loop until user indicates they want to exit
			
			String choice = (String)vendingMenu.getChoiceFromOptions(MAIN_MENU_OPTIONS);  // Display menu and get choice
			
			switch(choice) {                  // Process based on user menu choice
			
				case MAIN_MENU_OPTION_DISPLAY_ITEMS:
					displayItems();           // invoke method to display items in Vending Machine
					break;                    // Exit switch statement
			
				case MAIN_MENU_OPTION_PURCHASE:
					purchaseItems();          // invoke method to purchase items from Vending Machine
					break;                    // Exit switch statement
			
				case MAIN_MENU_OPTION_EXIT:
					endMethodProcessing();    // Invoke method to perform end of method processing
					shouldProcess = false;    // Set variable to end loop
					break;                    // Exit switch statement
			}	
		}
		return;                               // End method and return to caller
	}
/********************************************************************************************************
 * Methods used to perform processing
 * @throws IOException 
 ********************************************************************************************************/
	
	public void restock() throws IOException {
		itemInventory.restock();
	}
	
	public void displayItems() throws IOException {      // static attribute used as method is not associated with specific object instance
		itemInventory.displayItems();
	}
	
	public void purchaseItems() throws IOException {	 // static attribute used as method is not associated with specific object instance
		// Code to purchase items from Vending Machine
		// 
		boolean shouldProcess = true;         // Loop control variable
		
		while(shouldProcess) {                // Loop until user indicates they want to exit
			
			String choice = (String)vendingMenu.getChoiceFromOptions(SUB_MENU_OPTIONS);  // Display menu and get choice
			
			switch(choice) {                  // Process based on user menu choice
			
				case SUB_MENU_FEED_MONEY:
					feedMoney();          // invoke method to feed money in Vending Machine
					break;                    // Exit switch statement
			
				case SUB_MENU_SELECT_PRODUCT:
					selectProduct();          // invoke method to purchase items from Vending Machine
					break;                    // Exit switch statement
			
				case SUB_MENU_FINISH_TRANSACTION:
					//endMethodProcessing();    // Invoke method to perform end of method processing
					shouldProcess = false;    // Set variable to end loop
					break;                    // Exit switch statement
			}	
		}
	}
	
	public void feedMoney() {
		Scanner input = new Scanner(System.in);
		System.out.println("Please insert a bill of the following type: \n"
				+ "$1, $2, $5, $10");
		String userInput = input.nextLine();
		System.out.println(machineMoney.addMoney(Integer.parseInt(userInput)));
	}
	
	public void selectProduct() throws IOException {
		Scanner input = new Scanner(System.in);
		
		System.out.println("Vending Machine Items: ");
		itemInventory.displayItems();
		
		//Prompt user for input
		System.out.print("Please enter the slot ID of the Item you'd like:  ");
		String userInput = input.nextLine();
		//Validate input is an existing slot
		
		//if slot is valid:
		if(itemInventory.isValidSlot(userInput)) {
			Item selectedItem = itemInventory.getItem(userInput);
		//	if item is in stock:
			if(!itemInventory.isSoldOut(userInput) && machineMoney.hasEnoughMoney(selectedItem.getPrice())) {
		//		decrement item stock by one
				itemInventory.decrementStock(userInput);
		//		dispense item to customer
				System.out.println("You bought: " + selectedItem);
				System.out.println("There are now " + itemInventory.getStock(userInput) + " "
									+ selectedItem.getName() + " remaining");
				System.out.println(selectedItem.getDispenseMessage());
		//		subtract cost of item from total money
				System.out.println("Remaining Money: " + machineMoney.subtractMoney(selectedItem.getPrice()));
				return;
			}
			//	if item is not in stock:
			else if(itemInventory.isSoldOut(userInput)) {
				//		let customer know
				System.out.println(selectedItem.getName() + " is sold out.  Please select another. :)");
				//		return to purchase menu
				return;
			}
			else if(!(machineMoney.hasEnoughMoney(selectedItem.getPrice()))) {
				System.out.println("You're too broke.  Leave.\n"
						+ "The item costs: $" + selectedItem.getPrice() +
						" while you only have: $" + machineMoney.getCurrentMoney());
				return;
			}
		}
		//if slot is invalid:
		else if(!itemInventory.isValidSlot(userInput)) {
			//	let user know
			System.out.println("That slot doesn't exist.  Please read and type more carefully next time.");
			//	return to purchase menu
			return;
		}
	}
	
	public void endMethodProcessing() { // static attribute used as method is not associated with specific object instance
		// Any processing that needs to be done before method ends
	}
}

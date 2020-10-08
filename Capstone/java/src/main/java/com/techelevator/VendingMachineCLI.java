package com.techelevator;
import java.util.ArrayList;
import java.util.HashMap;
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
	
	ArrayList<Item> mountainFrankSlot = new ArrayList<>();
	ArrayList<Item> potatoCrispsSlot = new ArrayList<>();
	ArrayList<Item> stackersSlot = new ArrayList<>();
	ArrayList<Item> cloudPopcornsSlot = new ArrayList<>();
	ArrayList<Item> moonPiesSlot = new ArrayList<>();
	ArrayList<Item> cowTailsSlot = new ArrayList<>();
	ArrayList<Item> wonkaBarSlot = new ArrayList<>();
	ArrayList<Item> crunchySlot = new ArrayList<>();
	ArrayList<Item> colaSlot = new ArrayList<>();
	ArrayList<Item> drSaltSlot = new ArrayList<>();
	
	HashMap<String, ArrayList> inventory = new HashMap<>();
	
    // Main menu options defined as constants

	private static final String MAIN_MENU_OPTION_DISPLAY_ITEMS = "Display Vending Machine Items";
	private static final String MAIN_MENU_OPTION_PURCHASE      = "Purchase";
	private static final String MAIN_MENU_OPTION_EXIT          = "Exit";
	private static final String[] MAIN_MENU_OPTIONS = { MAIN_MENU_OPTION_DISPLAY_ITEMS,
													    MAIN_MENU_OPTION_PURCHASE,
													    MAIN_MENU_OPTION_EXIT
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
	*
	***************************************************************************************************************************/

	public void run() {
		restock();
		createInventory();

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
 ********************************************************************************************************/
	
	public void createInventory() {
		inventory.put("A1", mountainFrankSlot);
		inventory.put("A2", potatoCrispsSlot);
		inventory.put("A3", stackersSlot);
		inventory.put("B1", cloudPopcornsSlot);
		inventory.put("B2", moonPiesSlot);
		inventory.put("B3", cowTailsSlot);
		inventory.put("B4", wonkaBarSlot);
		inventory.put("C1", crunchySlot);
		inventory.put("C2", colaSlot);
		inventory.put("C3", drSaltSlot);
	}
	
	public void restock() {
		Drink  mountainFrank = new Drink("Mountain Frank", 15);
		Chip potatoCrips = new Chip("Potato Crips", 1.5);
		Candy stackers = new Candy("Stackers", .75);
		Chip cloudPopcorn = new Chip("Cloud Popcorn", 5);
		Candy moonPie = new Candy("Moon Pie", 1.25);
		Candy cowTail = new Candy("Cow Tail", .25);
		Candy wonkaBar = new Candy("Wonka Bar", 8);
		Candy crunchy = new Candy("Crunchy", .50);
		Drink  cola = new Drink("Cola", 1);
		Drink  drSalt = new Drink("Dr. Salt", 1.75);
		
		
		for( int i = 0; i < 5; i++) {
			mountainFrankSlot.add(mountainFrank);
			potatoCrispsSlot.add(potatoCrips);
			stackersSlot.add(stackers);
			cloudPopcornsSlot.add(cloudPopcorn);
			moonPiesSlot.add(moonPie);
			cowTailsSlot.add(cowTail);
			wonkaBarSlot.add(wonkaBar);
			crunchySlot.add(crunchy);
			colaSlot.add(cola);
			drSaltSlot.add(drSalt);
		}
	}
	
	public void displayItems() {      // static attribute used as method is not associated with specific object instance
		// Code to display items in Vending Machine
	}
	
	public void purchaseItems() {	 // static attribute used as method is not associated with specific object instance
		// Code to purchase items from Vending Machine
	}
	
	public void endMethodProcessing() { // static attribute used as method is not associated with specific object instance
		// Any processing that needs to be done before method ends
	}
}

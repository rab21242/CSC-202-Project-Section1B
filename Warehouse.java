/*
 *	Project Section #1 Part B
 * 	Warehouse Class
 * 	
 * 	Author: Connor Baker, Rae Bouldin
 * 	Date:	11/17/2016
 */

package section1B_package;
import java.util.Scanner;

public class Warehouse
{
	// ATTRIBUTES
	private Robot robot;
	private Station[] stations;
	
	// CONSTANT ATTRIBUTES
	private static final int PICKUP_STATION = 0;
	private static final int FREEZER = 5;
	private static final int SPECIAL_DELIVERY_1 = 9;
	private static final int SPECIAL_DELIVERY_2 = 10;
	private static final int IMMEDIATE_DELIVERY = 11;
	private static final int FIVE_DAY_STORAGE = 12;
	
/******************************* CONSTRUCTORS ******************************/
	
	public Warehouse()
	{
		robot = new Robot();
		
		// create and initialize array of stations
		stations = new Station[13];
		for(int i = 0; i < stations.length; i++)
		{
			if(i == PICKUP_STATION)
			{
				stations[i] = new Station("Pickup Station", 0);
			}
			else if(i <= 10)
			{
				stations[i] = new Station("Station " + i, 8);
			}
			else
			{
				if(i == IMMEDIATE_DELIVERY)
					stations[i] = new Station("Station 12", 4);
				if(i == FIVE_DAY_STORAGE)
					stations[i] = new Station("Station 14", 4);
			}
		}//end for loop
	}//end Warehouse constructor
	
/******************************* RUN METHOD ********************************/
	
	public void runWarehouse()
	{
		// unload the shipment onto pickup station
		unloadTruck();
		
		// have the Robot store the items in the correct Station
		storeAllItems();
	}//end runWarehouse method
	
/************************* PROBLEM SPECIFIC METHODS ************************/
	
	private void unloadTruck()
	{
		Scanner input = new Scanner(System.in);
		int itemsOnTruck = 0;
		Item tmpItem = new Item();
		
		// ask user how many items came on the truck shipment
		System.out.print("How many items need to be unloaded?: ");
		itemsOnTruck = input.nextInt();
		
		// define a new pickup station based on itemsOnTruck
		stations[PICKUP_STATION] = new Station(
				"Pickup Station", itemsOnTruck);
		
		// record item info and place the items on the pickup station
		for(int i = 0; i < stations[PICKUP_STATION].getNumSlots(); i++)
		{
			tmpItem.inputItemDetails();
			stations[PICKUP_STATION].fillNextSlot(
					tmpItem.getSerialNum(), tmpItem.getWeight());
		}//end 	
	}//end unloadTruck method
	
	private void storeAllItems()
	{
		for(int i = stations[PICKUP_STATION].getNumSlots(); i >= 0; i--)
		{
			storeOneItem(i);
		}//end for loop
	}//end storeAllItems method
	
	private void storeOneItem(int location)
	/*
	 * 		location - location from which to take the item
	 */
	{
		int stationNum = -1;	// initialized to an invalid value
		
		// pass item to Robot
		robot.pickItem(stations[PICKUP_STATION].getSlotSerialNum(location), 
					   stations[PICKUP_STATION].getSlotWeight(location));
		stations[PICKUP_STATION].unloadSlot(location);
		
		// place item in correct station
		stationNum = findCorrectStation();
		stations[stationNum].fillNextSlot(robot.getItemSerialNum(), 
										  robot.getItemWeight());
		robot.dropItem();
	}//end storeOneItem method
	
	private int findCorrectStation()
	{
		int correctStation = -1;	// initialized to an invalid value
		
		
		
		return(correctStation);
	}//end findCorrectStation
	
}//end Warehouse class
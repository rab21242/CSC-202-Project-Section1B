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
	// SCANNER
	Scanner input = new Scanner(System.in);
	
	// ATTRIBUTES
	private Station[] stations;	// stations in the warehouse
	private Robot robot;		// a robot to move items in the warehouse
	
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
		
		// create robot with a beginning position at the pickup station
		robot = new Robot(PICKUP_STATION);
		
	}//end Warehouse constructor
	
/******************************* RUN METHOD ********************************/
	
	public void runWarehouse()
	{
		// unload the shipment onto pickup station
		unloadTruck();
		
		// have the Robot store the items in the correct Station
		storeAllItems();
		
		// inform the user that the program has ended
		System.out.println("\nALL ITEMS HAVE BEEN STORED.");
	}//end runWarehouse method
	
/************************* PROBLEM SPECIFIC METHODS ************************/
	
	private void unloadTruck()
	{
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
		}//end for loop
		
	}//end unloadTruck method
	
	private void storeAllItems()
	{
		for(int i = (stations[PICKUP_STATION].getNumSlots() - 1); i >= 0; i--)
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
		
		// find correct station
		stationNum = findCorrectStation();
		
		// place item in correct station
		storeItemInStation(stationNum);
	}//end storeOneItem method
	
	private void storeItemInStation(int stationNum)
	/*
	 * 		station - station to store the item in
	 */
	{
		int aisle = 0;
		
		// determine aisle to move robot to
		if(stationNum%2 == 0)
		{
			aisle = (stationNum/2);
		}
		else
		{
			aisle = ((stationNum+1)/2);
		}
		
		// move robot to the correct aisle
		for(int i = 0; i < aisle; i++)
		{
			robot.moveForward( ((stations.length-1)/2) );
		}//end of for loop
		
		// store the item in the station
		robot.moveToSide();
		stations[stationNum].fillNextSlot(robot.getItemSerialNum(), 
										  robot.getItemWeight());
		robot.dropItem();
		
		// return robot to pickup station
		robot.moveToSide();
		for(int i = aisle; i > 0; i--)
		{
			robot.moveBackward(0);
		}//end of for loop
		
	}//end storeItemInStation method
	
	private int findCorrectStation()
	{
		// constants
		final int SPECIAL_ITEM = 1;
		
		// variables
		int specialItem = -1;
		int correctStation = -1;	// initialized to an invalid value
		
		// determine if the item is a special item
		System.out.print(
				"Enter " + SPECIAL_ITEM + " if this item is a special item,"
				+ " or any integer if it is not:");
		specialItem = input.nextInt();
		
		if(specialItem == SPECIAL_ITEM)
		{
			correctStation = findCorrectStationForSpecialItem();
		}
		else
		{
			correctStation = findCorrectStationForRegularItem();
		}
		
		// return the correct station for storage
		return(correctStation);
	}//end findCorrectStation
	
	private int findCorrectStationForSpecialItem()
	{
		// constants
		final int FREEZER_ITEM = 1;
		final int SPECIAL_DELIVERY_ITEM = 2;
		final int IMMEDIATE_DELIVERY_ITEM = 3;
		final int FIVE_DAY_STORAGE_ITEM = 4;
		
		// variables
		int specialItem = -1;	// initialize to invalid value
		int station = -1;		// initialize to invalid value
		
		do{
			// display directory of special items
			System.out.println("SPECIAL ITEM DIRECTORY\n"
					+ FREEZER_ITEM + " - Freezer Item\n"
					+ SPECIAL_DELIVERY_ITEM + " - Special Delivery\n"
					+ IMMEDIATE_DELIVERY_ITEM + " - Immediate Delivery\n"
					+ FIVE_DAY_STORAGE_ITEM + " - Five Day Storage\n");
			
			// ask user to input special item number
			System.out.print("Enter the type of special item from the directory above: ");
			specialItem = input.nextInt();
			
		}while(specialItem != FREEZER_ITEM 
				&& specialItem != SPECIAL_DELIVERY_ITEM 
				&& specialItem != IMMEDIATE_DELIVERY_ITEM 
				&& specialItem != FIVE_DAY_STORAGE_ITEM);
		
		// determine correct station
		if(specialItem == FREEZER_ITEM)
		{
			station = FREEZER;
		}
		else if(specialItem == SPECIAL_DELIVERY_ITEM)
		{
			if(stations[SPECIAL_DELIVERY_1].getStationIsFull() == false)
			{
				station = SPECIAL_DELIVERY_1;
			}
			else
			{
				station = SPECIAL_DELIVERY_2;
			}
		}
		else if(specialItem == IMMEDIATE_DELIVERY_ITEM)
		{
			station = IMMEDIATE_DELIVERY;
		}
		else if(specialItem == FIVE_DAY_STORAGE_ITEM)
		{
			station = FIVE_DAY_STORAGE;
		}
		else
		{
			System.out.println("Special item number " + specialItem + " not recognized."
					+ " No change made.");
		}
		
		// return correct station number
		return(station);
	}//end findCorrectStationForSpecialItem method
	
	private int findCorrectStationForRegularItem()
	{
		int station = -1;	// initialize to invalid value
		int startPos = -1;	// where the robot should start looking
		int bound = -1;		// where the robot should stop looking
		
		// determine if item serial number is even or odd
		// and choose to store on left/right accordingly
		if(robot.getItemSerialNum()%2 == 0)
		{
			// the item should be stored on left
			startPos = 2;
			bound = stations.length;
		}
		else
		{
			// the item should be stored on the right
			startPos = 1;
			bound = stations.length-1;
		}//end if
		
		// look at the stations for the correct side of the aisle
		for(int i = startPos; i < bound; i += 2)
		{
			// exclude the special storage stations
			if(i != FREEZER 
					&& i != SPECIAL_DELIVERY_1 
					&& i != SPECIAL_DELIVERY_2 
					&& i != IMMEDIATE_DELIVERY 
					&& i != FIVE_DAY_STORAGE)
			{

				// if the station is NOT one of the special storage units
				// test if the station is already full
				if(stations[i].getStationIsFull() == false)
				{
					// if the station is NOT full
					// check if there is only one slot remaining
					if(stations[i].getSlotIsFull(stations[i].getNumSlots()-2) 
							== true)
					{
						// if there is only one slot,
						// the item weight needs to be checked
						if(robot.getItemWeight() < 50)
						{
							// if the item weight is below 50, 
							// the item can be placed here
							// pass the station number
							station = i;
							// exit the loop
							i = stations.length;
						}
						else
						{
							// if the item weight is above 50, 
							// the item cannot be placed here 
							// and another station must be checked
							System.out.println(stations[i].getName() 
									+ " has only one slot left. Item #"
									+ robot.getItemSerialNum() 
									+ " is too heavy for this slot.");
						}//end item weight check if
					}
					else
					{
						// there is no potential conflict,
						// pass the station number
						station = i;
						// exit the loop
						i = stations.length;
					}//end last slot is only slot left if
				}//end station is full check if
			}//end special storage unit check if
		}//end of for loop
		
		return(station);
	}//end findCorrectStationForRegularItem method
	
}//end Warehouse class
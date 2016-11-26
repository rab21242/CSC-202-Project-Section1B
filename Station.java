/*
 *	Project Section #1 Part B
 * 	Station Class
 * 	
 * 	Author: Connor Baker, Rae Bouldin
 * 	Date:	11/17/2016
 */

package section1B_package;

public class Station
{
	// ATTRIBUTES
	private String name;		  	// name of the station
	private Item[] slots;		  	// slots in the station
	private boolean[] slotIsFull; 	// used to determine if one slot is full
	private boolean stationIsFull;	// used to determine if all slots are full
	
/******************************* CONSTRUCTORS ******************************/
	
	public Station(String n, int capacity)
	/*
	 * 	n - name of station
	 * 	capacity - total slots in the station
	 */
	{
		name = n;
		
		// create array and initialize array elements
		slots = new Item[capacity];
		for(int i = 0; i < slots.length; i++)
		{
			slots[i] = new Item();
		}
		
		// create array and initialize array elements
		slotIsFull = new boolean[capacity];
		for(int i = 0; i < slotIsFull.length; i++)
		{
			slotIsFull[i] = false;
		}
		
		stationIsFull = false;
	}//end Station constructor
	
/******************************* GET METHODS *******************************/
	
	public int getNumSlots()
	{
		return(slots.length);
	}//end getNumSlots method
	
	public int getSlotSerialNum(int location)
	/*
	 * 	location - location in the array to check
	 */
	{
		if(location >= 0 && location < slots.length)
		{
			return(slots[location].getSerialNum());
		}
		else
		{
			System.out.println("ERROR IN " + name
					+ ": Location out of array bounds. "
					+ "Returning int 0.");
			return(0);
		}
	}//end getSlotSerialNum
	
	public double getSlotWeight(int location)
	/*
	 * 	location - location in the array to check
	 */
	{
		if(location >= 0 && location < slots.length)
		{
			return(slots[location].getWeight());
		}
		else
		{
			System.out.println("ERROR IN " + name
					+ ": Location out of array bounds. "
					+ "Returning double 0.0.");
			return(0.0);
		}
	}//end getSlotWeight
	
	public boolean getSlotIsFull(int location)
	/*
	 * 	location - location in the array to check
	 */
	{
		return(slotIsFull[location]);
	}//end getSlotIsFull method
	
	public boolean getStationIsFull()
	{
		return(stationIsFull);
	}//end getStationIsFull method
	
/************************* PROBLEM SPECIFIC METHODS ************************/
	
	private void testStationIsFull()
	{
		int cntr = 0;	// a counter
		
		for(int i = 0; i < slots.length; i++)
		{
			if(slotIsFull[i] == true)
			{
				cntr++;
			}
			else
			{
				i = slots.length;
			}
		}
		
		if(cntr == slots.length)
		{
			stationIsFull = true;
		}
	}//end testStationIsFull method
	
	public void fillNextSlot(int sn, double w)
	/*
	 * 		sn - item serial number
	 * 		w - item weight
	 */
	{
		if(stationIsFull == false)
		{
			for(int i = 0; i < slots.length; i++)
			{
				// check if this slot is full
				if(slotIsFull[i] == false)
				{
					// if slot is not full
					slots[i] = new Item(sn, w);
					slotIsFull[i] = true;
					testStationIsFull();
					
					// inform the user
					System.out.println("FILLING " + name 
							+ " Slot " + i + " ...");
					
					// exit the for loop
					i = slots.length;
				}
				else
				{
					// if slot is full, inform the user
					System.out.println(name + " Slot " + i + " is full.");
				}//end check if slot is full
			}
		}//end if(stationIsFull == false)
		else
		{
			System.out.println(name + " cannot hold any more items.");
		}
	}//end fillSlot method
	
	public void unloadSlot(int location)
	/*
	 * 	location - location in the array to check
	 */
	{
		if(location >= 0 && location < slots.length)
		{
			slots[location] = new Item();
			slotIsFull[location] = false;
			testStationIsFull();
		}
		else
		{
			// inform user of error
			System.out.println("ERROR IN " + name
					+ ": Location " + location 
					+ " out of slots array bounds.");
		}
	}//end unloadSlot method
	
}//end Station class
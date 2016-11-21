/*
 *	Project Section #1 Part B
 * 	Robot Class
 * 	
 * 	Author: Connor Baker, Rae Bouldin
 * 	Date:	11/17/2016
 */

package section1B_package;

public class Robot
{
	// ATTRIBUTES
	private boolean hasItem;
	private Item heldItem;
	
/******************************* CONSTRUCTORS ******************************/
	
	public Robot()
	{
		hasItem = false;
		heldItem = new Item();
	}//end Robot default constructor
	
/******************************* SET METHODS *******************************/
	
	
	
/******************************* GET METHODS *******************************/
	
	public int getItemSerialNum()
	{
		return(heldItem.getSerialNum());
	}//end getItemSerialNum
	
	public double getItemWeight()
	{
		return(heldItem.getWeight());
	}//end getItemWeight method
	
/********************************* ACTIONS *********************************/
	
	public void pickItem(int sn, double w)
	/*
	 * 		sn - serial number of the item
	 * 		w - weight of the item
	 */
	{
		if(hasItem == false)
		{
			// if the robot does not already have an item, pass the new
			// item information and tell the robot it is holding something
			heldItem = new Item(sn, w);
			hasItem = true;
		}
		else
		{
			// if the robot is already holding an item, inform the user of
			// the error
			System.out.println("The robot is already holding an item"
					+ " and cannot pickup another.");
		}
	}//end pickItem method
	
	public void dropItem()
	{
		if(hasItem == true)
		{
			// if the robot has an item, drop it and tell the robot it
			// is no longer holding anything
			heldItem = new Item();
			hasItem = false;
		}
		else
		{
			// if the robot does not have an item, inform the user of
			// the error
			System.out.println("The robot is not holding anything"
					+ " and cannot drop an item.");
		}
	}//end dropItem method
	
}//end Robot class
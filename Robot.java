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
	private int position;		// current position of robot in its location
	private boolean isInAisle;	// determines possible lateral movement
	private boolean hasItem;	// is the robot holding an item?
	private Item heldItem;		// the item being held by the robot
	
/******************************* CONSTRUCTORS ******************************/
	
	public Robot(int startingPosition)
	/*
	 * 		startingPosition - location in warehouse where robot spawns
	 */
	{
		position = startingPosition;
		isInAisle = true;
		hasItem = false;
		heldItem = new Item();
	}//end Robot default constructor
	
/******************************* GET METHODS *******************************/
	
	public int getPostition()
	{
		return(position);
	}//end getPosition method
	
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
			// if the robot does not already have an item, 
			
			// pass the new item information
			heldItem = new Item(sn, w);
			// tell the robot it is holding something
			hasItem = true;
			
			// inform the user that the robot is picking up an item
			System.out.println("\nThe robot has picked up item #"
					+ heldItem.getSerialNum() + ".");
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
			// if the robot has an item,
			
			// inform the user that the robot is dropping the item
			System.out.println("The robot has dropped item #"
					+ heldItem.getSerialNum() + ".");
			
			// drop the item
			heldItem = new Item();
			// tell the robot it is no longer holding anything
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
	
/******************************** MOVEMENTS ********************************/
	
	public void moveForward(int movementBound)
	/*
	 * 		movementBound - used to determine boundary of movement
	 */
	{
		if(position != movementBound)
		{
			// move robot forward
			position++;
			
			// tell user the robot's location
			System.out.println("The robot has moved FORWARD"
					+ " and is now in aisle " + position + ".");
		}
		else
		{
			// error; robot cannot move forward
			System.out.println("The robot has reached the end of the"
					+ " warehouse and cannot move any farther FORWARD.");
		}
	}//end moveForward method
	
	public void moveBackward(int movementBound)
	/*
	 * 		movementBound - used to determine boundary of movement
	 */
	{
		if(position != movementBound)
		{
			// move robot backwards
			position--;
			
			// tell user the robot's location
			if(position == 0)
			{
				// special case; robot is in pickup station
				System.out.println("The robot has moved BACKWARD"
						+ " and is now in the Pickup Station.");
			}
			else
			{
				System.out.println("The robot has moved BACKWARD"
						+ " and is now in aisle " + position + ".");
			}
		}//end of if statement (position != movementBound)
		else
		{
			// error; robot cannot move backward
			System.out.println("The robot has reached the beginning of the"
					+ " warehouse and cannot move any farther BACKWARD.");
		}
	}//end moveBackward method
	
	public void moveToSide()
	{
		boolean isOdd;
		
		if(heldItem.getSerialNum()%2 == 0)
		{
			isOdd = false;
		}
		else
		{
			isOdd = true;
		}
		
		if(isInAisle == true)
		{
			if(isOdd == false)
			{
				System.out.println("The robot has moved into"
						+ " the station on the LEFT.");
			}
			else
			{
				System.out.println("The robot has moved into"
						+ " the station on the RIGHT.");
			}
			isInAisle = false;
		}
		else
		{
			System.out.println("The robot has moved out of the station.");
			isInAisle = true;
		}
	}//end moveToSide method
	
}//end Robot class
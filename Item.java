/*
 *	Project Section #1 Part B
 * 	Item Class
 * 	
 * 	Author:	Connor Baker, Rae Bouldin
 * 	Date:	11/17/2016
 */

package section1B_package;
import java.util.Scanner;

public class Item
{
	// ATTRIBUTES
	private int serialNum;	// 5-digit serial number of the item
	private double weight;	// weight of the item in kg
	
/******************************* CONSTRUCTORS ******************************/
	
	public Item()
	{
		serialNum = 00000;
		weight = 0.0;
	}//end Item default constructor
	
	public Item(int sn, double w)
	/*
	 * 		sn - serial number
	 * 		w - weight
	 */
	{
		serialNum = sn;
		weight = w;
	}//end Item constructor
	
/******************************* GET METHODS *******************************/
	
	public int getSerialNum()
	{
		return(serialNum);
	}//end getSerialNum method
	
	public double getWeight()
	{
		return(weight);
	}//end getWeight method

/****************************** INPUT METHODS ******************************/
	
	public void inputItemDetails()
	{
		serialNum = inputValidSerialNum();
		weight = inputValidWeight();
	}//end inputItemDetails method
	
	private int inputValidSerialNum()
	{
		Scanner input = new Scanner(System.in);	// create a new Scanner
		int sn = 0;	// to hold values
		
		do	// loop until input is between 10000 and 99999
		{
			// input the serial number
			System.out.print("Input 5-digit serial number: ");
			serialNum = input.nextInt();
			
			if(sn < 10000 || sn > 99999)
			{
				// tell the user what went wrong
				System.out.println("Enter a valid serial number"
						+ " between the values 10000 and 99999.");
			}
		}while(sn < 10000 || sn > 99999);
		
		// close input stream
		input.close();
		
		// return the valid sn
		return(sn);
	}//end inputValidSerialNum method
	
	private double inputValidWeight()
	{
		Scanner input = new Scanner(System.in);	// create a new Scanner
		double w = 0.0;	// to hold values
		
		do	// loop until the input is > 0.0
		{
			// input the weight
			System.out.print("\nInput weight (kg): ");
			w = input.nextDouble();
			
			if(w <= 0.0)
			{
				// tell the user what went wrong
				System.out.println("Enter a valid weight"
						+ " (your item cannot weigh nothing).");
			}
		}while(w <= 0.0);
		
		// close input stream
		input.close();
		
		// return the valid w
		return(w);
	}//end inputValidWeight method
	
}//end Item class
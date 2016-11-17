/*
 *	Project Section #1 Part B
 * 	Warehouse Class
 * 	
 * 	Author: Connor Baker, Rae Bouldin
 * 	Date:	11/17/2016
 */

package section1B_package;

public class Warehouse
{
	// ATTRIBUTES
	private Robot robot;
	private Station[] stations;
	
/******************************* CONSTRUCTORS ******************************/
	
	public Warehouse()
	{
		robot = new Robot();
		
		// create and initialize array of stations
		stations = new Station[13];
		for(int i = 0; i < stations.length; i++)
		{
			if(i == 0)
			{
				stations[i] = new Station("Pickup Station", 0);
			}
			else if(i <= 10)
			{
				stations[i] = new Station("Station " + i, 8);
			}
			else
			{
				if(i == 11)
					stations[i] = new Station("Station 12", 4);
				if(i == 12)
					stations[i] = new Station("Station 14", 4);
			}
		}//end for loop
	}//end Warehouse constructor
	
/******************************* SET METHODS *******************************/
	
	
	
/******************************* GET METHODS *******************************/
	
	
	
/******************************* RUN METHOD ********************************/
	
	
	
/************************* PROBLEM SPECIFIC METHODS ************************/
	
	
	
}//end Warehouse class
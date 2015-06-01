package polly.TSP;

import java.io.IOException;

/*
 * An example class used to pull in and display results from the 
 * DroneTSP program
 */


public class MainController {
	public static void main(String[] args) throws IOException {
		
		DroneTSP dtsp = new DroneTSP();
		
		double[][] result = dtsp.getResult();
		
		printResults(result);
	}
	
	/*
	 * Method prints results in the format of x,y,z,i per row 
	 */
	public static void printResults(double[][] result)
	{
		for(int i = 0; i<result.length; i++){
			for(int j = 0; j<result[0].length; j++){
				System.out.print(result[i][j]+ " ");
			}
			System.out.println("");
		}
	}
}

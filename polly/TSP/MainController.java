package polly.TSP;

import java.io.IOException;

public class MainController {
	public static void main(String[] args) throws IOException {
		
		DroneTSP dtsp = new DroneTSP();
		double[][] results =dtsp.getResult();
		for(int i = 0; i<results.length; i++){
			for(int j = 0; j<results[0].length; j++){
				System.out.print(results[i][j]+ " ");
			}
			System.out.println("");
		}
	}
}

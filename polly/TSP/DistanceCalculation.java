package polly.TSP;

import java.io.IOException;

public class DistanceCalculation {
	double[][] distances;
	private boolean run = false;
	
	public static void main(String[] args) throws IOException {
		
	}
	
	public DistanceCalculation (double[][] nodeCoordinates){
		runCalculation(nodeCoordinates);
	}
	
	public void runCalculation(double[][] nodeCoordinates){
		distances = new double[nodeCoordinates.length][nodeCoordinates.length];
		for (int i = 0; i < nodeCoordinates.length; i++){
			for (int j = 0; j < nodeCoordinates.length; j++){
				distances[i][j] = Math.sqrt((Math.pow((nodeCoordinates[j][0]-nodeCoordinates[i][0]), 2))+
						(Math.pow((nodeCoordinates[j][1]-nodeCoordinates[i][1]), 2))+
						(Math.pow((nodeCoordinates[j][2]-nodeCoordinates[i][2]), 2)));
			}
		}
		run = true;
	}
	
	public double[][] getDistanceMatrix(){
		if (run){
			return distances;
		} else {
			System.out.println("Matrix has not been calculated yet");
		}
		return distances;
	}
}
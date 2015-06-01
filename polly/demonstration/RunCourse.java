package polly.demonstration;

import java.io.IOException;

import polly.Command.Drone;
import polly.TSP.DroneTSP;
import polly.TSP.MainController;
import polly.imagecapture.ImageDecoder;

public class RunCourse {
	
	static float wallQRDistance = 0.5f;
	static float floorQRDistance = 1f;
	static ImageDecoder decoder;

	public static void main(String[] args) {
		// Run TSP
		double[][] route = generateRoute();
		System.out.println("Running the following route: ");
		MainController.printResults(route);
		// run drone
		initialiseDrone();
		takeOff();
		runRoute(route);
		land();

	}
	
	private static void runRoute(double[][] points){
		for(int i = 0; i <= points.length -1 ; i++){
			double[] readingPoint = findReadingPoint(points[i]);
			moveToPoint(readingPoint);
			attemptQRDecode();
		}
		
	}
	
	private static void attemptQRDecode() {
		// TODO implement simple decode method
		//TODO: alter path slightly if QR code an not be found
	}

	private static void moveToPoint(double[] readingPoint) {
		// TODO implement move to method
	}

	private static double[] findReadingPoint(double[] point) {
		if(point[3] == 0){ //QR code on wall
			point[1] = point[1] - wallQRDistance;
		}else if(point[3] == 1){/// QR code on floor
			point[2] = point[2] - floorQRDistance;
		}
		return point;
	}

	// TODO:configure the height of takeoff to equal the optimal distance to
	// take floor QR codes
	private static void takeOff() {
		try {
			Drone.takeOff();
			//Thread.sleep(4000);//possibly need to sleep after commands?
		} catch (IOException e) {
			System.out.println("ERROR occured during takeoff");
			e.printStackTrace();
		}
	}
	
	private static void land(){
		try {
			Drone.land();
		} catch (IOException e) {
			System.out.println("ERROR occured when landing");
			e.printStackTrace();
		}
		Drone.disconnect();
	}
	
	private static void initialiseDrone() {
		try {
			Drone.initialise();
			Drone.trimFlat();
			Thread.sleep(2500);
		} catch (IOException | InterruptedException e) {
			System.out.println("ERROR occured when initialising drone");
			e.printStackTrace();
		}
		decoder = new ImageDecoder();
	}
	
	private static double[][] generateRoute() {
		DroneTSP dtsp = new DroneTSP();
		double[][] result = dtsp.getResult();
		return result;
	}

}

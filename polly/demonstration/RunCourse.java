package polly.demonstration;

import java.io.IOException;

import polly.Command.Drone;
import polly.TSP.DroneTSP;
import polly.TSP.MainController;
import polly.imagecapture.ImageDecoder;

public class RunCourse {
	
	static float wallQRDistance = 5f;
	static float floorQRDistance = 5f;
	static ImageDecoder decoder;
	static double[] lastPoint = {0,0,0,1};
	static String secretCode = "";
	static int decodeQRTime = 3000;
	static float speed = 0.3f;

	public static void main(String[] args) throws Exception {
		// Run TSP
		double[][] route = generateRoute();
		System.out.println("Running the following route: ");
		MainController.printResults(route);
		initialiseDrone();
		takeOff();
		Thread.sleep(1500);
		// run course
		for(int i = 0; i < route.length -1; i ++){
			System.out.println("looking at co-ordinate "+ i);
			moveToPoint(route[i]);
			if(i == 0){
				goUp(1);
				decodeQRTime = 7000;
				attemptQRDecode(route[i][3]);
			}else{
				decodeQRTime = 3000;
				attemptQRDecode(route[i][3]);
			}
		}
		
		land();

	}
	
	private static void attemptQRDecode(double camera) {
		String result = "";
		if(camera == 1){
			result = decoder.decodeBelow(decodeQRTime);
		}else{
			result = decoder.decodeAhead(decodeQRTime);
		}
		secretCode += " "+ result;
		System.out.println("QR decode result result = "+ result);
	}

	private static void moveToPoint(double[] readingPoint) throws IOException, InterruptedException {
		readingPoint = findReadingPoint(readingPoint);
		moveX(readingPoint[0]);
		Thread.sleep(2000);
		moveY(readingPoint[1]);
		Thread.sleep(2000);
		movez(readingPoint[2]);
		lastPoint = readingPoint;
	}

	private static void moveX(double destX) throws IOException {
		double origX = lastPoint[0];
		double distance = (destX - origX);
		if(origX< destX){
			goRight(distance);
		}else{
			goLeft(distance);
		}
	}
	private static void moveY(double destY) throws IOException {
		double origY = lastPoint[1];
		double distance = (destY - origY);

		if(origY< destY){
			goForward(distance);
		}else{
			goBackward(distance);
		}
	}
	private static void movez(double destZ) throws IOException {
		double origZ = lastPoint[2];
		double distance = (destZ - origZ);
		if(origZ< destZ){
			goUp(distance);
		}else{
			goDown(distance);
		}
	}
	private static void goForward(double distance) throws IOException{
		long time = (long) (Math.abs(distance)/2.2);
		long t1= System.currentTimeMillis();
		long end1 = t1+time*1000;
		while(System.currentTimeMillis() < end1) {
			Drone.goForward(speed);
		}
	}
	private static void goBackward(double distance) throws IOException{
		long time = (long) (Math.abs(distance)/2.2);
		long t1= System.currentTimeMillis();
		long end1 = t1+time*1000;
		while(System.currentTimeMillis() < end1) {
			Drone.goBackward(speed);
		}
	}
	private static void goLeft(double distance) throws IOException{
		//todo: temp change
		long time = (long) (Math.abs(distance)/4);
		long t1= System.currentTimeMillis();
		long end1 = t1+time*500;
		while(System.currentTimeMillis() < end1) {
			Drone.goLeft(speed);
		}
	}
	private static void goRight(double distance) throws IOException{
		//todo temp change
		long time = (long) (Math.abs(distance)/4);
		long t1= System.currentTimeMillis();
		long end1 = t1+time*500;
		while(System.currentTimeMillis() < end1) {
			Drone.goRight(speed);
		}
	}
	private static void goUp(double distance) throws IOException{
		long t= System.currentTimeMillis();
		long end = t+1000;
		while(System.currentTimeMillis() < end) {
			Drone.goUp(0.2f);
		}
	}
	private static void goDown(double distance) throws IOException{
		/*
		long t= System.currentTimeMillis();
		long end = t+500;
		while(System.currentTimeMillis() < end) {
			Drone.goDown(0.2f);
		}
		*/
	}
	private static double[] findReadingPoint(double[] point) {
		if(point[3] == 0){ //QR code on wall
			point[1] = point[1] - wallQRDistance;
		}else if(point[3] == 1){/// QR code on floor
			point[2] = point[2]; //- floorQRDistance;
		}
		return point;
	}

	private static void takeOff() {
		try {
			Drone.takeOff();
			Thread.sleep(2000);
		} catch (IOException | InterruptedException e) {
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
	
	private static void initialiseDrone() throws Exception {
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
		//droneTSP has already ran and a course is defined
		DroneTSP dtsp = new DroneTSP();
		double[][] result = dtsp.getResult();
		return result;
	}

}

package Polly.Command;

import java.io.IOException;

import Polly.Timer;
import Polly.Command.DroneATCommandGenerator;
import Polly.Command.DroneATCommandGenerator.PCMD;

import java.net.SocketException;

public class Drone {

	private static DroneConnection droneConnection;

	public static void initialise(){

		//connect to the drone unless connected already
		if (droneConnection == null){
			try {
				droneConnection = new DroneConnection();
			} catch (SocketException e) {
				System.err.println("SocketException: "+e.getMessage());
			}	
		}
		//tries to calibrate the trim. ASSUMES drone is on a flat surface when initialised.
		//FAILS SILENTLY if can't connect
		try {
			Drone.trimFlat();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public static void disconnect() {
		droneConnection.close();
	}

	public static void land() throws IOException{
		transmitREFCommand(DroneATCommandGenerator.REF.LAND);
	};
	public static void takeOff() throws IOException{
		transmitREFCommand(DroneATCommandGenerator.REF.TAKEOFF);
	}
	public static void trimFlat() throws IOException{
		try {
			Thread.sleep(500);
		} catch (InterruptedException e) {e.printStackTrace();}
		sendRawATCommand("AT*FTRIM");
	}
	
	public static void goForward(float speed) throws IOException{
		transmitPCMDCommand(PCMD.PITCH_GO_FORWARD, -speed);
	}
	public static void goUp(float speed) throws IOException{
		transmitPCMDCommand(PCMD.ALTITUDE_GO_UP, speed);
	}
	public static void turnClockWise(float speed) throws IOException{
		transmitPCMDCommand(PCMD.TURN_TURN_CLOCKWISE, speed);
	}
	
	public static void sendRawATCommand(String atCommandString) throws IOException{
		droneConnection.transmitStringLn(atCommandString);
	}
	
	
	
	
	private static void transmitREFCommand(DroneATCommandGenerator.REF refCommand) throws IOException{
		droneConnection.transmitStringLn(
				DroneATCommandGenerator.toString(droneConnection.getSequenceNumber(), refCommand)
				);
	}
	private static void transmitPCMDCommand(DroneATCommandGenerator.PCMD pcmdCommand, float parameter) throws IOException{
		droneConnection.transmitStringLn(
				DroneATCommandGenerator.toString(droneConnection.getSequenceNumber(), pcmdCommand, parameter)
				);
	}

	

}

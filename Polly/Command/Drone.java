package Polly.Command;

import java.io.IOException;
import java.net.SocketException;

public class Drone {

	private static DroneATCommandGenerator droneConnection;

	public static void initialise(){

		//connect to the drone unless connected already
		if (droneConnection == null){
			try {
				droneConnection = new DroneATCommandGenerator();
			} catch (SocketException e) {
				System.err.println("SocketException: "+e.getMessage());
			}	
		}

	}

	public static void land(){
		transmitREFCommand(DroneCommand.REF.LAND);
	};
	public static void takeOff(){
		transmitREFCommand(DroneCommand.REF.TAKEOFF);
	}
	
	public static void sendRawATCommand(String atCommandString) throws IOException{
		droneConnection.transmitStringLn(atCommandString);
	}
	
	
	
	
	private static void transmitREFCommand(DroneCommand.REF refCommand){
		droneConnection.transmitStringLn(
				DroneATCommandGenerator.toString(droneConnection.getSequenceNumber(), refCommand)
				);
	}

}

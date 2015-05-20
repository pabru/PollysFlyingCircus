package Polly.Command;

import java.io.InputStream;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.Socket;
import java.nio.ByteBuffer;

public class Test {

	
	public static void qmain(String args[]) throws Exception
    {
    	Socket sock = new Socket("192.168.1.1", 5559);
    	
    	System.out.println("Here 1");
        InputStream is = sock.getInputStream();
        System.out.println("Here 2");
        int read;
        byte[] buffer = new byte[1024];
        while((read = is.read(buffer)) != -1) 
        {
            String output = new String(buffer, 0, read);
            System.out.print(output);
            System.out.flush();
        }
        sock.close();
    }
	
	public static void main(String args[]) throws Exception
	{
		Drone.initialise();
		
		//Drone.sendRawATCommand(DroneATCommandGenerator.toString(6, DroneATCommandGenerator.PCMD.PITCH_GO_FORWARD, -0.9f));
		
    //    Drone.sendRawATCommand("AT*CONFIG=1,\"control:altitude_max\",\"2000\"");
	
		Drone.takeOff();
		
		Drone.goForward(0.9f, 5000000);
		
		Thread.sleep(10000);
		
		Drone.land();
		
		Drone.disconnect();
	}
	

	
}

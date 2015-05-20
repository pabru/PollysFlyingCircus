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
		Drone.takeOff();
		
		Thread.sleep(2500);
		
		routine2();
		
		Drone.land();
		Drone.disconnect();
	}
	
	private static void routine2() throws Exception{
		Drone.goUp(0.3f, 5*100);
		
		Thread.sleep(1500);
		
		Drone.goUp(-0.3f, 5*100);
	}
	
	private static void routine1(){
//	    Drone.sendRawATCommand("AT*CONFIG=1,\"control:altitude_max\",\"2000\"");
		
	}

	
}

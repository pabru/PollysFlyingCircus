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
		
		Drone.sendRawATCommand(DroneCommand.toString(6, DroneCommand.PCMD.PITCH_GO_FORWARD, -0.9f));
		
        Drone.sendRawATCommand("AT*CONFIG=1,\"control:altitude_max\",\"2000\"");
	
		Drone.takeOff();
		
		Thread.sleep(10000);
		
		Drone.land();
		
		atsocket.close();
    	ndsocket.close();
	}
	
	public static void send_at_cmd(String at_cmd) throws Exception 
    {
 
    	System.out.println("AT command: " + at_cmd);    	
    	byte[] buffer = (at_cmd + "\r").getBytes();
    	
    	DatagramPacket packet = new DatagramPacket(buffer, buffer.length, inet_addr, 5556);
    	
    	atsocket.send(packet);
    	System.out.println("Hello!");
    	//sock.close();
    	//socket.receive(packet); //AR.Drone does not send back ack message (like "OK")
    	//System.out.println(new String(packet.getData(),0,packet.getLength()));   	
    }
	
	
}

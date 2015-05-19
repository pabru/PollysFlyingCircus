package PollysFlyingCircus;

import java.io.InputStream;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.Socket;
import java.nio.ByteBuffer;

public class Test {

	private static InetAddress inet_addr = null; 
	private static DatagramSocket atsocket = null;
	private static DatagramSocket ndsocket = null;
	public static void qqmain(String args[]) throws Exception
	{
		ByteBuffer bb = ByteBuffer.allocate(4);
		
		bb.put((byte)0,(byte)255);
		bb.put((byte)1,(byte)255);
		bb.put((byte)2,(byte)8);
		bb.put((byte)3,(byte)0);
		System.out.println(bb.getShort(0));
		System.out.println(bb.getShort(2));
	}
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
		
		int seq = 1;
		byte[] ip_bytes = new byte[4];
    	ip_bytes[0] = (byte)192;
    	ip_bytes[1] = (byte)168;
    	ip_bytes[2] = (byte)1;
    	ip_bytes[3] = (byte)1;
    	
        inet_addr = InetAddress.getByAddress(ip_bytes);
        atsocket = new DatagramSocket();
       	ndsocket = new DatagramSocket();
        atsocket.setSoTimeout(3000);
        ndsocket.setSoTimeout(3000);
		
        send_at_cmd("AT*CONFIG=1,\"control:altitude_max\",\"2000\"");
	
		String action = "Takeoff";
		String at_cmd = "AT*REF=" + (seq++) + ",290718208";
		send_at_cmd(at_cmd);
		
		Thread.sleep(10000);
		
		action = "Landing";
		at_cmd = "AT*REF=" + (seq++) + ",290717696";
		send_at_cmd(at_cmd);
		
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

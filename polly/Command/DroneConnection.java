package polly.Command;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;

public class DroneConnection {

	private int seq = 1;

	private InetAddress inet_addr = null; 
	private DatagramSocket atCommandSocket = null;
	private DatagramSocket navigationDataSocket = null;

	public int getSequenceNumber(){
		return seq;
	}

	//Transmit a raw string to the DroneConnection's socket
	public void transmitString(String contents) throws IOException{
		++seq;
		System.out.println("AT command sent: " + contents);
		byte[] buffer = (contents + "\r").getBytes();
		DatagramPacket packet = new DatagramPacket(buffer, buffer.length, inet_addr, 5556);
		atCommandSocket.send(packet);
	}
	
	public void transmitStringLn(String contents) throws IOException{
		transmitString(contents+"\r");
	}


	//Tries to establish a connection with the drone. FAILS SILENTLY if unable to establish connection.  
	public void tryEstablishConnection(){
		byte[] ip_bytes = new byte[4];
		ip_bytes[0] = (byte)192;
		ip_bytes[1] = (byte)168;
		ip_bytes[2] = (byte)1;
		ip_bytes[3] = (byte)1;

		try {
			inet_addr = InetAddress.getByAddress(ip_bytes);

			atCommandSocket = new DatagramSocket();
			atCommandSocket.setSoTimeout(3000);
			
			navigationDataSocket = new DatagramSocket();
			navigationDataSocket.setSoTimeout(3000);			
			
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SocketException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void close(){
		atCommandSocket.close();
		navigationDataSocket.close();
	}


	public DroneConnection() throws SocketException{

		tryEstablishConnection();
	}


}

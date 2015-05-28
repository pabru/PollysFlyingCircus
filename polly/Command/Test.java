package polly.Command;

import polly.imagecapture.QRReaderTest;


public class Test {
	
	public static void main(String args[]) throws Exception
	{
		Drone.initialise();
		Thread.sleep(2500);
		Drone.takeOff();
		
		Thread.sleep(4000);
		
		naviQR();
		
		Drone.land();
		Drone.disconnect();
	}
	
	private static void naviQR() throws Exception{
		
		long t= System.currentTimeMillis();
		long end = t+300;
		while(System.currentTimeMillis() < end) {
			Drone.goUp(1f);
		}
		
		
		
		long t1= System.currentTimeMillis();
		long end1 = t1+2000;
//		String qrDecoded = null;
		while(System.currentTimeMillis() < end1 ) {
//			qrDecoded = QRReaderTest.getQRBelow();
//			System.err.println("exiting qr recog");
			Drone.goForward(0.5f);
		}
		
		long t2= System.currentTimeMillis();
		long end2 = t2+3000;
//		String qrDecoded = null;
		while(System.currentTimeMillis() < end2 ) {
//			qrDecoded = QRReaderTest.getQRBelow();
//			System.err.println("exiting qr recog");
			Drone.goForward(-0.5f);
		}
		
		long t3= System.currentTimeMillis();
		long end3 = t3+3142;
//		String qrDecoded = null;
		while(System.currentTimeMillis() < end3 ) {
//			qrDecoded = QRReaderTest.getQRBelow();
//			System.err.println("exiting qr recog");
			Drone.turnClockWise(-1f);;
		}
		
		
//		for(int i=0; i<4000; ++i){
//			Drone.turnClockWise(-0.1f);
//		}
//		
//		for(int i=0; i<5000; ++i){
//			Drone.goForward(0.7f);
//		}
	}
	
	private static void routine1(){
//	    Drone.sendRawATCommand("AT*CONFIG=1,\"control:altitude_max\",\"2000\"");
		
	}

	
}

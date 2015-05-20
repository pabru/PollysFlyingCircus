package polly.Command;


public class Test {
	
	public static void main(String args[]) throws Exception
	{
		Drone.initialise();
		Drone.trimFlat();
		Thread.sleep(2500);
		Drone.takeOff();
		
		Thread.sleep(4000);
		
		routine2();
		
		Drone.land();
		Drone.disconnect();
	}
	
	private static void routine2() throws Exception{
		
		long t= System.currentTimeMillis();
		long end = t+700;
		while(System.currentTimeMillis() < end) {
			Drone.goUp(1f);
		}
		
		long t1= System.currentTimeMillis();
		long end1 = t1+4000;
		while(System.currentTimeMillis() < end1) {
			
			Drone.goForward(1f);
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

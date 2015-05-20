package Polly.Command;


public class DroneATCommandGenerator {

	public static enum PCMD{
		//PITCH_GO_FORWARD("1,", ",0,0,0"),
		ALTITUDE_GO_UP("1,0,0,", ",0");

		private String prefix;
		private String postfix;

		private PCMD(String prefix, String  postfix){
			this.prefix = prefix;
			this.postfix = postfix;
		}

		public String getPrefix(){
			return prefix;
		}
		public String getPostfix(){
			return postfix;
		}
	}

	public static enum REF{
		TAKEOFF(290718208),
		LAND(290717696);

		private int value;

		private REF(int value){
			this.value = value;
		}

		public int getValue(){
			return value;
		}
	}

	private final static String pcmdATPrefix = "AT*PCMD=";
	private final static String refATPrefix = "AT*REF=";
	
	public static String toString(int sequenceNumber, PCMD pcmdCommand, float parameter){
		return pcmdATPrefix + sequenceNumber + "," + 
				pcmdCommand.prefix + 
				DroneFloatToIntEncoder.encode(parameter) + 
				pcmdCommand.postfix
				+ "\r";
	}

	public static String toString(int sequenceNumber, REF refCommand){
		return refATPrefix + sequenceNumber + "," + 
				refCommand.value + 
				"\r";
	}

}

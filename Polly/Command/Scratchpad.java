package Polly.Command;

import java.nio.ByteBuffer;
import java.nio.FloatBuffer;
import java.nio.IntBuffer;

public class Scratchpad {

	static FloatBuffer fb;
	static IntBuffer ib;
	
	
	public static void main(String[] args){
		
		System.err.println(intOfFloat(0.001f));
		
	//	System.out.println(DroneFloatToIntEncoder.encode(0.001f));
		System.out.println(DroneFloatToIntEncoder.encode(0.1f));
		
	}
	
	
	public static int intOfFloat(float f) {
		ByteBuffer bb = ByteBuffer.allocate(4);
		
		ib = bb.asIntBuffer();
		
		fb = bb.asFloatBuffer();
		
		
		
		fb.put(0, f);
		return ib.get(0);
	}
	
}

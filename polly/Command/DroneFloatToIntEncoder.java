package polly.Command;

import java.nio.ByteBuffer;
import java.nio.FloatBuffer;
import java.nio.IntBuffer;

public class DroneFloatToIntEncoder {
	public static ByteBuffer byteBuffer = ByteBuffer.allocate(4);
	public static FloatBuffer floatBuffer = byteBuffer.asFloatBuffer();
	public static IntBuffer intBuffer = byteBuffer.asIntBuffer();
	
	public static int encode(float inputNumber){
		floatBuffer.put(0, inputNumber);
		return intBuffer.get(0);
	}
}
package polly.imagecapture;

import java.awt.image.BufferedImage;

public class QRReaderTest {

	static QRDecoder decoder = new QRDecoder();
	static DroneCamera droneCamera = new DroneCamera();

	public static void main(String[] args) {
		//read QR code from image
		System.out.println("QRCode from image: "+ getQRString("exampleQRCode.png"));
		//captureImageFromDrone
		//ahead
		BufferedImage aheadPicture = droneCamera.takePictureAhead();
		//below
		BufferedImage belowPicture = droneCamera.takePictureBellow();;
		//Decode images
		System.out.println("QRCode from ahead Image: "+ getQRString(aheadPicture));
		System.out.println("QRCode from ahead Image: "+ getQRString(belowPicture));
	}

	private static String getQRString(String filename) {
		try {
			return decoder.DecodeQRCode("exampleQRCode.png");
		} catch (Exception e) {
			e.printStackTrace();
			return "something went wrong when reading decoding a QR local image";
		}	
	}
	
	private static String getQRString(BufferedImage img ) {
		try {
			return decoder.DecodeQRCode(img);
		} catch (Exception e) {
			e.printStackTrace();
			return "something went wrong when reading decoding a QR local image";
		}	
	}
	
	
}

package polly.imagecapture;

import java.awt.image.BufferedImage;

import oldQRCode.QRDecoder;
import jp.sourceforge.qrcode.QRCodeDecoder;

public class QRReaderTest {

	static QRDecoder decoder = new QRDecoder();
	static DroneCamera droneCamera = new DroneCamera();

	public static void main(String[] args) {
		ImageDecoder decoder = new ImageDecoder();
		System.out.println("result = "+decoder.decodeBelow(0));
	/*
		boolean nullImage = true;
		while (nullImage == true) {
			System.out.println("taking picture");
			BufferedImage aheadPicture = droneCamera.takePictureAhead();
			// below
			// BufferedImage belowPicture = droneCamera.takePictureBellow();;
			// Decode images
			String AheadString = QRDecoder(aheadPicture);
			// System.out.println("QRCode from ahead Image: "+
			// getQRString(belowPicture));
			if (AheadString != "") {
				System.out.println("QRCode from ahead Image: !" + AheadString+"!");
				nullImage = false;
			}
		}
		*/
	}
	
	public static String QRDecoder(BufferedImage im) {
	String decodedString = "";
	QRCodeDecoder decoder = new QRCodeDecoder();
	try {
		decodedString = new String(decoder.decode(new J2SEImage(im)));
	} catch (Exception E) {
		decodedString = "";
	}
	return (decodedString);
}

}

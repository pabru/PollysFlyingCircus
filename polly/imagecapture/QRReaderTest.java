package polly.imagecapture;

import java.awt.image.BufferedImage;
import jp.sourceforge.qrcode.QRCodeDecoder;

/**
 * The test class used in our first demonstration.
 * 
 * @author liz
 *
 */
public class QRReaderTest {

	// static QRDecoder decoder = new QRDecoder();
	//static DroneCamera droneCamera = new DroneCamera();

	public static void main(String[] args) {
		ImageDecoder decoder = new ImageDecoder();
		System.out.println("result = " + decoder.decodeBelow(0));
		// old code used in 1st demonstration. The snippet continuously takes a
		// picture
		// and tries to decipher a QR code. the process will continue to run
		// until
		// a QR code is deciphered
		/*
		 * boolean nullImage = true; while (nullImage == true) {
		 * System.out.println("taking picture"); BufferedImage aheadPicture =
		 * droneCamera.takePictureAhead(); // below // BufferedImage
		 * belowPicture = droneCamera.takePictureBellow();; // Decode images
		 * String AheadString = QRDecoder(aheadPicture); //
		 * System.out.println("QRCode from ahead Image: "+ //
		 * getQRString(belowPicture)); if (AheadString != "") {
		 * System.out.println("QRCode from ahead Image: !" + AheadString+"!");
		 * nullImage = false; } }
		 */
	}

	/*
	 * decodes a QR code. more efficient
	 */
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

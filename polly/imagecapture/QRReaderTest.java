package polly.imagecapture;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import jp.sourceforge.qrcode.QRCodeDecoder;

public class QRReaderTest {

	static QRDecoder decoder = new QRDecoder();
	static DroneCamera droneCamera = new DroneCamera();

	public static String getQRBelow() {
		// read QR code from imae
		// System.out.println("QRCode from image: "+
		// getQRString("exampleQRCode.png"));

		// captureImageFromDrone
		// ahead
		//		System.out.println("running main");

		String BelowString = null;
		System.out.println("taking picture");
		BufferedImage BelowPicture = droneCamera.takePictureBellow();
		// below
		// BufferedImage belowPicture = droneCamera.takePictureBellow();;
		// Decode images
		//String AheadString = getQRString(aheadPicture);
		BelowString = QRDecoder(BelowPicture);
		// System.out.println("QRCode from ahead Image: "+
		// getQRString(belowPicture));

		System.out.println(BelowString!=null?("QRCode from ahead Image: !" + BelowString+"!"):"");

		return BelowString;
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


	/**
	 * Converts a given Image into a BufferedImage
	 *
	 * @param img
	 *            The Image to be converted
	 * @return The converted BufferedImage
	 */
	public static BufferedImage toBufferedImage(Image img) {
		if (img instanceof BufferedImage) {
			return (BufferedImage) img;
		}

		// Create a buffered image with transparency
		BufferedImage bimage = new BufferedImage(img.getWidth(null),
				img.getHeight(null), BufferedImage.TYPE_INT_ARGB);

		// Draw the image on to the buffered image
		Graphics2D bGr = bimage.createGraphics();
		bGr.drawImage(img, 0, 0, null);
		bGr.dispose();

		// Return the buffered image
		return bimage;
	}

	private static String getQRString(String filename) {
		try {
			return decoder.DecodeQRCode(filename);
		} catch (Exception e) {
			e.printStackTrace();
			return "something went wrong when reading decoding a QR local image";
		}
	}

	private static String getQRString(BufferedImage img) {
		try {
			return decoder.DecodeQRCode(img);
		} catch (Exception e) {
			e.printStackTrace();
			return "something went wrong when reading decoding a QR local image";
		}
	}

}
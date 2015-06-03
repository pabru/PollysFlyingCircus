package polly.imagecapture;

import java.awt.image.BufferedImage;

public class ImageDecoder {
	QRDecoder decoder;

	public ImageDecoder() {
		decoder = new QRDecoder();
	}

	public String decodeAhead(long timeout) {
		return decode(timeout, 0);
	}

	public String decodeBelow(long timeout) {
		return decode(timeout, 1);
	}
	/**
	 * Show or hide the javaWindow that displays pictures taken by the drone
	 * @param show true = show pictures, false = hide pictures
	 */
	public void showPictures(boolean show){
		DisplayCameraImage.showWindow = show;
	}

	/**
	 * continuously attempts to take a picture and decode a QR code if present
	 * 
	 * @param timeout
	 *            time in milliseconds the drone should spend attempting to
	 *            decode an image
	 * @param cameraPosition
	 *            1 = below camera, 0 = forward camera
	 * @return
	 */
	private String decode(long timeout, int cameraPosition) {
		int decodeAttempts = 0;
		String result = "";
		long start = System.currentTimeMillis();
		long elapsedTime = 0;
		BufferedImage picture;
		DisplayCameraImage.setCameraSelection(cameraPosition);
		do {
			picture = DisplayCameraImage.takePicture();
			result = decoder.decodeImage(picture);
			decodeAttempts ++;
			if (result.equals("")) {
				elapsedTime = System.currentTimeMillis() - start;
			} else {
				elapsedTime = timeout;
			}
		} while (elapsedTime < timeout);
		System.out.println("decoded attempts: "+ decodeAttempts);
		return result;
	}

}

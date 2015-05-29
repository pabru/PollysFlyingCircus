package polly.imagecapture;

import java.awt.image.BufferedImage;

public class ImageDecoder {
	DroneCamera camera;
	QRDecoder decoder;

	public ImageDecoder() {
		camera = new DroneCamera();
		decoder = new QRDecoder();
	}

	public String decodeAhead(long timeout) {
		return decode(timeout, 1);
	}

	public String decodeBelow(long timeout) {
		return decode(timeout, 0);
	}

	private String decode(long timeout, int cameraPosition) {
		String result = "";
		long start = System.currentTimeMillis();
		long elapsedTime = 0;
		BufferedImage picture;
		do {
			if (cameraPosition == 1) {
				picture = camera.takePictureAhead();
			} else {
				picture = camera.takePictureBellow();
			}
			result = decoder.decodeImage(picture);
			if (result.equals("")) {
				elapsedTime = System.currentTimeMillis() - start;
			} else {
				elapsedTime = timeout;
			}
		} while (elapsedTime < timeout);
		return result;
	}

}

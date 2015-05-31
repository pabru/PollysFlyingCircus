package polly.imagecapture;

import java.awt.image.BufferedImage;

import jp.sourceforge.qrcode.QRCodeDecoder;

public class QRDecoder {

	/**
	 * Attempt to decode a QR code in a buffered image
	 * 
	 * @param im
	 *            A buffered image that possibly contains a QR code
	 * @return The string of the QRCode. Return empty string if QR code can not
	 *         be deciphered
	 */
	public String decodeImage(BufferedImage im) {
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

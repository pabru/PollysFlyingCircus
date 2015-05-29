package polly.imagecapture;

import java.awt.image.BufferedImage;

import jp.sourceforge.qrcode.QRCodeDecoder;

public class QRDecoder {

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

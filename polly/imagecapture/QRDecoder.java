package polly.imagecapture;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.EnumMap;
import java.util.EnumSet;
import java.util.Map;

import javax.imageio.ImageIO;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.BinaryBitmap;
import com.google.zxing.DecodeHintType;
import com.google.zxing.LuminanceSource;
import com.google.zxing.MultiFormatReader;
import com.google.zxing.client.j2se.BufferedImageLuminanceSource;
import com.google.zxing.common.HybridBinarizer;

public class QRDecoder {
	//removed static for both DecodeQRCode methods
	public String DecodeQRCode(String filename) throws Exception {
		File whatFile = new File(filename);
		// check the required parameters
		if (whatFile == null || whatFile.getName().trim().isEmpty()) {
			throw new IllegalArgumentException(
					"File not found, or invalid file name.");
		}
		BufferedImage tmpBfrImage;
		try {
			tmpBfrImage = ImageIO.read(whatFile);
		} catch (IOException tmpIoe) {
			throw new Exception(tmpIoe.getMessage());
		}
		if (tmpBfrImage == null) {
			throw new IllegalArgumentException("Could not decode image.");
		}
		return (DecodeQRCode(tmpBfrImage));
	}
	
	public String DecodeQRCode(BufferedImage image) throws Exception {
		Map<DecodeHintType, Object> whatHints = new EnumMap<DecodeHintType, Object>(
				DecodeHintType.class);
		whatHints.put(DecodeHintType.TRY_HARDER, Boolean.TRUE);
		whatHints.put(DecodeHintType.POSSIBLE_FORMATS,
				EnumSet.allOf(BarcodeFormat.class));
		whatHints.put(DecodeHintType.PURE_BARCODE, Boolean.FALSE);
		LuminanceSource tmpSource = new BufferedImageLuminanceSource(image);
		BinaryBitmap tmpBitmap = new BinaryBitmap(
				new HybridBinarizer(tmpSource));
		MultiFormatReader tmpBarcodeReader = new MultiFormatReader();
		com.google.zxing.Result tmpResult;
		String tmpFinalResult = "";
		try {
			if (whatHints != null && !whatHints.isEmpty()) {
				tmpResult = tmpBarcodeReader.decode(tmpBitmap, whatHints);
			} else {
				tmpResult = tmpBarcodeReader.decode(tmpBitmap);
			}
			// setting results.
			tmpFinalResult = String.valueOf(tmpResult.getText());
		} catch (Exception tmpExcpt) {
			System.out.println("BarCodeUtil.decode Excpt err - "
					+ tmpExcpt.toString() + " - " + tmpExcpt.getMessage());
		}
		return (tmpFinalResult);
	}
	
}

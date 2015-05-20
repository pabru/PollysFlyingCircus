package polly.imagecapture;

import java.awt.image.BufferedImage;

public class DroneCamera {
		
	public BufferedImage takePictureBellow(){
		return takePicture(1);
	}
	
	public BufferedImage takePictureAhead(){
		return takePicture(0);
	}
	
	private BufferedImage takePicture(int position){
		DisplayCameraImage.setCameraSelection(position);
		BufferedImage picture = DisplayCameraImage.takePicture();
		return picture;
	}
	
}



package animations;

import java.awt.Image;

import javax.swing.ImageIcon;

import logic.Main;

public class CoinAnimation extends AnimationSet {

	
	public CoinAnimation(int delay) {
		this();
		
		super.setDelay(8);
	}
	
	public CoinAnimation() {
		super();
		
		Image[] images = new Image[8];
		
		// The size of the coin, diameter
		int size = Main.screenWidth / 25;

		// Cycle through files
		for (int i = 0; i < images.length; i++) {
			// Update the path
			String path = "/animations/coin" + (i + 1) + ".png";

			// Load the image
			ImageIcon coinIcon = new ImageIcon(getClass().getResource(path));

			// Resize the image
			images[i] = Main.resizer.resizeImage(coinIcon.getImage(), size,
					size, true);
		}
		
		super.setImages(images);		
	}
}

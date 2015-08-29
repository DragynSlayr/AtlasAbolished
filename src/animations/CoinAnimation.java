package animations;

import java.awt.Image;

import javax.swing.ImageIcon;

import logic.Main;

public class CoinAnimation {
	private Image[] coinImages;

	/**
	 * Create an animation for coins
	 */
	public CoinAnimation() {
		coinImages = new Image[8];
	}

	/**
	 * Sets up the coin set as well as loading the images
	 */
	public AnimationSet getAnimation() {
		// The size of the coin, diameter
		int size = Main.screenWidth / 25;

		// Cycle through files
		for (int i = 0; i < coinImages.length; i++) {
			// Update the path
			String path = "/animations/coin" + (i + 1) + ".png";

			// Load the image
			ImageIcon coinIcon = new ImageIcon(getClass().getResource(path));

			// Resize the image
			coinImages[i] = Main.resizer.resizeImage(coinIcon.getImage(), size,
					size, true);
		}

		// Return the animation
		return new AnimationSet(8, coinImages);
	}
}

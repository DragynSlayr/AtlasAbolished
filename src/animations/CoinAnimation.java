package animations;

import java.awt.Image;

import javax.swing.ImageIcon;

import logic.Main;

public class CoinAnimation extends Animation {

	/**
	 * Creates a new coin animation with a delay
	 * 
	 * @param delay
	 *            The delay between frames in the animation, in frames
	 */
	public CoinAnimation(int delay) {
		// Call the no-parameter constructor
		this();

		// Set the delay of the animation
		super.setDelay(delay);
	}

	/**
	 * Create a new coin animation with a default 1 frame delay
	 */
	public CoinAnimation() {
		// Call animation class constructor
		super();

		// Create an array for the images
		Image[] images = new Image[8];

		// The size of the coin
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

		// Add all images to the animation
		super.addToAnimation(images);
	}
}

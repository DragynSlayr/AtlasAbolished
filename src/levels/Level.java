package levels;

import java.awt.Image;

import javax.swing.ImageIcon;

import logic.Main;

public class Level {

	private Image background;

	/**
	 * The level the game is played on
	 */
	public Level() {
		// Load background
		background = new ImageIcon(getClass().getResource(
				"/levels/background.png")).getImage();
	}

	/**
	 * Gets an image that is sized correctly
	 * 
	 * @return The resized image
	 */
	public Image getSizedBackground() {
		// Resize the image
		Image resized = Main.resizer.resizeImage(background, Main.screenHeight,
				Main.screenWidth, false);

		// Return the resized image
		return resized;
	}
}

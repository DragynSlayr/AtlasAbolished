package animations;

import java.awt.Image;

import javax.swing.ImageIcon;

import logic.Main;

public class CoinAnimation {
	private Image[] coinImages;

	public CoinAnimation() {
		coinImages = new Image[8];
	}

	/**
	 * Sets up the coin set as well as loading the images
	 */
	public AnimationSet setupCoinAnimSet() {
		int size = Main.screenWidth / 25;

		for (int i = 0; i < coinImages.length; i++) {
			String path = "/animations/coin" + (i + 1) + ".png";

			ImageIcon coinIcon = new ImageIcon(getClass().getResource(path));

			coinImages[i] = Main.resizer.resizeImage(coinIcon.getImage(), size,
					size, true);
		}

		return new AnimationSet(8, coinImages);
	}
}

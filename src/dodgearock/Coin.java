package dodgearock;

import java.awt.Image;
import java.awt.Rectangle;
import java.util.Random;

import javax.swing.ImageIcon;

import fileHandling.AnimationSet;

/**
 * @author Inderpreet
 * @date Started 18-Apr-2013 continued 3-Aug-2015
 */
public class Coin {

	private int coinX, coinY, score;
	private Image[] coinImages;
	private AnimationSet coinSet;
	private final Random random;
	private Rectangle coinHitbox;

	public Coin() {
		random = new Random();
		coinX = random.nextInt(Main.screenWidth) - 6;
		coinY = random.nextInt(Main.screenHeight / 4) + Main.screenHeight / 8;

		coinImages = new Image[8];
		setupCoinAnimSet();
	}

	/**
	 * Sets up the coin set as well as loading the images
	 */
	private void setupCoinAnimSet() {
		int size = Main.screenWidth / 25;

		for(int i = 0; i < coinImages.length; i++) {
			String path = "images\\animations:coin" + String.valueOf(i + 1) + ".png";
			
			ImageIcon coinIcon = new ImageIcon(Main.loader.load(path).getAbsolutePath());
			
			coinImages[i] = Main.resizer.resizeImage(coinIcon.getImage(), size,
					size, true);;
		}

		coinSet = new AnimationSet(8, coinImages);
	}

	/**
	 * Gets the AnimationSet for the coin
	 * 
	 * @return The animation set
	 */
	public AnimationSet getCoinSet() {
		return coinSet;
	}

	/**
	 * Moves the coin
	 */
	public void move() {
		if (coinY > 0
				&& coinY < (Main.screenHeight / 2)
						+ (coinImages[0].getHeight(null) * 5)) {
			// Make power up fall if not on ground
			coinY += 2;
		}
	}

	/**
	 * Returns the value of the coinX
	 *
	 * @return x position
	 */
	public int getCoinX() {
		return coinX;
	}

	/**
	 * Returns the value of the coinY
	 *
	 * @return y position
	 */
	public int getCoinY() {
		return coinY;
	}

	/**
	 * Gets a random value
	 *
	 * @return Random value
	 */
	public int getRandomInt() {
		return random.nextInt(300) + 1;
	}

	/**
	 * Resets value of coinX and coinY to default values
	 */
	public void resetCoinLocation() {
		coinX = random.nextInt(Main.screenWidth) - 6;
		coinY = random.nextInt(Main.screenHeight / 4) + Main.screenHeight / 8;
	}

	/**
	 * Get the location of the coin
	 *
	 * @return The coin location
	 */
	public Rectangle getCoinLocation() {
		coinHitbox = new Rectangle(coinX, coinY, coinImages[0].getWidth(null),
				coinImages[0].getHeight(null));
		return coinHitbox;
	}

	/**
	 * Set the score
	 * 
	 * @param newScore
	 *            The new score
	 */
	public void setScore(int newScore) {
		if (newScore >= 0) {
			score = newScore;
		}
	}

	/**
	 * Gets the score
	 * 
	 * @return The score
	 */
	public int getScore() {
		return score;
	}

	/**
	 * Adds a value to the score
	 * 
	 * @param value
	 *            The value to add
	 */
	public void addToScore(int value) {
		if (value >= 1) {
			score += value;
		}
	}
}

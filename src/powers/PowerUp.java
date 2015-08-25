package powers;

import java.awt.Image;
import java.awt.Rectangle;
import java.util.Random;

import javax.swing.ImageIcon;

import logic.Main;

/**
 * @author Inderpreet
 * @date Started 18-Apr-2013 continued 3-Aug-2015
 */
public class PowerUp {

	private int xPosition;
	private int yPosition;
	private int speed;
	private int startingX, startingY;
	private Image powerUpImage;
	private final Random random;
	private Rectangle powerUpHitbox;

	public static final String[] POWERS = { "None", "Bullet Rain",
			"Invincibility", "Points", "Speed", "Time Slow" };
	public static final int STARTING_SPEED = 4, MAX_SPEED = 6, MIN_SPEED = 2;

	public PowerUp() {
		// Initiate random
		random = new Random();

		// Set speed
		speed = STARTING_SPEED;

		// Set starting x
		startingX = random.nextInt(Main.screenWidth) - 6;
		xPosition = startingX;

		// Set starting y
		startingY = random.nextInt(Main.screenHeight / 4) + Main.screenHeight
				/ 8;
		yPosition = startingY;
	}

	/**
	 * Moves the powerUp
	 *
	 * @param isDrawn
	 *            True if powerUp is visible
	 */
	public void move(boolean isDrawn) {
		// Check visibility and location
		if (yPosition > 0
				&& yPosition < (Main.screenHeight / 2)
						+ (powerUpImage.getHeight(null) * 5) && isDrawn) {

			// Make power up fall if not on ground
			yPosition += speed;
		}
	}

	/**
	 * Gets a powerUpImage
	 *
	 * @param currentPowerUp
	 *            The powerUpImage to return
	 * @return The power up image
	 */
	public Image getPowerUpImage(String currentPowerUp) {
		// Get the path of the image
		String path = "/powers/" + currentPowerUp + ".png";

		// Load the image
		ImageIcon powerUpIcon = new ImageIcon(getClass().getResource(path));

		// Resize icon
		powerUpImage = Main.resizer.resizeImage(powerUpIcon.getImage(),
				Main.screenWidth / 25, Main.screenWidth / 25, true);

		return powerUpImage;
	}

	/**
	 * Get the x position
	 *
	 * @return The x position
	 */
	public int getX() {
		return xPosition;
	}

	/**
	 * Get the y position
	 *
	 * @return The y position
	 */
	public int getY() {
		return yPosition;
	}

	/**
	 * Gets a random value between 1 and limit
	 *
	 * @param limit
	 *            The max number
	 * @return A random value
	 */
	public int getRandomInt(int limit) {
		return random.nextInt(limit) + 1;
	}

	/**
	 * Resets position of power up
	 */
	public void resetPowerUpLocation() {
		xPosition = startingX;
		yPosition = startingY;
	}

	/**
	 * Get power up position
	 *
	 * @return The power up location
	 */
	public Rectangle getPowerUpLocation() {
		// Update power up hit box
		powerUpHitbox = new Rectangle(xPosition, yPosition,
				powerUpImage.getWidth(null), powerUpImage.getHeight(null));

		return powerUpHitbox;
	}

	/**
	 * Returns a powerUpType randomly
	 *
	 * @return A random powerUp type
	 */
	public String getPowerUpType() {
		return POWERS[random.nextInt(POWERS.length - 1) + 1];
	}
}

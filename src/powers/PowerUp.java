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

	private int powerUpX;// Declares int, powerUpX
	private int powerUpY;// Declares int, powerUpY
	private Image powerUpImage;// Declares Image, powerUpImage
	private final Random random;// Declares Random, random
	private Rectangle powerUpHitbox;// Declares Rectangle, powerUpHitbox
	private String powerUpType;// Declares String, powerUpType

	public PowerUp() {
		random = new Random();// Initiates random
		powerUpX = random.nextInt(Main.screenWidth) - 6;// Initiates powerUpX
		powerUpY = random.nextInt(Main.screenHeight / 4) + Main.screenHeight
				/ 8;// Initiates powerUpY
	}

	/**
	 * Moves the powerUp
	 *
	 * @param isDrawn
	 *            boolean, true if powerUp is visible
	 */
	public void move(boolean isDrawn) {
		if (powerUpY > 0
				&& powerUpY < (Main.screenHeight / 2)
						+ (powerUpImage.getHeight(null) * 5) && isDrawn) {
			// Make power up fall if not on ground
			powerUpY += 2;
		}
	}

	/**
	 * Gets a powerUpImage
	 *
	 * @param currentPowerUp
	 *            String, the powerUpImage to return
	 * @return Image, powerUpImage
	 */
	public Image getPowerUpImage(String currentPowerUp) {
		String path = "/powers/" + currentPowerUp + ".png";
		
		ImageIcon powerUpIcon = new ImageIcon(getClass().getResource(path));

		// Resize icon
		powerUpImage = Main.resizer.resizeImage(powerUpIcon.getImage(),
				Main.screenWidth / 25, Main.screenWidth / 25, true);

		return powerUpImage;
	}

	/**
	 * Returns the value of the powerUpX
	 *
	 * @return int, powerUpX
	 */
	public int getPowerUpX() {
		return powerUpX;
	}

	/**
	 * Returns the value of the powerUpY
	 *
	 * @return int, powerUpY
	 */
	public int getPowerUpY() {
		return powerUpY;
	}

	/**
	 * Gets a random value
	 *
	 * @return int, random value
	 */
	public int getRandomInt() {
		return random.nextInt(300) + 1;
	}

	/**
	 * Resets value of powerUpX and powerUpY to default values
	 */
	public void resetPowerUpLocation() {
		powerUpX = random.nextInt(Main.screenWidth) - 6;
		powerUpY = random.nextInt(Main.screenHeight / 4) + Main.screenHeight
				/ 8;
	}

	/**
	 * Returns the rectangle for the powerUp
	 *
	 * @return Rectangle, powerUp Location
	 */
	public Rectangle getPowerUpLocation() {
		powerUpHitbox = new Rectangle(powerUpX, powerUpY,
				powerUpImage.getWidth(null), powerUpImage.getHeight(null));
		return powerUpHitbox;
	}

	/**
	 * Returns a powerUpType randomly
	 *
	 * @return String, the powerUp type
	 */
	public String getPowerUpType() {
		switch (random.nextInt(5)) {
		case 0:
			powerUpType = "Speed";
			break;
		case 1:
			powerUpType = "Points";
			break;
		case 2:
			powerUpType = "Time Slow";
			break;
		case 3:
			powerUpType = "Bullet Rain";
			break;
		case 4:
			powerUpType = "Invincibility";
			break;
		}
		return powerUpType;
	}
}

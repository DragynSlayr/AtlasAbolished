package logic;

import java.awt.Image;
import java.awt.Rectangle;
import java.util.Random;

import javax.swing.ImageIcon;

import menus.Main;

/**
 * @author Inderpreet
 * @date Started 15-Apr-2013 continued 3-Aug-2015
 */
public class Projectile {

	private int projectileX;// Declares int, rockX
	private int projectileY;// Declares int, rockY
	private int projectileSpeed;// Declares int, rockSpeed
	private int fallCount;// Declares int, rockFallCount
	private Image projectileImage;// Declares Image, rockImage
	private Random random;// Declares Random, random
	private Rectangle projectileHitbox;// Declares Rectangle, rockHitbox

	public Projectile() {
		random = new Random();// Initiates random

		ImageIcon fallingRock = new ImageIcon(Main.loader.load(
				"images\\projectiles:rock.png").getAbsolutePath());

		// Resize image
		projectileImage = Main.resizer.resizeImage(fallingRock.getImage(),
				(int) (Main.screenHeight / 12.5),
				(int) (Main.screenWidth / 41.67), true);

		// TODO use this for the construction site projectile
		// projectileImage = Main.resizer.resizeImage(fallingRock.getImage(),
		// (int) (Main.screenHeight / 12.67),
		// (int) (Main.screenWidth / 8), true);

		projectileX = Main.screenWidth / 4;
		projectileY = 25;

		projectileSpeed = 10;

		fallCount = 0;
	}

	/**
	 * Updates the values of rockX and rockY
	 */
	public void moveRock() {
		projectileY += projectileSpeed;// Increments rockY by rockSpeed
		if (projectileY >= Main.screenHeight) {
			projectileY = 25;// Resets rockY to default value if it is >= 250
			projectileX = random.nextInt(Main.screenWidth) - 6;// Resets rockX
																// to
																// default value
																// if
			// rockY >= 250
			fallCount++;// Increments rockFallCount if rockY >= 250
		}
		if (projectileX < -5) {
			// Move projectile right if too far left
			projectileX = (Main.screenWidth - projectileImage.getWidth(null)) - 5;
		}
		if (projectileX + projectileImage.getWidth(null) > Main.screenWidth - 4) {
			// Move projectile left if too far right
			projectileX = -4;
		}
	}

	/**
	 * Get rockX
	 *
	 * @return rockX
	 */
	public int getProjectileX() {
		return projectileX;
	}

	/**
	 * Get rockY
	 *
	 * @return rockY
	 */
	public int getProjectileY() {
		return projectileY;
	}

	/**
	 * Gets fall count
	 *
	 * @return The fall count
	 */
	public int getFallCount() {
		return fallCount;
	}

	/**
	 * Increments rockFallCount
	 *
	 * @param value
	 *            The value to add
	 */
	public void addToFallCount(int value) {
		fallCount += value;
	}

	/**
	 * Resets rockX to default value
	 */
	public void resetProjectileX() {
		projectileX = Main.screenWidth / 4;
	}

	/**
	 * Increments rockX
	 *
	 * @param newX
	 *            int, the amount to Increment by
	 */
	public void setProjectileX(int newX) {
		projectileX += newX;
	}

	/**
	 * Resets rockY to default value
	 */
	public void resetProjectileY() {
		projectileY = 25;
	}

	/**
	 * Sets rockSpeed
	 *
	 * @param speed
	 *            int, new Speed
	 */
	public void setProjectileSpeed(int speed) {
		projectileSpeed = speed;
	}

	/**
	 * Resets rockFallCount to default value
	 */
	public void resetFallCount() {
		fallCount = 0;
	}

	/**
	 * Returns rockImage
	 *
	 * @return Image, the rock image
	 */
	public Image getProjectileImage() {
		return projectileImage;
	}

	/**
	 * Returns random String
	 *
	 * @return String, random String
	 */
	public String getSway() {
		switch (random.nextInt(3)) {
		case 0:
			return "left";
		case 1:
			return "right";
		case 3:
			return "center";
		default:
			return "";
		}
	}

	/**
	 * Returns a rectangle of the rock
	 *
	 * @return Rectangle, rock location
	 */
	public Rectangle getProjectileLocation() {
		projectileHitbox = new Rectangle(projectileX, projectileY,
				projectileImage.getWidth(null), projectileImage.getHeight(null));
		return projectileHitbox;
	}
}

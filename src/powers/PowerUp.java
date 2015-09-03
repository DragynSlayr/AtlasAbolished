package powers;

import java.awt.Image;
import java.awt.Rectangle;

import javax.swing.ImageIcon;

import logic.Main;
import projectiles.FallingObject;

/**
 * @author Inderpreet
 * @date Started 18-Apr-2013 continued 3-Aug-2015
 */
public class PowerUp extends FallingObject {

	private int speed;

	public static String startingPower = "/powers/Points.png";
	
	public static final String[] POWERS = { "None", "Bullet Rain",
			"Invincibility", "Points", "Speed", "Time Slow" };
	public static final int STARTING_SPEED = 4, MAX_SPEED = 6, MIN_SPEED = 2;

	public PowerUp() {
		super(startingPower, Main.screenWidth / 25,
				Main.screenWidth / 25, true);

		// Set speed
		setNormalSpeed();

		// Set position
		resetPosition();
	}

	@Override
	public void move() {
		// Move if not on ground
		if (super.y > 0
				&& super.y < (Main.screenHeight / 2)
						+ (getImage().getHeight(null) * 5)) {
			super.y += speed;
		}
	}

	public void move(boolean isDrawn) {
		// Move if drawn
		if (isDrawn) {
			move();
		}
	}

	public void resetPosition() {
		super.x = Main.random.nextInt(Main.screenWidth) - 6;
		super.y = Main.random.nextInt(Main.screenHeight / 4)
				+ Main.screenHeight / 8;
	}

	@Override
	public Image getImage() {
		return getImage(getRandomPowerUpType());
	}

	public Image getImage(String currentPowerUp) {
		// Get the path of the image
		String path = "/powers/" + currentPowerUp + ".png";

		// Load the image
		ImageIcon powerUpIcon = new ImageIcon(getClass().getResource(path));

		// Resize icon
		Image powerUpImage = Main.resizer.resizeImage(powerUpIcon.getImage(),
				Main.screenWidth / 25, Main.screenWidth / 25, true);

		return powerUpImage;
	}

	public int getRandomInt(int limit) {
		return Main.random.nextInt(limit) + 1;
	}

	@Override
	public Rectangle getHitbox() {
		return new Rectangle(super.x, super.y, getImage().getWidth(null),
				getImage().getHeight(null));
	}

	public String getRandomPowerUpType() {
		return POWERS[getRandomInt(POWERS.length - 1)];
	}

	public void setNormalSpeed() {
		speed = STARTING_SPEED;
	}

	public void setMaxSpeed() {
		speed = MAX_SPEED;
	}

	public void setMinSpeed() {
		speed = MIN_SPEED;
	}
}

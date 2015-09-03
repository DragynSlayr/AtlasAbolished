package projectiles;

import logic.Main;
import animations.CoinAnimation;

/**
 * @author Inderpreet
 * @date Started 18-Apr-2013 continued 3-Aug-2015
 */
public class Coin extends FallingObject {

	private int speed;

	public static final int STARTING_SPEED = 2;

	public Coin() {
		super(new CoinAnimation(8));

		// Set score
		super.setScore(0);

		// Set position
		resetPosition();

		// Set speed
		setNormalSpeed();

	}

	public void move() {
		// Check if coin has hit bottom
		if (super.y < (Main.screenHeight / 2)
				+ (super.getImage().getHeight(null) * 5)) {
			// Make power up fall if not on ground
			super.y += speed;
		}
	}

	public void resetPosition() {
		x = Main.random.nextInt(Main.screenWidth) - 6;
		y = Main.random.nextInt(Main.screenHeight / 4) + Main.screenHeight / 8;
	}

	public void setNormalSpeed() {
		speed = STARTING_SPEED;
	}
}

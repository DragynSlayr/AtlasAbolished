package projectiles;

import logic.Main;

/**
 * @author Inderpreet
 * @date Started 15-Apr-2013 continued 3-Aug-2015
 */
public class Projectile extends FallingObject {

	private int speed;

	public static String startingProjectile = "/projectiles/rock.png";

	private static final int STARTING_SPEED = 15, MIN_SPEED = 10,
			MAX_SPEED = 20;

	public Projectile() {
		super(startingProjectile, (int) (Main.screenHeight / 12.5),
				(int) (Main.screenWidth / 41.67), true);

		// Set score
		super.setScore(0);

		// Set position
		resetPosition();

		// Set speed
		setNormalSpeed();
	}

	public void move() {
		// Make the rock fall
		super.y += speed;

		// Check if the rock has reached the bottom of the screen
		if (super.y >= Main.screenHeight + 5) {
			resetPosition();
			super.score++;
		}

		// Check if the rock has hit the left side of the screen
		if (super.x < -5) {
			super.x = (Main.screenWidth - super.getImage().getWidth(null)) - 5;
		}

		// Check if the rock has hit the right side of the screen
		if (super.x + super.getImage().getWidth(null) > Main.screenWidth - 4) {
			super.x = -4;
		}
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

	public void sway() {
		int max = 25;
		int sway = Main.random.nextInt(max) - (max / 2);
		super.x += sway;
	}

	@Override
	public void resetPosition() {
		super.x = Main.random.nextInt(Main.screenWidth) - 6;
		super.y = 25;
	}
}

package projectiles;

import java.awt.Image;
import java.awt.Rectangle;

import javax.swing.ImageIcon;

import logic.Main;

/**
 * @author Inderpreet
 * @date Started 15-Apr-2013 continued 3-Aug-2015
 */
public class Projectile {

	private int x, y, speed, score;
	private Image image;
	private Rectangle hitbox;

	public static final int STARTING_SPEED = 15, MIN_SPEED = 10,
			MAX_SPEED = 20;

	public Projectile() {
		// Load image
		ImageIcon fallingRock = new ImageIcon(getClass().getResource(
				"/projectiles/rock.png"));

		// Resize image
		image = Main.resizer.resizeImage(fallingRock.getImage(),
				(int) (Main.screenHeight / 12.5),
				(int) (Main.screenWidth / 41.67), true);

		// TODO use this for the construction site projectile
		// projectileImage = Main.resizer.resizeImage(fallingRock.getImage(),
		// (int) (Main.screenHeight / 12.67),
		// (int) (Main.screenWidth / 8), true);

		// Set position
		resetPosition();

		// Set speed
		setNormalSpeed();

		// Set score
		setScore(0);
	}

	public void move() {
		// Make the rock fall
		y += speed;

		// Check if the rock has reached the bottom of the screen
		if (y >= Main.screenHeight + 5) {
			resetPosition();
			score++;
		}

		// Check if the rock has hit the left side of the screen
		if (x < -5) {
			x = (Main.screenWidth - image.getWidth(null)) - 5;
		}

		// Check if the rock has hit the right side of the screen
		if (x + image.getWidth(null) > Main.screenWidth - 4) {
			x = -4;
		}
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public void setScore(int newScore) {
		if (newScore >= 0) {
			score = newScore;
		}
	}

	public int getScore() {
		return score;
	}

	public void addToScore(int value) {
		if (value >= 1) {
			score += value;
		}
	}

	public void resetPosition() {
		x = Main.random.nextInt(Main.screenWidth) - 6;
		y = 25;
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

	public Rectangle getHitbox() {
		hitbox = new Rectangle(x, y, image.getWidth(null),
				image.getHeight(null));
		return hitbox;
	}
	
	public Image getImage() {
		return image;
	}

	public void setXMovement(int xMovement) {
		x += xMovement;
	}

	public void resetScore() {
		setScore(0);
	}

	public void sway() {
		int max = 25;
		int sway = Main.random.nextInt(max) - (max / 2);
		setXMovement(sway);
	}
}

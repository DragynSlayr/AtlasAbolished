package projectiles;

import java.awt.Image;
import java.awt.Rectangle;

import logic.Main;
import animations.AnimationSet;
import animations.CoinAnimation;

/**
 * @author Inderpreet
 * @date Started 18-Apr-2013 continued 3-Aug-2015
 */
public class Coin {

	private int x, y, speed, score;
	private AnimationSet animationSet;
	private Rectangle hitbox;

	public static final int STARTING_SPEED = 2;

	public Coin() {
		// Initialize animation set
		animationSet = new CoinAnimation().getAnimation();

		// Set position
		resetPosition();

		// Set speed
		setNormalSpeed();

		// Set score
		setScore(0);
	}

	public void move() {
		// Check if coin has hit bottom
		if (y < (Main.screenHeight / 2)
				+ (animationSet.getAnimationSet().get(0).getHeight(null) * 5)) {
			// Make power up fall if not on ground
			y += speed;
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
		y = Main.random.nextInt(Main.screenHeight / 4) + Main.screenHeight / 8;
	}

	public void setNormalSpeed() {
		speed = STARTING_SPEED;
	}

	public Rectangle getHitbox() {
		hitbox = new Rectangle(x, y, animationSet.getAnimationSet().get(0)
				.getWidth(null), animationSet.getAnimationSet().get(0)
				.getHeight(null));
		return hitbox;
	}

	public Image getImage() {
		return animationSet.getNextFrame();
	}
}

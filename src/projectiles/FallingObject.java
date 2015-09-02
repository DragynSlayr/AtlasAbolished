package projectiles;

import java.awt.Image;
import java.awt.Rectangle;

import javax.swing.ImageIcon;

import logic.Main;
import animations.AnimationSet;

public abstract class FallingObject {

	public int x, y, score;
	private Image image;
	private AnimationSet animation;
	private Rectangle hitbox;
	private boolean isAnimated = false;

	public FallingObject(String imagePath, int height, int width,
			boolean isTransparent) {
		ImageIcon icon = new ImageIcon(getClass().getResource(imagePath));

		image = Main.resizer.resizeImage(icon.getImage(), height, width,
				isTransparent);
	}

	public FallingObject(AnimationSet set) {
		animation = set;

		isAnimated = true;
	}

	public Image getImage() {
		if (isAnimated) {
			return animation.getNextFrame();
		} else {
			return image;
		}
	}

	public abstract void resetPosition();

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

	public Rectangle getHitbox() {
		Image i = getImage();
		hitbox = new Rectangle(x, y, i.getWidth(null), i.getHeight(null));
		return hitbox;
	}

	public abstract void move();

}

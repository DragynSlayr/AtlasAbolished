package players;

import java.awt.Image;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;

import javax.swing.ImageIcon;

import logic.Main;

/**
 * @author Inderpreet
 * @date Started 15-Apr-2013 continued 3-Aug-2015
 */
public class Player {

	private int xMovement;
	private int yMovement;
	private int xPosition;
	private int yPosition;
	private int playerSpeed;
	private int playerJumpHeight;
	private int startingX, startingY;
	private boolean isInvincible;
	private boolean isJumping;
	private Image playerImage;
	private Image jumpingImage;
	private Rectangle playerHitbox;
	private ImageIcon playerIcon;
	private ImageIcon jumpingIcon;

	public static final int STARTING_SPEED = 4, MAX_SPEED = 6, MIN_SPEED = 2,
			STARTING_JUMP_HEIGHT = 6, MAX_JUMP_HEIGHT = 10,
			MIN_JUMP_HEIGHT = 2;

	/**
	 * Initiates player variables
	 */
	public Player() {
		// Load character icon
		playerIcon = new ImageIcon(getClass()
				.getResource("/players/player.png"));

		// Resize icon
		playerImage = Main.resizer.resizeImage(playerIcon.getImage(),
				(int) (Main.screenHeight / 7.57), Main.screenWidth / 20, true);

		// Load jumping icon
		jumpingIcon = new ImageIcon(getClass().getResource(
				"/players/player.png"));

		// Resize icon
		jumpingImage = Main.resizer.resizeImage(jumpingIcon.getImage(),
				(int) (Main.screenHeight / 7.57), Main.screenWidth / 20, true);

		// Set starting x position
		startingX = ((Main.screenWidth / 2) - (playerImage.getWidth(null) / 2));
		xPosition = startingX;

		// Set starting y position
		startingY = 3 * (Main.screenHeight / 4);
		yPosition = startingY;

		// Initiate jumping
		isJumping = true;

		// Initiates invincibility
		isInvincible = false;

		// Initiate starting speed
		playerSpeed = STARTING_SPEED;

		// Initiates jump height
		playerJumpHeight = STARTING_JUMP_HEIGHT;
	}

	/**
	 * Moves the player
	 */
	public void movePlayer() {
		// Increments x by the movement amount
		xPosition += xMovement;

		// Increments y by the movement amount
		yPosition += yMovement;

		if (xPosition < -5) {
			// Moves player from left of screen to right when they hit the edge
			xPosition = (Main.screenWidth - playerImage.getWidth(null)) - 5;
		}

		if (xPosition + playerImage.getWidth(null) > Main.screenWidth - 4) {
			// Moves player to the left if they are too far right
			xPosition = -4;
		}

		if (yPosition < ((Main.screenHeight + 30) / 2)
				+ (playerImage.getHeight(null) * 2) + 2) {
			// Moves the player downward if too high
			yPosition += 2;
		}

		if (yPosition < Main.screenHeight / 4) {
			// Stops the player from jumping too high
			yMovement = 0;
		}

		if (yPosition >= ((Main.screenHeight + 30) / 2)
				+ (playerImage.getHeight(null) * 2) + 1) {
			// Remove wings if player is on the ground
			isJumping = false;
		}
	}

	/**
	 * Gets player position
	 *
	 * @return Player x position
	 */
	public int getX() {
		return xPosition;
	}

	/**
	 * Gets player position
	 *
	 * @return Player y position
	 */
	public int getPlayerY() {
		return yPosition;
	}

	/**
	 * Gets player image
	 *
	 * @return Player image
	 */
	public Image getImage() {
		return playerImage;
	}

	/**
	 * Get jumping image
	 *
	 * @return Jumping image
	 */
	public Image getJumpingImage() {
		return jumpingImage;
	}

	/**
	 * Get whether the player is jumping
	 *
	 * @return True if jumping
	 */
	public boolean isJumping() {
		return isJumping;
	}

	/**
	 * Set if the player is jumping
	 *
	 * @param True
	 *            if jumping
	 */
	public void setJumping(boolean isJumping) {
		this.isJumping = isJumping;
	}

	/**
	 * Get if the player is invincible
	 *
	 * @return True if invincible
	 */
	public boolean isInvincible() {
		return isInvincible;
	}

	/**
	 * Gets the players hitbox
	 *
	 * @return The player's hitbox
	 */
	public Rectangle getPlayerHitbox() {
		// Update the hit box
		playerHitbox = new Rectangle(xPosition, yPosition,
				playerImage.getWidth(null), playerImage.getHeight(null));

		return playerHitbox;
	}

	/**
	 * Resets player coordinates to starting position
	 */
	public void resetPlayer() {
		// Reset position
		xPosition = startingX;
		yPosition = startingY;

		// Set status
		isJumping = true;
		isInvincible = false;
	}

	/**
	 * Sets player's X coordinate
	 *
	 * @param newX
	 *            The new x coordinate
	 */
	public void setPlayerX(int newX) {
		this.xPosition = newX;
	}

	/**
	 * Sets player's Y coordinate
	 *
	 * @param newY
	 *            The new Y coordinate
	 */
	public void setPlayerY(int newY) {
		this.yPosition = newY;
	}

	/**
	 * Sets x movement
	 *
	 * @param newXMovement
	 *            The new X movement
	 */
	public void setXMovement(int newXMovement) {
		this.xMovement = newXMovement;
	}

	/**
	 * Sets y movement
	 *
	 * @param newYMovement
	 *            The new y movement
	 */
	public void setYMovement(int newYMovement) {
		this.yMovement = newYMovement;
	}

	/**
	 * Sets player's speed
	 *
	 * @param playerSpeed
	 *            The new playerSpeed
	 */
	public void setPlayerSpeed(int playerSpeed) {
		this.playerSpeed = playerSpeed;
	}

	/**
	 * Sets player's invincibility status
	 *
	 * @param isInvincible
	 *            True to make the player invincible
	 */
	public void setInvincibility(boolean isInvincible) {
		this.isInvincible = isInvincible;
	}

	/**
	 * Parses key presses
	 *
	 * @param event
	 *            The key press
	 */
	public void keyPressed(KeyEvent event) {
		// Get key code
		int key = event.getKeyCode();

		// Switch based on key code
		switch (key) {
		case KeyEvent.VK_A:
		case KeyEvent.VK_LEFT:
			// Moves player to the left when pressing left
			xMovement = -(playerSpeed);

			// Stop
			break;
		case KeyEvent.VK_D:
		case KeyEvent.VK_RIGHT:
			// Moves player to the right when pressing right
			xMovement = playerSpeed;

			// Stop
			break;
		case KeyEvent.VK_W:
		case KeyEvent.VK_UP:
			// Moves player up when pressing up
			yMovement = -(playerJumpHeight);

			// Update player status
			isJumping = true;

			// Stop
			break;
		}
	}

	/**
	 * Parse key releases
	 *
	 * @param event
	 *            The key release
	 * 
	 */
	public void keyReleased(KeyEvent event) {
		int key = event.getKeyCode();
		switch (key) {
		case KeyEvent.VK_A:
		case KeyEvent.VK_D:
		case KeyEvent.VK_LEFT:
		case KeyEvent.VK_RIGHT:
			// Stop movement
			xMovement = 0;

			// Stop
			break;
		case KeyEvent.VK_W:
		case KeyEvent.VK_UP:
			// Stops player from moving up
			yMovement = 0;

			// Stop
			break;
		}
	}
}

package dodgearock;

import java.awt.Image;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;

import javax.swing.ImageIcon;

/**
 * @author Inderpreet
 * @date Started 15-Apr-2013 continued 3-Aug-2015
 */
public class Player {

	private int newPlayerX;// Declares int, newPlayerX
	private int newPlayerY;// Declares int, newPlayerY
	private int currentPlayerX;// Declares int, currentPlayerX
	private int currentPlayerY;// Declares int, currentPlayerY
	private int playerSpeed;// Declares int, playerSpeed
	private int playerJumpHeight;// Declares int, playerJumpHeight
	private boolean isInvincible;// Declares boolean, isInvincible
	private boolean isJumping;// Declares booleand, isJumping
	private Image playerImage;// Declares image, playerImage
	private Image jumpingPlayer;// Declares image, jumpingPlayer
	private Rectangle playerHitbox;// Declares rectangle, playerHitbox
	private ImageIcon character;// Declares imageicon, character
	private ImageIcon winged;// Declares imageicon, winged

	/**
	 * Initiates player variables
	 */
	public Player() {
		character = new ImageIcon(Main.loader.load("images\\players:player.png")
				.getAbsolutePath());

		// playerImage = character.getImage();// Initiates playerImage
		playerImage = Main.resizer.resizeImage(character.getImage(),
				(int) (Main.screenHeight / 7.57), Main.screenWidth / 20, true);

		winged = new ImageIcon(Main.loader.load("images\\players:player.png")
				.getAbsolutePath());

		// jumpingPlayer = winged.getImage();// Initiates jumpingPlayer
		jumpingPlayer = Main.resizer.resizeImage(winged.getImage(),
				(int) (Main.screenHeight / 7.57), Main.screenWidth / 20, true);

		currentPlayerX = ((Main.screenWidth / 2) - (playerImage.getWidth(null) / 2));// Initiates
		// currentPlayerX
		currentPlayerY = 3 * (Main.screenHeight / 4);// Initiates currentPlayerY
		isJumping = true;// Initiates isJumping
		playerSpeed = 4;// Initiates playerSpeed
		isInvincible = false;// Initiates isInvincible
		playerJumpHeight = 6;// Initiates playerJumpHeight
	}

	/**
	 * Moves the player
	 */
	public void movePlayer() {
		currentPlayerX += newPlayerX;// Increments currentPlayerX by the value
										// of newPlayerX
		currentPlayerY += newPlayerY;// Increments currentPlayerY by the value
										// of newPlayerY
		if (currentPlayerX < -5) {
			// Moves player from left of screen to right when they hit the edge
			currentPlayerX = (Main.screenWidth - playerImage.getWidth(null)) - 5;
		}
		if (currentPlayerX + playerImage.getWidth(null) > Main.screenWidth - 4) {
			//Moves player to the left if they are too far right
			currentPlayerX = -4;
		}
		if (currentPlayerY < ((Main.screenHeight + 30) / 2)
				+ (playerImage.getHeight(null) * 2) + 2) {
			// Moves the player downward if too high
			currentPlayerY += 2;
		}
		if (currentPlayerY < Main.screenHeight / 4) {
			// Stops the player from jumping too high
			newPlayerY = 0;
		}
		if (currentPlayerY >= ((Main.screenHeight + 30) / 2)
				+ (playerImage.getHeight(null) * 2) + 1) {
			// Remove wings if player is on the ground
			isJumping = false;
		}
	}

	/**
	 * Returns playerX
	 *
	 * @return int, currentPlayerX
	 */
	public int getPlayerX() {
		return currentPlayerX;
	}

	/**
	 * Returns playerY
	 *
	 * @return int, currentPlayerY
	 */
	public int getPlayerY() {
		return currentPlayerY;
	}

	/**
	 * Returns character image
	 *
	 * @return image, the character's image
	 */
	public Image getImage() {
		return playerImage;
	}

	/**
	 * Returns character image
	 *
	 * @return image, the character's image
	 */
	public Image getJumpingImage() {
		return jumpingPlayer;
	}

	/**
	 * Returns isJumping
	 *
	 * @return boolean, isJumping
	 */
	public boolean getIsJumping() {
		return isJumping;
	}

	/**
	 * Sets isJumping
	 *
	 * @param isJumping
	 *            , true if jumping
	 */
	public void setIsJumping(boolean isJumping) {
		this.isJumping = isJumping;
	}

	/**
	 * Returns player's invincibility status
	 *
	 * @return boolean, player's status
	 */
	public boolean getIsInvincible() {
		return isInvincible;
	}

	/**
	 * Returns player's location
	 *
	 * @return rectangle, the rectangle around the player
	 */
	public Rectangle getPlayerLocation() {
		playerHitbox = new Rectangle(currentPlayerX, currentPlayerY,
				playerImage.getWidth(null), playerImage.getHeight(null));
		return playerHitbox;
	}

	/**
	 * Resets player coordinates to starting position
	 */
	public void resetPlayer() {
		currentPlayerX = ((Main.screenWidth / 2) - (playerImage.getWidth(null) / 2));// Resets
		// playerX
		currentPlayerY = 27;// Resets playerY
		isJumping = true;// Sets player to float down
	}

	/**
	 * Sets player's X coordinate
	 *
	 * @param currentPlayerX
	 *            , the new X coordinate
	 */
	public void setPlayerX(int currentPlayerX) {
		this.currentPlayerX = currentPlayerX;
	}

	/**
	 * Sets player's Y coordinate
	 *
	 * @param currentPlayerY
	 *            , the new Y coordinate
	 */
	public void setPlayerY(int currentPlayerY) {
		this.currentPlayerY = currentPlayerY;
	}

	/**
	 * Sets new players X
	 *
	 * @param newPlayerX
	 *            , the new X
	 */
	public void setNewX(int newPlayerX) {
		this.newPlayerX = newPlayerX;
	}

	/**
	 * Sets new player Y
	 *
	 * @param newPlayerY
	 *            , the new Y
	 */
	public void setNewY(int newPlayerY) {
		this.newPlayerY = newPlayerY;
	}

	/**
	 * Sets player's speed
	 *
	 * @param playerSpeed
	 *            , the new playerSpeed
	 */
	public void setPlayerSpeed(int playerSpeed) {
		this.playerSpeed = playerSpeed;
	}

	/**
	 * Sets player's invincibility status
	 *
	 * @param isInvincible
	 *            , true or false
	 */
	public void setIsInvincible(boolean isInvincible) {
		this.isInvincible = isInvincible;
	}

	/**
	 * Sets newPlayerX, for keyPress
	 *
	 * @param event
	 *            , the key press
	 */
	public void keyPressed(KeyEvent event) {
		int key = event.getKeyCode();
		switch (key) {
		case KeyEvent.VK_LEFT:
			newPlayerX = -(playerSpeed);// Moves player to the left
			break;
		case KeyEvent.VK_RIGHT:
			newPlayerX = playerSpeed;// Moves player to the right
			break;
		case KeyEvent.VK_UP:
			newPlayerY = -(playerJumpHeight);// Moves player up
			isJumping = true;// Sets isJumping to true
			break;
		}
	}

	/**
	 * Sets newPlayerX, for key press
	 *
	 * @param event
	 *            , key release
	 */
	public void keyReleased(KeyEvent event) {
		int key = event.getKeyCode();
		switch (key) {
		case KeyEvent.VK_LEFT:
			newPlayerX = 0;// Stops player movement
			break;
		case KeyEvent.VK_RIGHT:
			newPlayerX = 0;// Stops player movement
			break;
		case KeyEvent.VK_UP:
			newPlayerY = 0;// Stops player from jumping
			break;
		}
	}
}

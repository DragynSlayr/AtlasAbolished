package logic;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;

import levels.Level;
import players.Player;
import powers.PowerUp;
import projectiles.Coin;
import projectiles.Projectile;
import files.StatsLoader;

/**
 * @author Inderpreet
 * @date Started 15-Apr-2013 continued 3-Aug-2015
 */

@SuppressWarnings("serial")
// This class is a JPanel and has an ActionListener
public class Game extends JPanel implements ActionListener {

	private Timer timer;
	private Player player;
	private Projectile projectile;
	private PowerUp powerUp;
	private Coin coin;
	private StatsLoader statsLoader;
	private Level level;
	private Rectangle powerUpHitbox;
	private Rectangle playerHitbox;
	private Rectangle bulletHitbox;
	private String currentPowerUp;
	private Image powerUpImage;
	private int powerUpTimer;
	private int playerScore;
	private int highScore;
	private int scoreLimit;
	private boolean powerUpReady;
	private boolean powerUpDrawn;
	private boolean gamePaused;
	private final JFrame gameOverFrame = new JFrame();
	private final JButton playAgain = new JButton();
	private final JButton quit = new JButton();
	private final JFrame winnerFrame = new JFrame();

	public Game() {
		initiateBoardVariables();
		setUpBoard();
	}

	/**
	 * Initiates variables for the board
	 */
	private void initiateBoardVariables() {
		// Initiates timer to tick every 10 milliseconds, 1000 = 1 second
		timer = new Timer(10, this);

		// Initialize the player
		player = new Player();

		// Initialize the projectile
		projectile = new Projectile();

		// Initialize the power up
		powerUp = new PowerUp();

		// Initialize coin
		coin = new Coin();

		// Initialize level
		level = new Level();

		// Initialize the score class
		statsLoader = new StatsLoader();

		// Set the score limit
		scoreLimit = 300;

		// Set the starting power up image
		powerUpImage = powerUp.getPowerUpImage("Points");

		// Load the all time high score
		highScore = statsLoader.getScore();

		// Load coin score
		coin.setScore(statsLoader.getCoins());
	}

	/**
	 * Sets the elements necessary for the board
	 */
	private void setUpBoard() {
		addKeyListener(new KeyInputListener());// Adds KeyListener
		setFocusable(true);// Allows board to be focussed
		setDoubleBuffered(true);// Makes board double buffered
		currentPowerUp = PowerUp.POWERS[0];// Sets current power up to none
		powerUpTimer = 0;// Sets the timer for the power ups
		timer.setInitialDelay(1000);// Sets starting delay
		timer.start();// Starts the timer
	}

	/**
	 * Overrides the paint method
	 *
	 * @param graphic
	 *            graphics, allows paint method to use graphics
	 */
	@Override
	public void paint(Graphics graphic) {
		super.paint(graphic);

		Graphics2D graphic2D = (Graphics2D) graphic;

		graphic2D.setColor(Color.WHITE);

		graphic2D.fillRect(0, 0, Main.screenWidth, Main.screenHeight);

		graphic2D.setColor(Color.BLACK);

		graphic2D.setFont(new Font("Times New Roman", Font.PLAIN, 23));

		graphic2D.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
				RenderingHints.VALUE_ANTIALIAS_ON);

		graphic2D.setRenderingHint(RenderingHints.KEY_INTERPOLATION,
				RenderingHints.VALUE_INTERPOLATION_BICUBIC);

		graphic2D.setRenderingHint(RenderingHints.KEY_RENDERING,
				RenderingHints.VALUE_RENDER_QUALITY);

		// Draw the background
		graphic2D.drawImage(level.getSizedBackground(), 0, 25, this);

		// Draw the projectile
		graphic2D.drawImage(projectile.getImage(), projectile.getX(),
				projectile.getY(), null);

		if (player.isJumping()) {
			graphic2D.drawImage(player.getJumpingImage(), player.getX(),
					player.getPlayerY(), null);
		} else {
			graphic2D.drawImage(player.getImage(), player.getX(),
					player.getPlayerY(), null);
		}

		// Displays the current power up at the top of the screen
		graphic2D.drawString("Current PowerUp: " + currentPowerUp, 5, 17);

		// Displays current score to the left of the screen
		graphic2D.drawString("Score: " + playerScore, 315, 17);

		// Displays the high score to the right of the screen
		graphic2D.drawString("HighScore: " + highScore, 555, 17);

		// Display coin count at the top right
		graphic2D.drawString("Coins: " + coin.getScore(), 755, 17);

		if (powerUpReady) {
			// Draws a power up if available
			graphic2D.drawImage(
					powerUp.getPowerUpImage(powerUp.getPowerUpType()),
					powerUp.getX(), powerUp.getY(), null);
			powerUpDrawn = true;// Changes value of powerUpDrawn to true
		}

		// Draw coin
		graphic2D.drawImage(coin.getImage(), coin.getX(), coin.getY(), null);

		Toolkit.getDefaultToolkit().sync();// Syncs toolkit
	}

	/**
	 * Overrides default actionPerformed method
	 *
	 * @param event
	 *            ActionEvent, the event that can be performed
	 */
	@Override
	public void actionPerformed(ActionEvent event) {
		checkForPowerUpAvailability();
		setScores();
		performGameActions();
		updateObjectLocations();
		checkCoin();
		if (powerUpReady && powerUpDrawn) {
			checkPowerUps();
		}
		if (playerScore >= scoreLimit) {
			timer.stop();// Stops game
			winnerSequence();// Displays winning window
		}
		if (event.getSource().equals(quit)) {
			statsLoader.writeCoins(coin.getScore());
			System.exit(0);// Close the window
		}
		if (event.getSource().equals(playAgain)) {
			gameOverFrame.dispose();// Closes the JFrame
			restartGame();
		}
	}

	public void checkCoin() {
		if (playerHitbox.intersects(coin.getHitbox())) {
			coin.addToScore(1);
			coin.resetPosition();
		}
	}

	/**
	 * Checks to see if a powerUp could be obtained by the user
	 */
	public void checkForPowerUpAvailability() {
		if (powerUp.getRandomInt(300) == 50) {
			powerUpReady = true;// Initiates powerUpReady based on return of
								// powerUp.getRandomInt()
		}
	}

	/**
	 * Increments player score and sets high score
	 */
	public void setScores() {
		// Sets player score
		playerScore = projectile.getScore();
		if (playerScore >= highScore) {
			highScore = playerScore;// Updates high score
		}
	}

	/**
	 * Runs actions necessary for the game to work
	 */
	public void performGameActions() {
		powerUpTimer++;// Increments powerUptimer
		player.movePlayer();// Moves player one
		projectile.move();// Moves the bullet
		powerUp.move(powerUpDrawn);// Moves the powerUp
		coin.move();
		projectile.sway();
		
		repaint();// Repaints the board
	}

	/**
	 * Checks the location of all of the images on the board
	 */
	public void updateObjectLocations() {
		playerHitbox = player.getPlayerHitbox();
		bulletHitbox = projectile.getHitbox();
		if (powerUpReady && powerUpDrawn) {
			powerUpHitbox = new Rectangle(powerUp.getX(), powerUp.getY(),
					powerUpImage.getWidth(this), powerUpImage.getHeight(this));// Declares
																				// powerUpHitbox
		}

		if (playerHitbox.intersects(bulletHitbox) && !player.isInvincible()) {
			timer.stop();// Stops game
			gameOver();// Displays gameOver window
			if (playerScore <= highScore) {
				statsLoader.writeScore(highScore);// Saves playerHighscore if it
													// is
													// // higher than current
													// highScore
			}
		}

	}

	/**
	 * Checks to see if the player picked up a power up, or if it has been 10
	 * seconds since last power up
	 */
	public void checkPowerUps() {
		if (playerHitbox.intersects(powerUpHitbox)) {
			if (powerUp.getPowerUpType().equals("Points")) {
				resetBuffs();// Resets stats
				applyBuffs("Points");// Applies effects
			} else if (powerUp.getPowerUpType().equals("Speed")) {
				resetBuffs();// Resets stats
				applyBuffs("Speed");// Resets stats
			} else if (powerUp.getPowerUpType().equals("Time Slow")) {
				resetBuffs();// Resets stats
				applyBuffs("Time Slow");// Resets stats
			} else if (powerUp.getPowerUpType().equals("Bullet Rain")) {
				resetBuffs();// Resets stats
				applyBuffs("Bullet Rain");// Resets stats
			} else if (powerUp.getPowerUpType().equals("Invincibility")) {
				resetBuffs();// Resets stats
				applyBuffs("Invincibility");// Resets stats
			}
		}
		if (powerUpTimer / 1500 == 1) {
			resetBuffs();// Resets players stats after 10 seconds
		}
	}

	/**
	 * Resets player statistics
	 */
	public void resetBuffs() {
		powerUpDrawn = false;// Stops powerUp from being checked
		powerUpReady = false;// Stops power up from showing for a duration
		player.setPlayerSpeed(4);// Sets the player's speed
		powerUp.resetPowerUpLocation();// Changes the next powerup's location
		projectile.setNormalSpeed();// Resets the bullet speed
		player.setInvincibility(false);// Stops the player from being
										// invincible
		powerUpTimer = 0;// Sets counter for power ups to 0
		currentPowerUp = "None";// Reset the current PowerUp
	}

	/**
	 * Applies powerUp effects to player
	 *
	 * @param powerUpType
	 *            String, the powerUp to be applied
	 */
	public void applyBuffs(String powerUpType) {
		if (powerUpType.equalsIgnoreCase("Points")) {
			currentPowerUp = "Points";// Set the current PowerUp
			projectile.addToScore(10);// Adds 10 to the score if the user
										// got
			// a powerup
		} else if (powerUpType.equalsIgnoreCase("Speed")) {
			currentPowerUp = "Speed";// Set the current PowerUp
			player.setPlayerSpeed(7);// Increases the users speed to seven
		} else if (powerUpType.equalsIgnoreCase("Time Slow")) {
			currentPowerUp = "Time Slow";// Set the current PowerUp
			projectile.setMinSpeed();
		} else if (powerUpType.equalsIgnoreCase("Bullet Rain")) {
			currentPowerUp = "Bullet Rain";// Set the current PowerUp
			projectile.setMaxSpeed();// Sets the bullet speed to 1
		} else if (powerUpType.equalsIgnoreCase("Invincibility")) {
			currentPowerUp = "Invincibility";// Set the current PowerUp
			player.setInvincibility(true);// Makes player Invincible
		}
	}

	/**
	 * Displays gameOver window
	 */
	public void gameOver() {
		GridLayout gameOverGrid = new GridLayout(0, 2);// Declares a new grid
														// layout
		gameOverFrame.setLayout(gameOverGrid);// Sets the layout for the
												// game over screen
		gameOverFrame.setVisible(true);// Sets the game over frame to be visible
		gameOverFrame.setLocationRelativeTo(this);// Centers the frame
		gameOverFrame.setTitle("Game Over");// Sets the title of the game over
											// frame
		gameOverFrame.setSize(450, 200);// Sets the size of the game over window
		gameOverFrame.setResizable(false);// Makes it so the game over window
											// can't be resized
		playAgain.setText("Play Again?");// Sets the text on the first button to
											// be play again
		playAgain.addActionListener(this);// Adds an actionListener for the
											// play again button
		gameOverFrame.add(playAgain);// Adds the button playAgain to the frame
		quit.setText("Quit");// Sets the text on the second button to be quit
		quit.addActionListener(this);// Adds an actionListener for the second
										// button
		gameOverFrame.add(quit);// Adds the button quit to the frame
	}

	/**
	 * Restarts the game
	 */
	public void restartGame() {
		player.resetPlayer();// Resets player location
		player.setXMovement(0);// Sets the player's newX
		player.setYMovement(0);// Sets the player's newY
		timer.restart();// Restarts the game
		projectile.resetPosition();// Sets the bullet's x coordinate
		projectile.resetScore();// Resets player score
		coin.resetPosition();// Resets coin location
		resetBuffs();// Resets power up bonuses
	}

	/**
	 * Displays the winner window
	 */
	public void winnerSequence() {
		GridLayout winnerGrid = new GridLayout(0, 2);// Declares a new grid
														// layout
		winnerFrame.setLayout(winnerGrid);// Sets the layout for the winning
											// screen
		winnerFrame.setVisible(true);// Sets the winner frame to be visible
		winnerFrame.setLocationRelativeTo(this);// Centers the frame
		winnerFrame.setTitle("~~Congratulations, you win!~~");// Sets the title
																// of the winner
																// frame
		winnerFrame.setSize(450, 200);// Sets the size of the winner window
		winnerFrame.setResizable(false);// Makes it so the winner window can't
										// be resized
		playAgain.setText("Play Again?");// Sets the text on the first button to
											// be playagain
		playAgain.addActionListener(this);// Adds an actionListener for the
											// playagain button
		winnerFrame.add(playAgain);// Adds the button playAgain to the frame
		quit.setText("Quit");// Sets the text on the second button to be quit
		quit.addActionListener(this);// Adds an actionListener for the second
										// button
		winnerFrame.add(quit);// Adds the button quit to the frame
	}

	public class KeyInputListener extends KeyAdapter {

		@Override
		public void keyPressed(KeyEvent keyPress) {
			int key = keyPress.getKeyCode();
			if (key == KeyEvent.VK_SPACE && !gamePaused) {
				gamePaused = true;// Sets gamePaused to true if false
				timer.stop();// Stops timer
			} else if (key == KeyEvent.VK_SPACE && gamePaused) {
				gamePaused = false;// Sets gamePaused to false if true
				timer.restart();// Restarts timer
			} else if (key == KeyEvent.VK_ESCAPE) {
				statsLoader.writeScore(highScore);// Saves playerHighscore if it
													// is
													// higher than current
													// highScore
				statsLoader.writeCoins(coin.getScore());
				System.exit(0);// Closes game
			}
			player.keyPressed(keyPress);// Performs the event on keypress
		}

		@Override
		public void keyReleased(KeyEvent keyRelease) {
			player.keyReleased(keyRelease);// Performs the event on
											// keyrelease
		}
	}
}

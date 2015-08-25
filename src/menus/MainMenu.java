package menus;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

import logic.Game;
import logic.Main;

public class MainMenu {

	private final String START_TEXT = "Start", SETTINGS_TEXT = "Settings",
			EXIT_TEXT = "Exit", SHOP_TEXT = "Shop", CREDITS_TEXT = "Credits";
	public static JFrame menuFrame;

	/**
	 * The main menu
	 */
	public MainMenu() {
		createMainMenu();

		// Create a font for the buttons
		Font buttonFont = new Font(Font.SANS_SERIF, Font.PLAIN, 50);

		// Create a font for the title
		Font titleFont = new Font("Segoe Script", Font.PLAIN, 75);

		// Create an action listener for the buttons
		ActionListener buttonActionListener = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				switch (e.getActionCommand()) {
				case START_TEXT:
					// When the start button is pushed

					// Dispose of the menu
					menuFrame.dispose();

					// Start the game
					createGameFrame();

					// Stop
					break;
				case SETTINGS_TEXT:
					// Called when the settings button is pushed

					// Create a settings menu
					new SettingsMenu();

					// Disable the menu
					menuFrame.setEnabled(false);

					// Stop
					break;
				case EXIT_TEXT:
					// When the exit button is pushed

					// Dispose of menu
					menuFrame.dispose();

					// Shut down program
					System.exit(0);

					// Stop
					break;
				case SHOP_TEXT:
					// When the shop button is pressed

					// Show shop menu
					// TODO new ShopMenu();

					// Disable menu frame
					// TODO menuFrame.setEnabled(false);

					// Stop
					break;
				case CREDITS_TEXT:
					// When credits button is pushed

					// Show the credits menu
					new CreditsMenu();

					// Disable the main menu
					menuFrame.setEnabled(false);

					// Stop
					break;
				}
			}
		};

		// Create a new layout
		GridBagLayout layout = new GridBagLayout();

		// Set the layout of the menu
		menuFrame.setLayout(layout);

		// Create the padding
		Insets padding = new Insets(1, 1, 1, 1);

		// Create a label
		JLabel titleLabel = new JLabel(Main.GAME_NAME, SwingConstants.CENTER);

		// Set font for the title
		titleLabel.setFont(titleFont);

		// Create GridBagConstraints for the layout
		GridBagConstraints constraints = new GridBagConstraints();

		// Set fill
		constraints.fill = GridBagConstraints.HORIZONTAL;

		// Set grid width
		constraints.gridwidth = 6;

		// Set padding
		constraints.insets = padding;

		// Plot at 0, 0
		constraints.gridx = 0;
		constraints.gridy = 0;

		// Add title
		menuFrame.add(titleLabel, constraints);

		// Create a button
		JButton startButton = new JButton(START_TEXT);

		// Set the button font
		startButton.setFont(buttonFont);

		// Set the action listener
		startButton.addActionListener(buttonActionListener);

		// Plot at 0, 1
		constraints.gridy = 1;

		// Set width
		constraints.gridwidth = 3;

		// Set weight
		constraints.weightx = 0.5;

		// Add button
		menuFrame.add(startButton, constraints);

		// Create a button
		JButton shopButton = new JButton(SHOP_TEXT);

		// Set button font
		shopButton.setFont(buttonFont);

		// Set action listener
		shopButton.addActionListener(buttonActionListener);

		// Plot at 3, 1
		constraints.gridx = 3;

		// Add button
		menuFrame.add(shopButton, constraints);

		// Create button
		JButton settingsButton = new JButton(SETTINGS_TEXT);

		// Set button font
		settingsButton.setFont(buttonFont);

		// Set action listener
		settingsButton.addActionListener(buttonActionListener);

		// Plot at 0, 2
		constraints.gridx = 0;
		constraints.gridy = 2;

		// Add button
		menuFrame.add(settingsButton, constraints);

		// Create button
		JButton creditsButton = new JButton(CREDITS_TEXT);

		// Set font
		creditsButton.setFont(buttonFont);

		// Set action listener
		creditsButton.addActionListener(buttonActionListener);

		// Plot at 3, 2
		constraints.gridx = 3;

		// Add button
		menuFrame.add(creditsButton, constraints);

		// Create button
		JButton exitButton = new JButton(EXIT_TEXT);

		// Set font
		exitButton.setFont(buttonFont);

		// Set action listener
		exitButton.addActionListener(buttonActionListener);

		// Set width
		constraints.gridwidth = 6;

		// Plot at 0, 3
		constraints.gridx = 0;
		constraints.gridy = 3;

		// Add button
		menuFrame.add(exitButton, constraints);

		displayFrame();
	}

	/**
	 * Sets main aspects of the menu
	 */
	private void createMainMenu() {
		// Create a new frame
		menuFrame = new JFrame();

		// Set the title of the frame
		menuFrame.setTitle(Main.GAME_NAME + " - Main Menu");

		// Stops the program when the 'x' button is clicked
		menuFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

		// Set background color
		menuFrame.getContentPane().setBackground(Color.WHITE);
	}

	/**
	 * Display the frame
	 */
	private void displayFrame() {
		// Set the size of the frame
		menuFrame.pack();

		// Center the frame
		menuFrame.setLocationRelativeTo(null);

		// Make the frame visible
		menuFrame.setVisible(true);

		// Stop the frame from being resized
		menuFrame.setResizable(false);
	}

	/**
	 * Creates the game frame
	 */
	private void createGameFrame() {
		// Create a new frame
		JFrame gameFrame = new JFrame();

		// Add the game to the frame
		gameFrame.add(new Game());

		// Set the title of the frame
		gameFrame.setTitle(Main.GAME_NAME);

		// Stops the program when the 'x' button is clicked
		gameFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		// Set the size of the frame
		gameFrame.setSize(Main.screenWidth, Main.screenHeight);

		// Center the frame
		gameFrame.setLocationRelativeTo(null);

		// Make the frame visible
		gameFrame.setVisible(true);

		// Stop the frame from being resized
		gameFrame.setResizable(false);
	}

}

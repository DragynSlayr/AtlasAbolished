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

public class MainMenu {

	private final String START_TEXT = "Start", SETTINGS_TEXT = "Settings",
			EXIT_TEXT = "Exit";
	private JFrame menuFrame;

	public MainMenu() {
		createMainMenu();

		Font buttonFont = new Font(Font.SANS_SERIF, Font.PLAIN, 50);
		Font titleFont = new Font("Quartz MS", Font.PLAIN, 75);

		ActionListener buttonActionListener = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				switch (e.getActionCommand()) {
				case START_TEXT:
					menuFrame.dispose();
					createGameFrame();
					break;
				case SETTINGS_TEXT:
					new SettingsMenu();
					break;
				case EXIT_TEXT:
					menuFrame.dispose();
					System.exit(0);
					break;
				}
			}
		};

		GridBagLayout layout = new GridBagLayout();

		menuFrame.setLayout(layout);

		Insets padding = new Insets(1, 1, 1, 1);

		JLabel titleLabel = new JLabel(Main.GAME_NAME, SwingConstants.CENTER);
		titleLabel.setFont(titleFont);

		GridBagConstraints titleConstraints = new GridBagConstraints();
		titleConstraints.fill = GridBagConstraints.HORIZONTAL;
		titleConstraints.gridx = 0;
		titleConstraints.gridy = 0;
		titleConstraints.insets = padding;

		menuFrame.add(titleLabel, titleConstraints);

		JButton startButton = new JButton(START_TEXT);
		startButton.setFont(buttonFont);
		startButton.addActionListener(buttonActionListener);

		GridBagConstraints buttonConstraints = new GridBagConstraints();
		buttonConstraints.fill = GridBagConstraints.HORIZONTAL;
		buttonConstraints.gridx = 0;
		buttonConstraints.gridy = 1;
		buttonConstraints.insets = padding;

		menuFrame.add(startButton, buttonConstraints);

		JButton settingsMenu = new JButton(SETTINGS_TEXT);
		settingsMenu.setFont(buttonFont);
		settingsMenu.addActionListener(buttonActionListener);

		buttonConstraints.gridy = 2;

		menuFrame.add(settingsMenu, buttonConstraints);

		JButton exitButton = new JButton(EXIT_TEXT);
		exitButton.setFont(buttonFont);
		exitButton.addActionListener(buttonActionListener);

		buttonConstraints.gridy = 3;

		menuFrame.add(exitButton, buttonConstraints);

		displayFrame();
	}

	private void createMainMenu() {
		// Create a new frame
		menuFrame = new JFrame();

		// Set the title of the frame
		menuFrame.setTitle(Main.GAME_NAME + " - Main Menu");

		// Stops the program when the 'x' button is clicked
		menuFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

		// Set background color
		menuFrame.getContentPane().setBackground(Color.WHITE);

		// Set the size of the frame
		// menuFrame.setSize(Main.screenWidth / 3, Main.screenHeight / 2);
	}

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

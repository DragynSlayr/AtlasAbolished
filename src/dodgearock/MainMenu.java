package dodgearock;

import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;

public class MainMenu {

	private final String START_TEXT = "Start", SETTINGS_TEXT = "Settings",
			EXIT_TEXT = "Exit";
	private JFrame menuFrame;

	public MainMenu() {
		createMainMenu();

		Font buttonFont = new Font(Font.SERIF, Font.PLAIN, 50);

		ActionListener buttonActionListener = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				menuFrame.dispose();
				switch (e.getActionCommand()) {
				case START_TEXT:
					createGameFrame();
					break;
				case SETTINGS_TEXT:
					//TODO new SettingsMenu();
					new MainMenu();
					break;
				case EXIT_TEXT:
					System.exit(0);
					break;
				}
			}
		};

		GridLayout layout = new GridLayout(3, 1);

		menuFrame.setLayout(layout);

		JButton startButton = new JButton(START_TEXT);

		startButton.setFont(buttonFont);

		startButton.addActionListener(buttonActionListener);

		menuFrame.add(startButton);

		JButton settingsMenu = new JButton(SETTINGS_TEXT);

		settingsMenu.setFont(buttonFont);

		settingsMenu.addActionListener(buttonActionListener);

		menuFrame.add(settingsMenu);

		JButton exitButton = new JButton(EXIT_TEXT);

		exitButton.setFont(buttonFont);

		exitButton.addActionListener(buttonActionListener);

		menuFrame.add(exitButton);
	}

	private void createMainMenu() {
		// Create a new frame
		menuFrame = new JFrame();

		// Set the title of the frame
		menuFrame.setTitle("Dodge A Rock");

		// Stops the program when the 'x' button is clicked
		menuFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

		// Set the size of the frame
		menuFrame.setSize(Main.screenWidth / 3, Main.screenHeight / 2);

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
		gameFrame.setTitle("Dodge A Rock");

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

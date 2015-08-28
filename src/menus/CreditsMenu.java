package menus;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

import logic.Main;

public class CreditsMenu {

	private final String EXIT_TEXT = "Exit",
			CREDIT_A = "Programming - Inderpreet Dhillon",
			CREDIT_B = "Art - Harman Dhillon";
	private JFrame creditsFrame;

	public CreditsMenu() {
		// Listener to activate once a button is pushed
		ActionListener buttonListener = new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				switch (e.getActionCommand()) {
				case EXIT_TEXT:
					// Once the exit button is pushed

					// Enable the main menu
					MainMenu.menuFrame.setEnabled(true);

					// Close the credits frame
					creditsFrame.dispose();

					// Stop the case
					break;
				}
			}
		};

		createCreditsMenu();

		// Create a font for the menu to use
		Font creditsMenuFont = new Font(Font.SANS_SERIF, Font.PLAIN, 50);

		// Create a layout to use
		GridBagLayout layout = new GridBagLayout();

		// Set the frame's layout
		creditsFrame.setLayout(layout);

		// Set the padding to use for the items
		Insets padding = new Insets(-5, -5, -5, -5);

		// Create a label for the credits
		JLabel creditALabel = new JLabel(CREDIT_A);

		// Set the font of the credit label
		creditALabel.setFont(creditsMenuFont);

		// Create a GridBagConstraints object for formatting
		GridBagConstraints constraints = new GridBagConstraints();

		// Set the fill
		constraints.fill = GridBagConstraints.HORIZONTAL;

		// Set width of each grid
		constraints.gridwidth = 1;

		// Set the padding
		constraints.insets = padding;

		// Plot at 0, 0
		constraints.gridx = 0;
		constraints.gridy = 0;

		// Add label
		creditsFrame.add(creditALabel, constraints);

		// Create a credit label
		JLabel creditBLabel = new JLabel(CREDIT_B);

		// Set the font of the credits
		creditBLabel.setFont(creditsMenuFont);

		// Plot at 0, 1
		constraints.gridy = 1;

		// Add label
		creditsFrame.add(creditBLabel, constraints);

		// Create button
		JButton exitButton = new JButton(EXIT_TEXT);

		// Set font to a modified version of the credits font
		exitButton.setFont(creditsMenuFont.deriveFont(Font.PLAIN, 25));

		// Add an action listener to the button
		exitButton.addActionListener(buttonListener);

		// Plot at 0, 2
		constraints.gridy = 2;

		// Add button
		creditsFrame.add(exitButton, constraints);

		// Add window listener
		creditsFrame.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				// Enable main menu
				MainMenu.menuFrame.setEnabled(true);
			}
		});

		displayFrame();
	}

	/**
	 * Sets up necessary aspects of the menu
	 */
	private void createCreditsMenu() {
		// Create a new frame
		creditsFrame = new JFrame();

		// Set the title of the frame
		creditsFrame.setTitle(Main.GAME_NAME + " - Credits");

		// Stops the program when the 'x' button is clicked
		creditsFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

		// Set the background color
		creditsFrame.getContentPane().setBackground(Color.WHITE);
	}

	/**
	 * Display the menu
	 */
	private void displayFrame() {
		// Size the frame
		creditsFrame.pack();

		// Center the frame
		creditsFrame.setLocationRelativeTo(null);

		// Make the frame visible
		creditsFrame.setVisible(true);

		// Stop the frame from being resized
		creditsFrame.setResizable(false);
	}
}

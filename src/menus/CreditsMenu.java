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
		ActionListener buttonListener = new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				switch (e.getActionCommand()) {
				case EXIT_TEXT:
					MainMenu.menuFrame.setEnabled(true);
					creditsFrame.dispose();
					break;
				}
			}
		};

		createCreditsMenu();

		Font creditsMenuFont = new Font(Font.SANS_SERIF, Font.PLAIN, 50);

		GridBagLayout layout = new GridBagLayout();

		creditsFrame.setLayout(layout);

		Insets padding = new Insets(1, 1, 1, 1);

		JLabel creditALabel = new JLabel(CREDIT_A);
		creditALabel.setFont(creditsMenuFont);

		GridBagConstraints constraints = new GridBagConstraints();
		constraints.fill = GridBagConstraints.HORIZONTAL;
		constraints.gridwidth = 1;
		constraints.gridx = 0;
		constraints.gridy = 0;
		constraints.insets = padding;

		creditsFrame.add(creditALabel, constraints);

		JLabel creditBLabel = new JLabel(CREDIT_B);
		creditBLabel.setFont(creditsMenuFont);

		constraints.gridy = 1;

		creditsFrame.add(creditBLabel, constraints);

		JButton exitButton = new JButton(EXIT_TEXT);
		exitButton.setFont(creditsMenuFont.deriveFont(Font.PLAIN, 25));
		exitButton.addActionListener(buttonListener);

		constraints.gridy = 2;

		creditsFrame.add(exitButton, constraints);

		creditsFrame.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				MainMenu.menuFrame.setEnabled(true);
			}
		});

		displayFrame();
	}

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

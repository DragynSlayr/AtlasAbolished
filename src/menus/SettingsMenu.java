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
import javax.swing.JPanel;
import javax.swing.JTabbedPane;

public class SettingsMenu {

	private final String[] TABS = { "Game", "Video", "Audio", "Controls" };
	private final String RESET_TEXT = "Reset", APPLY_TEXT = "Apply",
			CANCEL_TEXT = "Cancel";
	private JFrame settingsFrame;

	public SettingsMenu() {
		ActionListener buttonListener = new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				switch (e.getActionCommand()) {
				case RESET_TEXT:
					applyDefaultSettings();
					settingsFrame.dispose();
					break;
				case APPLY_TEXT:
					applyNewSettings();
					settingsFrame.dispose();
					break;
				case CANCEL_TEXT:
					settingsFrame.dispose();
					break;
				}
			}
		};

		createSettingsMenu();

		Font settingsMenuFont = new Font(Font.SANS_SERIF, Font.PLAIN, 25);

		GridBagLayout layout = new GridBagLayout();

		settingsFrame.setLayout(layout);

		JTabbedPane tabbedPane = new JTabbedPane();
		tabbedPane.setFont(settingsMenuFont);

		for (String s : TABS) {
			tabbedPane.addTab(s, createTextPanel(s));
		}

		Insets padding = new Insets(1, 1, 1, 1);

		GridBagConstraints constraints = new GridBagConstraints();
		constraints.fill = GridBagConstraints.HORIZONTAL;
		constraints.gridwidth = 3;
		constraints.gridx = 0;
		constraints.gridy = 0;
		// settingsConstraints.ipady = 270;
		constraints.insets = padding;

		settingsFrame.add(tabbedPane, constraints);

		JButton resetButton = new JButton(RESET_TEXT);
		resetButton.setFont(settingsMenuFont);
		resetButton.addActionListener(buttonListener);

		constraints.gridy = 1;
		constraints.gridwidth = 1;
		constraints.insets = padding;

		settingsFrame.add(resetButton, constraints);

		JButton applyButton = new JButton(APPLY_TEXT);
		applyButton.setFont(settingsMenuFont);
		applyButton.addActionListener(buttonListener);

		constraints.gridx = 1;

		settingsFrame.add(applyButton, constraints);

		JButton cancelButton = new JButton(CANCEL_TEXT);
		cancelButton.setFont(settingsMenuFont);
		cancelButton.addActionListener(buttonListener);

		constraints.gridx = 2;

		settingsFrame.add(cancelButton, constraints);

		displayFrame();
	}

	private JPanel createTextPanel(String label) {
		JPanel panel = new JPanel();

		panel.add(new JLabel(label));

		return panel;
	}

	private void createSettingsMenu() {
		// Create a new frame
		settingsFrame = new JFrame();

		// Set the title of the frame
		settingsFrame.setTitle(Main.GAME_NAME + " - Settings");

		// Stops the program when the 'x' button is clicked
		settingsFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

		// Set the background color
		settingsFrame.getContentPane().setBackground(Color.WHITE);

		// Set the size of the frame
		// settingsFrame.setSize(Main.screenWidth / 3, Main.screenHeight / 2);
	}

	private void displayFrame() {
		// Size the frame
		settingsFrame.pack();

		// Center the frame
		settingsFrame.setLocationRelativeTo(null);

		// Make the frame visible
		settingsFrame.setVisible(true);

		// Stop the frame from being resized
		settingsFrame.setResizable(false);
	}

	private void applyDefaultSettings() {
		System.out.println("Default");
	}

	private void applyNewSettings() {
		System.out.println("New");
	}

}

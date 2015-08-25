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
import javax.swing.JTabbedPane;

import logic.Main;

public class SettingsMenu {

	private final String TAB_VIDEO = "Video", TAB_AUDIO = "Audio",
			TAB_GAME = "Game", TAB_CONTROLS = "Controls";
	private final String RESET_TEXT = "Reset", APPLY_TEXT = "Apply",
			CANCEL_TEXT = "Cancel";
	private JFrame settingsFrame;

	public SettingsMenu() {
		// Create an action listener for the buttons
		ActionListener buttonListener = new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// Enable the main menu
				MainMenu.menuFrame.setEnabled(true);

				// Switch based on button
				switch (e.getActionCommand()) {
				case RESET_TEXT:
					// When pressing the reset button
					applyDefaultSettings();

					// Stop
					break;
				case APPLY_TEXT:
					// When pressing the apply button
					applyNewSettings();

					// Stop
					break;
				case CANCEL_TEXT:
					// When the cancel button is pressed

					// Close the settings menu
					settingsFrame.dispose();

					// Stop
					break;
				}
			}
		};

		createSettingsMenu();

		// Create a font
		Font settingsMenuFont = new Font(Font.SANS_SERIF, Font.PLAIN, 25);

		// Create a new layout
		GridBagLayout layout = new GridBagLayout();

		// Set the layout
		settingsFrame.setLayout(layout);

		// Create a tab pane
		JTabbedPane tabbedPane = new JTabbedPane();

		// Set the font
		tabbedPane.setFont(settingsMenuFont);

		// Add the tabs to the pane
		tabbedPane.addTab(TAB_VIDEO, new VideoSettingsMenu().getMenu());
		tabbedPane.addTab(TAB_AUDIO, new AudioSettingsMenu());
		tabbedPane.addTab(TAB_GAME, new GameSettingsMenu());
		tabbedPane.addTab(TAB_CONTROLS, new ControlsSettingsMenu());

		// Create padding
		Insets padding = new Insets(1, 1, 1, 1);

		// Create constraints for the layout
		GridBagConstraints constraints = new GridBagConstraints();

		// Set fill
		constraints.fill = GridBagConstraints.HORIZONTAL;

		// Set width
		constraints.gridwidth = 3;

		// Set the padding
		constraints.insets = padding;

		// Plot at 0, 0
		constraints.gridx = 0;
		constraints.gridy = 0;

		// constraints.ipady = 270;

		// Add tabbed pane
		settingsFrame.add(tabbedPane, constraints);

		// Create button
		JButton resetButton = new JButton(RESET_TEXT);

		// Set font
		resetButton.setFont(settingsMenuFont);

		// Set action listener
		resetButton.addActionListener(buttonListener);

		// Plot at 0, 1
		constraints.gridy = 1;

		// Decrease width
		constraints.gridwidth = 1;

		// Set weight
		constraints.weightx = 1.0;

		// Add button
		settingsFrame.add(resetButton, constraints);

		// Create button
		JButton applyButton = new JButton(APPLY_TEXT);

		// Set font
		applyButton.setFont(settingsMenuFont);

		// Set action listener
		applyButton.addActionListener(buttonListener);

		// Plot at 1, 1
		constraints.gridx = 1;

		// Add button
		settingsFrame.add(applyButton, constraints);

		// Create button
		JButton cancelButton = new JButton(CANCEL_TEXT);

		// Set font
		cancelButton.setFont(settingsMenuFont);

		// Set action listener
		cancelButton.addActionListener(buttonListener);

		// Plot at 2, 1
		constraints.gridx = 2;

		// Add button
		settingsFrame.add(cancelButton, constraints);

		// Set window listener
		settingsFrame.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				// Enable main menu
				MainMenu.menuFrame.setEnabled(true);
			}
		});

		displayFrame();
	}

	/**
	 * Sets up the settings menu
	 */
	private void createSettingsMenu() {
		// Create a new frame
		settingsFrame = new JFrame();

		// Set the title of the frame
		settingsFrame.setTitle(Main.GAME_NAME + " - Settings");

		// Stops the program when the 'x' button is clicked
		settingsFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

		// Set the background color
		settingsFrame.getContentPane().setBackground(Color.WHITE);
	}

	/**
	 * Show the settings menu
	 */
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

	/**
	 * Resets settings to default
	 */
	private void applyDefaultSettings() {
		System.out.println("Default");

		// Close settings menu
		settingsFrame.dispose();
	}

	/**
	 * Uses new settings
	 */
	private void applyNewSettings() {
		// Apply video settings
		VideoSettingsMenu.setResolution();

		// Close settings menu
		settingsFrame.dispose();
	}

}

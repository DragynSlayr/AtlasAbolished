package menus;

import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;

public class SettingsMenu {

	private final String[] TABS = { "Game", "Video", "Audio", "Controls" };
	private final String[] BUTTONS = { "Reset", "Apply", "Cancel" };
	private JFrame settingsFrame;

	public SettingsMenu() {
		createSettingsMenu();

		Font settingsMenuFont = new Font(Font.SANS_SERIF, Font.PLAIN, 25);

		GridBagLayout layout = new GridBagLayout();

		settingsFrame.setLayout(layout);

		JTabbedPane tabbedPane = new JTabbedPane();
		tabbedPane.setFont(settingsMenuFont);

		for (String s : TABS) {
			tabbedPane.addTab(s, createTextPanel(s));
		}

		GridBagConstraints settingsConstraints = new GridBagConstraints();
		settingsConstraints.anchor = GridBagConstraints.NORTHWEST;
		settingsConstraints.fill = GridBagConstraints.HORIZONTAL;
		settingsConstraints.gridheight = 1;
		settingsConstraints.gridwidth = 4;
		settingsConstraints.gridx = 0;
		settingsConstraints.gridy = 0;
		settingsConstraints.ipady = 270;
		settingsConstraints.weightx = 1;
		settingsConstraints.weighty = GridBagConstraints.REMAINDER;
		settingsConstraints.insets = new Insets(0, 1, 0, 1);

		settingsFrame.add(tabbedPane, settingsConstraints);

		JButton resetButton = new JButton(BUTTONS[0]);
		resetButton.setFont(settingsMenuFont);

		GridBagConstraints resetConstraints = new GridBagConstraints();
		resetConstraints.anchor = GridBagConstraints.SOUTHWEST;
		resetConstraints.fill = GridBagConstraints.HORIZONTAL;
		resetConstraints.gridheight = 1;
		resetConstraints.weightx = 0.25;
		resetConstraints.weighty = 1;
		resetConstraints.gridx = 0;
		resetConstraints.gridy = 1;
		resetConstraints.ipady = 10;
		resetConstraints.insets = new Insets(0, 1, 1, 0);

		settingsFrame.add(resetButton, resetConstraints);

		JButton applyButton = new JButton(BUTTONS[1]);
		applyButton.setFont(settingsMenuFont);

		GridBagConstraints applyConstraints = new GridBagConstraints();
		applyConstraints.anchor = GridBagConstraints.SOUTH;
		applyConstraints.fill = GridBagConstraints.HORIZONTAL;
		applyConstraints.gridheight = 1;
		applyConstraints.weightx = 0.25;
		applyConstraints.weighty = 1;
		applyConstraints.gridx = 1;
		applyConstraints.gridy = 1;
		applyConstraints.ipady = 10;
		applyConstraints.insets = new Insets(0, 1, 1, 0);

		settingsFrame.add(applyButton, applyConstraints);

		JButton cancelButton = new JButton(BUTTONS[2]);
		cancelButton.setFont(settingsMenuFont);

		GridBagConstraints cancelConstraints = new GridBagConstraints();
		cancelConstraints.anchor = GridBagConstraints.SOUTH;
		cancelConstraints.fill = GridBagConstraints.HORIZONTAL;
		cancelConstraints.gridheight = 1;
		cancelConstraints.weightx = 0.25;
		cancelConstraints.weighty = 1;
		cancelConstraints.gridx = 2;
		cancelConstraints.gridy = 1;
		cancelConstraints.ipady = 10;
		cancelConstraints.insets = new Insets(0, 1, 1, 1);

		settingsFrame.add(cancelButton, cancelConstraints);

		/*
		 * JButton exitButton = new JButton("Exit");
		 * exitButton.addActionListener(new ActionListener() {
		 * 
		 * @Override public void actionPerformed(ActionEvent e) {
		 * settingsFrame.dispose(); } });
		 * 
		 * settingsFrame.add(exitButton);
		 */
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

		// Set the size of the frame
		settingsFrame.setSize(Main.screenWidth / 3, Main.screenHeight / 2);

		// Center the frame
		settingsFrame.setLocationRelativeTo(null);

		// Make the frame visible
		settingsFrame.setVisible(true);

		// Stop the frame from being resized
		settingsFrame.setResizable(false);
	}

}

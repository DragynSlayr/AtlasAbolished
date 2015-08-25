package menus;

import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.PlainDocument;

import logic.Main;

public class VideoSettingsMenu {

	private JPanel videoPanel;
	private static JTextField widthField, heightField;
	private final int NUMBER_LIMIT = 4;

	@SuppressWarnings("serial")
	public VideoSettingsMenu() {
		// Create a document for the width field
		PlainDocument widthFilter = new PlainDocument() {
			@Override
			public void insertString(int offs, String str, AttributeSet a)
					throws BadLocationException {
				// Check if the input is not too long and if it is a number
				if (getLength() + str.length() <= NUMBER_LIMIT && isNumber(str)) {
					super.insertString(offs, str, a);
				}
			}
		};

		// Create a document for the height field
		PlainDocument heightFilter = new PlainDocument() {
			@Override
			public void insertString(int offs, String str, AttributeSet a)
					throws BadLocationException {
				// Check if the input is not too long and is a number
				if (getLength() + str.length() <= NUMBER_LIMIT && isNumber(str)) {
					super.insertString(offs, str, a);
				}
			}
		};

		try {
			// Fill the width document with default width
			widthFilter.insertString(0, String.valueOf(Main.screenWidth), null);

			// Fill the height document with default height
			heightFilter.insertString(0, String.valueOf(Main.screenHeight),
					null);
		} catch (BadLocationException ble) {
			System.out.println(ble.getMessage());
		}

		// Create new panel
		videoPanel = new JPanel();

		// Create new layout
		GridBagLayout layout = new GridBagLayout();

		// Set the panel's layout
		videoPanel.setLayout(layout);

		// Create font for the labels
		Font labelFont = new Font(Font.SANS_SERIF, Font.PLAIN, 50);

		// Create padding
		Insets padding = new Insets(1, 1, 1, 1);

		// Create constraints for the layout
		GridBagConstraints constraints = new GridBagConstraints();

		// Set the fill
		constraints.fill = GridBagConstraints.HORIZONTAL;

		// Set the padding
		constraints.insets = padding;

		// Set the weight
		constraints.weightx = 0.25;

		// Plot at 0, 0
		constraints.gridx = 0;
		constraints.gridy = 0;

		// Create a label
		JLabel widthLabel = new JLabel("Width");

		// /Set the font
		widthLabel.setFont(labelFont);

		// Add the label
		videoPanel.add(widthLabel, constraints);

		// Initialize the width field
		widthField = new JTextField();

		// Set the font
		widthField.setFont(labelFont);

		// Set the document
		widthField.setDocument(widthFilter);

		// Plot at 1, 0
		constraints.gridx = 1;

		// Increase weight
		constraints.weightx = 0.75;

		// Add width field
		videoPanel.add(widthField, constraints);

		// Create label
		JLabel heightLabel = new JLabel("Height");

		// Set font
		heightLabel.setFont(labelFont);

		// Plot at 0, 1
		constraints.gridx = 0;
		constraints.gridy = 1;

		// Decrease weight
		constraints.weightx = 0.25;

		// Add label
		videoPanel.add(heightLabel, constraints);

		// Initialize height field
		heightField = new JTextField();

		// Set font
		heightField.setFont(labelFont);

		// Set document
		heightField.setDocument(heightFilter);

		// Plot at 1, 1
		constraints.gridx = 1;

		// Increase weight
		constraints.weightx = 0.75;

		// Add height field
		videoPanel.add(heightField, constraints);
	}

	/**
	 * Get the panel
	 * 
	 * @return The video panel
	 */
	public JPanel getPanel() {
		return videoPanel;
	}

	/**
	 * Identifies if a string is a number
	 * 
	 * @param str
	 *            The string to check
	 * @return Whether the string is a number
	 */
	private boolean isNumber(String str) {
		// Try to convert the string to an int
		try {
			Integer.valueOf(str);
		} catch (NumberFormatException nfe) {
			// Return false if conversion not possible
			return false;
		}
		// Return true if the conversion worked
		return true;
	}

	/**
	 * Set the resolution of the game screen
	 */
	public static void setResolution() {
		// Get the width
		int width = Integer.parseInt(widthField.getText());

		// Get the height
		int height = Integer.parseInt(heightField.getText());

		// Check if width is valid
		if (width > 0) {
			// Set main width
			Main.screenWidth = width;
		}

		// Check if height is valid
		if (height > 0) {
			// Set main height
			Main.screenHeight = height;
		}
	}
}

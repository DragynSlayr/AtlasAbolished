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
		PlainDocument widthFilter = new PlainDocument() {
			@Override
			public void insertString(int offs, String str, AttributeSet a)
					throws BadLocationException {
				if (getLength() + str.length() <= NUMBER_LIMIT && isNumber(str)) {
					super.insertString(offs, str, a);
				}
			}
		};

		PlainDocument heightFilter = new PlainDocument() {
			@Override
			public void insertString(int offs, String str, AttributeSet a)
					throws BadLocationException {
				if (getLength() + str.length() <= NUMBER_LIMIT && isNumber(str)) {
					super.insertString(offs, str, a);
				}
			}
		};

		try {
			widthFilter.insertString(0, String.valueOf(Main.screenWidth), null);
			heightFilter.insertString(0, String.valueOf(Main.screenHeight),
					null);
		} catch (BadLocationException ble) {
			System.out.println(ble.getMessage());
		}

		videoPanel = new JPanel();

		GridBagLayout layout = new GridBagLayout();
		videoPanel.setLayout(layout);

		Font font = new Font(Font.SANS_SERIF, Font.PLAIN, 50);

		Insets padding = new Insets(1, 1, 1, 1);

		GridBagConstraints constraints = new GridBagConstraints();
		constraints.fill = GridBagConstraints.HORIZONTAL;
		constraints.gridx = 0;
		constraints.gridy = 0;
		constraints.insets = padding;
		constraints.weightx = 0.25;

		JLabel widthLabel = new JLabel("Width");
		widthLabel.setFont(font);

		videoPanel.add(widthLabel, constraints);

		widthField = new JTextField();
		widthField.setFont(font);
		widthField.setDocument(widthFilter);

		constraints.gridx = 1;
		constraints.weightx = 0.75;

		videoPanel.add(widthField, constraints);

		JLabel heightLabel = new JLabel("Height");
		heightLabel.setFont(font);

		constraints.gridx = 0;
		constraints.gridy = 1;
		constraints.weightx = 0.25;

		videoPanel.add(heightLabel, constraints);

		heightField = new JTextField();
		heightField.setFont(font);
		heightField.setDocument(heightFilter);

		constraints.gridx = 1;
		constraints.weightx = 0.75;

		videoPanel.add(heightField, constraints);
	}

	public JPanel getPanel() {
		return videoPanel;
	}

	private boolean isNumber(String str) {
		try {
			Integer.valueOf(str);
		} catch (NumberFormatException nfe) {
			return false;
		}
		return true;
	}

	public static void setResolution() {
		int width = Integer.parseInt(widthField.getText());
		int height = Integer.parseInt(heightField.getText());

		int[] resolution = { Main.screenWidth, Main.screenHeight };

		if (width > 0) {
			resolution[0] = width;
		}

		if (height > 0) {
			resolution[1] = height;
		}

		Main.screenWidth = width;
		Main.screenHeight = height;
	}
}

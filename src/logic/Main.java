package logic;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.io.File;

import menus.MainMenu;
import files.ImageResizer;
import files.PseudoEncrypter;

/**
 * @author Inderpreet
 * @date Started 15-Apr-2013 continued 3-Aug-2015
 */

public class Main {

	public static int screenWidth, screenHeight;
	public static ImageResizer resizer;
	public static PseudoEncrypter encrypter;
	public static String filepath = "";

	public static final String GAME_NAME = "Atlas Abolished";

	public static void main(String[] args) {
		filepath = ((new File("test").exists()) ? "src\\" : "");

		// Store the screen size
		storeScreenDimensions();

		// Create a image re-sizer for every class to use
		resizer = new ImageResizer();

		// Create an encrypting object for every class to use
		encrypter = new PseudoEncrypter();

		// Create a main menu
		new MainMenu();
	}

	/**
	 * Gets the size of the screen and saves it to variables
	 */
	private static void storeScreenDimensions() {
		// Get the screen size
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();

		// Store the size
		screenWidth = (int) screenSize.getWidth();
		screenHeight = (int) screenSize.getHeight();
	}
}

package dodgearock;

import java.awt.Dimension;
import java.awt.Toolkit;

import fileHandling.FileLoader;
import fileHandling.ImageResizer;
import fileHandling.PseudoEncrypter;

/**
 * @author Inderpreet
 * @date Started 15-Apr-2013 continued 3-Aug-2015
 */

public class Main {

	public static int screenWidth, screenHeight;
	public static FileLoader loader;
	public static ImageResizer resizer;
	public static PseudoEncrypter encrypter;

	public static void main(String[] args) {
		// Store the screen size
		storeScreenDimensions();

		// Create a file loader for every class to use
		loader = new FileLoader();

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

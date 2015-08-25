package levels;

import java.awt.Image;

import javax.swing.ImageIcon;

import logic.Main;

public class Level {

	private Image background;

	public Level() {
		background = new ImageIcon(getClass()
				.getResource("/levels/background.png")).getImage();
	}

	public Image getSizedBackground() {
		return Main.resizer.resizeImage(background, Main.screenHeight,
				Main.screenWidth, false);
	}
}

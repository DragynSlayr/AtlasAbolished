package projectiles;

import java.awt.Image;

import javax.swing.ImageIcon;

import logic.Main;

public abstract class FallingObject {

	private Image image;

	public FallingObject(String imagePath, int height, int width,
			boolean isTransparent) {
		ImageIcon icon = new ImageIcon(getClass().getResource(imagePath));

		image = Main.resizer.resizeImage(icon.getImage(), height, width,
				isTransparent);
	}

	public Image getImage() {
		return image;
	}

}

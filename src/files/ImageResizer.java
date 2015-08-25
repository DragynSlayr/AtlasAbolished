package files;

import java.awt.AlphaComposite;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;

public class ImageResizer {

	public Image resizeImage(Image image, int height, int width,
			boolean hasAlpha) {
		// Set the type of the image
		int imageType = hasAlpha ? BufferedImage.TYPE_INT_ARGB
				: BufferedImage.TYPE_INT_RGB;

		// Create a new image
		BufferedImage newImage = new BufferedImage(width, height, imageType);

		// Get graphics from image
		Graphics2D g = newImage.createGraphics();

		if (hasAlpha) {
			g.setComposite(AlphaComposite.Src);
		}

		g.drawImage(image, 0, 0, width, height, null);
		g.dispose();

		return newImage;
	}

}

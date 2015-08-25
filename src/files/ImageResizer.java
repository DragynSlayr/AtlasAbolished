package files;

import java.awt.AlphaComposite;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;

public class ImageResizer {

	/**
	 * Resize an image
	 * 
	 * @param image
	 *            The image to resize
	 * @param height
	 *            The new height
	 * @param width
	 *            The new width
	 * @param hasAlpha
	 *            Whether the image is partially transparent
	 * @return The resized image
	 */
	public Image resizeImage(Image image, int height, int width,
			boolean hasAlpha) {
		// Set the type of the image
		int imageType = hasAlpha ? BufferedImage.TYPE_INT_ARGB
				: BufferedImage.TYPE_INT_RGB;

		// Create a new image
		BufferedImage newImage = new BufferedImage(width, height, imageType);

		// Get graphics from image
		Graphics2D g2d = newImage.createGraphics();

		if (hasAlpha) {
			// Set the graphics to support alpha
			g2d.setComposite(AlphaComposite.Src);
		}

		// Resize the image
		g2d.drawImage(image, 0, 0, width, height, null);

		// Dispose of graphics object
		g2d.dispose();

		// Return resized image
		return newImage;
	}

}

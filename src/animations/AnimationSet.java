package animations;

import java.awt.Image;
import java.util.ArrayList;

public abstract class AnimationSet {

	private ArrayList<Image> imageList;
	private int frameNumber, frameDelay, frameCount;

	public AnimationSet() {
		// Initialize list of images
		imageList = new ArrayList<Image>();

		// Initialize index
		frameNumber = 0;

		// Initialize counter
		frameCount = 0;

		// Set initial delay
		setDelay(1);
	}

	/**
	 * Adds images when to the animation list
	 * 
	 * @param images
	 *            The images to add
	 */
	public void addToAnimation(Image... images) {
		// Enumerate through images
		for (Image i : images) {
			// Add each image to the list
			imageList.add(i);
		}
	}

	/**
	 * Set the delay between images, in frames, each frame is 1/100th of a
	 * second
	 * 
	 * @param newDelay
	 *            The new delay, in frames
	 */
	public void setDelay(int newDelay) {
		this.frameDelay = newDelay;
	}

	/**
	 * Gets the next image in the list
	 * @return The next image
	 */
	public Image getNextFrame() {
		// Reset the index if the index is invalid
		if (frameNumber >= imageList.size()) {
			frameNumber = 0;
		}

		// Get the frame
		Image currentFrame = imageList.get(frameNumber);

		// Increment frame count
		frameCount++;

		// If the frame count has reached the delay then increments the index
		// and reset the frame count
		if (frameCount == frameDelay) {
			frameNumber++;
			frameCount = 0;
		}

		// Return the frame
		return currentFrame;
	}

}

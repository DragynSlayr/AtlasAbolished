package animations;

import java.awt.Image;
import java.util.ArrayList;

public class AnimationSet {

	private ArrayList<Image> animationSet;
	private int frameIndex, delay, frameCount;

	/**
	 * Create a set of images that can be cycled through
	 * 
	 * @param frameDelay
	 *            The delay between switching from on image to the next
	 * @param images
	 *            Starting images to add
	 */
	public AnimationSet(int frameDelay, Image... images) {
		animationSet = new ArrayList<Image>();
		frameIndex = 0;
		frameCount = 0;
		delay = frameDelay;

		// Add the starting images to the set
		for (Image i : images) {
			animationSet.add(i);
		}
	}

	/**
	 * Gets the size of the animation set
	 * 
	 * @return The length of the set
	 */
	public int getNumberOfFrames() {
		return animationSet.size();
	}

	/**
	 * Gets the animation set
	 * 
	 * @return The animation set
	 */
	public ArrayList<Image> getAnimationSet() {
		return animationSet;
	}

	/**
	 * Gets the next frame of the animation
	 * 
	 * @return The next frame
	 */
	public Image getNextFrame() {
		// Reset the index if the index is invalid
		if (frameIndex >= animationSet.size()) {
			frameIndex = 0;
		}

		// Get the frame
		Image currentFrame = animationSet.get(frameIndex);

		// Increment frame count
		frameCount++;

		// If the frame count has reached the delay then increments the index
		// and reset the frame count
		if (frameCount == delay) {
			frameIndex++;
			frameCount = 0;
		}

		// Return the frame
		return currentFrame;
	}

}

package animations;

import java.awt.Image;
import java.util.ArrayList;

public class AnimationSet {

	private ArrayList<Image> animationSet;
	private int frameIndex, delay, frameCount;

	public AnimationSet() {
		animationSet = new ArrayList<Image>();
		frameIndex = 0;
		frameCount = 0;

		setDelay(1);
	}

	public void setImages(Image... images) {
		for (Image i : images) {
			animationSet.add(i);
		}
	}

	public void setDelay(int newDelay) {
		this.delay = newDelay;
	}

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

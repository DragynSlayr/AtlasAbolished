package animations;

import java.awt.Image;
import java.util.ArrayList;

public class AnimationSet {

	private ArrayList<Image> set;
	private int frameIndex, delay, frameCount;

	public AnimationSet(int frameDelay, Image... images) {
		set = new ArrayList<Image>();
		frameIndex = 0;
		frameCount = 0;
		delay = frameDelay;

		for (Image i : images) {
			set.add(i);
		}
	}

	public int getNumberOfFrames() {
		return set.size();
	}

	public ArrayList<Image> getSet() {
		return set;
	}

	public Image getNextFrame() {
		if (frameIndex >= set.size()) {
			frameIndex = 0;
		}

		Image temp = set.get(frameIndex);

		frameCount++;

		if (frameCount == delay) {
			frameIndex++;
			frameCount = 0;
		}

		return temp;
	}

}

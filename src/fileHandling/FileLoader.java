package fileHandling;

import java.io.File;

public class FileLoader {

	/**
	 * Loads a file
	 * 
	 * @param filename
	 *            A name in the format: folder:file, where folder is within the
	 *            assets folder
	 * @return A loaded file
	 */
	public File load(String filename) {
		// Separate folder from file
		String[] split = filename.split(":");

		// Rejoin split segments into proper file path
		String filepath = "assets\\" + split[0] + "\\" + split[1];

		// Return the file
		return new File(filepath);
	}
}

package files;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import logic.Main;

/**
 * @author Inderpreet
 * @date Started 21-Apr-2013 continued 3-Aug-2015
 */
public class StatsLoader {

	private BufferedReader reader;
	private BufferedWriter writer;
	private File scoreFile, coinFile;

	/**
	 * Loads statistics from a file
	 */
	public StatsLoader() {
		try {
			// Test if the program is being ran as a jar
			if (Main.filepath == "src\\") {
				// If the program is run normally
				scoreFile = new File(Main.filepath + "files\\score.txt");
				coinFile = new File(Main.filepath + "files\\coins.txt");
			} else {
				// If the program is a jar
				scoreFile = new File("score.txt");
				coinFile = new File("coins.txt");

				// Create the files outside the jar
				scoreFile.createNewFile();
				coinFile.createNewFile();
			}
		} catch (IOException ioe) {
			System.out.println(ioe.getMessage());
		}
	}

	/**
	 * Reads score from a file
	 *
	 * @return The score
	 */
	public int getScore() {
		// Initialize a variable
		int score = 0;
		try {
			// Open a reader
			reader = new BufferedReader(new FileReader(scoreFile));

			// Get score
			String encryptedScore = reader.readLine().trim();

			// Decrypt score
			score = Integer.parseInt(Main.encrypter.decrypt(encryptedScore));

			// Close reader
			reader.close();
		} catch (IOException ioe) {
			System.out.println(ioe.getMessage());
		}

		// Return score
		return score;
	}

	/**
	 * Writes a score to the file
	 *
	 * @param score
	 *            The score to be written to the file
	 */
	public void writeScore(int score) {
		try {
			// Open writer
			writer = new BufferedWriter(new FileWriter(scoreFile));

			// Encrypt score
			String encryptedScore = Main.encrypter.encrypt(String
					.valueOf(score));

			// Write score
			writer.write(encryptedScore);

			// Flush writer
			writer.flush();

			// Close writer
			writer.close();
		} catch (IOException ioe) {
			System.out.println(ioe.getMessage());
		}
	}

	/**
	 * Gets coin score
	 * 
	 * @return The coin score
	 */
	public int getCoins() {
		// Initialize score
		int score = 0;
		try {
			// Open reader
			reader = new BufferedReader(new FileReader(coinFile));

			// Read encrypted score
			String encryptedScore = reader.readLine().trim();

			// Decrypt score
			score = Integer.parseInt(Main.encrypter.decrypt(encryptedScore));

			// Close reader
			reader.close();
		} catch (IOException ioe) {
			System.out.println(ioe.getMessage());
		}

		// Return score
		return score;
	}

	/**
	 * Stores the coin score
	 * 
	 * @param score
	 *            The coin score
	 */
	public void writeCoins(int score) {
		try {
			// Open writer
			writer = new BufferedWriter(new FileWriter(coinFile));

			// Encrypt score
			String encryptedScore = Main.encrypter.encrypt(String
					.valueOf(score));

			// Write score
			writer.write(encryptedScore);

			// Flush writer
			writer.flush();

			// Close writer
			writer.close();
		} catch (IOException ioe) {
			System.out.println(ioe.getMessage());
		}
	}
}

package files;

public class PseudoEncrypter {

	private int shift = 7;

	// TODO make a seed chain

	/**
	 * Encrypts a string kind of
	 * 
	 * @param plain
	 *            The plain text to encrypt
	 * @return The encrypted string
	 */
	public String encrypt(String plain) {
		char[] chars = plain.toCharArray();

		for (int i = 0; i < chars.length; i++) {
			chars[i] += shift;
		}

		return new String(chars);
	}

	/**
	 * Decrypts a kind-of encrypted string
	 * 
	 * @param encrypted
	 *            The encrypted string
	 * @return The plain text string
	 */
	public String decrypt(String encrypted) {
		char[] chars = encrypted.toCharArray();

		for (int i = 0; i < chars.length; i++) {
			chars[i] -= shift;
		}

		return new String(chars);
	}
}

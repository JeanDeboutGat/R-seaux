package ex3;

import java.util.Random;

/**
 * A class that generates names
 * @author Thomas Liard
 *
 */
public class NameGenerator {
	private static final char[] letters = "abcdefghijklmnopqrstuvwxyz".toCharArray();
	private static final int LENGTH=8;
	private static Random r = new Random();
	
	
	public static String generateName() {
		char[] name = new char[LENGTH];
		for(int i=0; i< LENGTH; i++) {
			name[i] = letters[r.nextInt(Integer.MAX_VALUE) % LENGTH];
		}
		return new String(name);
	}
}

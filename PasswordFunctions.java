package passwordStrengthCheckerAndGenerator;

import java.util.HashSet;
import java.util.Random;

public class PasswordFunctions {
	public static char[] passwordArrayPrep() {
		String passSpecial = "~`!@#$%^&*()_-+={[}]|\\:;\"'<,>.?/";
		char[] arr = new char[95];
		// capital letters
		for (int i = 0; i < 26; i++)
			arr[i] = (char) (65 + i);
		// lowercase letters
		for (int i = 26; i < 52; i++)
			arr[i] = (char) (97 + i - 26);
		// numbers
		for (int i = 52; i < 63; i++)
			arr[i] = (char) (i - 4);
		// special characters
		for (int i = 63; i < 95; i++) {
			arr[i] = passSpecial.charAt(i - 63);
		}

		return arr;
	}

	public static String passwordGenerator(int length, boolean includeSpecialCharacters, char[] alpha) {
		if (includeSpecialCharacters)
			return passGen(alpha, length, 95);
		else
			return passGen(alpha, length, 62);
	}

	private static String passGen(char[] arr, int length, int upper) {
		String password = "";
		HashSet<Character> set = new HashSet<Character>();
		Random rand = new Random();

		for (int i = 0; i < length; i++) {
			char c = arr[rand.nextInt(upper)];
			while (set.contains(c) || password.length() > 63) {
				c = arr[rand.nextInt(upper)];
			}
			set.add(c);
			password += c;
		}

		return password;
	}

	public static int[] passwordStrengthChecker(String password) {
		int[] results = new int[6];
		results[0] = password.length();
		HashSet<Character> set = new HashSet<Character>();
		for (int i = 0; i < password.length(); i++) {
			char c = password.charAt(i);
			
			if (c < 123 && c > 96)
				results[1] = 1;
			else if (c > 64 && c < 91)
				results[2] = 1;
			else if (c > 47 && c < 58)
				results[3] = 1;
			else
				results[4] = 1;
			if (set.contains(c))
				results[5] = 1;
			
			set.add(c);
		}

		return results;
	}
}

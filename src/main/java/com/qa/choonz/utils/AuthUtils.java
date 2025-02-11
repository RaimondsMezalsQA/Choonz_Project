package com.qa.choonz.utils;

import java.security.SecureRandom;
import java.util.HashMap;

public class AuthUtils {

	// variables
	private static HashMap<String, Long> userTokens = new HashMap<>();
	private static int tokenSize = 10;

	// methods

	private static String genToken() {
		int lLimit = 48; // 48 = 0
		int uLimit = 122; // 122 = z
		SecureRandom generated = new SecureRandom();
		// credit: https://www.baeldung.com/java-random-string
		return generated.ints(lLimit, uLimit + 1).filter(i -> (i <= 57 || i >= 65) && (i <= 90 || i >= 97))
				.limit(tokenSize).collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
				.toString();
	}


	public static String createUserToken(Long userId) {
		String newToken = genToken();
		userTokens.put(newToken, userId);
		return newToken;
	}

	public static void deleteUserToken(String token) {
		if (userTokens.containsKey(token)) {
			userTokens.remove(token);
		}
	}

	public static Long getToken(String token) {
		if (userTokens.containsKey(token)) {
			return userTokens.get(token);
		} else {
			return null;
		}
	}

}

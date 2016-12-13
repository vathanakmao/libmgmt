package com.vathanakmao.libmgmt.util;

import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Random;

public final class SecurityUtil {
	private static final int MAX_SALT_LENGTH = 10;

	public static String hashMD5(String text, String salt) throws NoSuchAlgorithmException, UnsupportedEncodingException {
		MessageDigest md = MessageDigest.getInstance("MD5");
		byte[] digestedText = md.digest((text + salt).getBytes(StandardCharsets.UTF_8));
		return java.util.Base64.getEncoder().encodeToString(digestedText);
	}
	
	public static String getRandomText() {
		final String chars = "abcdefghijklmnopqrstuvwxyz0123456789";
		Random rand = new Random();
		StringBuffer result = new StringBuffer();
		while(result.length() < MAX_SALT_LENGTH) {
			int idx = (int) (rand.nextFloat() * chars.length());
			result.append(chars.charAt(idx));
		}
		return result.toString(); 
	}
}

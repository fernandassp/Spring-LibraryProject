package com.libraryProject.services.util;

import org.apache.commons.codec.digest.DigestUtils; // commons codec

public class HashUtil {

	public static String getSecureHash(String text) {
		String hash = DigestUtils.sha256Hex(text);
		return hash;
	}
}

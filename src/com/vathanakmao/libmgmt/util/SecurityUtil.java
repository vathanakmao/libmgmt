package com.vathanakmao.libmgmt.util;

import java.util.Base64;

public final class SecurityUtil {

	public static final String decodeBase64(String encodedText) {
		return new String(Base64.getDecoder().decode(encodedText));
	}
}

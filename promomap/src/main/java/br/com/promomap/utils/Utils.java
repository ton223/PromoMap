package br.com.promomap.utils;

import java.util.UUID;

/**
 * @author <a href="mailto:leandro.lucas_@hotmail.com">Leandro Lucas Santos</a>
 */

public class Utils {
	
	public static String generateRandomUUID() {
		return UUID.randomUUID().toString();
	}
}
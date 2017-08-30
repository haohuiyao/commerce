package com.meeno.framework.util;

public interface EncryptService {
	String encrypt(String encryptStr);
	String decrypt(String decryptString);
	String MD5_32(String sourceStr);
}

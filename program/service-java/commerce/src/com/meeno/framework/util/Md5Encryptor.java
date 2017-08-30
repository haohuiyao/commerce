package com.meeno.framework.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import com.meeno.framework.exception.BusinessRuntimeException;

/**
 * Implements MD5 Encryption method. Used to encrypt user password that will be
 * stored in database USERS table.
 */
public class Md5Encryptor implements EncryptService {

    private final String[] hexDigits = { "0", "1", "2", "3", "4", "5",
        "6", "7", "8", "9", "a", "b", "c", "d", "e", "f" };

    /*
     * (non-Javadoc)
     *
     * @see com.merck.pgate.util.PasswordEncryptor#encrypt(java.lang.String)
     */
    public String encrypt(String originalStr) {
        String resultString = null;

        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            resultString = byteArrayToHexString(md.digest(originalStr.getBytes()));
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return resultString;
    }

    /**
     * Transfer byte array to HEX string
     *
     * @param b
     *            byte array
     * @return HEX string
     */

    private String byteArrayToHexString(byte[] b) {
        StringBuffer resultSb = new StringBuffer();
        for (int i = 0; i < b.length; i++) {
            resultSb.append(byteToHexString(b[i]));
        }
        return resultSb.toString();
    }

    /**
     * Transfer byte to HEX string
     *
     * @param b byte
     * @return HEX string
     */
    private String byteToHexString(byte b) {
        int n = b;
        if (n < 0)
            n = 256 + n;
        int d1 = n / 16;
        int d2 = n % 16;
        return hexDigits[d1] + hexDigits[d2];
    }

	public String decrypt(String decryptString) {
		throw new BusinessRuntimeException("Not implements");
	}

	@Override
	public String MD5_32(String sourceStr) {
		String resultString = null;
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            resultString = byteArrayToHexString(md.digest(sourceStr.getBytes()));
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return resultString;
	}

}

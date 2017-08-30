package com.meeno.framework.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;


public class DESEncryptor implements EncryptService {

	public static final String ALGORITHM = "DES";

	private static String strDefaultKey = "meeno";
	private static final Log LOGGER = LogFactory.getLog(DESEncryptor.class);

	private SecretKey desKey;

	public DESEncryptor(){
		try{
			MessageDigest md = MessageDigest.getInstance("MD5");
			md.update(strDefaultKey.getBytes());
			byte[] pswKey = md.digest();
			DESKeySpec desKeySpec = new DESKeySpec(pswKey);
			SecretKeyFactory desKeyFac = SecretKeyFactory.getInstance(ALGORITHM);
			desKey = desKeyFac.generateSecret(desKeySpec);
		}catch(Exception e){
			e.printStackTrace();
			LOGGER.error(e.getMessage());

		}
	}

	public String encrypt(String password) {
		String enStr = null;
		try{
			Cipher encryptCipher = Cipher.getInstance("DES");
			encryptCipher.init(Cipher.ENCRYPT_MODE, desKey);

			byte[] enPassword = encryptCipher.doFinal(password.getBytes("UTF8"));

			 enStr = new BASE64Encoder().encode(enPassword);

		}catch(Exception e){
			e.printStackTrace();
			LOGGER.error(e.getMessage());
		}

		return enStr;

	}

	public String MD5_32(String sourceStr) {
        String result = "";
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(sourceStr.getBytes());
            byte b[] = md.digest();
            int i;
            StringBuffer buf = new StringBuffer("");
            for (int offset = 0; offset < b.length; offset++) {
                i = b[offset];
                if (i < 0)
                    i += 256;
                if (i < 16)
                    buf.append("0");
                buf.append(Integer.toHexString(i));
            }
            result = buf.toString();
            System.out.println("MD5(" + sourceStr + ",32) = " + result);
        } catch (NoSuchAlgorithmException e) {
            System.out.println(e);
        }
        return result;
    }
	
	public String decrypt(String password){

		String deStr = null;

		try{
			Cipher decryptCipher = Cipher.getInstance("DES");
			decryptCipher.init(Cipher.DECRYPT_MODE, desKey);

			byte[] dePassword = new BASE64Decoder().decodeBuffer(password);

			byte[] dec = decryptCipher.doFinal(dePassword);

			deStr = new String(dec, "UTF8");

		}catch(Exception e){
			e.printStackTrace();
			LOGGER.error(e.getMessage());
		}

		return deStr;

	}

	/**
	 * @param args
	 * @throws InvalidKeySpecException
	 * @throws NoSuchAlgorithmException
	 * @throws InvalidKeyException
	 */

	public static void main(String[] args) throws Exception {
		DESEncryptor d = new DESEncryptor();
		String s = d.encrypt("111111");
		System.out.println(s.toString());
		String b = d.decrypt(s);
		System.out.println(b);

	}

}

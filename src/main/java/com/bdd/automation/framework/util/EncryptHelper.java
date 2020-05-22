package com.bdd.automation.framework.util;

import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

import javax.crypto.Cipher;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.SecretKeySpec;

public class EncryptHelper {
	public static String ALGO = "AES";
	public static final String KEY_VALUE = FrameworkProperties.getProperty("framework.decoder.key");
	
	private static Key generateKey() throws Exception {
		Key key = new SecretKeySpec(KEY_VALUE.getBytes(), ALGO);
		return key;
	}
	
	public static String encrypt(String data) throws Exception {
		Key key = EncryptHelper.generateKey();
		Cipher cipher = Cipher.getInstance(ALGO);
		cipher.init(Cipher.ENCRYPT_MODE, key);
		byte[] encVal = cipher.doFinal(data.getBytes());
		String encryptedValue = new String(Base64.getEncoder().encode(encVal));
		
		return encryptedValue;
		
	}
	
	public static String decrypt(String encryptedData) throws Exception  {
		Key key = generateKey();
		Cipher cipher = Cipher.getInstance(ALGO);
		cipher.init(Cipher.DECRYPT_MODE, key);
		byte[] decordedValue = Base64.getDecoder().decode(encryptedData);
		byte[] decVal = cipher.doFinal(decordedValue);
		String decryptedValue = new String(decVal);
		return decryptedValue;
		
	}
	
	

}

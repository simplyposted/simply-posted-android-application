package com.qedum.simplyposted.util;

import android.support.annotation.Nullable;
import android.util.Base64;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;

public class StringsEncryptorDecryptor {

	private static String charsetName = "UTF8";
    private static String algorithm = "DES";
    private static int base64Mode = Base64.DEFAULT;


//    @Nullable
//    public static String getEncryptedPassword(String password) {
//        return StringsEncryptorDecryptor.encrypt(Storage.getInstance().getSecretKey(), password);
//    }

    public static String encrypt(String key, String data) {
        if (key == null || data == null)
            return null;
        try {
            DESKeySpec desKeySpec = new DESKeySpec(key.getBytes(charsetName));
            SecretKeyFactory secretKeyFactory = SecretKeyFactory.getInstance(algorithm);
            SecretKey secretKey = secretKeyFactory.generateSecret(desKeySpec);
            byte[] dataBytes = data.getBytes(charsetName);
            Cipher cipher = Cipher.getInstance(algorithm);
            cipher.init(Cipher.ENCRYPT_MODE, secretKey);
            return Base64.encodeToString(cipher.doFinal(dataBytes), base64Mode);
        } catch (Exception e) {
            return null;
        }
    }

    public static String decrypt(String key, String data) {
        if (key == null || data == null)
            return null;
        try {
            byte[] dataBytes = Base64.decode(data, base64Mode);
            DESKeySpec desKeySpec = new DESKeySpec(key.getBytes(charsetName));
            SecretKeyFactory secretKeyFactory = SecretKeyFactory.getInstance(algorithm);
            SecretKey secretKey = secretKeyFactory.generateSecret(desKeySpec);
            Cipher cipher = Cipher.getInstance(algorithm);
            cipher.init(Cipher.DECRYPT_MODE, secretKey);
            byte[] dataBytesDecrypted = (cipher.doFinal(dataBytes));
            return new String(dataBytesDecrypted);
        } catch (Exception e) {
            return null;
        }
    }

}

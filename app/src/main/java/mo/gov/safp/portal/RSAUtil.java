package mo.gov.safp.portal;


import android.util.Base64;

import java.io.ByteArrayOutputStream;
import java.security.Key;
import java.security.KeyFactory;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;

import javax.crypto.Cipher;


public class RSAUtil {

    public final static String PUBLIC_KEY_NAME = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQDTFEG7hD2aRO8mDdSU8wor8LwM\n" +
            "eOvrlUCJ9wL7hGh0HNxiWfVPD10jBwnSAdNq1Ijy+qQEzA1Ow651HdUsrQSNo2Cv\n" +
            "rso/HM8kGzAiPrYcpuEHyxD9NtzuyK1HAGJ7ogQN4GP7mJuFBW8ki355J131Yyjx\n" +
            "FgXOyEkzw9/IHkUJ/QIDAQAB";
    public final static String PRIVATE_KEY_NAME = "MIICdgIBADANBgkqhkiG9w0BAQEFAASCAmAwggJcAgEAAoGBANMUQbuEPZpE7yYN\n" +
            "1JTzCivwvAx46+uVQIn3AvuEaHQc3GJZ9U8PXSMHCdIB02rUiPL6pATMDU7DrnUd\n" +
            "1SytBI2jYK+uyj8czyQbMCI+thym4QfLEP023O7IrUcAYnuiBA3gY/uYm4UFbySL\n" +
            "fnknXfVjKPEWBc7ISTPD38geRQn9AgMBAAECgYBL4MFMblpSxasOi/Q6Yy/p4c1H\n" +
            "0oREmM+tiN1KmyuNaf6TYHE9rXE2sUYHd16Pzb05U354tv2lrdReB45lVAEJXmsY\n" +
            "DOBhxiZ3IS9CyImujCZ4wFR+DgNZaBD2E8JEC71TOwUQtfrkQpQdp9n+oQuRFxeC\n" +
            "sYJG6YTyZ0SQ+X/rhQJBAOoDChfm8msThgIlN6gk08Nozb7fCp+If1AvzzsWO9uJ\n" +
            "EYfaSOkvXhyi5CDmaGi51fM+33UJsvr2qdPjHZ8BbisCQQDm6Zba37TlinziG+wq\n" +
            "fGNI4otGkQCMg3BKv+y/9XCps8uVuRbJEkNXab7paG7bix4uLWYloWcY2xjOQhlo\n" +
            "unx3AkBrTmF+fGSFPTqiGTbgX6LLZG+UKurQJONcfc/lJFjmXoAvb6UnaJUbRIaf\n" +
            "n4gZHb5CAH6vSHdOJXXSt76JfzppAkBItWzG5oHzdTP1jo1Co4AvxwXL5oShuhF1\n" +
            "uBE4MHSVOwPz1SATmCiUMbvniva9MoUCTVFIsBXigbrB15Zgn7fpAkEAsNnRrYiU\n" +
            "8q0LFZ9ekidyZGyPuMBhF7McQbgaprHtVAm1anqDPNyOqcBHqxRJ6cr1RfKWcncB\n" +
            "fV2H4GNCUrTaWg==";
    public final static String SERVICE_PUBLIC_KEY_NAME = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQDTFEG7hD2aRO8mDdSU8wor8LwM\n" +
            "eOvrlUCJ9wL7hGh0HNxiWfVPD10jBwnSAdNq1Ijy+qQEzA1Ow651HdUsrQSNo2Cv\n" +
            "rso/HM8kGzAiPrYcpuEHyxD9NtzuyK1HAGJ7ogQN4GP7mJuFBW8ki355J131Yyjx\n" +
            "FgXOyEkzw9/IHkUJ/QIDAQAB";

    /**
     * 加密算法RSA
     */
    public static final String KEY_ALGORITHM = "RSA";

    /**
     * RSA最大加密明文大小
     */
    private static final int MAX_ENCRYPT_BLOCK = 117;

    /**
     * RSA最大解密密文大小
     */
    private static final int MAX_DECRYPT_BLOCK = 128;

    /**
     * RSA公钥加密
     *
     * @param str       加密字符串
     * @param publicKey 公钥
     * @return 密文
     * @throws Exception 加密过程中的异常信息
     */
    public static String encrypt(String str, String publicKey) throws Exception {
        //base64编码的公钥
        byte[] decoded = Base64.decode(publicKey, Base64.DEFAULT);
        RSAPublicKey pubKey = (RSAPublicKey) KeyFactory.getInstance("RSA").generatePublic(new X509EncodedKeySpec(decoded));
        //RSA加密
        Cipher cipher = Cipher.getInstance("RSA");
        cipher.init(Cipher.ENCRYPT_MODE, pubKey);
        String outStr = Base64.encodeToString(cipher.doFinal(str.getBytes("UTF-8")), Base64.DEFAULT);
        return outStr;
    }

    /**
     * RSA私钥解密
     *
     * @param str        加密字符串
     * @param privateKey 私钥
     * @return 铭文
     * @throws Exception 解密过程中的异常信息
     */
    public static String decrypt(String str, String privateKey) throws Exception {
        //64位解码加密后的字符串
        byte[] inputByte = Base64.decode(str.getBytes("UTF-8"), Base64.DEFAULT);
        //base64编码的私钥
        byte[] decoded = Base64.decode(privateKey, Base64.DEFAULT);
        RSAPrivateKey priKey = (RSAPrivateKey) KeyFactory.getInstance("RSA").generatePrivate(new PKCS8EncodedKeySpec(decoded));
        //RSA解密
        Cipher cipher = Cipher.getInstance("RSA");
        cipher.init(Cipher.DECRYPT_MODE, priKey);
        String outStr = new String(cipher.doFinal(inputByte));
        return outStr;
    }

    public static byte[] decryptByPrivateKey(byte[] encryptedData, String privateKey)
            throws Exception {
        byte[] keyBytes = Base64.decode(privateKey, Base64.DEFAULT);
        PKCS8EncodedKeySpec pkcs8KeySpec = new PKCS8EncodedKeySpec(keyBytes);
        KeyFactory keyFactory = KeyFactory.getInstance(KEY_ALGORITHM);
        Key privateK = keyFactory.generatePrivate(pkcs8KeySpec);
        Cipher cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding");
        cipher.init(Cipher.DECRYPT_MODE, privateK);
        int inputLen = encryptedData.length;
        byte[] result = new byte[(encryptedData.length / 128 + 1) * 117];
        int offSet = 0;
        byte[] cache;
        int i = 0;
        int len = 0;
        // 对数据分段解密
        while (inputLen - offSet > 0) {
            if (inputLen - offSet > MAX_DECRYPT_BLOCK) {
                cache = cipher.doFinal(encryptedData, offSet, MAX_DECRYPT_BLOCK);
            } else {
                cache = cipher.doFinal(encryptedData, offSet, inputLen - offSet);
            }
            i++;
            offSet = i * MAX_DECRYPT_BLOCK;
            for (byte b : cache) {
                result[len++] = b;
            }
        }
        return result;
    }

    public static byte[] decryptByPublicKey(byte[] encryptedData, String publicKey)
            throws Exception {
        byte[] keyBytes = Base64.decode(publicKey, Base64.DEFAULT);
        X509EncodedKeySpec x509KeySpec = new X509EncodedKeySpec(keyBytes);
        KeyFactory keyFactory = KeyFactory.getInstance(KEY_ALGORITHM);
        Key publicK = keyFactory.generatePublic(x509KeySpec);
        Cipher cipher = Cipher.getInstance(keyFactory.getAlgorithm());
        cipher.init(Cipher.DECRYPT_MODE, publicK);
        int inputLen = encryptedData.length;
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        int offSet = 0;
        byte[] cache;
        int i = 0;
        // 对数据分段解密
        while (inputLen - offSet > 0) {
            if (inputLen - offSet > MAX_DECRYPT_BLOCK) {
                cache = cipher.doFinal(encryptedData, offSet, MAX_DECRYPT_BLOCK);
            } else {
                cache = cipher.doFinal(encryptedData, offSet, inputLen - offSet);
            }
            out.write(cache, 0, cache.length);
            i++;
            offSet = i * MAX_DECRYPT_BLOCK;
        }
        byte[] decryptedData = out.toByteArray();
        out.close();
        return decryptedData;
    }

    public static byte[] encryptByPublicKey(byte[] data, String publicKey)
            throws Exception {
        byte[] keyBytes = Base64.decode(publicKey, Base64.DEFAULT);
        ;
        X509EncodedKeySpec x509KeySpec = new X509EncodedKeySpec(keyBytes);
        KeyFactory keyFactory = KeyFactory.getInstance(KEY_ALGORITHM);
        Key publicK = keyFactory.generatePublic(x509KeySpec);
        // 对数据加密
        Cipher cipher = Cipher.getInstance(keyFactory.getAlgorithm());
        cipher.init(Cipher.ENCRYPT_MODE, publicK);
        int inputLen = data.length;
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        int offSet = 0;
        byte[] cache;
        int i = 0;
        // 对数据分段加密
        while (inputLen - offSet > 0) {
            if (inputLen - offSet > MAX_ENCRYPT_BLOCK) {
                cache = cipher.doFinal(data, offSet, MAX_ENCRYPT_BLOCK);
            } else {
                cache = cipher.doFinal(data, offSet, inputLen - offSet);
            }
            out.write(cache, 0, cache.length);
            i++;
            offSet = i * MAX_ENCRYPT_BLOCK;
        }
        byte[] encryptedData = out.toByteArray();
        out.close();
        return encryptedData;
    }


    public static byte[] encryptByPrivateKey(byte[] data, String privateKey)
            throws Exception {
        byte[] keyBytes = Base64.decode(privateKey, Base64.DEFAULT);
        PKCS8EncodedKeySpec pkcs8KeySpec = new PKCS8EncodedKeySpec(keyBytes);
        KeyFactory keyFactory = KeyFactory.getInstance(KEY_ALGORITHM);
        Key privateK = keyFactory.generatePrivate(pkcs8KeySpec);
        Cipher cipher = Cipher.getInstance(keyFactory.getAlgorithm());
        cipher.init(Cipher.ENCRYPT_MODE, privateK);
        int inputLen = data.length;
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        int offSet = 0;
        byte[] cache;
        int i = 0;
        // 对数据分段加密
        while (inputLen - offSet > 0) {
            if (inputLen - offSet > MAX_ENCRYPT_BLOCK) {
                cache = cipher.doFinal(data, offSet, MAX_ENCRYPT_BLOCK);
            } else {
                cache = cipher.doFinal(data, offSet, inputLen - offSet);
            }
            out.write(cache, 0, cache.length);
            i++;
            offSet = i * MAX_ENCRYPT_BLOCK;
        }
        byte[] encryptedData = out.toByteArray();
        out.close();
        return encryptedData;
    }
}
package com.corp.oa.util;

import org.apache.commons.codec.digest.DigestUtils;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import javax.crypto.spec.IvParameterSpec;

/**
 * Created by hawk on 2018/5/14.
 */
public class DesUtils {
    private static String keyStr = "DIANXIAO";// 密钥

    /**
     * DES解密
     *
     * @param message
     * @param key
     * @return
     * @throws Exception
     */
    public static String DecodeDES(String message, String key) throws Exception {
        byte[] bytesrc = convertHexString(message);
        Cipher cipher = Cipher.getInstance("DES/CBC/PKCS5Padding");
        DESKeySpec desKeySpec = new DESKeySpec(key.getBytes("UTF-8"));
        SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");
        SecretKey secretKey = keyFactory.generateSecret(desKeySpec);
        IvParameterSpec iv = new IvParameterSpec(key.getBytes("UTF-8"));
        cipher.init(Cipher.DECRYPT_MODE, secretKey, iv);
        byte[] retByte = cipher.doFinal(bytesrc);
        return new String(retByte, "UTF-8");
    }

    public static String DecodeDES(String message) throws Exception {
        return DecodeDES(message, keyStr);
    }

    /**
     * DES加密
     *
     * @param message
     * @param key
     * @return
     * @throws Exception
     */
    public static String EncodeDES(String message, String key) throws Exception {
        Cipher cipher = Cipher.getInstance("DES/CBC/PKCS5Padding");
        DESKeySpec desKeySpec = new DESKeySpec(key.getBytes("UTF-8"));
        SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");
        SecretKey secretKey = keyFactory.generateSecret(desKeySpec);
        IvParameterSpec iv = new IvParameterSpec(key.getBytes("UTF-8"));
        cipher.init(Cipher.ENCRYPT_MODE, secretKey, iv);
        return toHexString(cipher.doFinal(message.getBytes("UTF-8")));
    }

    public static String EncodeDES(String message) throws Exception {
        return EncodeDES(message, keyStr);
    }

    /**
     * MD5加密
     * 使用org.apache.commons.codec.digest.DigestUtils加密
     * http://commons.apache.org/proper/commons-codec/download_codec.cgi
     *
     * @param message
     * @return
     */
    public static String EncodeMD5(String message) {
        return DigestUtils.md5Hex(message);
    }


    public static byte[] convertHexString(String ss) {
        byte digest[] = new byte[ss.length() / 2];
        for (int i = 0; i < digest.length; i++) {
            String byteString = ss.substring(2 * i, 2 * i + 2);
            int byteValue = Integer.parseInt(byteString, 16);
            digest[i] = (byte) byteValue;
        }
        return digest;
    }

    public static String toHexString(byte b[]) {
        StringBuffer hexString = new StringBuffer();
        for (int i = 0; i < b.length; i++) {
            String plainText = Integer.toHexString(0xff & b[i]);
            if (plainText.length() < 2)
                plainText = "0" + plainText;
            hexString.append(plainText);
        }
        return hexString.toString();
    }

    /**
     * 加密解密测试
     *
     * @param args
     */
    public static void main(String[] args) {
        try {
            String strText = "吴淞路400号";
            String deseResult = EncodeDES(strText);//加密
            System.out.println("加密结果：" + deseResult);
            deseResult = "8499BCC368F49F5EDA81C577B9A4988E";
            String desdResult = DecodeDES(deseResult);// 解密
            System.out.println("解密结果：" + desdResult);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

package com.corp.sso.utils;

import com.certicom.security.cert.internal.x509.Base64;
import com.corp.oa.util.G;

import javax.crypto.Mac;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.security.MessageDigest;
import java.util.Arrays;
import java.util.Map;

/**
 * @author wqj
 */
public class SignUtil {

    private static String MAC_NAME = "HmacSHA1";
    private static String ENCODING = "UTF-8";

    public static String sign(Map<String, Object> params, String clientKey) throws Exception {

        if (G.isNvl(clientKey)) {
            throw new NullPointerException("签名密钥clientKey为空！");
        }
        Object[] keys = params.keySet().toArray();
        Arrays.sort(keys);
        //将参数进行拼接
        StringBuilder builder = new StringBuilder();
        for (Object key: keys) {
            builder.append(key)
                    .append("=")
                    .append(params.get(key))
                    .append("&");
        }

        String urlStr = builder.toString();
        String urlencoded = null;
        if (!G.isNvl(urlStr)) {
            urlencoded = urlStr.substring(0, urlStr.length() - 1);
        }
        byte[] signBytes = HmacSHA1Encrypt(urlencoded, clientKey);
        String signature = Base64.encode(signBytes);

        return signature;
    }


    /**
     * HmacSHA1加密
     * @param encryptContent 签名内容
     * @param encryptKey 签名密钥
     * @return
     */
    private static byte[] HmacSHA1Encrypt(String encryptContent, String encryptKey) throws Exception {

        byte[] data = encryptKey.getBytes(ENCODING);
        // 根据给定的字节数组生成一个密钥，第二参数为密钥算法的名称
        SecretKey secretKey = new SecretKeySpec(data, MAC_NAME);
        // 生成一个 Mac 算法的 mac 对象
        Mac mac = Mac.getInstance(MAC_NAME);

        mac.init(secretKey);
        byte[] content = encryptContent.toLowerCase().getBytes(ENCODING);
        mac.update(content);

        return mac.doFinal();
    }

    public static String md5(String secretContent) throws Exception {

        MessageDigest md = MessageDigest.getInstance("MD5");
        md.update(secretContent.getBytes());
        return bytesToString(md.digest());
    }

    public static String sha1(String secretContent) throws Exception {
        MessageDigest md = MessageDigest.getInstance("SHA1");
        md.update(secretContent.getBytes());

        return bytesToString(md.digest());
    }

    private static String bytesToString(byte[] bits) {
        StringBuffer buf = new StringBuffer();

        for(int i=0;i<bits.length;i++){
            int a = bits[i];

            if(a<0) a += 256;
            if(a<16) buf.append("0");

            buf.append(Integer.toHexString(a));
        }
        return buf.toString();
    }


}

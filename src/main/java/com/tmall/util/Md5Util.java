package com.tmall.util;

import java.security.MessageDigest;

/**
 * MD5 加密工具类
 * @author qiuxin
 */
public class Md5Util {

    private static String byteArrayToHexString(byte[] b) {
        StringBuffer resultString = new StringBuffer();
        for (byte aB : b) {
            resultString.append(byteToHexString(aB));
        }
        return resultString.toString();
    }

    private static String byteToHexString(byte b) {
        int n = b;
        if (n < 0) {
            n += 256;
        }
        int d1 = n / 16;
        int d2 = n % 16;
        return HEX_DIGITS[d1] + HEX_DIGITS[d2];
    }

    /**
     * 返回大写 MD5 密码
     * @param origin 原始值
     * @param charsetName 编码用字符集
     * @return 返回大写 MD5
     */
    private static String md5Encode(String origin, String charsetName) {
        String resultString = null;
        try {
            resultString = origin;
            MessageDigest md = MessageDigest.getInstance("MD5");
            if (charsetName == null || "".equals(charsetName)) {
                resultString = byteArrayToHexString(md.digest(resultString.getBytes()));
            } else {
                resultString = byteArrayToHexString(md.digest(resultString.getBytes(charsetName)));
            }
        } catch (Exception exception) {}
        return resultString.toUpperCase();
    }

    public static String md5EncodeUtf8(String origin) {
        origin = origin + PropertiesUtil.getProperty("password.salt", "");
        return md5Encode(origin, "utf-8");
    }

    private static final String[] HEX_DIGITS = {"0", "1", "2", "3", "4", "5",
            "6", "7", "8", "9", "a", "b", "c", "d", "e", "f"};
}
package com.hang.array;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * @author Hang
 * @date 2020-09-01 16:42
 */
public class QidaSHA256 {
    // 调用
    public static void main(String[] args) {
        String secretkey = "78142683-2f62-4be0-92c3-dd0985e1bd45";
        String salt = "123";
        String sign = QidaSHA256.SHA256Encrypt(secretkey + salt);
        System.out.println(sign);
    }

    // 加密方法
    public static final String ALGORITHM = "SHA-256";

    public static String SHA256Encrypt(String orignal) {
        MessageDigest md = null;
        try {
            md = MessageDigest.getInstance(ALGORITHM);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        if (null != md) {
            byte[] origBytes = orignal.getBytes();
            md.update(origBytes);
            byte[] digestRes = md.digest();
            String digestStr = getDigestStr(digestRes);
            return digestStr;
        }

        return null;
    }

    private static String getDigestStr(byte[] origBytes) {
        String tempStr = null;
        StringBuffer stb = new StringBuffer();
        for (int i = 0; i < origBytes.length; i++) {
            // System.out.println("and by bit: " + (origBytes[i] & 0xff));
            // System.out.println("no and: " + origBytes[i]);
            // System.out.println("---------------------------------------------");
            // 这里按位与是为了把字节转整时候取其正确的整数，java中一个int是4个字节
            // 如果origBytes[i]最高位为1，则转为int时，int的前三个字节都被1填充了
            tempStr = Integer.toHexString(origBytes[i] & 0xff);
            if (tempStr.length() == 1) {
                stb.append("0");
            }
            stb.append(tempStr);

        }
        return stb.toString();
    }

}

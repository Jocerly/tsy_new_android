package cn.tsy.base.uitls;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Description:
 * 对外提供getSHA(String str)方法
 * Created by HeinoC on 15/5/7.
 */
public class SHA {
    public static byte[] getSHABytes(String val) throws NoSuchAlgorithmException {
        MessageDigest sha = MessageDigest.getInstance("SHA-1");
        sha.update(val.getBytes());
        byte[] m = sha.digest();//加密
        return m;
    }

    public static String getSHA1(String val) throws NoSuchAlgorithmException {
        MessageDigest sha = MessageDigest.getInstance("SHA-1");
        sha.update(val.getBytes());
        byte[] m = sha.digest();//加密
        return getString(m);
    }

    private static String getString(byte[] messageDigest) {

        // Create Hex String
        StringBuffer hexString = new StringBuffer();
        // 字节数组转换为 十六进制 数
        for (int i = 0; i < messageDigest.length; i++) {
            String shaHex = Integer.toHexString(messageDigest[i] & 0xFF);
            if (shaHex.length() < 2) {
                hexString.append(0);
            }
            hexString.append(shaHex);
        }

        return hexString.toString();
    }
}
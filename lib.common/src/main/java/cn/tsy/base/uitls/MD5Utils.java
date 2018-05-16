package cn.tsy.base.uitls;

import android.text.TextUtils;

import java.security.MessageDigest;

/**
 * md5加密处理类
 *
 * @author Jocerly
 */
public class MD5Utils {

    @SuppressWarnings("unused")
    public static String compute(String inStr) {
        if (TextUtils.isEmpty(inStr)) {
            return "";
        }
        char hexDigits[] = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F'};
        try {
            byte[] btInput = inStr.getBytes();
            // 获得MD5摘要算法的 MessageDigest 对象
            MessageDigest mdInst = MessageDigest.getInstance("MD5");
            // 使用指定的字节更新摘要
            mdInst.update(btInput);
            // 获得密文
            byte[] md = mdInst.digest();
            // 把密文转换成十六进制的字符串形式
            int j = md.length;
            char str[] = new char[j * 2];
            int k = 0;
            for (int i = 0; i < j; i++) {
                byte byte0 = md[i];
                str[k++] = hexDigits[byte0 >>> 4 & 0xf];
                str[k++] = hexDigits[byte0 & 0xf];
            }
            return new String(str);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * @return String
     */
    public static String computeDigest(String inStr) {
        if (TextUtils.isEmpty(inStr)) {
            return "";
        }
        try {
            MessageDigest mdInst = MessageDigest.getInstance("MD5");
            mdInst.update(inStr.getBytes());
            byte[] arrayOfByte = mdInst.digest();
            String str = "";
            int i = 0;
            for (int j = 0; j < arrayOfByte.length; j += 2) {
                i = arrayOfByte[j] & 0xFF;
                if (i < 16) {
                    str = str + "0" + Integer.toHexString(i);
                } else {
                    str = str + Integer.toHexString(i);
                }
                i = arrayOfByte[(j + 1)] & 0xFF;
                if (i < 16) {
                    str = str + "0" + Integer.toHexString(i);
                } else {
                    str = str + Integer.toHexString(i);
                }
            }

            return str.trim().toLowerCase();
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }
}

package com.tsy.base.tool;


import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Comparator;
import java.util.Map;
import java.util.TreeMap;


/**
 * Created by jay on 17/7/31.
 */

public class RequestSignTool {

    private RequestSignTool() {
    }

    /**
     * SHA1(MD5())加密
     *
     * @param str
     * @return
     */
    public static String getVerifyCode(String str) {
        try {
            return getSHA1(getMD5(str.replaceAll(" ", "")));
        } catch (NoSuchAlgorithmException e) {
            return "";
        }
    }

    private static String getSHA1(String value) throws NoSuchAlgorithmException {
        MessageDigest sha = MessageDigest.getInstance("SHA-1");
        sha.update(value.getBytes());
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

    public static String getMD5(String value) {
        try {
            MessageDigest messageDigest = MessageDigest.getInstance("MD5");
            byte[] paramArrayOfByte = value.getBytes();
            messageDigest.update(paramArrayOfByte);
            byte[] arrayOfByte = messageDigest.digest();
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

        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }

        return "";

    }

    /**
     * 对所有非公共参数按key字母排序以"key=value&key=value..."的形式拼接,并以SHA1(MD5(params))加密
     *
     * @param params
     * @return
     */
    public static String getSignature(Map params) {


        Map<String, String> resultMap = sortMapByKey(params);    //按Key进行排序

        StringBuffer buffer = new StringBuffer();

        for (Map.Entry<String, String> entry : resultMap.entrySet()) {
            buffer.append("&");
            buffer.append(entry.getKey());
            buffer.append("=");
            buffer.append(entry.getValue());
//            System.out.println(entry.getKey() + " " + entry.getValue());
        }

        resultMap.clear();
        resultMap = null;

        if (buffer.length() > 0) {
            buffer.deleteCharAt(0);
        }

        try {
            return ShaTool.getSHA1(new StrMd5(buffer.toString()).getResult());
        } catch (NoSuchAlgorithmException e) {
            return "";
        }

    }

    private static MapKeyComparator mapKeyComparator = new MapKeyComparator();

    /**
     * 使用 Map按key进行排序
     *
     * @param map
     * @return
     */
    public static Map<String, String> sortMapByKey(Map<String, String> map) {
        if (map == null || map.isEmpty()) {
            return null;
        }

        Map<String, String> sortMap = new TreeMap<>(mapKeyComparator);

        sortMap.putAll(map);

        return sortMap;
    }

    private static class MapKeyComparator implements Comparator<String> {
        @Override
        public int compare(String str1, String str2) {

            return str1.compareTo(str2);
        }
    }

}

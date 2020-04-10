package cn.tellsea.sunday.common.authorization;

import java.io.UnsupportedEncodingException;
import java.util.Base64;

/**
 * Base64工具类
 *
 * @author Tellsea
 * @date 2020/4/10
 */
public class Base64Utils {

    /**
     * 加密
     *
     * @param str
     * @return
     */
    public static String encode(String str) {
        try {
            byte[] encodeBytes = Base64.getEncoder().encode(str.getBytes("utf-8"));
            return new String(encodeBytes);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 解密
     *
     * @param str
     * @return
     */
    public static String decode(String str) {
        try {
            byte[] decodeBytes = Base64.getDecoder().decode(str.getBytes("utf-8"));
            return new String(decodeBytes);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return null;
    }
}


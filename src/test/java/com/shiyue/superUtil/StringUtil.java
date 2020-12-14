package com.shiyue.superUtil;

import org.junit.Test;

public class StringUtil {

    /**
     * @param str 判断字符串是否为空
     * @return 是否为空
     */
    public static boolean isEmpty(String str) {
        if (str == null || str.length() == 0) {
            return true;
        }
        return false;
    }

    @Test
    public void uuid() {
        String UUID = java.util.UUID.randomUUID().toString();
        System.out.println(UUID);
    }

    /**
     * 将字符串翻转三种方式
     * @param str
     * @return
     */
    // StringBuffer
    public static String reverse1(String str) {
        return new StringBuilder(str).reverse().toString();
    }

    // toCharArray
    public static String reverse2(String str) {
        char[] chars = str.toCharArray();
        String reverse = "";
        for (int i = chars.length - 1; i >= 0; i--) {
            reverse += chars[i];
        }
        return reverse;
    }

    // charAt
    public static String reverse3(String str) {
        String reverse = "";
        int length = str.length();
        for (int i = 0; i < length; i++) {
            reverse = str.charAt(i) + reverse;
        }
        return reverse;
    }
    //测试
    public static void main(String[] args) {
        String s = "abc123";

        System.out.println("----------------");
        for (int i = s.length() - 1; i >= 0; i--) {
            System.out.print(s.charAt(i));
        }
        System.out.println("----------------");

        System.out.println("变换前: " + s);
        System.out.println("变换后: " + reverse1(s));
        System.out.println("变换后: " + reverse2(s));
        System.out.println("变换后: " + reverse3(s));


    }
}
